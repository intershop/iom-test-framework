package com.intershop.oms.test.servicehandler.omsdb.v1;

import com.intershop.oms.test.businessobject.OMSArticle;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSupplier;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSProduct;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.order.OMSOrderPosition;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.util.OMSPlatformSchedules;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

class OMSDbHandlerV1 implements com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler
{
    public static final String CONFIG_KEY_DB_NAME = "dbName";
    public static final String CONFIG_KEY_DUMMY_MODE = "DUMMY_MODE";
    // use this with a write lock to be the only one test running
    private static final int LOCK_ID_THREAD_RUNNING = 1;
    private static final int LOCK_ID_LOAD_CONFIGURATION = 2;
    private static final int LOCK_ID_AGGREGATED_INVOICES = 3;
    private static final int LOCK_ID_TEST_SHOP_US = 4;
    private static final Logger log = LoggerFactory.getLogger(OMSDbHandlerV1.class);
    private static final HikariDataSource ds = new HikariDataSource();
    private static final Object lock = new Object();
    private static boolean dsInitialized = false;
    int maxRetry = 500;
    int retryDelay = 2000;
    Connection connection;

    OMSDbHandlerV1(String aHostList, String aDbName, String aDbUser, String aDbPass, boolean aForceSsl)
    {
        try
        {
            synchronized(lock)
            {
                if (!dsInitialized)
                {
                    String url = "jdbc:postgresql://" + aHostList + "/" + aDbName;
                    ds.setJdbcUrl(url);
                    ds.setUsername(aDbUser);
                    ds.setPassword(aDbPass);
                    ds.setMaximumPoolSize(20);

                    if (aForceSsl)
                    {
                        ds.addDataSourceProperty("ssl", "true");
                        ds.addDataSourceProperty("sslmode", "require");
                    }
                    dsInitialized = true;
                }
            }
            connection = ds.getConnection();
        }
        catch(SQLException e)
        {
            log.error(String.format("Connection failed for \"jdbc:postgresql://%s/%s - %s - %s - SSL: %s", aHostList,
                            aDbName, aDbUser, aDbPass, aForceSsl), e);
            throw new RuntimeException(e);
        }
    }

    public OMSDbHandlerV1(ServiceConfiguration configuration)
    {
        if(configuration.getParameterValue(CONFIG_KEY_DUMMY_MODE) != null) {
            return;
        }
//        this(configuration.serviceEndpoint().get().host(), configuration.getParameterValue(CONFIG_KEY_DB_NAME),
//                        configuration.username().get(), configuration.password().get(), false);

        String aHostList = configuration.serviceEndpoint().get().host();
        String aDbName = configuration.getParameterValue(CONFIG_KEY_DB_NAME);
        String aDbUser = configuration.username().get();
        String aDbPass =configuration.password().get();
        boolean aForceSsl = false;

        try
        {
            synchronized(lock)
            {
                if (!dsInitialized)
                {
                    String url = "jdbc:postgresql://" + aHostList + "/" + aDbName;
                    ds.setJdbcUrl(url);
                    ds.setUsername(aDbUser);
                    ds.setPassword(aDbPass);
                    ds.setMaximumPoolSize(20);

                    if (aForceSsl)
                    {
                        ds.addDataSourceProperty("ssl", "true");
                        ds.addDataSourceProperty("sslmode", "require");
                    }
                    dsInitialized = true;
                }
            }
            connection = ds.getConnection();
        }
        catch(SQLException e)
        {
            log.error(String.format("Connection failed for \"jdbc:postgresql://%s/%s - %s - %s - SSL: %s", aHostList,
                            aDbName, aDbUser, aDbPass, aForceSsl), e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setTimeOutParameters(int maxRetry, int retryDelay)
    {
        this.maxRetry = maxRetry;
        this.retryDelay = retryDelay;
    }

    @Override
    public int runDBStmtIntById(String query, Long id, String resultColumnName)
    {
        int result;
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            if (null != id)
            {
                sqlStatement.setLong(1, id);
            }
            log.info("Calling '" + sqlStatement.toString() + "'.");
            resultSet = sqlStatement.executeQuery();
            if (!resultSet.next())
            {
                throw new RuntimeException("Query '" + query + "' did not return a result!");
            }

            result = resultSet.getInt(resultColumnName);

            if (resultSet.next())
            {
                throw new RuntimeException("Query '" + query + "' returned more than one result!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting integer result '" + resultColumnName + "' from '" + query + "':"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got integer result for column '" + resultColumnName + "' from '" + query + "': " + result + ".");

        return result;
    }

    @Override
    public Long runDBStmtLongById(String query, Long id, String resultColumnName)
    {
        Long result = null;
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            if (null != id)
            {
                sqlStatement.setLong(1, id);
            }

            log.info("Calling '" + sqlStatement.toString() + "'.");
            resultSet = sqlStatement.executeQuery();

            if (resultSet.next())
            {
                result = resultSet.getLong(resultColumnName);
                if (resultSet.wasNull())
                {
                    result = null;
                }
            }

            if (resultSet.next())
            {
                throw new RuntimeException("Query '" + query + "' returned more than one result!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting integer result '" + resultColumnName + "' from '" + query + "':"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got Long result for column '" + resultColumnName + "' from '" + query + "' using id '" + id + "': "
                        + result + ".");

        return result;
    }

    @Override
    public ArrayList<String> runDBStmtString(String query, String resultColumnName)
    {
        ArrayList<String> result = new ArrayList<>();
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query);
                        ResultSet resultSet = sqlStatement.executeQuery())
        {
            while(resultSet.next())
            {
                result.add(resultSet.getString(resultColumnName));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting string result '" + resultColumnName + "' from '" + query + "':"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }

        return result;
    }

    @Override
    public Map<Integer, String> runDBStmtString(String query, String resultColumnNameKey, String resultColumnNameValue)
    {
        return runDBStmtStringById(query, null, resultColumnNameKey, resultColumnNameValue);
    }

    @Override
    public String runDBStmtStringById(String query, Long id, String resultColumnName)
    {
        String result = null;
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            if (null != id)
            {
                sqlStatement.setLong(1, id);
            }

            log.info("Calling '{}'.", sqlStatement);
            resultSet = sqlStatement.executeQuery();

            if (resultSet.next())
            {
                result = resultSet.getString(resultColumnName);
                if (resultSet.wasNull())
                {
                    result = null;
                }
            }

            if (resultSet.next())
            {
                throw new RuntimeException("Query '" + query + "' returned more than one result!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting string result '" + resultColumnName + "' from '" + query + "':"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got String result for column '" + resultColumnName + "' from '" + query + "' using id '" + id + "': "
                        + result + ".");

        return result;
    }

    @Override
    public Map<Integer, String> runDBStmtStringById(String query, Long id, String resultColumnNameKey,
                    String resultColumnNameValue)
    {
        Map<Integer, String> result = new LinkedHashMap<>();
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            if (null != id)
            {
                sqlStatement.setLong(1, id);
            }
            log.info("Calling '" + sqlStatement.toString() + "'.");
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                result.put(resultSet.getInt(resultColumnNameKey), resultSet.getString(resultColumnNameValue));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting string result '" + resultColumnNameKey + "/" + resultColumnNameValue
                            + "' from '" + query + "':" + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        return result;
    }

    @Override
    public Map<Integer, Integer> runDBStmtInt(String query, String resultColumnNameKey, String resultColumnNameValue)
    {
        return runDBStmtIntById(query, null, resultColumnNameKey, resultColumnNameValue);
    }

    @Override
    public Map<Integer, Integer> runDBStmtIntById(String query, Long id, String resultColumnNameKey,
                    String resultColumnNameValue)
    {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            if (null != id)
            {
                sqlStatement.setLong(1, id);
            }
            log.info("Calling '" + sqlStatement.toString() + "'.");
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                result.put(resultSet.getInt(resultColumnNameKey), resultSet.getInt(resultColumnNameValue));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting string result '" + resultColumnNameKey + "/" + resultColumnNameValue
                            + "' from '" + query + "':" + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        return result;
    }

    /**
     * runs the query and return true if
     * throws an exception if the query does not return any result or returns more than one row
     *
     * @return the query result
     */
    @Override
    public boolean runDBStmtBoolean(String query, String resultColumnName, boolean expectedResult)
    {
        boolean resultOK;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query);
                        ResultSet resultSet = sqlStatement.executeQuery())
        {

            if (!resultSet.next())
            {
                throw new RuntimeException(
                                "Found no result getting boolean result '" + resultColumnName + "' from '" + query);
            }

            resultOK = (expectedResult == resultSet.getBoolean(resultColumnName));

            // further rows, if any
            while(resultSet.next() && resultOK)
            {
                resultOK = (expectedResult == resultSet.getBoolean(resultColumnName));
            }

        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting boolean result '" + resultColumnName + "' from '" + query + "':"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }

        log.info("Got boolean result(s) '" + resultColumnName + "' from '" + query);

        return resultOK;
    }

    @Override
    public boolean runDBStmtBoolean(String query, int waitTimeSec, boolean expectedStatus, int sleepTimeSec)
    {
        boolean matched = false;
        ResultSet resultSet = null;
        int stillWaitFor = waitTimeSec;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {

            do
            {
                resultSet = sqlStatement.executeQuery();
                log.info("called " + query);

                if (!resultSet.next())
                {
                    throw new RuntimeException("Found no result getting boolean result from '" + query);
                }
                if (expectedStatus == resultSet.getBoolean(1))
                {
                    matched = true;
                }
                if (resultSet.next())
                {
                    throw new RuntimeException("The query did return more than one row: '" + query);
                }
                if (!matched && stillWaitFor > 0)
                {
                    Thread.sleep(1000L * sleepTimeSec);
                    stillWaitFor = stillWaitFor - sleepTimeSec;
                }
            }
            while(!matched && stillWaitFor > 0);

        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting boolean result ' from '" + query + "':" + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        catch(InterruptedException intEx)
        {
            log.error("InterruptedException (runDBStmtBoolean (String query, int waitTimeSec,  boolean expectedStatus, int sleepTimeSec) )': "
                            + intEx.getMessage());
            throw new RuntimeException(intEx);
        }

        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        return matched;
    }

    /**
     * runs a statement and checks if the result is success or failure as expected in 'assertTrue'
     *
     * @return check flag
     */
    @Override
    public boolean runDBStmt(String query, boolean assertTrue)
    {
        boolean check = true;
        StringBuilder out = new StringBuilder();

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.executeQuery();
            if (assertTrue)
            {
                out.append("+ ").append(query).append("\n");
            }
            else
            {
                check = false;
                out.append("- Failed (success on expected failure): ").append(query).append("\n");
            }
        }
        catch(Exception e)
        {
            if (assertTrue)
            {
                check = false;
                out.append("+ Failed: ").append(query).append("\n").append(e).append("\n");
            }
            else
            {
                out.append("- this is an EXPECTED EXCEPTION - ").append(query).append("\n");
            }
        }

        if (check)
        {
            log.info(out.toString());
        }
        else
        {
            log.error(out.toString());
        }
        return check;
    }

    /**
     * runs an update statement (no resultSet expected)
     *
     * @return true on success, false on exception
     */
    @Override
    public boolean runDBUpdate(String query, Optional<String> objectId1, Optional<Long> objectId2)
    {
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {

            if (objectId1.isPresent())
            {
                sqlStatement.setString(1, objectId1.get());
                if (objectId2.isPresent())
                {
                    sqlStatement.setLong(2, objectId2.get());
                }
            }
            else if (objectId2.isPresent())
            {
                sqlStatement.setLong(1, objectId2.get());
            }

            sqlStatement.executeUpdate();
            log.info("updated: " + query);
        }
        catch(Exception e)
        {
            log.error("+ Failed: " + query + "\n" + e + "\n");
            return false;
        }
        return true;
    }

    @Override
    public boolean runDBUpdate(String query)
    {
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.executeUpdate();
            log.info("updated: " + query);
        }
        catch(Exception e)
        {
            log.error("+ Failed: " + query + "\n" + e + "\n");
            return false;
        }
        return true;
    }

    @Override
    public String createUniqueFullOMTUser(Integer testCaseID)
    {
        String login = createUniqueOMTUser("FullOMTAdmin_tc", testCaseID);
        boolean result = runDBStmt(
                        "/* 1.0*/ select tc_omt_addusr('" + login + "', '" + login + "@company.com', 'OmsSystem')",
                        true);
        if (result)
        {
            result = runDBStmt("/* 1.1*/ select tc_omt_createrole('" + login + "', " + tcr_fullomtclient + ")", true);
        }
        if (result)
        {
            result = runDBStmt("/* 1.2*/ select tc_omt_add_usr2rol2org ('" + login + "', '" + login + "', 'OmsSystem')",
                            true);
        }
        if (!result)
        {
            throw new RuntimeException("Couldn't create unique full OMT user '" + login + "'");
        }
        return login;
    }

    @Override
    public void cleanupUniqueFullOMTUser(String login)
    {
        runDBStmt("select tc_omt_dropusr('" + login + "')", true);
        runDBStmt("select tc_omt_droprole('" + login + "')", true);
    }

    @Override
    public List<OMSSupplier> getSuppliersForShop(OMSShop shop)
    {
        List<Long> supplierRefs = new ArrayList<>();
        List<OMSSupplier> suppliers = new ArrayList<>();

        String query = "SELECT \"supplierRef\" FROM oms.\"Shop2SupplierDO\" WHERE \"shopRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, shop.getId());
            log.info("Calling '" + sqlStatement + "'.");
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                supplierRefs.add(resultSet.getLong("supplierRef"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting supplier refs for shop '" + shop.getName() + "' (" + shop.getId() + ")!"
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (supplierRefs.isEmpty())
        {
            log.error("No suppliers found for shop '" + shop.getName() + "' (" + shop.getId() + ")!");
            throw new RuntimeException("No suppliers found for shop '" + shop.getName() + "' (" + shop.getId() + ")!");

        }

        for (Long supplierRef : supplierRefs)
        {
            OMSSupplier supplier = getOMSSupplierById(supplierRef);
            log.info("Supplier '" + supplier.getName() + "' (" + supplier.getId() + ") found for shop '"
                            + shop.getName() + "' (" + shop.getId() + ")!");
            suppliers.add(supplier);
        }

        return suppliers;
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getSupplierShopName(com.intershop.oms.test.businessobject.OMSShop, com.intershop.oms.test.businessobject.OMSSupplier)
     */
    @Override
    public String getSupplierShopName(OMSShop shop, OMSSupplier supplier)
    {
        return getSupplierShopName(shop.getName(), supplier.getName());
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getSupplierShopName(java.lang.String, java.lang.String)
     */
    @Override
    public String getSupplierShopName(String shopName, String supplierName)
    {
        String supplierShopName = null;

        String query = "SELECT \"supplierShopName\" FROM oms.\"Shop2SupplierDO\" WHERE \"shopRef\" = (select id from \"ShopDO\" where name = ? ) AND \"supplierRef\" = (select id from \"SupplierDO\" where name = ? )";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, shopName);
            sqlStatement.setString(2, supplierName);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                supplierShopName = resultSet.getString(1);
            }
            if (resultSet.next())
            {
                log.error("More than one supplier shop name found for '" + shopName + "' and '" + supplierName + "'!");
                throw new RuntimeException(
                                "More than one supplier shop name found for '" + shopName + "' and '" + supplierName
                                                + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting supplierShopName: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == supplierShopName)
        {
            log.error("No supplier shop name found for '" + shopName + "' and '" + supplierName + "'!");
            throw new RuntimeException(
                            "No supplier shop name found for '" + shopName + "' and '" + supplierName + "'!");
        }

        log.info("Got shop supplier name: " + supplierShopName + " from '" + shopName + "' and '" + supplierName
                        + "'!");

        return supplierShopName;
    }

    //    /* (non-Javadoc)
    //     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getOrderPosIdsByOrderId(java.lang.Long)
    //     */
    //    @Override
    //    public Collection<Long> getOrderPosIdsByOrderId(Long orderId)
    //    {
    //        Collection<Long> orderPosIds = new ArrayList<>();
    //
    //        log.info("Getting orderPositions for order '"+orderId+"'...");
    //
    //        return orderPosIds;
    //    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getSupplierId(java.lang.String)
     */
    @Override
    public long getSupplierId(String supplierName)
    {
        Long supplierId = null;

        String query = "SELECT id FROM oms.\"SupplierDO\" WHERE name = ?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, supplierName);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                supplierId = resultSet.getLong(1);
            }
            if (resultSet.next())
            {
                log.error("More than one supplier found for '" + supplierName + "'!");
                throw new RuntimeException("More than one supplier found for '" + supplierName + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting supplierId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == supplierId)
        {
            log.error("No supplier found for '" + supplierName + "'!");
            throw new RuntimeException("No supplier found for '" + supplierName + "'!");
        }

        log.info("Got supplierId: " + supplierId + " from supplierName '" + supplierName + "'!");

        return supplierId;
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getShopId(java.lang.String)
     */
    @Override
    public Long getShopId(String shopName)
    {
        Long shopId = null;

        String query = "SELECT id FROM oms.\"ShopDO\" WHERE name = ?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, shopName);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                shopId = resultSet.getLong(1);
            }
            if (resultSet.next())
            {
                log.error("More than one shop found for '" + shopName + "'!");
                throw new RuntimeException("More than one shop found for '" + shopName + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == shopId)
        {
            log.error("No shop found for '" + shopName + "'!");
            throw new RuntimeException("No shop found for '" + shopName + "'!");
        }

        log.info("Got shopId: " + shopId + " from shopName '" + shopName + "'.");

        return shopId;
    }

    @Override
    public String getShopName(long shopId)
    {
        try (PreparedStatement stmt = connection.prepareStatement(
                        "SELECT \"shopName\" FROM oms.\"ShopDO\" WHERE id = ?"))
        {
            stmt.setLong(1, shopId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString(1);
        }
        catch(Exception e)
        {
            throw new RuntimeException("issue while retrieving shop name for id", e);
        }
    }

    public Long getShopSpecificID(String query, long shopId, String shopNumber)
    {
        Long shopSpecificId = null;
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, shopId);
            sqlStatement.setString(2, shopNumber);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                shopSpecificId = resultSet.getLong(1);
            }
            if (resultSet.next())
            {
                log.error("More than one id found for '{}' in shop '{}' in query '{}'!", shopNumber, shopId, query);
                throw new RuntimeException(
                                "More than one id found for '" + shopNumber + "' in shop '" + shopId + "' using query '"
                                                + query + "'! ");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: {}", sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return shopSpecificId;
    }

    @Override
    public long getOrderId(Long shopId, String shopOrderNumber)
    {
        Long orderId = getShopSpecificID("SELECT id FROM oms.\"OrderDO\" WHERE \"shopRef\" = ? AND \"shopOrderNo\" = ?",
                        shopId, shopOrderNumber);

        if (null == orderId)
        {
            log.error("No order id found for '{}' in shop '{}'!", shopOrderNumber, shopId);
            throw new RuntimeException("No order id found for '" + shopOrderNumber + "' in shop '" + shopId + "'!");
        }

        log.info("Got order id '{}' for '{}' in shop '{}'.", orderId, shopOrderNumber, shopId);

        return orderId;
    }

    @Override
    public long getOrderChangeRequestId(Long orderId, String changeRequestId)
    {
        Long orderChangeRequestId = getShopSpecificID(
                        "SELECT id FROM oms.\"OrderChangeRequestDO\" WHERE \"orderRef\" = ? AND \"changeRequestId\" = ?",
                        orderId, changeRequestId);

        if (null == orderChangeRequestId)
        {
            log.error("No order change request id found for '{}' and order '{}'!", changeRequestId, orderId);
            throw new RuntimeException(
                            "No order change request id found for '" + changeRequestId + "' and order '" + orderId
                                            + "'!");
        }

        log.info("Got order change request id '{}' for '{}' and order '{}'.", orderChangeRequestId, changeRequestId,
                        orderId);

        return orderChangeRequestId;
    }

    @Override
    public long getOrderChangeRequestId(Long shopRef, String shopOrderNo, String changeRequestId)
    {
        long orderId = getOrderId(shopRef, shopOrderNo);
        return getOrderChangeRequestId(orderId, changeRequestId);
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getArticleId(long, java.lang.String)
     */
    @Override
    public long getArticleId(long shopId, String shopArticleNo)
    {
        Long articleId = getShopSpecificID("select get_article_ref(?, ?)", shopId, shopArticleNo);

        if (null == articleId)
        {
            log.error("No articleRef found for '" + shopArticleNo + "' in shop '" + shopId + "'!");
            throw new RuntimeException("No articleRef found for '" + shopArticleNo + "' in shop '" + shopId + "'!");
        }

        if (0 == articleId)
        {
            log.warn("Probably no articleRef found for '" + shopArticleNo + "' in shop '" + shopId
                            + "'! (Got '0' as id!)");
        }
        else
        {
            log.info("Got article Id: " + articleId);
        }

        return articleId;
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#setStock(long, java.lang.String, java.lang.String, int, int)
     */
    @Override
    public void setStock(long shopId, String shopArticleNo, String supplierName, int stockLevel, int blocked)
    {
        log.info("Setting stock to " + stockLevel + " and blocked to " + blocked + " for " + shopArticleNo + "/"
                        + shopId + "/" + supplierName + ".");

        long articleId = getArticleId(shopId, shopArticleNo);

        if (!resetExistingStockInfo(articleId, shopId, supplierName, stockLevel, blocked))
        {
            createNewStockInfo(articleId, shopId, supplierName, stockLevel, blocked);
        }
    }

    @Override
    public void deleteReservations(long shopId, String shopArticleNoRegexp)
    {

        /*
         * WITH  itlist as (
SELECT i."stockReservationRef", i.id
FROM "StockReservationDO" r JOIN "StockReservationItemDO" i ON r.id =i."stockReservationRef"
WHERE i."shopArticleNo" ~ 'foo'
AND r."shopRef" >0),
DEL_0 as (DELETE FROM "OrderSupplierEvaluationStateHistoryDO" WHERE "orderSupplierEvaluationRef" IN (select id from  "OrderSupplierEvaluationDO" ose0  WHERE ose0."stockReservationItemRef" IN (select id FROM itlist))),
DEL_1 as (DELETE FROM "OrderSupplierEvaluationDO" ose WHERE ose."stockReservationItemRef" IN (select id FROM itlist)),
DEL_2 as (DELETE FROM "StockReservationItemDO" it2 WHERE it2.id IN (select id FROM itlist))
DELETE  FROM "StockReservationDO" r2
  WHERE r2.id IN (select distinct "stockReservationRef" FROM itlist)
  AND NOT EXISTS (SELECT FROM "StockReservationItemDO" it3 WHERE  it3."stockReservationRef"=r2.id)
         */

        StringBuilder queryString = new StringBuilder();
        queryString.append("WITH  itlist as ( ").append("SELECT i.\"stockReservationRef\", i.id ")
                        .append("FROM \"StockReservationDO\" r JOIN \"StockReservationItemDO\" i ON r.id =i.\"stockReservationRef\" ")
                        .append("WHERE i.\"shopArticleNo\" ~ ?  ").append("AND r.\"shopRef\" = ?), ")
                        .append("DEL_0 as (DELETE FROM \"OrderSupplierEvaluationStateHistoryDO\" WHERE \"orderSupplierEvaluationRef\" IN (select id from  \"OrderSupplierEvaluationDO\" ose0  WHERE ose0.\"stockReservationItemRef\" IN (select id FROM itlist))), ")
                        .append("DEL_1 as (DELETE FROM \"OrderSupplierEvaluationDO\" ose WHERE ose.\"stockReservationItemRef\" IN (select id FROM itlist)), ")
                        .append("DEL_2 as (DELETE FROM \"StockReservationItemDO\" it2 WHERE it2.id IN (select id FROM itlist)) ")
                        .append("SELECT string_agg ( DISTINCT \"stockReservationRef\"::text, ',') FROM itlist ");

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(queryString.toString());
                        Statement statement2 = connection.createStatement())
        {
            sqlStatement.setString(1, shopArticleNoRegexp);
            sqlStatement.setLong(2, shopId);
            ResultSet resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                String reservationList = resultSet.getString(1);
                if (null != reservationList && !reservationList.isEmpty())
                {
                    StringBuilder queryString2 = new StringBuilder();
                    queryString2.append("DELETE  FROM \"StockReservationDO\" r ").append("   WHERE r.id = ANY (ARRAY [")
                                    .append(reservationList).append("]) ")
                                    .append("   AND NOT EXISTS (SELECT FROM \"StockReservationItemDO\" it WHERE  it.\"stockReservationRef\"=r.id) ");

                    statement2.executeUpdate(queryString2.toString());

                }
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException in \"deleteReservations\": " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        catch(Exception ex2)
        {
            log.error("Exception in \"deleteReservations\": " + ex2.getMessage());
            throw new RuntimeException(ex2);
        }
    }

    /**
     * no stock defined yet. create one, but only if there is a shop - supplier mapping
     */
    private void createNewStockInfo(Long articleId, long shopId, String supplierName, int stockLevel, int blocked)
    {
        boolean result;

        String query = "SELECT 1 FROM \"Shop2SupplierDO\" WHERE \"shopRef\"= ? AND \"supplierRef\" = (select id from \"SupplierDO\" where name = ?)";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, shopId);
            sqlStatement.setString(2, supplierName);
            resultSet = sqlStatement.executeQuery();
            result = resultSet.next();
            if (resultSet.next())
            {
                log.error("Cannot create a stock info in ShopAtpAO: found more than 1 shop2Supplier relation for shopID '"
                                + shopId + "' and supplier '" + supplierName + "'!");
                throw new RuntimeException(
                                "Cannot create a stock info in ShopAtpAO: found more than 1 shop2Supplier relation for shopID '"
                                                + shopId + "' and supplier '" + supplierName + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting article ref: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (!result)
        {
            throw new RuntimeException(
                            "Cannot create a stock info in ShopAtpAO for article '" + articleId + "' as the shopID '"
                                            + shopId + "' does not map to the supplier '" + supplierName + "'");
        }

        query = "INSERT INTO product.\"ShopAtpAO\" (\"stockLevel\", \"blockedStock\", \"modificationDate\", \"articleRef\", \"shopRef\", \"shopSupplierName\", \"supplierRef\") "
                        + "SELECT ?, ?, now(), ?, ?, ?, (select id from \"SupplierDO\" where name = ?) RETURNING 1";

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setInt(1, stockLevel);
            sqlStatement.setInt(2, blocked);
            sqlStatement.setLong(3, articleId);
            sqlStatement.setLong(4, shopId);
            sqlStatement.setString(5, supplierName);
            sqlStatement.setString(6, supplierName);
            resultSet = sqlStatement.executeQuery();
            result = resultSet.next();
            if (resultSet.next())
            {
                log.error("Cannot create a new stock info in ShopAtpAO: Cannot insert a new stock info in ShopAtpAO for article '"
                                + articleId + "' for shopID '" + shopId + "' and supplier '" + supplierName + "'!");
                throw new RuntimeException(
                                "Cannot create a new stock info in ShopAtpAO: Cannot insert a new stock info in ShopAtpAO for article '"
                                                + articleId + "' for shopID '" + shopId + "' and supplier '"
                                                + supplierName + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting article ref: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (!result)
        {
            throw new RuntimeException("Cannot create a new stock info in ShopAtpAO for article '" + articleId
                            + "' as the shopID '" + shopId + "' does not map to the supplier '" + supplierName + "'");
        }
    }

    private boolean resetExistingStockInfo(long articleId, long shopId, String supplierName, int stockLevel,
                    int blocked)
    {
        boolean result;
        ResultSet resultSet = null;

        String query1 = "UPDATE product.\"ArticleSupplierShopDO\" "
                        + " SET \"stockLevel\"=? , \"modificationDate\"=now() WHERE \"shopRef\" = ? "
                        + " AND \"articleSupplierRef\"= (select id from  \"ArticleSupplierDO\" where \"supplierRef\"=(select id from \"SupplierDO\" where name = ?) AND  \"articleRef\"=?) RETURNING 1";

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query1))
        {
            sqlStatement.setInt(1, stockLevel);
            sqlStatement.setLong(2, shopId);
            sqlStatement.setString(3, supplierName);
            sqlStatement.setLong(4, articleId);
            resultSet = sqlStatement.executeQuery();
            result = resultSet.next();
            if (resultSet.next())
            {
                log.error("Cannot reset a stockLevel in ArticleSupplierShopDO: found more than 1 row for articleRef= '"
                                + articleId + "', shopID= '" + shopId + "' and supplier= '" + supplierName + "'!");
                throw new RuntimeException("Cannot reset a stock info in ShopAtpAO: found more than 1 row for article '"
                                + articleId + "', shopID '" + shopId + "' and supplier '" + supplierName + "'!");
            }
            if (!result)
            {
                throw new RuntimeException(
                                "No entry found in ArticleSupplierShopDO to set the stockLevel for articleRef="
                                                + articleId + " in shop '" + shopId + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException resetting stock info: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        String query2 = "UPDATE product.\"ShopAtpAO\" SET \"blockedStock\"=?, \"modificationDate\"=now() WHERE \"articleRef\"=? AND \"shopRef\" = ? AND \"supplierRef\"=(select id from \"SupplierDO\" where name = ?) RETURNING 1";

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query2))
        {
            sqlStatement.setInt(1, blocked);
            sqlStatement.setLong(2, articleId);
            sqlStatement.setLong(3, shopId);
            sqlStatement.setString(4, supplierName);
            resultSet = sqlStatement.executeQuery();
            result = resultSet.next();
            if (resultSet.next())
            {
                log.error("Cannot reset a stock info in ShopAtpAO: found more than 1 row for articleRef= '" + articleId
                                + "', shopID= '" + shopId + "' and supplier= '" + supplierName + "'!");
                throw new RuntimeException("Cannot reset a stock info in ShopAtpAO: found more than 1 row for article '"
                                + articleId + "', shopID '" + shopId + "' and supplier '" + supplierName + "'!");
            }
            if (!result)
            {
                throw new RuntimeException("No entry found in ShopAtpAO to reset the stock for articleRef=" + articleId
                                + " in shop '" + shopId + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException resetting stock info: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#getAvailableStock(long, java.lang.String, java.lang.String)
     */
    @Override
    public long getAvailableStock(long shopId, String shopArticleNo, String supplierName)
    {
        // ensure that we do not use this function for products offered from a parent shop (the recursive logic is not covered in the used private methods)

        String query = "select offering_shop from product.get_article_ref_and_offering_shop(?,?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, shopId);
            sqlStatement.setString(2, shopArticleNo);

            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                if (resultSet.getLong(1) != shopId)
                {

                    //see IOM-8247
                    log.error("(getAvailableStock): get_article_ref_and_offering_shop(" + shopId + ", '" + shopArticleNo
                                    + "') returned an ancestor shop (" + resultSet.getLong(1) + ") as offering shop.");
                    throw new RuntimeException(
                                    "getAvailableStock can't cope with products offered from an ancestor shop.");
                }
            }
            else
            {
                log.error("(getAvailableStock): no articleRef found for shop '" + shopId + "' and article '"
                                + shopArticleNo + "'!");
                throw new RuntimeException(
                                "no articleRef found for shop '" + shopId + "' and article '" + shopArticleNo + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting available stock found for '\"+shopId+\"' and '\"+shopArticleNo+\"'!\": "
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        AtpStock atpStock = getAtpStock(shopId, shopArticleNo, supplierName);
        long reservedStock = getReservedStock(shopId, shopArticleNo);

        long available = atpStock.stockLevel - atpStock.blockedStock - reservedStock;
        log.info("available in getAvailableStock:" + available);

        return available;
    }

    private long getReservedStock(long shopId, String shopArticleNo)
    {
        Long reservedStock = null;

        String query = "SELECT true as is_valid, sum(item.\"reservedStock\") AS reserved, cast(min(res.\"validUntil\") as text) as min_expires, cast(max(res.\"validUntil\") as text) as max_expires, cast(now() as text) as ctime "
                        + "FROM oms.\"StockReservationDO\" AS res JOIN oms.\"StockReservationItemDO\" AS item ON res.id = item.\"stockReservationRef\" "
                        + "WHERE item.\"shopArticleNo\" = ? AND res.\"validUntil\" >= now() AND res.\"shopRef\" = ? "
                        + "UNION ALL "
                        + "SELECT false as is_valid,  sum(item.\"reservedStock\") AS reserved, cast(min(res.\"validUntil\") as text) as min_expires, cast(max(res.\"validUntil\") as text) as max_expires ,cast (now() as text) as ctime "
                        + "FROM oms.\"StockReservationDO\" AS res JOIN oms.\"StockReservationItemDO\" AS item ON res.id = item.\"stockReservationRef\" "
                        + "WHERE item.\"shopArticleNo\" = ? AND res.\"validUntil\" < now() AND res.\"shopRef\" = ? ";

        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, shopArticleNo);
            sqlStatement.setLong(2, shopId);
            sqlStatement.setString(3, shopArticleNo);
            sqlStatement.setLong(4, shopId);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next() && resultSet.getLong(2) > 0)
            {
                if (resultSet.getBoolean(1))
                {
                    reservedStock = resultSet.getLong(2);
                    log.info("Got reserved stock for '" + shopId + "' and '" + shopArticleNo + "': " + reservedStock
                                    + "; min/max expirations:" + resultSet.getString(3) + "/" + resultSet.getString(4)
                                    + "; current time:" + resultSet.getString(5));
                }
                else
                {
                    reservedStock = 0L;
                    log.info("only found expired reserved stock for '" + shopId + "' and '" + shopArticleNo + "': "
                                    + resultSet.getLong(2) + " min/max expirations:" + resultSet.getString(3) + "/"
                                    + resultSet.getString(4) + "; current time:" + resultSet.getString(5));
                }
            }
            if (resultSet.next())
            {
                if (!resultSet.getBoolean(1) && resultSet.getLong(2) > 0)
                {
                    log.info("also found expired reserved stock for '" + shopId + "' and '" + shopArticleNo + "': "
                                    + resultSet.getLong(2) + "min/max expirations:" + resultSet.getString(3) + "/"
                                    + resultSet.getString(4) + "; current time:" + resultSet.getString(5));
                }
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting reserved stock found for '\"+shopId+\"' and '\"+shopArticleNo+\"'!\": "
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == reservedStock)
        {
            reservedStock = 0L;
        }

        log.info("Got reserved stock for '" + shopId + "' and '" + shopArticleNo + "': " + reservedStock);

        return reservedStock;
    }

    /**
     * beware: use this methold only to check the content of ShopAtpAO.
     * Complex cases like a product avaibility from a parent shop is not verified!
     */
    private AtpStock getAtpStock(long shopId, String shopArticleNo, String supplierName)
    {
        AtpStock atpStock = null;

        if (null == supplierName || 0 == supplierName.trim().length())
        {
            throw new RuntimeException("getAtpStock called without a supplier");
        }

        String query = "SELECT \"stockLevel\", \"blockedStock\" FROM product.\"ShopAtpAO\" WHERE \"articleRef\" = get_article_ref(?, ?) AND \"supplierRef\" = (select id from \"SupplierDO\" where name = ?)";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, shopId);
            sqlStatement.setString(2, shopArticleNo);
            sqlStatement.setString(3, supplierName);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                atpStock = new AtpStock();
                atpStock.stockLevel = resultSet.getLong("stockLevel");
                atpStock.blockedStock = resultSet.getLong("blockedStock");
            }
            if (resultSet.next())
            {
                log.error("More than one atp stock found for '" + shopId + "', '" + shopArticleNo + "', '"
                                + supplierName + "'!");
                throw new RuntimeException(
                                "More than one atp stock found for '" + shopId + "', '" + shopArticleNo + "', '"
                                                + supplierName + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting atp stock for '" + shopId + "', '" + shopArticleNo + "', '" + supplierName
                            + "': " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == atpStock)
        {
            log.error("No atp stock found for '" + shopId + "', '" + shopArticleNo + "', '" + supplierName + "'!");
            throw new RuntimeException(
                            "No atp stock found for '" + shopId + "', '" + shopArticleNo + "', '" + supplierName
                                            + "'!");
        }

        log.info("Got atpStock: for '" + shopId + "', '" + shopArticleNo + "', '" + supplierName + "': atp.stockLevel:"
                        + atpStock.stockLevel + " atp.blockedStock:" + atpStock.blockedStock);

        return atpStock;
    }

    @Override
    public Map<OMSSupplier, Collection<OMSDispatchPosition>> getDispatchPositionsForOrder(OMSOrder order,
                    boolean useSupplierData)
    {
        Map<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatches = new LinkedHashMap<>();

        log.info("getDispatchPositionsForOrder: calling getOrderPositionsBySupplier for order: " + order.toString());
        Map<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPositions = getOrderPositionsBySupplier(order);

        // fill article names from the given Ids
        for (Map.Entry<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPos : supplierOrderPositions.entrySet())
        {
            Collection<OMSDispatchPosition> supplierDisp = supplierDispatches.computeIfAbsent(supplierOrderPos.getKey(),
                            k -> new ArrayList<>());

            for (OMSOrderPosition orderPos : supplierOrderPos.getValue())
            {
                // a dispatch should use the article information used by the supplier? But then it fails "Not found"
                log.info("getDispatchPositionsForOrder: preparing orderPos '" + orderPos.toString() + "' for supplier: "
                                + supplierOrderPos.getKey().toString() + ":");

                OMSProduct product;
                if (useSupplierData)
                {
                    product = getSupplierProductData(supplierOrderPos.getKey(), orderPos);
                }
                else
                {
                    product = new OMSProduct();
                    product.setName(orderPos.getProduct().getName());
                    product.setNumber(orderPos.getProduct().getNumber());
                }

                OMSDispatchPosition disp = new OMSDispatchPosition();
                disp.setProduct(product);
                disp.setDispatchedQuantity(orderPos.getQuantity());
                disp.setOrderPositionNumber(orderPos.getNumber());

                log.info("getDispatchPositionsForOrder: prepared dispatchPos '" + disp + "'.");

                supplierDisp.add(disp);
            }
        }

        return supplierDispatches;
    }

    @Override
    public Map<OMSSupplier, Collection<OMSOrderResponsePosition>> getOrderResponsePositionsForOrder(OMSOrder order,
                    boolean useSupplierData)
    {
        Map<OMSSupplier, Collection<OMSOrderResponsePosition>> supplierOrderResponses = new LinkedHashMap<>();
        log.info("getOrderResponsePositionsForOrder: calling getOrderPositionsBySupplier for order: "
                        + order.toString());
        Map<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPositions = getOrderPositionsBySupplier(order);

        // fill article names from the given Ids
        for (Map.Entry<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPos : supplierOrderPositions.entrySet())
        {
            Collection<OMSOrderResponsePosition> supplierOrderResp = supplierOrderResponses.computeIfAbsent(
                            supplierOrderPos.getKey(), k -> new ArrayList<>());

            for (OMSOrderPosition orderPos : supplierOrderPos.getValue())
            {
                // an order response should use the article information used by the supplier? But then it fails "Not found"
                log.info("getOrderResponsePositionsForOrder: preparing orderPos '" + orderPos.toString()
                                + "' for supplier: " + supplierOrderPos.getKey().toString());

                OMSProduct product;
                if (useSupplierData)
                {
                    product = getSupplierProductData(supplierOrderPos.getKey(), orderPos);
                }
                else
                {
                    product = new OMSProduct();
                    product.setName(orderPos.getProduct().getName());
                    product.setNumber(orderPos.getProduct().getNumber());
                }

                OMSOrderResponsePosition orderResp = new OMSOrderResponsePosition();
                orderResp.setProduct(product);
                orderResp.setOrderPositionNumber(orderPos.getNumber());
                // FIXME this doesn't make any sense, purchase price can't be mapped from the sales price!
                if (orderPos.getSum() != null && orderPos.getSum().getGross() != null)
                {
                    orderResp.getNetPurchasePrice().setAmount(orderPos.getSum().getGross().getAmount().doubleValue());
                }
                else if (orderPos.getSum() != null && orderPos.getSum().getNet() != null)
                {
                    orderResp.getNetPurchasePrice().setAmount(orderPos.getSum().getNet().getAmount().doubleValue());
                }
                else
                {
                    log.error("Neither Gross nor Net price is set at order position!");
                }
                orderResp.getNetPurchasePrice().setCurrency("EUR");
                orderResp.getConfirmedDelivery().setQuantity(orderPos.getQuantity());
                orderResp.setQuantityCanceled(0);
                orderResp.getConfirmedDelivery().setPlannedDeliveryDate(LocalDate.now());
                log.info("Adding order response position to order " + order.getId() + ":\n" + orderResp);
                supplierOrderResp.add(orderResp);
            }
        }

        return supplierOrderResponses;
    }

    @Override
    public Collection<OMSWriteReturnRequestPosition> getReturnRequestPositionsForOrder(OMSOrder order)
    {
        Collection<OMSWriteReturnRequestPosition> returnRequestPositions = new ArrayList<>();

        log.info("getDispatchPositionsForReturnRequest: calling getDispatchPositionsForReturnRequest for order: "
                        + order.toString());
        Collection<OMSDispatchPosition> dispatchedOrderPositions = getDispatchPositionsForReturnRequest(order);

        // fill article number, position number from the given Ids
        for (OMSDispatchPosition dispatchPos : dispatchedOrderPositions)
        {
            returnRequestPositions.add(new OMSWriteReturnRequestPosition(dispatchPos));
        }

        return returnRequestPositions;
    }

    @Override
    public List<String> getReturnPositionsErrors(Long returnRef)
    {
        String sqlStatement = "SELECT \"errorText\" FROM \"ReturnPosDO\" WHERE \"id\" = (?)";

        List<Long> positions = getAllReturnsPositionIds(returnRef);
        List<String> positionsErrors = new ArrayList<>(positions.size());

        for (Long positionId : positions)
        {
            positionsErrors.add(runDBStmtStringById(sqlStatement, positionId, "errorText"));
        }

        StringBuilder output = new StringBuilder(
                        "getReturnPositionsErrors: got error messages for return positions:\n");
        int i = 0;
        for (String err : positionsErrors)
        {
            output.append("Return position ").append(positions.get(i++)).append(": ").append(err);
        }
        log.info(output.toString());
        return positionsErrors;
    }

    @Override
    public String getReturnError(Long returnRef)
    {
        String error = runDBStmtStringById("SELECT \"errorText\" FROM \"ReturnDO\" WHERE \"id\" = (?)", returnRef,
                        "errorText");
        log.info("getDispatchError: got error messages for return: " + returnRef + ": " + error);
        return error;
    }

    @Override
    public Map<OMSSupplier, Collection<OMSOrderPosition>> getOrderPositionsBySupplier(OMSOrder order)
    {
        Map<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPositions = new LinkedHashMap<>();
        log.info("getOrderPositionsBySupplier: calling getSupplierOrderPositionsForOrder for order: "
                        + order.toString());
        Map<Long, Collection<OMSOrderPosition>> supplierIdOrderPositions = getSupplierOrderPositionsForOrder(order);

        log.info("getOrderPositionsBySupplier: remapping suppliers...");

        // remap suppliers from their IDs
        for (Map.Entry<Long, Collection<OMSOrderPosition>> supplierDisp : supplierIdOrderPositions.entrySet())
        {
            log.info("getOrderPositionsBySupplier: remapping supplier: " + supplierDisp.getKey());
            OMSSupplier omsSupplier = getOMSSupplierById(supplierDisp.getKey());
            supplierOrderPositions.put(omsSupplier, supplierDisp.getValue());
            log.info("getOrderPositionsBySupplier: remapping supplier: " + supplierDisp.getKey() + " to "
                            + omsSupplier);
        }

        return supplierOrderPositions;
    }

    @Override
    public Long getReservedStockForArticle(long reservationId, OMSArticle article)
    {
        long reservedStock;

        String query = "SELECT \"reservedStock\" FROM oms.\"StockReservationItemDO\" WHERE \"shopArticleNo\"=? AND \"stockReservationRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, article.getShopArticleNo());
            sqlStatement.setLong(2, reservationId);
            log.info("Calling '" + sqlStatement + "'.");
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                reservedStock = resultSet.getLong("reservedStock");
            }
            else
            {
                reservedStock = 0L;
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting reserved stock for reservationId '" + reservationId + "' and article '"
                            + article.getShopArticleNo() + "'!" + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return reservedStock;
    }

    @Override
    public boolean checkMinimumReservationLifeTime(long reservationId, long lifeTime)
    {
        boolean ret = false;
        String query = "SELECT \"validUntil\" FROM oms.\"StockReservationDO\" WHERE \"id\" = ? AND \"validUntil\" >= current_timestamp + interval '"
                        + lifeTime + " milliseconds'";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, reservationId);
            log.info("Calling '" + sqlStatement + "'.");
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                ret = true;
            }
        }
        catch(SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return ret;
    }

    @Override
    public OMSSupplier getReservationAssignedSupplier(long reservationId, OMSArticle article)
    {
        long supplierRef;

        String query = "SELECT \"supplierRef\" FROM oms.\"StockReservationItemDO\" WHERE \"shopArticleNo\"=? AND \"stockReservationRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, article.getShopArticleNo());
            sqlStatement.setLong(2, reservationId);
            log.info("Calling '" + sqlStatement + "'.");
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                supplierRef = resultSet.getLong("supplierRef");
            }
            else
            {
                log.error("No supplier found for reservationId '" + reservationId + "' and article '"
                                + article.getShopArticleNo() + "'!");
                throw new RuntimeException("No supplier found for reservationId '" + reservationId + "' and article '"
                                + article.getShopArticleNo() + "'!");
            }
            if (resultSet.next())
            {
                log.error("More than one supplier found for reservationId '" + reservationId + "' and article '"
                                + article.getShopArticleNo() + "'!");
                throw new RuntimeException(
                                "More than one supplier found for reservationId '" + reservationId + "' and article '"
                                                + article.getShopArticleNo() + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting supplier for reservationId '" + reservationId + "' and article '"
                            + article.getShopArticleNo() + "'!" + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return getOMSSupplierById(supplierRef);
    }

    // gets the product by ID as used by the supplier
    private OMSProduct getSupplierProductData(OMSSupplier supplier, OMSOrderPosition orderPos)
    {
        OMSProduct product;

        String query = "SELECT \"supplierArticleNo\", \"supplierShortDescription\" FROM product.\"ArticleSupplierDO\" WHERE \"articleRef\"=? AND \"supplierRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, orderPos.getProduct().getProductId());
            sqlStatement.setLong(2, supplier.getId());
            log.info("Calling '" + sqlStatement + "'.");
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                String articleNumber = resultSet.getString("supplierArticleNo");
                String articleName = resultSet.getString("supplierShortDescription");
                if (null == articleName || null == articleNumber)
                {
                    log.error("Incomplete supplier article entry found for supplier '" + supplier + "' and article '"
                                    + orderPos.getProduct().getProductId() + "'! (number: '" + articleNumber
                                    + "', name:'" + articleName + "')");
                    throw new RuntimeException("More than one supplier article entry found for supplier '" + supplier
                                    + "' and article '" + orderPos.getProduct().getProductId() + "'!");
                }
                else
                {
                    product = new OMSProduct();
                    product.setName(articleName);
                    product.setNumber(articleNumber);
                    log.info("Found supplier article entry found for supplier '" + supplier + "' and article '"
                                    + orderPos.getProduct().getProductId() + "' - number: '" + articleNumber
                                    + "', name:'" + articleName + "'.");
                }
            }
            else
            {
                log.error("No supplier article entry found for supplier '" + supplier + "' and article '"
                                + orderPos.getProduct().getProductId() + "'!");
                throw new RuntimeException(
                                "No supplier article entry found for supplier '" + supplier + "' and article '"
                                                + orderPos.getProduct().getProductId() + "'!");
            }
            if (resultSet.next())
            {
                log.error("More than one supplier article entry found for supplier '" + supplier + "' and article '"
                                + orderPos.getProduct().getProductId() + "'!");
                throw new RuntimeException("More than one supplier article entry found for supplier '" + supplier
                                + "' and article '" + orderPos.getProduct().getProductId() + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting supplier article for supplier '" + supplier.toString() + "' and article '"
                            + orderPos.getProduct().getProductId() + "': " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }
        return product;
    }

    // get the Supplier by ID
    private OMSSupplier getOMSSupplierById(Long supplierId)
    {
        OMSSupplier supplier;
        String supplierName = null;

        String query = "SELECT name FROM oms.\"SupplierDO\" WHERE id = ?";
        ResultSet resultSet = null;

        try (PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, supplierId);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                supplierName = resultSet.getString("name");
            }
            if (resultSet.next())
            {
                log.error("More than one supplier found for '" + supplierId + "'!");
                throw new RuntimeException("More than one supplier found for '" + supplierId + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (null == supplierName)
        {
            log.error("No supplier name found for id '" + supplierId + "'!");
            throw new RuntimeException("No supplier name found for id '" + supplierId + "'!");
        }

        supplier = new OMSSupplier(supplierId, supplierName);

        log.info("Got supplier name: '" + supplierName + "' from id '" + supplierId + "'!");

        return supplier;
    }

    private Map<Long, Collection<OMSOrderPosition>> getSupplierOrderPositionsForOrder(OMSOrder order)
    {
        Map<Long, Collection<OMSOrderPosition>> supplierIdOrderPositions = new LinkedHashMap<>();

        String query = "SELECT id, \"supplierRef\", \"orderPosNo\", \"articleRef\", \"quantityOrdered\", \"shopArticleName\", \"shopArticleNo\"  FROM \"OrderPosDO\" where \"orderRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, order.getId());
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                Long orderPosID = resultSet.getLong("id");
                Long supplierId = resultSet.getLong("supplierRef");
                Integer orderPosNo = resultSet.getInt("orderPosNo");
                Long articleId = resultSet.getLong("articleRef");
                int quantity = resultSet.getInt("quantityOrdered");
                String shopArticleName = resultSet.getString("shopArticleName");
                String shopArticleNo = resultSet.getString("shopArticleNo");

                Collection<OMSOrderPosition> supplierPos = supplierIdOrderPositions.computeIfAbsent(supplierId,
                                k -> new ArrayList<>());
                OMSOrderPosition orderPosition = new OMSOrderPosition();
                com.intershop.oms.test.businessobject.order.OMSProduct product = new com.intershop.oms.test.businessobject.order.OMSProduct();
                product.productId(articleId).name(shopArticleName).number(shopArticleNo);
                orderPosition.quantity(quantity).number(orderPosNo).product(product).id(orderPosID);
                log.info("Adding orderPos '" + orderPosition + "' for supplier id '" + supplierId + "'");
                supplierPos.add(orderPosition);
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting order positions for '" + order.getId() + "': " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (supplierIdOrderPositions.isEmpty())
        {
            log.error("No order positions for '" + order.getId() + "'!");
            throw new RuntimeException("No order positions for '" + order.getId() + "'!");
        }

        return supplierIdOrderPositions;
    }

    private Collection<OMSDispatchPosition> getDispatchPositionsForReturnRequest(OMSOrder order)
    {
        Collection<OMSDispatchPosition> dispatchedPositions = new ArrayList<>();

        String query = "SELECT id, \"orderPosNo\", \"quantityOrdered\", \"shopArticleNo\" FROM \"OrderPosDO\" where \"orderRef\"=?";
        ResultSet resultSet = null;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, order.getId());
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                int quantity = resultSet.getInt("quantityOrdered");
                Integer orderPosNo = resultSet.getInt("orderPosNo");
                String shopArticleNo = resultSet.getString("shopArticleNo");

                OMSDispatchPosition dispatchedPosition = new OMSDispatchPosition();
                dispatchedPosition.setArticleNo(shopArticleNo);
                dispatchedPosition.setDispatchedQuantity(quantity);
                dispatchedPosition.setOrderPositionNumber(orderPosNo);
                log.info("Adding dispatchPos '" + dispatchedPositions + "' for order '" + order + "'");
                dispatchedPositions.add(dispatchedPosition);
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting order positions for '" + order.getId() + "': " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        return dispatchedPositions;
    }

    @Override
    public int getNumberOfOrderSupplierEvaluationsForReservation(long reservationId)
    {
        String query = "SELECT count(*) FROM oms.\"OrderSupplierEvaluationDO\" WHERE \"stockReservationItemRef\" IN (select id from \"StockReservationItemDO\" where \"stockReservationRef\" = ?)";
        ResultSet resultSet = null;
        int numberOfReservations = -1;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, reservationId);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                numberOfReservations = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got " + numberOfReservations + " supplier evaluations for reservation id: '" + reservationId + "'.");

        return numberOfReservations;
    }

    @Override
    public int getNumberOfOrderSupplierEvaluationsForReservationAndOrder(long reservationId, long orderId)
    {
        String query = "SELECT count(*) FROM oms.\"OrderSupplierEvaluationDO\" WHERE \"stockReservationItemRef\" IN (select id from \"StockReservationItemDO\" where \"stockReservationRef\" = ?) AND \"orderPosRef\" IN (select id from \"OrderPosDO\" where \"orderRef\" = ?)";
        ResultSet resultSet = null;
        int numberOfReservations = -1;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, reservationId);
            sqlStatement.setLong(2, orderId);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                numberOfReservations = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got " + numberOfReservations + " supplier evaluations for reservation id: '" + reservationId
                        + "' and order id: '" + orderId + "'.");

        return numberOfReservations;
    }

    @Override
    public Map<OMSSupplier, Collection<OMSReturnPosition>> getReturnPositionsForOrder(OMSOrder order,
                    boolean useSupplierData)
    {
        Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturns = new LinkedHashMap<>();

        log.info("getReturnPositionsForOrder: calling getOrderPositionsBySupplier for order: " + order.toString());
        Map<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPositions = getOrderPositionsBySupplier(order);

        // fill article names from the given Ids
        for (Map.Entry<OMSSupplier, Collection<OMSOrderPosition>> supplierOrderPos : supplierOrderPositions.entrySet())
        {
            Collection<OMSReturnPosition> supplierRet = supplierReturns.computeIfAbsent(supplierOrderPos.getKey(),
                            k -> new ArrayList<>());

            for (OMSOrderPosition orderPos : supplierOrderPos.getValue())
            {
                // a dispatch should use the article information used by the supplier? But then it fails "Not found"
                log.info("getReturnPositionsForOrder: preparing orderPos '" + orderPos.toString() + "' for supplier: "
                                + supplierOrderPos.getKey().toString());

                OMSProduct product;
                if (useSupplierData)
                {
                    product = getSupplierProductData(supplierOrderPos.getKey(), orderPos);
                }
                else
                {
                    product = new OMSProduct();
                    product.setName(orderPos.getProduct().getName());
                    product.setNumber(orderPos.getProduct().getNumber());
                }

                OMSReturnPosition Retp = new OMSReturnPosition();
                Retp.setProduct(product);
                Retp.setReturnedQuantity(orderPos.getQuantity());
                Retp.setOrderPositionNumber(orderPos.getNumber());

                supplierRet.add(Retp);
            }
        }

        return supplierReturns;
    }

    @Override
    public int getNumberOfOrderSupplierEvaluationsForOrder(long orderId)
    {
        String query = "SELECT count(*) FROM oms.\"OrderSupplierEvaluationDO\" WHERE \"orderPosRef\" IN (select id from \"OrderPosDO\" where \"orderRef\" = ?)";
        //PreparedStatement sqlStatement = null;
        ResultSet resultSet = null;
        int numberOfReservations = -1;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, orderId);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                numberOfReservations = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting shopId: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got " + numberOfReservations + " supplier evaluations for order id: '" + orderId + "'.");

        return numberOfReservations;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllDispatchesPositionIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllDispatchesPositionIds(Long dipatchRef)
    {
        List<Long> allDispatchPositionIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"DispatchPosDO\" WHERE \"dispatchRef\" in (?) order by id";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, dipatchRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allDispatchPositionIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allDispatchPositionIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (allDispatchPositionIds.isEmpty())
        {
            log.error("No dispatch position found for '" + dipatchRef + "'!");
            throw new RuntimeException("No dispatch position found for '" + dipatchRef + "'!");
        }

        log.info("Got allDispatchPositionIds: " + allDispatchPositionIds + " from dispatchId '" + dipatchRef + "'!");

        return allDispatchPositionIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllDispatchPositionPropertyIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllDispatchPositionPropertyIds(Long dispatchPosRef)
    {
        List<Long> allDispatchPositionPropertyIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"DispatchPosPropertyDO\" WHERE \"dispatchPosRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, dispatchPosRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allDispatchPositionPropertyIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allDispatchPositionPropertyIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got allDispatchPositionPropertyIds: " + allDispatchPositionPropertyIds + " from dispatchPosId '"
                        + dispatchPosRef + "'!");

        return allDispatchPositionPropertyIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllDispatchPositionItemIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllDispatchPositionItemIds(Long dispatchPosRef)
    {
        List<Long> allDispatchPositionItemIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"DispatchItemDO\" WHERE \"dispatchPosRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, dispatchPosRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allDispatchPositionItemIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allDispatchPositionItemIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got allDispatchPositionItemIds: " + allDispatchPositionItemIds + " from dispatchPosId '"
                        + dispatchPosRef + "'!");

        return allDispatchPositionItemIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getDispatchPositionQuantityDispatched(java.lang.Long)
     */
    @Override
    public Long getDispatchPositionQuantityDispatched(Long dispatchPosRef)
    {
        long quantityDispatched = 0L;

        String query = "SELECT \"quantityDispatched\" FROM oms.\"DispatchPosDO\" WHERE \"id\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, dispatchPosRef);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                quantityDispatched = resultSet.getLong(1);
            }
            if (resultSet.next())
            {
                log.error("More than one quantityDispatched found for the dispatch position with the id '"
                                + dispatchPosRef + "'!");
                throw new RuntimeException(
                                "More than one quantityDispatched found for the dispatch position with the id '"
                                                + dispatchPosRef + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting quantityDispatched: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got quantityDispatched: " + quantityDispatched + " from dispatchPosId '" + dispatchPosRef + "'!");

        return quantityDispatched;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllOrderResponsesPositionIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllOrderResponsesPositionIds(Long responseRef)
    {
        List<Long> allOrderResponsePositionIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"ResponsePosDO\" WHERE \"responseRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, responseRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allOrderResponsePositionIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allOrderResponsePositionIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (allOrderResponsePositionIds.isEmpty())
        {
            log.error("No order response position found for '" + responseRef + "'!");
            throw new RuntimeException("No order response position found for '" + responseRef + "'!");
        }

        log.info("Got allOrderResponsePositionIds: " + allOrderResponsePositionIds + " from responseId '" + responseRef
                        + "'!");

        allOrderResponsePositionIds.sort(Comparator.naturalOrder());
        return allOrderResponsePositionIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllOrderResponsePositionPropertyIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllOrderResponsePositionPropertyIds(Long responsePosRef)
    {
        List<Long> allOrderResponsePositionPropertyIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"ResponsePosPropertyDO\" WHERE \"responsePosRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, responsePosRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allOrderResponsePositionPropertyIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allOrderResponsePositionPropertyIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got allOrderResponsePositionPropertyIds: " + allOrderResponsePositionPropertyIds
                        + " from responsePosRef '" + responsePosRef + "'!");

        return allOrderResponsePositionPropertyIds;
    }

    @Override
    public List<String> getOrderResponsePositionsErrors(Long responseRef)
    {
        String sqlStatement = "SELECT \"errorText\" FROM \"ResponsePosDO\" WHERE \"id\" = (?)";

        List<Long> positions = getAllOrderResponsesPositionIds(responseRef);
        List<String> positionsErrors = new ArrayList<>(positions.size());

        for (Long positionId : positions)
        {
            positionsErrors.add(runDBStmtStringById(sqlStatement, positionId, "errorText"));
        }

        StringBuilder output = new StringBuilder(
                        "getOrderResponsePositionsErrors: got error messages for order response positions:\n");
        int i = 0;
        for (String err : positionsErrors)
        {
            output.append("Order repsonse position ").append(positions.get(i)).append(": ").append(err);
        }
        log.info(output.toString());
        return positionsErrors;
    }

    @Override
    public String getOrderResponseError(Long responseRef)
    {
        String error = runDBStmtStringById("SELECT \"errorText\" FROM \"ResponseDO\" WHERE \"id\" = (?)", responseRef,
                        "errorText");
        log.info("getOrderResponseError: got error messages for order response: " + responseRef + ": " + error);
        return error;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllReturnsPositionIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllReturnsPositionIds(Long returnRef)
    {
        List<Long> allReturnPositionIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"ReturnPosDO\" WHERE \"returnRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, returnRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allReturnPositionIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allReturnPositionIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        if (allReturnPositionIds.isEmpty())
        {
            log.error("No return position found for '" + returnRef + "'!");
            throw new RuntimeException("No return position found for '" + returnRef + "'!");
        }

        log.info("Got allReturnPositionIds: " + allReturnPositionIds + " from returnId '" + returnRef + "'!");

        allReturnPositionIds.sort(Comparator.naturalOrder());
        return allReturnPositionIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllReturnPositionPropertyIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllReturnPositionPropertyIds(Long returnPosRef)
    {
        List<Long> allReturnPositionPropertyIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"ReturnPosPropertyDO\" WHERE \"returnPosRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, returnPosRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allReturnPositionPropertyIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allReturnPositionPropertyIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got allReturnPositionPropertyIds: " + allReturnPositionPropertyIds + " from returnPosId '"
                        + returnPosRef + "'!");

        return allReturnPositionPropertyIds;
    }

    @Override
    public List<String> getDispatchPositionsErrors(Long dispatchRef)
    {
        String sqlStatement = "SELECT \"errorText\" FROM \"DispatchPosDO\" WHERE \"id\" = (?)";

        List<Long> positions = getAllDispatchesPositionIds(dispatchRef);
        List<String> positionsErrors = new ArrayList<>(positions.size());

        for (Long positionId : positions)
        {
            positionsErrors.add(runDBStmtStringById(sqlStatement, positionId, "errorText"));
        }

        StringBuilder output = new StringBuilder(
                        "getDispatchPositionsErrors: got error messages for dispatch positions:\n");
        int i = 0;
        for (String err : positionsErrors)
        {
            output.append("Dispatch position ").append(positions.get(i)).append(": ").append(err);
        }
        log.info(output.toString());
        return positionsErrors;
    }

    @Override
    public String getDispatchError(Long dispatchRef)
    {
        String error = runDBStmtStringById("SELECT \"errorText\" FROM \"DispatchDO\" WHERE \"id\" = (?)", dispatchRef,
                        "errorText");
        log.info("getDispatchError: got error messages for dispatch: " + dispatchRef + ": " + error);
        return error;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getPlatformConfigShowgMaxReturnQty(java.lang.Long)
     */
    @Override
    public boolean getPlatformConfigShowgMaxReturnQty()
    {
        return runDBStmtBoolean("SELECT \"showMaxReturnQuantity\" FROM \"PlatformConfigDO\"", "showMaxReturnQuantity",
                        true);
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllReturnPositionItemIds(java.lang.Long)
     */
    @Override
    public List<Long> getAllReturnPositionItemIds(Long returnPosRef)
    {
        List<Long> allReturnPositionItemIds = new ArrayList<>();

        String query = "SELECT id FROM oms.\"ReturnItemDO\" WHERE \"returnPosRef\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, returnPosRef);
            resultSet = sqlStatement.executeQuery();
            while(resultSet.next())
            {
                allReturnPositionItemIds.add(resultSet.getLong("id"));
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting allReturnPositionItemIds: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got allReturnPositionItemIds: " + allReturnPositionItemIds + " from returnPosId '" + returnPosRef
                        + "'!");

        return allReturnPositionItemIds;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getReturnPositionQuantityReturned(java.lang.Long)
     */
    @Override
    public Long getReturnPositionQuantityReturned(Long returnPosRef)
    {
        long quantityReturned = 0L;

        String query = "SELECT \"quantityReturned\" FROM oms.\"ReturnPosDO\" WHERE \"id\" in (?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, returnPosRef);
            resultSet = sqlStatement.executeQuery();
            if (resultSet.next())
            {
                quantityReturned = resultSet.getLong(1);
            }
            if (resultSet.next())
            {
                log.error("More than one quantityReturned found for the return position with the id '" + returnPosRef
                                + "'!");
                throw new RuntimeException("More than one quantityReturned found for the return position with the id '"
                                + returnPosRef + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting quantityReturned: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got quantityReturned: " + quantityReturned + " from returnPosId '" + returnPosRef + "'!");

        return quantityReturned;
    }

    @Override
    public int getProductImportFileCount(String fileType)
    {
        String query = "select count(*) from product.\"ImportDatapackDO\" where \"fileName\" ~ ? and \"importDatapackStateDefRef\" = 100";
        int imported;

        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, fileType);
            ResultSet resultSet = sqlStatement.executeQuery();
            resultSet.next();
            imported = resultSet.getInt(1);
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException during the article import check: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }

        return imported;
    }

    /*
     * (non-Javadoc)
     * @see com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler#
     * getAllReturnsPositionIds(java.lang.Long)
     */
    @Override
    public List<Boolean> hasSupplierSinglePositionArticle(
                    Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions)
    {
        List<Boolean> singlePositionArticles = new ArrayList<>();
        String query = "SELECT \"singlePositionArticle\" FROM oms.\"SupplierDO\" WHERE \"id\" in (?)";
        ResultSet resultSet = null;
        boolean singlePositionArticle = false;
        long supplierId;
        for (Map.Entry<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPosition : supplierReturnPositions.entrySet())
        {
            supplierId = supplierReturnPosition.getKey().getId();
            try (Connection connection = ds.getConnection();
                            PreparedStatement sqlStatement = connection.prepareStatement(query))
            {
                sqlStatement.setLong(1, supplierId);
                resultSet = sqlStatement.executeQuery();
                while(resultSet.next())
                {
                    singlePositionArticles.add(resultSet.getBoolean("singlePositionArticle"));
                }
            }
            catch(SQLException sqlEx)
            {
                log.error("SQLException getting singlePositionArticle: " + sqlEx.getMessage());
                throw new RuntimeException(sqlEx);
            }
            finally
            {
                if (null != resultSet)
                {
                    try
                    {
                        resultSet.close();
                    }
                    catch(SQLException e)
                    {
                    }
                }
            }

            log.info("Got singlePositionArticle: " + singlePositionArticle + " from supplier: '" + supplierId + "'!");
        }

        return singlePositionArticles;
    }

    @Override
    public long checkAggregatedInvoice(long orderId1, long orderId2)
    {
        long invoiceId;

        String query = "SELECT distinct \"invoicingRef\" FROM oms.\"Invoicing2OrderDO\" WHERE \"orderRef\" in (?, ?)";
        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setLong(1, orderId1);
            sqlStatement.setLong(2, orderId2);
            resultSet = sqlStatement.executeQuery();

            if (resultSet.next())
            {
                invoiceId = resultSet.getLong(1);
            }
            else
            {
                log.error("No aggregated invoice found for orders '" + orderId1 + "' and '" + orderId2 + "'!");
                throw new RuntimeException(
                                "No aggregated invoice found for orders '" + orderId1 + "' and '" + orderId2 + "'!");
            }
            if (resultSet.next())
            {
                log.error("More than one aggregated invoice found for orders '" + orderId1 + "' and '" + orderId2
                                + "'!");
                throw new RuntimeException(
                                "More than one aggregated invoice found for orders '" + orderId1 + "' and '" + orderId2
                                                + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting aggregated invoice: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Got aggregated invoice '" + invoiceId + "' for orders '" + orderId1 + "' and '" + orderId2 + "'.");

        return invoiceId;
    }

    @Override
    public boolean waitForDispatchStateOfOrder(long orderId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"DispatchDO\" WHERE \"orderRef\" = (?)";
        return doDBWaitForStateCheck("order dispatch", sqlStatement, orderId, expectedState);
    }

    @Override
    public boolean waitForDispatchState(long dispatchId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"DispatchDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("dispatch", sqlStatement, dispatchId, expectedState);
    }

    @Override
    public boolean waitForReturnState(long returnId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"ReturnDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("return", sqlStatement, returnId, expectedState);
    }

    @Override
    public boolean waitForReturnStateOfOrder(long orderId, int expectedState, int returnCount)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"ReturnDO\" WHERE \"orderRef\" = (?)";
        return doDBWaitForStatesCheck("order return", sqlStatement, orderId, expectedState, returnCount);
    }

    @Override
    public boolean waitForOrderState(long orderId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"OrderDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("order", sqlStatement, orderId, expectedState);
    }

    @Override
    public boolean waitForOrderChangeRequestState(long orderChangeRequestId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"OrderChangeRequestDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("order", sqlStatement, orderChangeRequestId, expectedState);
    }

    @Override
    public int getOrderResponseState(Long responseId)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"ResponseDO\" WHERE \"id\" = (?)";
        return runDBStmtIntById(sqlStatement, responseId, "stateRef");
    }

    @Override
    public boolean waitForOrderResponseState(long responseId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"ResponseDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("order response", sqlStatement, responseId, expectedState);
    }

    @Override
    public boolean waitForOrderConfirmationMailState(long orderId, int expectedState)
    {
        //String sqlStatement = "SELECT \"stateRef\" FROM \"ShopCustomerMailTransmissionDO\" WHERE \"orderRef\" = (?) AND \"responseRef\" is null AND \"dispatchRef\" is null AND \"returnRef\" is null";
        String sqlStatement = "SELECT \"stateRef\" FROM \"ShopCustomerMailTransmissionDO\" WHERE \"orderRef\" = (?) AND \"transmissionTypeDefRef\" BETWEEN 500 AND 799"; //500 = SEND_CUSTOMER_MAIL_ORDER
        return doDBWaitForStateCheck("order confirmation mail", sqlStatement, orderId, expectedState, true);
    }

    @Override
    public boolean waitForReturnAnnouncementState(long rmaId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"ReturnAnnouncementDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("return announcement", sqlStatement, rmaId, expectedState);
    }

    @Override
    public boolean waitForInvoiceState(long invoiceId, int expectedState)
    {
        String sqlStatement = "SELECT \"stateRef\" FROM \"InvoicingDO\" WHERE \"id\" = (?)";
        return doDBWaitForStateCheck("invoice", sqlStatement, invoiceId, expectedState);
    }

    @Override
    public boolean waitForInvoiceDocumentState(long orderId, int expectedState)
    {
        //        String sqlStatement = "SELECT \"stateRef\" FROM \"DocumentDO\" WHERE \"id\" = ( SELECT \"documentRef\" FROM \"Document2OrderDO\" WHERE \"orderRef\" = (?) ) AND \"documentTypeDefRef\" = 18";
        long invoiceId = getFirstInvoiceIDForOrder(orderId);
        log.info("Got invoicing id " + invoiceId + " for order " + orderId + ".");
        long documentId = getFirstDocumentIDForInvoice(invoiceId);
        log.info("Got document id " + invoiceId + " for invoice " + invoiceId + ".");
        String sqlStatement = "SELECT \"stateRef\" FROM \"DocumentDO\" WHERE \"id\" = (?) AND \"documentTypeDefRef\" = 18";
        return doDBWaitForStateCheck("invoice document", sqlStatement, documentId, expectedState);
    }

    private long getFirstDocumentIDForInvoice(long invoiceId)
    {
        String query = "SELECT \"documentRef\" from \"Document2InvoicingDO\" where \"invoicingRef\" = ?";
        int countRetry = 0;
        Long result;
        do
        {
            if (countRetry > 0)
            {
                try
                {
                    Thread.sleep(retryDelay);
                }
                catch(InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            result = runDBStmtLongById(query, invoiceId, "documentRef");
        }
        while(result == null && countRetry++ < maxRetry);
        if (result == null)
        {
            throw new RuntimeException("no result found for " + query + " with id '" + invoiceId + "'!");
        }
        return result;
    }

    private long getFirstInvoiceIDForOrder(long orderId)
    {
        String query = "SELECT \"invoicingRef\" from \"Invoicing2OrderDO\" where \"orderRef\" = ?";
        int countRetry = 0;
        Long result;
        do
        {
            if (countRetry > 0)
            {
                try
                {
                    Thread.sleep(retryDelay);
                }
                catch(InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            result = runDBStmtLongById(query, orderId, "invoicingRef");
        }
        while(result == null && countRetry++ < maxRetry);
        if (result == null)
        {
            throw new RuntimeException("no result found for " + query + " with id '" + orderId + "'!");
        }
        return result;
    }

    private boolean doDBWaitForStateCheck(String debugType, String sqlStatementQuery, long objectId, int expectedState)
    {
        return doDBWaitForStateCheck(debugType, sqlStatementQuery, objectId, expectedState, false);
    }

    private boolean doDBWaitForStateCheck(String debugType, String sqlStatementQuery, long objectId, int expectedState,
                    boolean acceptMultipleResults)
    {
        // expected worst case: delay of 1.5s to wait for cleanup job should be covered by maxRetry*retryDelay = 60s is not enough :-(
        // the last job cleanup should be done after app. >30min --- so after 31min everything must be ok! (But for the case where two waiting objects collided, again. We won't wait for these to resolve, though.)
        int countRetry = 0;
        Integer currentStatus = null;

        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(sqlStatementQuery))
        {
            sqlStatement.setLong(1, objectId);

            do
            {
                if (countRetry > 0)
                {
                    Thread.sleep(retryDelay);
                }
                resultSet = sqlStatement.executeQuery();
                if (resultSet.next())
                {
                    currentStatus = resultSet.getInt(1);
                }
                if (!acceptMultipleResults && resultSet.next())
                {
                    log.error("More than one status found for " + debugType + " with id '" + objectId + "'!");
                    throw new RuntimeException(
                                    "More than one status found for " + debugType + " with id '" + objectId + "'!");
                }
            }
            while((currentStatus == null || currentStatus < expectedState) && countRetry++ < maxRetry);
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting currentStatus of " + debugType + " '" + objectId + "': "
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        catch(InterruptedException intEx)
        {
            log.error("InterruptedException (retry " + countRetry + " of " + maxRetry
                            + ") getting currentStatus of \"+debugType+\" '\"+objectId+\"': " + intEx.getMessage());
            throw new RuntimeException(intEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        debugWaitingMsg(debugType, countRetry, maxRetry, retryDelay, Optional.of(objectId), expectedState,
                        currentStatus);

        return currentStatus != null && currentStatus >= expectedState;
    }

    private boolean doDBWaitForStatesCheck(String debugType, String sqlStatementQuery, long objectId, int expectedState,
                    int expectedObjectCount)
    {
        // expected worst case: delay of 1.5s to wait for cleanup job should be covered by maxRetry*retryDelay = 60s is not enough :-(
        // the last job cleanup should be done after app. 15min --- so after 16min everything must be ok!
        int countRetry = 0;
        Integer currentStatus = null;

        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(sqlStatementQuery))
        {
            sqlStatement.setLong(1, objectId);

            do
            {
                int objectCount = 0;
                if (countRetry > 0)
                {
                    Thread.sleep(retryDelay);
                }
                resultSet = sqlStatement.executeQuery();
                while(resultSet.next())
                {
                    objectCount++;
                    currentStatus = resultSet.getInt(1);
                    if (currentStatus < expectedState)
                    {
                        break;
                    }
                    if (objectCount < expectedObjectCount)
                    {
                        currentStatus = null;
                    }
                }
            }
            while((currentStatus == null || currentStatus < expectedState) && countRetry++ < maxRetry);
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting currentStatus of " + debugType + " '" + objectId + "': "
                            + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        catch(InterruptedException intEx)
        {
            log.error("InterruptedException (retry " + countRetry + " of " + maxRetry
                            + ") getting currentStatus of \"+debugType+\" '\"+objectId+\"': " + intEx.getMessage());
            throw new RuntimeException(intEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        debugWaitingMsg(debugType, countRetry, maxRetry, retryDelay, Optional.of(objectId), expectedState,
                        currentStatus);

        return currentStatus != null && currentStatus >= expectedState;
    }

    private void debugWaitingMsg(String topic, int countRetry, int maxRetry, int retryDelay, Optional<Long> objectId,
                    int expectedState, Integer currentState)
    {
        if (objectId.isPresent())
        {
            if (countRetry >= maxRetry)
            {
                log.error("\n\n\nGAVE UP!\nWaited " + countRetry + " x " + retryDelay / 1000 + "s for " + topic + " '"
                                + objectId.get() + "' to be processed to state '" + expectedState
                                + "'. Current state is '" + (currentState != null ? currentState.intValue() : "null")
                                + "'.\n\n\n");
            }
            else
            {
                log.info("Waited " + countRetry + " x " + retryDelay / 1000 + "s for " + topic + " '" + objectId.get()
                                + "' to be processed to state '" + expectedState + "'.");
            }
        }
        else
        {
            if (countRetry >= maxRetry)
            {
                log.error("\n\n\nGAVE UP!\nWaited " + countRetry + " x " + retryDelay / 1000 + "s for " + topic
                                + " to be processed to state '" + expectedState + "'. Current state is '" + (
                                currentState != null ?
                                                currentState.intValue() :
                                                "null") + "'.\n\n\n");
            }
            else
            {
                log.info("Waited " + countRetry + " x " + retryDelay / 1000 + "s for " + topic
                                + " to be processed to state '" + expectedState + "'.");
            }
        }
    }

    @Override
    public void setAggregateInvoicesFlagForCustomer(String shopCustomerNo, String shopName, boolean aggregateInvoices)
    {
        String sqlStatementUpdateCustomer = "UPDATE \"CustomerDO\" SET \"aggregateInvoices\" = " + aggregateInvoices
                        + ", \"invoiceAggregationIntervalDefRef\" = 1 " + "WHERE \"shopCustomerNo\" = '"
                        + shopCustomerNo + "' " + "AND \"shopRef\" IN (SELECT id FROM \"ShopDO\" WHERE \"shopName\" = '"
                        + shopName + "') RETURNING true";
        runDBStmt(sqlStatementUpdateCustomer, true);
    }

    @Override
    public void setInvoiceProcessesActive(boolean dailyJobEnabled, boolean weeklyJobEnabled, boolean monthlyJobEnabled,
                    boolean cleanupJobEnabled)
    {
        setJobActive(OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_DAILY, dailyJobEnabled);
        setJobActive(OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_WEEKLY, weeklyJobEnabled);
        setJobActive(OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_MONTHLY, monthlyJobEnabled);

        setJobActive(OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_CLEANUP_WEEKLY, dailyJobEnabled);
        setJobActive(OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_CLEANUP_DISABLED, cleanupJobEnabled);
    }

    public void setJobActive(String jobKey, boolean active)
    {
        String statementEnableJob = "UPDATE \"ScheduleDO\" SET \"active\" = " + active + " WHERE \"key\" = '" + jobKey
                        + "' RETURNING true";
        runDBStmt(statementEnableJob, true);
    }

    @Override
    public boolean triggerAggregation(String aggregationInterval)
    {
        String jobKey;

        Calendar cal = Calendar.getInstance();

        switch(aggregationInterval)
        {
            case "daily":
                cal.add(Calendar.DAY_OF_MONTH, -1);
                cal.add(Calendar.MINUTE, -1);
                jobKey = OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_DAILY;
                break;
            case "weekly":
                int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int lastDayOfCurrentMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
                if (dayOfMonth == lastDayOfCurrentMonth)
                {
                    cal.add(Calendar.MONTH, -1);
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                    cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    cal.set(Calendar.HOUR_OF_DAY, 22);
                    jobKey = OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_CLEANUP_WEEKLY;
                }
                else
                {
                    cal.add(Calendar.DAY_OF_MONTH, -7);
                    cal.add(Calendar.MINUTE, -1);
                    jobKey = OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_WEEKLY;
                }
                break;
            case "monthly":
                cal.add(Calendar.MONTH, -1);
                cal.add(Calendar.MINUTE, -1);
                jobKey = OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_MONTHLY;
                break;
            case "endOfMonth":
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.HOUR_OF_DAY, 22);
                jobKey = OMSPlatformSchedules.Invoicing.AGGREGATE_DISPATCH_INVOICES_CLEANUP_WEEKLY;
                break;
            default:
                throw new RuntimeException("Unsupported aggregation interval '" + aggregationInterval + "'!");
        }
        Date newLastRunDate = cal.getTime();

        return aggregateInvoices(Optional.of(newLastRunDate), jobKey);
    }

    @Override
    public boolean aggregateInvoices(Optional<Date> lastRun, String aggregationKey)
    {
        log.info("DBHandler.aggregateInvoices: lastRun " + lastRun + " aggregationKey " + aggregationKey);

        // Update table "ScheduleDO" to reset the field "lastRun" and start the aggregated invoices job
        // Set also the registerDate as it is used to determine the next run time if lastRun is not set
        String sqlStatement1;
        if (lastRun.isPresent())
        {
            sqlStatement1 = "UPDATE \"ScheduleDO\" SET active = true," + " \"lastRun\" = '" + lastRun.get()
                            + "', \"registerDate\" = now() - interval '2 days' " + ", \"retryDelay\" ='30s'"
                            + ", \"countRetry\" =0 " + " WHERE \"key\" = '" + aggregationKey + "' RETURNING true";
        }
        else
        {
            sqlStatement1 = "UPDATE \"ScheduleDO\" SET active = true, " + "\"lastRun\" = NULL "
                            + ", \"registerDate\" = now() - interval '2 days' " + ", \"retryDelay\" ='30s'"
                            + ", \"countRetry\" =0 " + " WHERE \"key\" = '" + aggregationKey + "' RETURNING true";
        }

        boolean result = runDBStmt(sqlStatement1, true);

        if (result)
        {
            // Wait for job to finish and document to be created
            // TO DO: add a check on nextRetryDateCurrent and retryCount to allow more than one attempt when the schedule failed ?
            String lastRunStr = lastRun.isPresent() ? " AND \"lastRun\" != '" + lastRun.get() + "' " : "";
            String sqlStatementJobRun = "SELECT * FROM \"ScheduleDO\" " + "WHERE \"lockedSince\" IS NULL "
                            + "AND \"lastRun\" IS NOT NULL " + lastRunStr + " AND \"key\" = '" + aggregationKey + "'";
            result = doDBWaitForResult("invoice aggregation", sqlStatementJobRun, Optional.empty(), Optional.empty(),
                            1);
            if (!result)
            {
                log.error("DBHandler.aggregateInvoices: lastRun " + lastRun + " aggregationKey " + aggregationKey
                                + " failed to wait for result from: " + sqlStatementJobRun);
            }
        }
        else
        {
            log.error("DBHandler.aggregateInvoices: lastRun " + lastRun + " aggregationKey " + aggregationKey
                            + " failed to call: " + sqlStatement1);
        }

        if (result)
        {
            // let the schedules that show an error as active (\"nextRetryDate\" IS NOT NULL)
            // return false in such cases (will cause a test error)
            String sqlStatementActiveFalse = "UPDATE \"ScheduleDO\" SET \"active\" = \"nextRetryDate\" IS NOT NULL"
                            + " WHERE \"key\" = '" + aggregationKey + "'" + " RETURNING \"nextRetryDate\" IS NULL";
            result = runDBStmt(sqlStatementActiveFalse, true);
            if (!result)
            {
                log.error("DBHandler.aggregateInvoices: lastRun " + lastRun + " aggregationKey " + aggregationKey
                                + " returned false: " + sqlStatementActiveFalse);
            }
        }
        return result;
    }

    @Override
    public boolean predateDispatchForFasterProcessing(Long dispatchId)
    {
        String sqlStatement = "UPDATE oms.\"DispatchDO\"\n"
                        + "SET \"modificationDate\" = \"modificationDate\" - interval '10 minutes'\n" + "WHERE id = ?"
                        + "AND now() at time zone 'UTC' - \"modificationDate\" < interval '10 minutes';";

        return runDBUpdate(sqlStatement, Optional.empty(), Optional.of(dispatchId));
    }

    @Override
    public boolean predateReturnForFasterProcessing(Long returnId)
    {
        String sqlStatement = "UPDATE oms.\"ReturnDO\"\n"
                        + "SET \"modificationDate\" = \"modificationDate\" - interval '10 minutes'\n" + "WHERE id = ?"
                        + "AND now() at time zone 'UTC' - \"modificationDate\" < interval '10 minutes';";

        return runDBUpdate(sqlStatement, Optional.empty(), Optional.of(returnId));
    }

    /**
     * waits until all invoices of the given type for the given customerShopNo are there and processed to 8000
     * <p>
     * invoiceTypes: 1...invoice 2...credit
     */
    @Override
    public boolean waitForInvoicesProcessed(String customerShopNo, int invoiceType, int minimumNumberOfThoseInvoices)
    {
        //CheckInvoicingControllerBean  does not process invoices younger than 5 minutes
        // to prevent this waiting time, we fake the InvoiceDO time when required.
        String sqlStatement = "with ids as (select \"invoicingRef\" as i_id FROM oms.\"Invoicing2OrderDO\" \n"
                        + "              where \"orderRef\" = (select id from \"OrderDO\" where \"shopCustomerNo\"=? limit 1))\n"
                        + "update \"InvoicingDO\"\n"
                        + "set \"creationDate\" = \"creationDate\" - interval '270 seconds',\n"
                        + "\"modificationDate\" = \"modificationDate\" - interval '270 seconds'\n"
                        + "where id in (select id  FROM \"InvoicingDO\"\n"
                        + "             where id in  (select i_id from ids)\n"
                        + "             and  now() at time zone 'UTC' - \"modificationDate\" < interval '270 seconds')";

        runDBUpdate(sqlStatement, Optional.of(customerShopNo), Optional.empty());

        sqlStatement = "SELECT count(*) FROM \"InvoicingDO\" WHERE \"debitorNo\" = ? AND \"invoicingTypeDefRef\" = ? AND \"stateRef\"=8000";
        return doDBWaitForResult(
                        "invoice processing for customer '" + customerShopNo + "' and invoices of type " + invoiceType,
                        sqlStatement, Optional.of(customerShopNo), Optional.of((long)invoiceType),
                        minimumNumberOfThoseInvoices);
    }

    /**
     * @param objectId1 a string parameter for the first parameter
     * @param objectId2 a long parameter for the first parameter
     */
    private boolean doDBWaitForResult(String debugType, String sqlStatementQuery, Optional<String> objectId1,
                    Optional<Long> objectId2, int expectedResultSize)
    {
        // expected worst case: delay of 1.5s to wait for cleanup job should be covered by maxRetry*retryDelay = 60s is not enough :-(
        // the last job cleanup should be done after app. 15min --- so after 16min everything must be ok!
        int countRetry = 0;
        int currentResultSize = 0;

        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(sqlStatementQuery))
        {
            if (objectId1.isPresent())
            {
                sqlStatement.setString(1, objectId1.get());
                if (objectId2.isPresent())
                {
                    sqlStatement.setLong(2, objectId2.get());
                }
            }
            else if (objectId2.isPresent())
            {
                sqlStatement.setLong(1, objectId2.get());
            }

            do
            {
                if (countRetry > 0)
                {
                    Thread.sleep(retryDelay);
                }
                resultSet = sqlStatement.executeQuery();
                if (resultSet.next())
                {
                    currentResultSize = resultSet.getInt(1);
                }
            }
            while(currentResultSize < expectedResultSize && countRetry++ < maxRetry);
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting currentStatus of " + debugType + ": " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        catch(InterruptedException intEx)
        {
            log.error("InterruptedException (retry " + countRetry + " of " + maxRetry + ") getting currentStatus of "
                            + debugType + ": " + intEx.getMessage());
            throw new RuntimeException(intEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        debugWaitingMsg(debugType, countRetry, maxRetry, retryDelay, Optional.empty(), expectedResultSize,
                        currentResultSize);

        return currentResultSize >= expectedResultSize;
    }

    @Override
    public boolean createPaymentNotificationDO(String shopOrderId, String paymentState, String paymentAction,
                    String errorMsg, boolean imported, String invoiceNo, float amount)
    {
        long paymentNotificationId;
        String invoiceNoSql = (null == invoiceNo) ? "null" : "'" + invoiceNo + "'";

        String query = "INSERT INTO oms.\"PaymentNotificationDO\" (" + "    \"id\",\n" + "    \"version\",\n"
                        + "    \"creationDate\",\n" + "    \"modificationDate\",\n" + "    \"actionDate\",\n"
                        + "    \"orderRef\",\n" + "    \"paymentDefRef\",\n" + "    \"stateRef\",\n"
                        + "    \"paymentNotificationTypeDefRef\",\n" + "    \"paymentNotificationActionDefRef\",\n"
                        + "    \"amount\",\n" + "    \"currency\",\n" + "    \"notificationNo\",\n"
                        + "    \"manuallyApproved\",\n" + "    \"errorMessage\",\n" + "    \"imported\",\n"
                        + "    \"doProcess\",\n" + "    \"paymentProviderOrderNo\",\n" + "    \"waitStateRef\",\n"
                        + "    \"invoiceNo\",\n" + "    \"comment\",\n" + "    \"referenceId\",\n"
                        + "    \"correctionReferenceId\",\n" + "    \"correctionDate\")\n" + "    SELECT\n"
                        + "        nextval('oms.\"PaymentNotificationDO_id_seq\"'),\n" + "        1,\n"
                        + "        now(),\n" + "        now(),\n" + "        null,\n"
                        + "        (select \"id\" from \"OrderDO\" where \"shopOrderNo\" = ?),\n"// 1
                        + "        (select \"paymentDefRef\" from \"OrderDO\" where \"shopOrderNo\" = ?),\n"// 2
                        + "        (select id from \"PaymentNotificationStatesDefDO\" where name = ?), --stateRef\n"// 3
                        + "        1, --paymentNotificationTypeDefRef = inbound\n"
                        + "        (select id from \"PaymentNotificationActionDefDO\" where name = ?),\n"// 4
                        + "        ?,\n"// 5
                        + "        (select \"currency\" from \"CountryDefDO\" where \"id\" =\n"
                        + "            (select \"countryDefRef\" from \"ShopDO\" where \"id\" =\n"
                        + "                (select \"shopRef\" from \"OrderDO\" where \"shopOrderNo\" = ?))),\n"  // 6
                        + "        null,\n" + "        null,\n" + "        ?, --errorMsg\n"      // 7
                        + "        ?, --imported\n"      // 8
                        + "        false,\n" + "        null,\n" + "        null,\n" + "        ?, --invoiceNo\n"// 9
                        + "        'testing',\n" + "        null,\n" + "        null,\n "
                        + "        null -- allows more than one\n" + "        WHERE NOT EXISTS\n"
                        + "            (SELECT FROM oms.\"PaymentNotificationDO\" WHERE \"orderRef\" = \n"
                        + "                (select \"id\" from \"OrderDO\" where \"shopOrderNo\" = ?))\n"       // 10
                        + "    RETURNING id";

        log.info("Creating paymentNotification for order '" + shopOrderId + "' by calling:\n" + query);

        ResultSet resultSet = null;
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(query))
        {
            sqlStatement.setString(1, shopOrderId);
            sqlStatement.setString(2, shopOrderId);
            sqlStatement.setString(3, paymentState);
            sqlStatement.setString(4, paymentAction);
            sqlStatement.setFloat(5, amount);
            sqlStatement.setString(6, shopOrderId);
            sqlStatement.setString(7, errorMsg);
            sqlStatement.setBoolean(8, imported);
            sqlStatement.setString(9, invoiceNoSql);
            sqlStatement.setString(10, shopOrderId);

            resultSet = sqlStatement.executeQuery();

            if (resultSet.next())
            {
                paymentNotificationId = resultSet.getLong(1);
            }
            else
            {
                log.error("Cannot create paymentNotification for order '" + shopOrderId + "'!");
                throw new RuntimeException("Cannot create paymentNotification for order '" + shopOrderId + "'!");
            }
            if (resultSet.next())
            {
                log.error("More than one paymentNotification created for order '" + shopOrderId + "'!");
                throw new RuntimeException(
                                "More than one paymentNotification created for order '" + shopOrderId + "'!");
            }
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException getting aggregated invoice: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (null != resultSet)
            {
                try
                {
                    resultSet.close();
                }
                catch(SQLException e)
                {
                }
            }
        }

        log.info("Created paymentNotification '" + paymentNotificationId + "' for order '" + shopOrderId + "'.");

        return true;
    }

    @Override
    public boolean markOrderAsPaid(String shopName, String shopOrderNb)
    {
        String sqlStatement = "INSERT INTO \"PaymentNotificationDO\" SELECT\n"
                        + "                nextval('\"PaymentNotificationDO_id_seq\"'),\n" + "                1,\n"
                        + "                now(),\n" + "                now(),\n" + "                now(),\n"
                        + "                (SELECT id from \"OrderDO\" where \"shopOrderNo\" = '" + shopOrderNb + "'\n"
                        + "                and \"shopRef\"=(select id from \"ShopDO\" where name= '" + shopName + "')\n"
                        + "                ),\n" + "                5,\n" + "                8000,\n"
                        + "                1,\n" + "                3,\n" + "                123.95000,\n"
                        + "                'EUR',\n" + "                null,\n" + "                null,\n"
                        + "                '',\n" + "                true,\n" + "                false,\n"
                        + "                '',\n" + "                null,\n"
                        + "                (SELECT  i.\"invoiceNo\" FROM \"InvoicingPosDO\" ip\n"
                        + "                 JOIN  \"InvoicingDO\" i ON (ip.\"invoicingRef\"= i.id AND i.\"invoicingTypeDefRef\" = (select id from \"InvoicingTypeDefDO\" where name= 'invoice') )\n"
                        + "                 JOIN  \"OrderPosDO\" op ON (ip.\"orderPosRef\"= op.id)\n"
                        + "                 JOIN \"OrderDO\" o ON (op.\"orderRef\" = o.id)\n"
                        + "                 WHERE o.\"shopOrderNo\" = '" + shopOrderNb + "'\n"
                        + "                 LIMIT 1),\n" + "                '',\n" + "                null,\n"
                        + "                null,\n" + "                null\n" + "            RETURNING id";
        boolean result = runDBStmt(sqlStatement, true);
        log.info("Incoming payment for {} created.", shopOrderNb);
        return result;
    }

    @Override
    public boolean lockThreadRunningRead()
    {
        int[] parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Trying to aquire thread running read lock: Currently are " + parallelCount[0] + "/" + parallelCount[1]
                        + " threads active.");
        long startDate = Calendar.getInstance().getTimeInMillis();
        boolean result = getDBLock(LOCK_ID_THREAD_RUNNING, 1800, DBLockType.R);
        long endDate = Calendar.getInstance().getTimeInMillis();
        parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        if (result)
        {
            log.info("Aquired thread running read lock in " + ((endDate - startDate) / 1000) + "s. Now there are "
                            + parallelCount[0] + "/" + parallelCount[1] + " threads active.");
        }
        else
        {
            log.error("Couldn't aquire thread running read lock in " + ((endDate - startDate) / 1000)
                            + "s! Currently there are " + parallelCount[0] + "/" + parallelCount[1]
                            + " threads active.");
        }
        return result;
    }

    @Override
    public boolean lockThreadRunningWrite()
    {
        int[] parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Trying to aquire thread running WRITE lock: Currently are " + parallelCount[0] + "/"
                        + parallelCount[1] + " threads active.");
        long startDate = Calendar.getInstance().getTimeInMillis();
        boolean result = getDBLock(LOCK_ID_THREAD_RUNNING, 1800, DBLockType.W);
        long endDate = Calendar.getInstance().getTimeInMillis();
        if (result)
        {
            parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
            log.info("Aquired thread running WRITE lock in " + ((endDate - startDate) / 1000) + "s. Now there are "
                            + parallelCount[0] + "/" + parallelCount[1] + " threads active. Am I the only one?");
            if (parallelCount[0] != 1 || parallelCount[1] != 1)
            {
                throw new RuntimeException(
                                "Acquired write lock, but am not the only one! (shared: " + parallelCount[0] + " in "
                                                + parallelCount[2] + " sessions, exclusiv: " + parallelCount[1] + ")");
            }
        }
        else
        {
            log.error("Couldn't aquire thread running WRITE lock in " + ((endDate - startDate) / 1000) + "s!");
        }
        return result;
    }

    @Override
    public boolean unlockThreadRunningRead()
    {
        int[] parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Releasing thread running read lock: Currently are {}/{} threads active.", parallelCount[0],
                        parallelCount[1]);
        boolean result = releaseDBLock(LOCK_ID_THREAD_RUNNING, DBLockType.R);
        parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Released thread running read lock: Now there are {}/{} threads active.", parallelCount[0],
                        parallelCount[1]);
        return result;
    }

    @Override
    public boolean unlockThreadRunningWrite()
    {
        int[] parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Releasing thread running write lock: Currently are {}/{} threads active.", parallelCount[0],
                        parallelCount[1]);
        boolean result = releaseDBLock(LOCK_ID_THREAD_RUNNING, DBLockType.W);
        parallelCount = parallelLockCounter(LOCK_ID_THREAD_RUNNING);
        log.info("Released thread running write lock: Now there are {}/{} threads active.", parallelCount[0],
                        parallelCount[1]);
        return result;
    }

    @Override
    public boolean lockLoadConfiguration()
    {
        log.info("Trying to aquire lock for load configuration...");
        long startDate = Calendar.getInstance().getTimeInMillis();
        boolean result = getDBLock(LOCK_ID_LOAD_CONFIGURATION, 30, DBLockType.W);
        long endDate = Calendar.getInstance().getTimeInMillis();
        if (result)
        {
            log.info("Aquired lock for load configuration in {}s.", (endDate - startDate) / 1000);
        }
        else
        {
            log.error("Couldn't aquire lock for load configuration in {}s!", (endDate - startDate) / 1000);
        }
        return result;
    }

    @Override
    public boolean unlockLoadConfiguration()
    {
        log.info("Releasing lock for load configuration.");
        return releaseDBLock(LOCK_ID_LOAD_CONFIGURATION, DBLockType.W);
    }

    @Override
    public boolean lockAggregatedInvoices()
    {
        log.info("Trying to aquire lock for aggregated invoices...");
        long startDate = Calendar.getInstance().getTimeInMillis();
        // 30 Minutes should be enough to run them aggregated invoice tests serially
        boolean result = getDBLock(LOCK_ID_AGGREGATED_INVOICES, 1800, DBLockType.W);
        long endDate = Calendar.getInstance().getTimeInMillis();
        if (result)
        {
            log.info("Aquired lock for aggregated invoices in {}s.", (endDate - startDate) / 1000);
        }
        else
        {
            log.error("Couldn't aquire lock for aggregated invoices in {}s!", (endDate - startDate) / 1000);
        }
        return result;
    }

    @Override
    public boolean unlockAggregatedInvoices()
    {
        log.info("Releasing lock for aggregated invoices.");
        return releaseDBLock(LOCK_ID_AGGREGATED_INVOICES, DBLockType.W);
    }

    @Override
    public boolean lock_TEST_SHOP(String testShop)
    {
        log.info("Trying to aquire lock for {}", testShop);
        long startDate = Calendar.getInstance().getTimeInMillis();
        // 30 Minutes should be enough to run those other tests serially
        boolean result = getDBLock(LOCK_ID_TEST_SHOP_US, 1800, DBLockType.W);
        long endDate = Calendar.getInstance().getTimeInMillis();
        if (result)
        {
            log.info("Aquired lock for {} in {}s.", testShop, (endDate - startDate) / 1000);
        }
        else
        {
            log.error("Couldn't aquire lock for {} in {}s.", testShop, (endDate - startDate) / 1000);
        }
        return result;
    }

    @Override
    public boolean unlock_TEST_SHOP(String testShop)
    {
        log.info("Releasing lock for {}.", testShop);
        return releaseDBLock(LOCK_ID_TEST_SHOP_US, DBLockType.W);
    }

    /**
     * @return [shareLocks, exclusiveLocks]
     */
    private int[] parallelLockCounter(int lockId)
    {
        String parallelLockCounter = "select * from "
                        + "(select count(*) as exclusivelock from pg_locks where locktype = 'advisory' and classid = 4 and objid = "
                        + lockId + " and objsubid = 2 and mode='ExclusiveLock' and granted) as foo,"
                        + "(select count(*) as sharelock     from pg_locks where locktype = 'advisory' and classid = 4 and objid = "
                        + lockId + " and objsubid = 2 and mode='ShareLock'  and granted) as bar,"
                        + "(select count(distinct pid) as sharedlocksessions     from pg_locks where locktype = 'advisory' and classid = 4 and objid = "
                        + lockId + " and objsubid = 2 and mode='ShareLock' and granted) as boo,"
                        + "(select  count(*) as waiting    from pg_locks where locktype = 'advisory' and classid = 4 and objid = "
                        + lockId + " and objsubid = 2 and NOT granted) as boah";
        int[] parallelLockCount = new int[] { -1, -1, -1, -1 };
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(parallelLockCounter))
        {
            ResultSet resultSet = sqlStatement.executeQuery();
            resultSet.next();
            parallelLockCount[0] = resultSet.getInt("sharelock");
            parallelLockCount[1] = resultSet.getInt("exclusivelock");
            parallelLockCount[2] = resultSet.getInt("sharedlocksessions");
            parallelLockCount[3] = resultSet.getInt("waiting");
            log.info("parallelLockCounter   ({}) sharelock: {}, exclusivelock: {}, sharelock sessions: {}, waiting: {}",
                            lockId, parallelLockCount[0], parallelLockCount[1], parallelLockCount[2],
                            parallelLockCount[3]);
        }
        catch(SQLException sqlEx)
        {
            log.error("Could not get parallel lock count: {}\n{}", sqlEx.getMessage(), sqlEx);
        }
        return parallelLockCount;
    }

    /**
     * @return true if the lock has been acquired, false on timeout
     */
    @Deprecated
    private boolean releaseDBLock(int lockId, DBLockType rw)
    {
        switch(rw)
        {
            case W:
                LockHolder.releaseWriteLock(lockId);
                return true;
            case R:
                LockHolder.releaseReadLock(lockId);
                return true;
            default:
                throw new RuntimeException("Unknown lock type '" + rw + "' found! Supported are 'W' and 'R'.");
        }

        /*
        String statement = null;
        if (rw.equals(DBLockType.R))
        {
            statement = "select count (*) from (select pg_advisory_unlock_shared(4, " + lockId + ")) foo";
        }
        else if (rw.equals(DBLockType.W))
        {
            statement = "select count (*) from (select pg_advisory_unlock(4, " + lockId + ")) foo";
        }
        else
        {
            throw new RuntimeException("Unknown lock type '" + rw + "' found! Supported are 'W' and 'R'.");
        }

        try (Connection connection = ds.getConnection(); PreparedStatement sqlStatement  = connection.prepareStatement(statement))
        {
            sqlStatement.execute();
            log.info("released gebTest {} lock {}\n", rw, lockId);
        }
        catch (SQLException sqlEx)
        {
            log.error("Could release lock using '{}':{}\n{}", statement, sqlEx.getMessage(), sqlEx);
            return false;
        }
        return true;*/
    }

    /**
     * @param timeout in seconds
     * @return true if the lock has been acquired, false on timeout
     */
    @Deprecated
    private boolean getDBLock(int lockId, int timeout, DBLockType rw)
    {
        switch(rw)
        {
            case W:
                return LockHolder.acquireWriteLock(lockId, timeout);
            case R:
                return LockHolder.acquireReadLock(lockId, timeout);
            default:
                throw new RuntimeException("Unknown lock type '" + rw + "' found! Supported are 'W' and 'R'.");
        }
        /*
        String statement = null;
        if (rw.equals(DBLockType.R))
        {
            statement = "select count (*) from (select pg_advisory_lock_shared(4, " + lockId + ")) foo";
        }
        else if (rw.equals(DBLockType.W))
        {
            statement = "select count (*) from (select pg_advisory_lock(4, " + lockId + ")) foo";
        }
        else
        {
            throw new RuntimeException("Unknown lock type '"+rw+"' found! Supported are 'W' and 'R'.");
        }

        try (Connection connection = ds.getConnection(); PreparedStatement sqlStatement  = connection.prepareStatement("SET statement_timeout='" + timeout + "s'");
             PreparedStatement sqlStatement2 = connection.prepareStatement(statement))
        {
            sqlStatement.executeUpdate();
            sqlStatement2.execute();
            log.info("got gebTest {} lock {}\n", rw.toString(), lockId);

        }
        catch (SQLException sqlEx)
        {
            log.error("Could not get lock: {}\n{}", sqlEx.getMessage(), sqlEx);
            return false;
        }
        finally
        {
            try (Connection connection = ds.getConnection(); PreparedStatement sqlStatement3= connection.prepareStatement("RESET statement_timeout"))
            {
                sqlStatement3.executeUpdate();
            }
            catch(Exception ex)
            {
                log.error("Could not reset statement_timeout after lock attempt: {}", ex.getMessage());
            }
        }
        return true;*/
    }

    /**
     * @return the list of currently registered servers for the bakery-config-cache
     */
    private Collection<String> getConfigCachedServerIds()
    {
        return runDBStmtString("SELECT \"serverId\" FROM \"CacheStateDO\" where \"cacheName\"='bakery-cache-config'",
                        "serverId");
    }

    @Override
    public boolean clearConfigCache(Boolean sleepUntilInvalidation)
    {
        Collection<String> oldServerIdList = null;
        if (null == sleepUntilInvalidation)
        {
            oldServerIdList = getConfigCachedServerIds();
        }
        boolean result = runDBStmt("SELECT clear_cache_request('" + OMSDbHandlerV1.CACHE_NAME_CONFIG + "')", true);

        if (null == sleepUntilInvalidation)
        {
            Collection<String> newServerIdList = new ArrayList<>();
            Date start = new Date();
            Date now = start;
            while(!newServerIdList.containsAll(oldServerIdList) && (now.getTime() - start.getTime()
                            < CACHE_INVALIDATION_SLEEP_TIME))
            {
                StringBuilder out = new StringBuilder();
                out.append(newServerIdList.size());
                out.append(" of ");
                out.append(oldServerIdList.size());
                out.append(" servers reacted. Waiting ");
                if (now.getTime() - start.getTime() == 0)
                {
                    out.append("for ");
                }
                else
                {
                    out.append("another ");
                }
                out.append("3s to recheck optimistic cache clear. Waited ");
                out.append((now.getTime() - start.getTime()) / 1000);
                out.append("s of ");
                out.append(CACHE_INVALIDATION_SLEEP_TIME / 1000);
                out.append("s.");
                log.info("{}", out);
                try
                {
                    Thread.sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    result = false;
                }
                now = new Date();
                newServerIdList = getConfigCachedServerIds();
            }
            if (now.getTime() - start.getTime() < CACHE_INVALIDATION_SLEEP_TIME)
            {
                log.info("Waited only " + ((now.getTime() - start.getTime()) / 1000) + "s of " + (
                                CACHE_INVALIDATION_SLEEP_TIME / 1000)
                                + "s until all servers reacted to clear cache request.");
            }
            else
            {
                log.info("Waited all the " + (CACHE_INVALIDATION_SLEEP_TIME / 1000)
                                + "s until all servers should have time to react to clear cache request. ("
                                + newServerIdList.size() + " of " + oldServerIdList.size() + " reacted)");
            }
        }
        else if (sleepUntilInvalidation)
        {
            try
            {
                log.info("Waiting for " + CACHE_INVALIDATION_SLEEP_TIME / 1000
                                + "s - until all servers had time to react to the clear cache request.");
                Thread.sleep(CACHE_INVALIDATION_SLEEP_TIME);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                result = false;
            }
        }
        return result;
    }

    @Override
    public int setAdminSessionDuration(int newTimeoutMinutes)
    {
        String query = "WITH\r\n"
                        + "    \"oldSessionDuration\" as (SELECT * from oms.\"PlatformConfigDO\" where id = 1),\r\n"
                        + "    \"newSessionDuration\" as (UPDATE oms.\"PlatformConfigDO\" set \"userSessionDuration\" = "
                        + newTimeoutMinutes + " where id = 1)\r\n"
                        + "SELECT \"userSessionDuration\" FROM \"oldSessionDuration\" where id = 1\r\n";
        return runDBStmtIntById(query, null, "userSessionDuration");
    }

    @Override
    public Map<Integer, String> getPermissionMap()
    {
        String query = "Select id, name from \"RightDefDO\"";
        return runDBStmtString(query, "id", "name");
    }

    @Override
    public Map<Integer, String> getShopReturnReasons(long shopId)
    {
        Map<Integer, String> shopReturnReasons = new TreeMap<>();
        String query = "SELECT \"returnReasonDefRef\", \"shopReturnReasonName\" FROM oms.\"Shop2ReturnReasonDefDO\" WHERE \"shopRef\" = ?";
        shopReturnReasons.putAll(runDBStmtStringById(query, shopId, "returnReasonDefRef", "shopReturnReasonName"));
        if (shopReturnReasons.isEmpty())
        {
            Long shopParentId = getShopParentId(shopId);
            if (shopParentId != null)
            {
                shopReturnReasons.putAll(getShopReturnReasons(shopParentId));
            }
        }
        return shopReturnReasons;
    }

    @Override
    public Long getShopParentId(long shopId)
    {
        String query = "SELECT \"parentRef\" from \"ShopDO\" where id = ?";
        return runDBStmtLongById(query, shopId, "parentRef");
    }

    @Override
    public boolean setLockedSinceForSchedule(int scheduleId, Date lockedSince)
    {
        String sqlUpdateSchedule = "UPDATE \"ScheduleDO\" SET \"lockedSince\" = to_timestamp(" + lockedSince.getTime()
                        + " / 1000) " + "WHERE \"id\" = '" + scheduleId + "' RETURNING true";
        log.info("Executing query:\n     {}", sqlUpdateSchedule);
        return runDBStmt(sqlUpdateSchedule, true);
    }

    @Override
    public boolean checkPairValuesLongId(String sqlWithIdPlaceholder, Long longId,
                    Collection<AbstractMap.SimpleEntry<BigDecimal, BigDecimal>> entries)
    {
        return this.checkPairValues(sqlWithIdPlaceholder, longId, null, entries);
    }

    @Override
    public boolean checkPairValuesStringId(String sqlWithIdPlaceholder, String stringId,
                    Collection<AbstractMap.SimpleEntry<BigDecimal, BigDecimal>> entries)
    {
        return this.checkPairValues(sqlWithIdPlaceholder, null, stringId, entries);
    }

    private boolean checkPairValues(String sqlWithIdPlaceholder, Long longId, String stringId,
                    Collection<AbstractMap.SimpleEntry<BigDecimal, BigDecimal>> entries)
    {
        try (Connection connection = ds.getConnection();
                        PreparedStatement sqlStatement = connection.prepareStatement(sqlWithIdPlaceholder))
        {
            if (longId != null)
            {
                sqlStatement.setLong(1, longId);
            }
            else if (stringId != null)
            {
                sqlStatement.setString(1, stringId);
            }
            ResultSet resultSet = sqlStatement.executeQuery();
            int resultCount = 0;
            while(resultSet.next())
            {
                boolean match = false;
                for (AbstractMap.SimpleEntry<BigDecimal, BigDecimal> pair : entries)
                {
                    if (resultSet.getBigDecimal(1).compareTo(pair.getKey()) == 0
                                    && resultSet.getBigDecimal(2).compareTo(pair.getValue()) == 0)
                    {
                        match = true;
                        break;
                    }
                }
                if (!match)
                {
                    return false;
                }
                resultCount = resultCount + 1;
            }
            return resultCount == entries.size();
        }
        catch(SQLException sqlEx)
        {
            log.error("SQLException in checkPairValues with query : {}\n{}\n", sqlWithIdPlaceholder,
                            sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
    }

    @Override
    public Connection getConnection()
    {
        return connection;
    }

    @Override
    public void releaseConnection()
    {
        //        try
        //        {
        //            connection.close();
        //        }
        //        catch(SQLException e)
        //        {
        //            log.error("Failed to close connection!", e);
        //            e.printStackTrace();
        //        }
        //        connection = null;
    }

    public enum DBLockType
    {
        W,
        R
    }

    private class AtpStock
    {
        long stockLevel;
        long blockedStock;
    }
}
