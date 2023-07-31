package com.intershop.oms.test.servicehandler.omsdb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.order.OMSOrderPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.test.businessobject.OMSArticle;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSupplier;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;
import com.intershop.oms.test.util.Experimental;
import com.intershop.oms.test.util.InvoiceAggregationInterval;
import com.intershop.oms.test.util.OMSPlatformSchedules;

@Experimental("Full rework of the handler is still pending")
public interface OMSDbHandler
{
    String CACHE_NAME_CONFIG          = "bakery-cache-config";

    // 60 seconds required until all servers can have acted + 10s processing time
    int CACHE_INVALIDATION_SLEEP_TIME = 70000;

    /* "standard" Roles
     *
     * The permissions lists below were extracted from the initial dump fo 1.1.0.0,
     * and enriched with some new  permissions for tcr_omtusermanagement.
     *
     * code gen per sql:
     *
     select '// ('||lpad(id::varchar,8)||')
     public static final String tcr_'||rpad((lower(name)),70)||' = "ARRAY['||string_agg(ppi::varchar,',')||']"'
     FROM
     (
     select r.id,r.name, p."rightDefRef" ppi
     from "RoleDO" r join oms."Role2RightDO" p ON (r.id=p."roleRef")
     order by 3,2) x
     group by name,id
     order by id
     */
    // FIXME extract those
    String tcr_fullomtclient               = "ARRAY[1,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,20,21,22,23,24,25,26,27,28,29,30,31,32,33,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,56,57,58,61,62,63,64,65,66,68,69,70,71,72,73,74,75,76,77,78,79,82,83,84,87,96,97,98,99,100,101,102,103,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,129,130,132,133,135,136,137,138,139,140,141,142,143,144,145,146,147,148]";
    String tcr_fullbackofficeclient        = "ARRAY[1,3,4,5,6,7,8,10,11,12,13,17,20,25,26,27,28,29,30,31,33,37,39,43,45,46,47,48,50,56,61,62,63,64,65,66,70,71,72,73,74,75,76,77,78,79]";
    String tcr_fullvdclient                = "ARRAY[1,4,5,6,7,10,11,12,13,17,26,27,28,29,30,38,39,43,57,61,62,63,69,70,71,72]";
    String tcr_fullpaymentclient           = "ARRAY[101,102,103,104,105,125,126]";
    String tcr_shopserviceclient           = "ARRAY[1,2,3,5,7,8,11,13,44,45,46,47,48,49,50,58,63,64,65,66,67,68,69,70,71,72,74,75,77,78,123,124,131,136,137,138,139,140,141,142,143,144,145,146,147,148]";
    //= tcr_shopserviceclient +11
    String tcr_order_state_service         = "ARRAY[2,3,8,11,44,45,46,47,48,49,50,58,63,64,65,66,67,68,69,70,71,72,123,124,131]";
    String tcr_customeradministration      = "ARRAY[73,74,75,76,77,78, 135]";
    String tcr_fullplatformadmin           = "ARRAY[55,59,60,85,86,87,88,90,91,95,97,127,134,135]";
    String tcr_omtusermanagement           = "ARRAY[85,88,127,129,130]";

    /**
     * return the current connection
     */
    Connection getConnection();

    /**
     * close the connection and release all ressources
     */
    void releaseConnection();

    /**
     * should be private - but in an interface?
     */
    Map<Integer, String> permissionMap = new LinkedHashMap<>();
    static Map<Integer, String> buildPermissionDefinitionMap()
    {
        synchronized(permissionMap)
        {
            if (permissionMap.isEmpty())
            {
                permissionMap.put(1, "View order");
                permissionMap.put(2, "Create order");
                permissionMap.put(3, "Cancel order/create cancellation request");
                permissionMap.put(4, "Create order note");
                permissionMap.put(5, "Create cancellation message");
                permissionMap.put(6, "View return/cancellation message");
                permissionMap.put(7, "Create return message");
                permissionMap.put(8, "Approve return message");
                permissionMap.put(9, "Reject return message");
                permissionMap.put(10, "View delivery message");
                permissionMap.put(11, "Create delivery message");
                permissionMap.put(12, "View receipt message");
                permissionMap.put(13, "Create receipt message");
                permissionMap.put(14, "Create help text");
                permissionMap.put(15, "Edit carrier/logistics partner");
                permissionMap.put(16, "Edit manufacturer");
                permissionMap.put(17, "Set time frame");
                permissionMap.put(18, "View debug information");
                permissionMap.put(19, "Trigger order");
                permissionMap.put(20, "Show invoice address");
                permissionMap.put(21, "Edit delivery message");
                permissionMap.put(22, "Configure product export");
                permissionMap.put(23, "Manage product export classifications/manufacturers.");
                permissionMap.put(24, "Product imports ");
                permissionMap.put(25, "Order status group Received");
                permissionMap.put(26, "Order status group In progress by supplier");
                permissionMap.put(27, "Order status group Shipped");
                permissionMap.put(28, "Order status group Returned");
                permissionMap.put(29, "Order status group Canceled");
                permissionMap.put(30, "Order status group Reserved");
                permissionMap.put(31, "Order status group Manual check required");
                permissionMap.put(32, "Cancel response");
                permissionMap.put(33, "Different order information");
                permissionMap.put(34, "Search receipt messages being processed externally.");
                permissionMap.put(35, "View and recheck defective orders");
                permissionMap.put(36, "Search orders by internal product ID");
                permissionMap.put(37, "Search orders by shop product ID");
                permissionMap.put(38, "Search orders by supplier product ID");
                permissionMap.put(39, "Open orders");
                permissionMap.put(40, "Defective delivery messages");
                permissionMap.put(41, "Reject defective delivery messages");
                permissionMap.put(42, "Defective return/cancellation messages");
                permissionMap.put(43, "Requested supplier cancellations");
                permissionMap.put(44, "Request return note");
                permissionMap.put(45, "Return confirmation");
                permissionMap.put(46, "Approve returns/cancellations");
                permissionMap.put(47, "Read returns to be confirmed");
                permissionMap.put(48, "Read closed returns");
                permissionMap.put(49, "Reject allowed returns");
                permissionMap.put(50, "Reject disallowed returns");
                permissionMap.put(51, "Defective receipt messages with open change requests");
                permissionMap.put(52, "Discard defective receipt messages with open change requests");
                permissionMap.put(53, "Missing commodity groups in product export");
                permissionMap.put(54, "Delete missing commodity groups in product export");
                permissionMap.put(55, "View shop configuration");
                permissionMap.put(56, "Dashboard on homepage");
                permissionMap.put(57, "Create return message via return tool");
                permissionMap.put(58, "View return label");
                permissionMap.put(59, "Create shop configuration");
                permissionMap.put(60, "Edit shop configuration");
                permissionMap.put(61, "Edit Profile");
                permissionMap.put(62, "Order search");
                permissionMap.put(63, "Order status group In approval");
                permissionMap.put(64, "Create approval message");
                permissionMap.put(65, "View approval message");
                permissionMap.put(66, "Search orders to be approved");
                permissionMap.put(67, "Order status web service");
                permissionMap.put(68, "View return note");
                permissionMap.put(69, "View delivery note");
                permissionMap.put(70, "Turnover trend");
                permissionMap.put(71, "System performance");
                permissionMap.put(72, "Performance suppliers/backlog");
                permissionMap.put(73, "Show customer data");
                permissionMap.put(74, "Modify customer data");
                permissionMap.put(75, "Create customer");
                permissionMap.put(76, "View customer contact history entry");
                permissionMap.put(77, "Modify customer contact history entry");
                permissionMap.put(78, "Create customer contact history entry");
                permissionMap.put(79, "View product data");
                permissionMap.put(80, "Manage business processes");
                permissionMap.put(82, "View transmission objects");
                permissionMap.put(83, "Failed transmissions");
                permissionMap.put(84, "Resend transmission objects");
                permissionMap.put(85, "Manage permissions and roles");
                permissionMap.put(86, "Manage news");
                permissionMap.put(87, "Edit product data");
                permissionMap.put(88, "Manage users");
                permissionMap.put(89, "Change product supplier");
                permissionMap.put(90, "View supplier configuration");
                permissionMap.put(91, "Edit supplier configuration");
                permissionMap.put(95, "View order message logs");
                permissionMap.put(96, "Edit product assignments");
                permissionMap.put(97, "Edit commodity group-product permissionMapping");
                permissionMap.put(98, "Edit product classifications and shop catalogs");
                permissionMap.put(99, "Edit product dependencies");
                permissionMap.put(100, "Edit product content");
                permissionMap.put(101, "Show payment transmissions");
                permissionMap.put(102, "Edit rejected payment transmissions");
                permissionMap.put(103, "Edit failed payment transmissions");
                permissionMap.put(104, "Show payments to be approved");
                permissionMap.put(105, "Approve/reject refund");
                permissionMap.put(106, "Search defective receipt messages");
                permissionMap.put(107, "Edit receipt message");
                permissionMap.put(108, "Show process status history");
                permissionMap.put(109, "Edit return message");
                permissionMap.put(110, "Show payment notifications");
                permissionMap.put(111, "Resend payment transmissions");
                permissionMap.put(112, "Show invoices/refunds of an order");
                permissionMap.put(113, "Show invoice/refund details");
                permissionMap.put(114, "Modify invoice details");
                permissionMap.put(115, "Modify refund details");
                permissionMap.put(116, "Create inbound payment notification");
                permissionMap.put(117, "Create outbound payment notification");
                permissionMap.put(118, "Download invoice document");
                permissionMap.put(119, "Edit order address");
                permissionMap.put(120, "Download delivery document");
                permissionMap.put(121, "Download return document");
                permissionMap.put(122, "Release new OMT features");
                permissionMap.put(123, "ATP REST service");
                permissionMap.put(124, "Reservation REST service");
                permissionMap.put(125, "Payment notification REST service");
                permissionMap.put(126, "Payment approval REST service");
                permissionMap.put(127, "Show admin section");
                permissionMap.put(128, "View organizations");
                permissionMap.put(129, "Edit organizations");
                permissionMap.put(130, "Edit role-user assignments");
                permissionMap.put(131, "Return Announcement REST service");
                permissionMap.put(132, "Approve order");
                permissionMap.put(133, "Create manual credit notes");
                permissionMap.put(134, "Basic application management");
                permissionMap.put(135, "Delete customer");
                permissionMap.put(136, "Create return label");
                permissionMap.put(137, "View GDPR relevant data");
                permissionMap.put(138, "Send return label");
                permissionMap.put(139, "View return requests");
                permissionMap.put(140, "Manage return requests");
                permissionMap.put(141, "View approvals of return requests");
                permissionMap.put(142, "Manage approvals of return requests");
                permissionMap.put(143, "View transmissions");
                permissionMap.put(144, "Manage transmissions");
                permissionMap.put(145, "View schedules");
                permissionMap.put(146, "Manage schedules");
                permissionMap.put(147, "Change orders");
                permissionMap.put(148, "View order changes");
            }
        }
        return permissionMap;
    }

    /**
     * returns a string with a postgres array with the permission ids
     *
     * @return string with a postgres array with the permission ids
     */
    default String getPermissionArray(List<String> permissionList)
    {
        Logger log = LoggerFactory.getLogger(this.getClass());

        if (permissionList.isEmpty())
        {
            return "NULL::int[]";
        }

        Map<Integer, String> dbPermissionMap = getPermissionMap();


        StringBuilder permissionBuffer = null;
        StringBuilder errorBuffer = new StringBuilder();

        for (String permission : permissionList)
        {
            boolean found = false;
            for (Map.Entry<Integer, String> entry: dbPermissionMap.entrySet())
            {
                if (permission.equals(entry.getValue()))
                {
                    if (permissionBuffer == null)
                    {
                        permissionBuffer = new StringBuilder();

                    }
                    else
                    {
                        permissionBuffer.append(",");
                    }
                    found = true;
                    permissionBuffer.append(entry.getKey().toString());

                    break;
                }
            }
            if (!found)
            {
                errorBuffer.append("Couldn't find ID for requested permission '").append(permission).append("'!\n");
            }
        }

        if (errorBuffer.length() > 0)
        {
            throw new RuntimeException("Error building permission array:\n" + errorBuffer);
        }
        log.info("Built positive permission array: ARRAY[{}]", permissionBuffer);
        return "ARRAY[" + permissionBuffer + "]";
    }

    /**
     * returns a string with a postgres array with all permission ids not contained in the list --- for negative permission checking
     *
     * @return string with a postgres array with the permission ids not contained in the list --- for negative permission checking
     */
    default String getPermissionArrayNegative(List<String> permissionList)
    {
        Logger log = LoggerFactory.getLogger(this.getClass());

        if (permissionList.isEmpty())
        {
            return "NULL::int[]";
        }

        Map<Integer, String> dbPermissionMap = getPermissionMap();

        StringBuilder permissionBuffer = null;
        List<String> digestedPermissions = new ArrayList<>();

        for (Map.Entry<Integer, String> entry: dbPermissionMap.entrySet())
        {
            boolean foundInRequestedList = false;
            for (String permission : permissionList)
            {
                if (permission.equals(entry.getValue()))
                {
                    foundInRequestedList = true;
                    digestedPermissions.add(permission);
                    break;
                }
            }
            if (!foundInRequestedList)
            {
                if (permissionBuffer == null)
                {
                    permissionBuffer = new StringBuilder();

                }
                else
                {
                    permissionBuffer.append(",");
                }

                permissionBuffer.append(entry.getKey().toString());
            }
        }

        if (!digestedPermissions.containsAll(permissionList))
        {
            StringBuilder errorBuffer = new StringBuilder();
            for (String requestedPermission : permissionList)
            {
                if (!digestedPermissions.contains(requestedPermission))
                {
                    errorBuffer.append("Couldn't find ID for requested permission '").append(requestedPermission)
                                    .append("'!\n");
                }
            }
            throw new RuntimeException("Error building negative permission array:\n" + errorBuffer);
        }
        log.info("Built negative permission array: ARRAY[{}]", permissionBuffer);
        return "ARRAY[" + permissionBuffer + "]";
    }

    default String createUniqueOMTUser(String prefix)
    {
        return prefix +'_' + new SimpleDateFormat("yyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }

    default String createUniqueOMTUser(String prefix, int testCaseId)
    {
        return createUniqueOMTUser( prefix +'_' + testCaseId );
    }

    void deleteSsoUserByMail(String mail);

    /**
     * creates a user and a role to fully access the whole OMT
     * login and role have the same name to be able to cleanup afterwards
     *
     * @return the login of the new user
     */
    String createUniqueFullOMTUser(Integer testCaseID);

    /**
     * cleanup the mess...
     *
     */
    void cleanupUniqueFullOMTUser(String login);

    /**
     *   runs an update statement (no resultSet expected)
     *
     * @return true on success, false on exception
     */
    boolean runDBUpdate(String query);

    /**
     *   runs an update statement (no resultSet expected)
     *
     * @param objectId1 optional query parameter
     * @param objectId2  optional query parameter
     * @return true on success, false on exception
     */
    boolean runDBUpdate(String query, Optional<String> objectId1, Optional<Long> objectId2);

    /**
    *   runs a statement and checks if the result is success or failure as expected in 'assertTrue'
    *
    * @return check flag
    */
    boolean runDBStmt(String query, boolean assertTrue);

    /**
     * runs the query and checks that all resultColumnName  results match the boolean 'expectedResult'
     * throws an exception if the query does not return any result.
     *
     * @return true - if all result rows match expectedResult
     *
     */
    boolean runDBStmtBoolean(String query, String resultColumnName, boolean expectedResult);
    

    /**
     * runs the query repeatedly until it reach the expected status for at max waitTimeSec
     * will throw an exception if the query does not return a boolean in the first column or does not return exactly one row.
     *
     * @return true - if the expected status was found in the given time, false otherwise
     *
     */
    boolean runDBStmtBoolean(String query, int waitTimeSec, boolean expectedResult, int sleepTimeSec);
    
    /**
     * runs the query repeatedly until it reach the expected status.
     * will throw an exception if the query does not return a boolean in the first column or does not return exactly one row.
     * use the instance maxRetry and retryDelayparameters.
     * @param query
     * @param expectedStatus
     * @param parameters the query parameters in the correct position order
     * @return true - if the expected status was found in the given time, false otherwise
     */
    boolean runDBStmtBooleanWait(String query, boolean expectedStatus, List<Object> parameters);

    /**
     * runs the query and returns the resulting integer as the result of the only one result line
     * throws an exception if the query does not return any result or returns more than one row
     * @return the resulting integer as the result of the only one result line
     */
    int runDBStmtIntById(String query, Long id, String resultColumnName);
    Long runDBStmtLongById(String query, Long id, String resultColumnName);
    List<Long> runDBStmtLongListById(String query, Long id, String resultColumnName);

    Map<Integer, Integer> runDBStmtInt(String query, String resultColumnNameKey, String resultColumnNameValue);
    Map<Integer, Integer> runDBStmtIntById(String query, Long id, String resultColumnNameKey, String resultColumnNameValue);

    String runDBStmtStringById(String query, Long id, String resultColumnName);
    List<String> runDBStmtString(String query, String resultColumnName);
    Map<Integer, String> runDBStmtString(String query, String resultColumnNameKey, String resultColumnNameValue);
    Map<Integer, String> runDBStmtStringById(String query, Long id, String resultColumnNameKey, String resultColumnNameValue);

    /**
     * returns the Map { ID =&gt; permissionName } as currently defined in the DB
     *
     * @return the Map { ID =&gt; permissionName } as currently defined in the DB
     */
    Map<Integer, String> getPermissionMap();

    /**
     * returns the name of the shop as used by the supplier
     */
    String getSupplierShopName(OMSShop shop, OMSSupplier supplier);

    String getSupplierShopName(String shopName, String supplierName);

    List<OMSSupplier> getSuppliersForShop(OMSShop shop);

    /**
     * returns the id corresponding to a Supplier.name
     */
    long getSupplierId(String supplierName);

    /**
     * returns the id corresponding to a ShopDO.name
     */
    Long getShopId(String shopName);

    /**
     * returns ShopDO.shopName corresponding to ShopDO.id
     */
    String getShopName(long shopId);

    long getArticleId(long shopId, String shopArticleNo);

    long getOrderId(Long shopId, String shopOrderNumber);

    long getOrderChangeRequestId(Long orderId, String changeRequestId);

    long getOrderChangeRequestId(Long shopRef, String shopOrderNo, String changeRequestId);

    /**
     */
    void setStock(long shopId, String shopArticleNo, String supplierName, int stockLevel, int blocked);

    /**
     * delete all reservations or reserved item for the given shop and shopArticleNos matching the regexp.
     */
    void deleteReservations(long shopId, String shopArticleNoRegexp);

    long getAvailableStock(long shopId, String shopArticleNo, String supplierName);

    Long getReservedStockForArticle(long reservationId, OMSArticle article);

    boolean checkMinimumReservationLifeTime(long reservationId, long lifeTime);

    /**
     *
     */
    OMSSupplier getReservationAssignedSupplier(long reservationID, OMSArticle article);

    /**
     * returns the number of reservation registered in OrderSupplierEvaluationDO for the given reservationID
     *
     * @return the number of reservation registered in OrderSupplierEvaluationDO for the given reservationID
     */
    int getNumberOfOrderSupplierEvaluationsForReservation(long reservationId);

    /**
     * returns the number of reservation registered in OrderSupplierEvaluationDO for the given reservationID and a given order
     *
     * @param reservationId
     * 1param orderId
     * @return the number of reservation registered in OrderSupplierEvaluationDO for the given reservationID and a given order
     */
    int getNumberOfOrderSupplierEvaluationsForReservationAndOrder(long reservationId, long orderId);

    /**
     * returns the number of reservation registered in OrderSupplierEvaluationDO for a given order
     *
     * 1param orderId
     * @return the number of reservation registered in OrderSupplierEvaluationDO for a given order
     */
    int getNumberOfOrderSupplierEvaluationsForOrder(long orderId);

    /**
     * prepares the dispatch positions for a complete dispatch of the given
     * order
     *
     * @param useSupplierData
     *            if true - use supplierArticleName and supplierArticleNumber,
     *            if false - use shopArticleName and shopArticleNumber
     * @return a map &lt;Supplier--&gt;dispatchPositions&gt; for the given order
     */
    Map<OMSSupplier, Collection<OMSDispatchPosition>> getDispatchPositionsForOrder(OMSOrder order,
                    boolean useSupplierData);

    /**
     * prepares the order response positions for a complete order response of the given
     * order
     *
     * @param useSupplierData
     *            if true - use supplierArticleName and supplierArticleNumber,
     *            if false - use shopArticleName and shopArticleNumber
     * @return a map &lt;Supplier--&gt;orderResponsePositions&gt; for the given order
     */
    Map<OMSSupplier, Collection<OMSOrderResponsePosition>> getOrderResponsePositionsForOrder(OMSOrder order,
                    boolean useSupplierData);

    /**
     *
     */
    Map<OMSSupplier, Collection<OMSOrderPosition>> getOrderPositionsBySupplier(OMSOrder order);

    /**
     * prepares the return request positions for the given order
     *
     * @return a map &lt;Shop--&gt;returnRequestPositions&gt; for the given order
     */
    Collection<OMSWriteReturnRequestPosition> getReturnRequestPositionsForOrder(OMSOrder order);

    /**
     * prepares the return positions for a complete return of the given
     * order
     *
     * @param useSupplierData
     *            if true - use supplierArticleName and supplierArticleNumber,
     *            if false - use shopArticleName and shopArticleNumber
     * @return a map &lt;Supplier--&gt;returnPositions&gt; for the given order
     */
    Map<OMSSupplier, Collection<OMSReturnPosition>> getReturnPositionsForOrder(OMSOrder order, boolean useSupplierData);

    /**
     * Get all returnIds for a given order
     *
     * @return list of return ids
     */
    List<Long> getAllReturnIdsForOrder(OMSOrder order);
    
    /**
     * Wait for expectedCount returnIds for a given order and returns them, sorted by their creation date (ascending)
     *
     * @return list of return ids
     */
    public List<Long> waitForAllReturnIdsForOrder(OMSOrder order, int expectedCount);

    /**
     * Get all dispatchPositionIds for the given dispatchId
     *
     * @return list of the dispatch position ids
     */
    List<Long> getAllDispatchesPositionIds(Long dispatchRef);

    /**
     * Get all dispatchPositionPropertyIds for the given dispatchPositionId
     *
     * @return list of the dispatch position property ids
     */
    List<Long> getAllDispatchPositionPropertyIds(Long dispatchPosRef);

    /**
     * Get all dispatchPositionItemIds for the given dispatchPositionId
     *
     * @return list of the dispatch position item ids
     */
    List<Long> getAllDispatchPositionItemIds(Long dispatchPosRef);

    /**
     * Get a the list with an error message for each dispatch position (might be null for any single position!)
     *
     * @return the list with an error message for each dispatch position (might be null for any single position!)
     */
    List<String> getDispatchPositionsErrors(Long dispatchRef);

    /**
     * gets the error from the DB for this dispatch (might be null)
     * @return the error from the DB for this dispatch (might be null)
     */
    String getDispatchError(Long dispatchRef);

    /**
     * Get the dispatchPositionQuantityDispatched for the given
     * dispatchPositionId
     *
     * @return the quantity dispatched of the given dispatch position
     */
    Long getDispatchPositionQuantityDispatched(Long dispatchPosRef);

    /**
     * Get all responsePositionIds for the given dispatchId
     *
     * @return list of the response position ids
     */
    List<Long> getAllOrderResponsesPositionIds(Long responseRef);

    /**
     * Get a the list with an error message for each response position (might be null for any single position!)
     *
     * @return the list with an error message for each response position (might be null for any single position!)
     */
    List<String> getOrderResponsePositionsErrors(Long responseRef);

    /**
     * gets the error from the DB for this response (might be null)
     * @return the error from the DB for this response (might be null)
     */
    String getOrderResponseError(Long responseRef);

    /**
     * get the current state of the response ("waitFor*" just checks if the state is larger!)
     */
    int getOrderResponseState(Long responseId);

    /**
     * Get all responsePositionPropertyIds for the given dispatchPositionId
     *
     * @return list of the response position property ids
     */
    List<Long> getAllOrderResponsePositionPropertyIds(Long responsePosRef);

    /**
     * Get all returnPositionIds for the given returnId
     *
     * @return list of the return position ids
     */
    List<Long> getAllReturnsPositionIds(Long returnRef);

    /**
     * Get all returnPositionPropertyIds for the given returnPositionId
     *
     * @return list of the return position property ids
     */
    List<Long> getAllReturnPositionPropertyIds(Long returnPosRef);

    /**
     * Get all returnPositionItemIds for the given returnPositionId
     *
     * @return list of the return position item ids
     */
    List<Long> getAllReturnPositionItemIds(Long returnPosRef);

    /**
     * Get a the list with an error message for each return position (might be null for any single position!)
     *
     * @return the list with an error message for each return position (might be null for any single position!)
     */
    List<String> getReturnPositionsErrors(Long returnRef);

    /**
     * gets the error from the DB for this return (might be null)
     * @return the error from the DB for this response (might be null)
     */
    String getReturnError(Long returnRef);

    /**
     * @return the value of showMaxReturnQuantity configured in PlatformConfig
     */
    boolean getPlatformConfigShowgMaxReturnQty();

    /**
     * Get the returnPositionQuantityDispatched for the given
     * returnPositionId
     *
     * @return the quantity returned of the given return position
     */
    Long getReturnPositionQuantityReturned(Long returnPosRef);

    /**
     * returns a list with the Ids of all invoices for the order
     * @param orderId
     * @return a list with the Ids of all invoices for the order
     */
    List<Long> getInvoiceIdsForOrder(long orderId);

    /**
     * Check if both orders are referencing the same (aggregated) invoice
     *
     * @return the invoicing Id
     */
    long checkAggregatedInvoice(long orderId1, long orderId2);

    /**
     * Wait until the stateRef of the order is at least of the given state
     *
     */
    boolean waitForOrderState(long orderId, int expectedState);

    /**
     * Wait until the stateRef of the order change request is at least of the given state
     *
     */
    boolean waitForOrderChangeRequestState(long orderChangeRequestId, int expectedState);

    /**
     * Wait until the stateRef of the dispatch for the given order is at least of the given state
     *
     */
    boolean waitForDispatchStateOfOrder(long orderId, int expectedState);

    /**
     * Wait until the stateRef of the dispatch is the endState = 8000
     *
     */
    default boolean waitForDispatchState(long dispatchId)
    {
        return waitForDispatchState(dispatchId, 8000);
    }

    /**
     * Wait until the stateRef for the given dispatch is at least of the given state
     *
     */
    boolean waitForDispatchState(long dispatchId, int expectedState);

    default boolean waitForDispatchesState(Collection<Long> dispatchIds)
    {
        return waitForDispatchesState(dispatchIds, 8000);
    }

    default boolean waitForDispatchesState(Collection<Long> dispatchIds, int expectedState)
    {
        boolean result = true;
        for (long dispatchId : dispatchIds)
        {
            if (!waitForDispatchState(dispatchId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef of the return is the endState = 8000
     *
     */
    default boolean waitForReturnState(long returnId)
    {
        return waitForReturnState(returnId, 8000);
    }

    /**
     * Wait until the stateRef for the given return is at least of the given state
     *
     */
    boolean waitForReturnState(long returnId, int expectedState);

    default boolean waitForReturnsState(Collection<Long> returnIds)
    {
        return waitForReturnsState(returnIds, 8000);
    }

    default boolean waitForReturnsState(Collection<Long> returnIds, int expectedState)
    {
        boolean result = true;
        for (long returnId : returnIds)
        {
            if (!waitForReturnState(returnId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef and the expected count of return(s) for the given order is at least of the given state
     *
     */
    boolean waitForReturnStateOfOrder(long orderId, int expectedState, int returnCount);

    /**
     * Wait until the stateRef of the order response is at least the endState = 8000
     *
     */
    default boolean waitForOrderResponseState(long responseId)
    {
        return waitForOrderResponseState(responseId, 8000);
    }

    /**
     * Wait until the stateRef of the order state is at least expectedState
     *
     */
    boolean waitForOrderResponseState(long responseId, int expectedState);

    default boolean waitForOrderResponsesState(Collection<Long> responseIds)
    {
        return waitForOrderResponsesState(responseIds, 8000);
    }

    default boolean waitForOrderResponsesState(Collection<Long> responseIds, int expectedState)
    {
        boolean result = true;
        for (long responseId : responseIds)
        {
            if (!waitForOrderResponseState(responseId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef of the return announcement is the endState = 8000
     *
     */
    default boolean waitForReturnAnnouncementState(long rmaId)
    {
        return waitForReturnAnnouncementState(rmaId, 8000);
    }

    /**
     * Wait until the stateRef of the return announcement (request) is expectedState (def = 8000)
     *
     */
    boolean waitForReturnAnnouncementState(long rmaId, int expectedState);

    default boolean waitForReturnAnnouncementsState(Collection<Long> rmaIds)
    {
        return waitForReturnAnnouncementsState(rmaIds, 8000);
    }

    default boolean waitForReturnAnnouncementsState(Collection<Long> rmaIds, int expectedState)
    {
        boolean result = true;
        for (long rmaId : rmaIds)
        {
            if (!waitForReturnAnnouncementState(rmaId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef of the invoice is the endState = 8000
     *
     */
    default boolean waitForInvoiceState(long invoiceId)
    {
        return waitForInvoiceState(invoiceId, 8000);
    }

    /**
     * Wait until the stateRef of the invoice is expectedState (def = 8000)
     *
     */
    boolean waitForInvoiceState(long invoiceId, int expectedState);

    default boolean waitForInvoicesState(Collection<Long> invoiceIds)
    {
        return waitForInvoicesState(invoiceIds, 8000);
    }

    default boolean waitForInvoicesState(Collection<Long> invoiceIds, int expectedState)
    {
        boolean result = true;
        for (long invoiceId : invoiceIds)
        {
            if (!waitForInvoiceState(invoiceId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef of the payment notification is the endState = 8000
     *
     * assumes, that there is only one payment per invoice
     */
    default boolean waitForPaymentState(long invoiceId)
    {
        return waitForPaymentState(invoiceId, 8000);
    }

    /**
     * Wait until the stateRef of the payment notification is at least expectedEndState
     *
     * assumes, that there is only one payment per invoice
     */
    boolean waitForPaymentState(long invoiceId, int expectedState);

    /**
     * Wait until the stateRef of the invoice document is the endState = 5000
     *
     */
    default boolean waitForInvoiceDocumentState(long orderId)
    {
        return waitForInvoiceDocumentState(orderId, 5000);
    }

    /**
     * Wait until the stateRef of the invoice document is expectedState (def = 5000)
     *
     */
    boolean waitForInvoiceDocumentState(long orderId, int expectedState);

    default boolean waitForInvoiceDocumentsState(Collection<Long> orderIds)
    {
        return waitForInvoiceDocumentsState(orderIds, 5000);
    }

    default boolean waitForInvoiceDocumentsState(Collection<Long> orderIds, int expectedState)
    {
        boolean result = true;
        for (long orderId : orderIds)
        {
            if (!waitForInvoiceDocumentState(orderId, expectedState))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Wait until the stateRef of the MailTransmission is the endState = 6000
     *
     */
    default boolean waitForOrderConfirmationMailState(long orderId)
    {
        return waitForOrderConfirmationMailState(orderId, 6000);
    }

    /**
     * Wait until the stateRef for the given mail is of the given state
     *
     */
    boolean waitForOrderConfirmationMailState(long orderId, int expectedState);


    /**
     * store the current state of the invoice aggregation jobs
     * @return boolean[] dailyJobEnabled, boolean weeklyJobEnabled, boolean monthlyJobEnabled, boolean cleanupJobEnabled
     */
    boolean[] getInvoiceProcessState();

    /**
     * restore the preserved state of the invoice aggregation jobs
     * @param preservedState boolean[] dailyJobEnabled, boolean weeklyJobEnabled, boolean monthlyJobEnabled, boolean cleanupJobEnabled
     */
    void setInvoiceProcessState(boolean[] preservedState);

    /**
     * sets the active state for the invoice processes
     *
     */
    void setInvoiceProcessesActive(boolean dailyJobEnabled, boolean weeklyJobEnabled, boolean monthlyJobEnabled, boolean cleanupJobEnabled);

    /**
     * tries to trigger the aggregation of invoices by resetting
     * the DB to a state that runs a specific invoice aggregation job with the next scheduler check
     *
     * @param aggregationKey the identifier of the job
     * @param lastRun a date when the last run of this job should be simulated (might be null, meaning the job never ran before)
     *
     * @return true if the job ran successfully --&gt; "nextRetryDate\" IS NULL
     */
    boolean runInvoiceAggregationJob(OMSPlatformSchedules.Invoicing aggregationKey, Date lastRun);
    default boolean runInvoiceAggregationJob(OMSPlatformSchedules.Invoicing aggregationKey)
    {
        return runInvoiceAggregationJob(aggregationKey, null);
    }

    /**
     * Sets modification date of dispatch back by 10 minutes
     * as CheckDispatchControllerBean does not trigger processing for dispatches younger than 10 minutes
     *
     * @return true on success, false on exception
     */
    boolean predateDispatchForFasterProcessing(Long dispatchId);

    /**
     * Sets modification date of return back by 10 minutes
     * as CheckReturnControllerBean does not trigger processing for returns younger than 10 minutes
     *
     * @return true on success, false on exception
     */
    boolean predateReturnForFasterProcessing(Long returnId);

    /**
     * waits until all invoices of the given type for the given customerShopNo are there and processed to 8000
     *
     * invoiceTypes: 1...invoice 2...credit
     */
    boolean waitForInvoicesProcessed(String customerShopNo, int invoiceType, int minimumNumberOfThoseInvoices);
    default boolean waitForInvoicesProcessed(String customerShopNo, int invoiceType)
    {
        return waitForInvoicesProcessed(customerShopNo, invoiceType, 1);
    }

    /**
     * sets the aggregation flag for a customer
     *
     */
    void setAggregateInvoicesFlagForCustomer(String shopCustomerNo, String shopName, boolean aggregateInvoices, InvoiceAggregationInterval invoiceAggregationInterval);

    /**
     * @param paymentState one of: INITIAL, DO_ROUTE, DO_PROCESS, PROCESS_FAILED, DO_MANUAL_APPROVE, DO_TRANSMIT, DO_CLOSE, CLOSED, CANCELED
     * @param paymentAction one of: authorize, reverse, capture, refund
     * @param imported: true or false
     */
    boolean createPaymentNotificationDO(String shopOrderId, String paymentState, String paymentAction, String erroMsg,
                    boolean imported, String invoiceNo, float amount);

    /**
     * marks an order as paid
     * @return true if everything is ok
     */
    boolean markOrderAsPaid(String shopName, String shopOrderNb);

    /**
     * checks if the supplier is configured for not sending return messages with
     * same article in multiple positions
     *
     * @return list of the singlePositionArticle
     *         true if the supplier is configured for not sending return
     *         messages with same article in multiple positions
     */
    List<Boolean> hasSupplierSinglePositionArticle(
                    Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions);

    /**
     *
     */
    boolean setLockedSinceForSchedule(int scheduleId, Date lockedSince);

    /**
     * prevents the running of threads requiring the write lock
     * @return true if the thread running read lock has been obtained within 1800s
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     */
    @Deprecated
    boolean lockThreadRunningRead();

    /**
     * prevents the running of all other threads requiring this lock (write or read)
     * @return true if the thread running write lock has been obtained within 1800s
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     * <p><b>this is not thread safe anymore!</b>
     *
     * <p>Note: You have to release your own read lock before getting a write lock.
     *       Otherwise we would get dead locks when two thread try the write lock at the same time
     */
    @Deprecated
    boolean lockThreadRunningWrite();
    @Deprecated
    boolean unlockThreadRunningRead();
    @Deprecated
    boolean unlockThreadRunningWrite();

    /**
     * @return true if the configuration lock has been obtained within 30s
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     */
    @Deprecated
    boolean lockLoadConfiguration();
    /**
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
                */
    @Deprecated
    boolean unlockLoadConfiguration();

    /**
     * @return true if the lock for aggregated invoices has been obtained within 1800s
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     */
    @Deprecated
    boolean lockAggregatedInvoices();
    /**
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     */
    @Deprecated
    boolean unlockAggregatedInvoices();

    /**
     * this is used for the concurrent usage of article1, article2, article3
     *
     * @return true if the shop has been locked within 1800s
     */
    @Deprecated
    boolean lock_TEST_SHOP(String testShop);
    /**
     * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
     * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
     */
    @Deprecated
    boolean unlock_TEST_SHOP(String testShop);

    /**
     * sets the clear configuration cache in the DB
     *
     * @param sleepUntilInvalidation true  - always wait for the cache invalidation to reach all servers
     *                               false - don't wait at all
     *                               null  - try the fastest optimistic approach - when all currently registered servers have reacted, than the cache is clear and we don't need to wait any longer
     *                                       optimistic means, there might have been old server entries, which don't appear anymore --- than we just wait the normal CACHE_INVALIDATION_SLEEP_TIME
     * @return true is everything is OK
     */
    boolean clearConfigCache(Boolean sleepUntilInvalidation);

    /**
     * sets the session duration for the session in the OMT
     *
     * @return the old session timeout in minutes or -1 on failure
     */
    int setAdminSessionDuration(int newTimeoutMinutes);

    /**
     * returns the number of Shop2ReturnReasonDefDOs for the given shop
     *
     * @return a map id-&gt;name for the return reasons for the shop (if none assigned directly to the shop, the parent shop will be asked)
     */
    Map<Integer, String> getShopReturnReasons(long shopId);

    /**
     * returns the Id of the parent shop or null, if there is none
     * @return the Id of the parent shop or null, if there is none
     */
    Long getShopParentId(long shopId);

    /**
     * for (all?) db-state-waitings this sets the number of retries and the waitings between two retries
     *
     */
    void setTimeOutParameters(int maxRetry, int retryDelay);

    /**
     * Returns a count of successfully (stateRef=100) imported product data files of a specific type.
     *
     * @param fileType the "ImportFileTypeDefDO"
     */
    int getProductImportFileCount(String fileType);

    /**
     * "positionSalesSumTax", "salesSumSubTax" from "InvoicingSalesPriceTaxDO"
     *  filter : shopOrderNo
     */
    String GET_INVOICING_TAX_SUMMARY = "select \"positionSalesSumTax\" as sumtax, \"salesSumSubTax\" as subtax\n" +
                    "    from \"InvoicingSalesPriceTaxDO\"\n" +
                    "    where \"invoicingSalesPriceRef\" = (\n" +
                    "       select id from \"InvoicingSalesPriceDO\" where \"invoicingRef\" = \n" +
                    "          (select \"invoicingRef\" from \"Invoicing2OrderDO\" where \"orderRef\"=\n" +
                    "            (select id from \"OrderDO\" where \"shopOrderNo\" = ?)\n" +
                    "         )\n" +
                    "     )";
    /**
     * "salesSumGrossDiscounted", "gross-net-tax (=0)" from "InvoicingSalesPriceChargeDO"
     *  filter : shopOrderNo
     */
    String  GET_INVOICING_PROMOTIONS = "select \"salesSumGross\" as sum_gross, \"salesSumGross\" - \"salesSumTax\"- \"salesSumNet\" as tax_check\n" +
                    "    from \"InvoicingSalesPricePromotionDO\"\n" +
                    "    where \"invoicingSalesPriceRef\" = (\n" +
                    "       select id from \"InvoicingSalesPriceDO\" where \"invoicingRef\" = \n" +
                    "          (select \"invoicingRef\" from \"Invoicing2OrderDO\" where \"orderRef\"=\n" +
                    "            (select id from \"OrderDO\" where \"shopOrderNo\" = ?)\n" +
                    "         )\n" +
                    "     )";
    /**
     * "salesSumGrossDiscounted", "gross-net-tax (=0)" from "InvoicingSalesPriceChargeDO"
     *  filter : shopOrderNo
     */
    String  GET_INVOICING_CHARGES = "select \"salesSumGrossDiscounted\" as sum_gross, \"salesSumGrossDiscounted\" - \"salesSumTaxDiscounted\"- \"salesSumNetDiscounted\" as tax_check\n" +
                    "    from \"InvoicingSalesPriceChargeDO\"\n" +
                    "    where \"invoicingSalesPriceRef\" = (\n" +
                    "       select id from \"InvoicingSalesPriceDO\" where \"invoicingRef\" = \n" +
                    "          (select \"invoicingRef\" from \"Invoicing2OrderDO\" where \"orderRef\"=\n" +
                    "            (select id from \"OrderDO\" where \"shopOrderNo\" = ?)\n" +
                    "         )\n" +
                    "     )";
    /**
     * "taxRate", "taxValue" from "InvoicingSalesPriceChargeTaxDO"
     *  filter : shopOrderNo
     */
    String  GET_INVOICING_CHARGETAXES = "select \"taxRate\", \"taxValue\"\n" +
                    " from \"InvoicingSalesPriceChargeTaxDO\" where \"invoicingSalesPriceChargeRef\" IN "+
                    " (SELECT id from \"InvoicingSalesPriceChargeDO\"\n" +
                    "    where \"invoicingSalesPriceRef\" = (\n" +
                    "       select id from \"InvoicingSalesPriceDO\" where \"invoicingRef\" = \n" +
                    "          (select \"invoicingRef\" from \"Invoicing2OrderDO\" where \"orderRef\"=\n" +
                    "            (select id from \"OrderDO\" where \"shopOrderNo\" = ?)\n" +
                    "         )\n" +
                    "     )" +
                    ")";

    /**
     * check that the passed query return exactly the list of values.
     * @param sqlWithIdPlaceholder example: GET_INVOICING_TAX_SUMMARY
     * @param longId to be used as query parameter
     */
    @Deprecated
    // FIXME get rid of this, we want to compare business objects, not query results
    boolean checkPairValuesLongId(String sqlWithIdPlaceholder, Long longId,
                    Collection<SimpleEntry<BigDecimal, BigDecimal>> entries);

    /**
     * check that the passed query return exactly the list of values.
     * @param sqlWithIdPlaceholder example: GET_INVOICING_TAX_SUMMARY
     * @param stringId to be used as query parameter
     */
    @Deprecated
    // FIXME get rid of this, we want to compare business objects, not query results
    boolean checkPairValuesStringId(String sqlWithIdPlaceholder, String stringId,
                    Collection<SimpleEntry<BigDecimal, BigDecimal>> entries);

    public void setPlatformConfigPropertyDO(String key, String value);
    public void deletePlatformConfigPropertyDO(String key);

/**
 * wait until an order has at least reached the given state (wait time = 12 minutes)
 * @param orderId
 * @param expectedState
 * @return
 */
    boolean waitForOrderStateMet(long orderId, int expectedState);


}
