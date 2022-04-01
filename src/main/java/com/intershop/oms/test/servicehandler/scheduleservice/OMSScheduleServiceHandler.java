package com.intershop.oms.test.servicehandler.scheduleservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSSortDirection;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleState;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleStateCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleUpdate;
import com.intershop.oms.test.businessobject.schedule.OMSSortableScheduleAttribute;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;

@Experimental("Full rework of the handler is still pending")
public interface OMSScheduleServiceHandler extends OMSServiceHandler
{
    public static Logger log = Logger.getLogger("OMSScheduleServiceHandlerInterface");

    /**
     * @param customCall optional to use custom options for the request call
     * @return
     * @throws ApiException
     */
    public OMSScheduleCollectionContainer getSchedules() throws ApiException;

    /**
     * @param active
     * @param ids
     * @param jobNames
     * @param keys
     * @param isLocked
     * @param willRetry
     * @param orderBy
     * @param sortDirection
     * @param limit
     * @param offset
     * @return
     * @throws ApiException
     */
    public OMSScheduleCollectionContainer getSchedules(Boolean active, List<Integer> ids, List<String> jobNames,
                    List<String> keys, Boolean isLocked, Boolean willRetry, OMSSortableScheduleAttribute orderBy,
                    OMSSortDirection sortDirection, Integer limit, Integer offset) throws ApiException;

    /**
     * @param customCall optional to use custom options for the request call
     * @return
     * @throws ApiException
     */
    public OMSScheduleStateCollectionContainer getSchedulesStates() throws ApiException;

    /**
     * @param active
     * @param ids
     * @param jobNames
     * @param keys
     * @param isLocked
     * @param stopped
     * @param orderBy
     * @param sortDirection
     * @param limit
     * @param offset
     * @return
     * @throws ApiException
     */
    public OMSScheduleStateCollectionContainer getSchedulesStates(Boolean active, List<Integer> ids,
                    List<String> jobNames, List<String> keys, Boolean isLocked, Boolean stopped,
                    OMSSortableScheduleAttribute orderBy, OMSSortDirection sortDirection, Integer limit, Integer offset)
                    throws ApiException;

    /**
     * a direct usage of the generated API - no possible customCall, but should also be used for tests
     *
     * @param scheduleId the required ID of a schedule
     * @param customCall optional to use custom options for the request call
     * @return
     * @throws ApiException
     */
    public OMSScheduleState getScheduleStates(Integer scheduleId) throws ApiException;

    /**
     * @param scheduleId
     * @param omsScheduleUpdate
     * @param customCall
     * @return
     * @throws ApiException
     */
    public OMSScheduleState updateSchedule(Integer scheduleId, OMSScheduleUpdate omsScheduleUpdate) throws ApiException;

    default public boolean checkListSorting(List<Object> sortedThings, OMSSortableScheduleAttribute orderBy,
                    OMSSortDirection sortDirection)
    {
        return checkListSorting(sortedThings, orderBy.getValue(), sortDirection);
    }

    /**
     * @param sortedThings
     * @param orderBy       we don't know how to overwrite/enrich enums in GEB so let's also use a String caller...
     * @param sortDirection
     * @return
     */
    default public boolean checkListSorting(List<Object> sortedThings, String orderBy, OMSSortDirection sortDirection)
    {
        // sorted map with duplicate key entries :-(
        TreeMap<Comparable<?>, List<Object>> expectedSorting = new TreeMap<>();

        // and the "null"-keys
        List<Object> nullies = new ArrayList<>();

        int numberOfMappedThings = 0;
        for (Object sortedThingy : sortedThings)
        {
            //            log.info("checking element " + sortedThingy.toString());
            Comparable<?> sortedByAttribute = null;
            Number id = null;
            for (Method method : sortedThingy.getClass().getDeclaredMethods())
            {
                String methodName = null;
                try
                {
                    methodName = method.getName();
                    String returnTypeName = method.getGenericReturnType().getTypeName();
                    if (returnTypeName.equals("void"))
                    {
                        continue;
                    }
                    if (returnTypeName.equals("boolean") || returnTypeName.equals("int") || returnTypeName.equals(
                                    "long") || returnTypeName.equals("float") || returnTypeName.equals("double"))
                    {
                        //                        log.info("Found primitive return type: " + returnTypeName);
                    }
                    else
                    {
                        Class<?> returnType = Class.forName(method.getGenericReturnType().getTypeName());
                        if (returnType.isAssignableFrom(Comparable.class))
                        {
                            //                            log.info("Method '"+methodName+"' doesn't doesn't return a Comparable '"+method.getGenericReturnType()+"'.");
                            continue;
                        }
                    }
                    Type[] parameterTypes = method.getGenericParameterTypes();
                    if (parameterTypes.length > 0)
                    {
                        //                        log.info("Method '"+methodName+"' requires "+parameterTypes.length+" parameters.");
                        continue;
                    }

                    if (methodName.equals("getId"))
                    {
                        id = (Number)method.invoke(sortedThingy);
                    }

                    if (!methodName.toLowerCase().equals(("get" + orderBy).toLowerCase()))
                    {
                        //                        log.info("Method name '"+methodName+"' doesn't end with '"+orderBy+"'.");
                        continue;
                    }

                    log.info("invoking " + methodName + "() with return type " + method.getGenericReturnType()
                                    .getTypeName() + " on " + sortedThingy.toString());
                    // m.setAccessible(true);
                    sortedByAttribute = (Comparable<?>)method.invoke(sortedThingy);
                    //                    log.info("invoked "+methodName+"() with return type "+method.getGenericReturnType().getTypeName() + " on " + sortedThingy.toString());
                    numberOfMappedThings++;
                    if (null == sortedByAttribute)
                    {
                        //                        log.info(methodName+"() returned null...");
                        nullies.add(sortedThingy);
                    }
                    else
                    {
                        //                        log.info(methodName+"() returned " + sortedByAttribute.toString());
                        List<Object> duplicateKeyValues = expectedSorting.get(sortedByAttribute);
                        if (duplicateKeyValues == null)
                        {
                            duplicateKeyValues = new ArrayList<>();
                            expectedSorting.put(sortedByAttribute, duplicateKeyValues);
                        }
                        if (sortDirection.equals(OMSSortDirection.ASC))
                        {
                            duplicateKeyValues.add(sortedThingy);
                        }
                        else
                        {
                            duplicateKeyValues.add(0, sortedThingy);
                        }
                    }
                    break;
                }
                catch(InvocationTargetException x)
                {
                    Throwable cause = x.getCause();
                    log.info("invocation of " + methodName + " failed: " + cause.getMessage());
                }
                catch(ClassNotFoundException x)
                {
                    x.printStackTrace();
                }
                catch(IllegalAccessException x)
                {
                    x.printStackTrace();
                }
            }
            if (null != sortedByAttribute)
            {
                log.info("checked element " + id + " with key " + sortedByAttribute.toString());
            }
            else
            {
                log.info("checked element " + id + " with key null");
            }
        }

        if (numberOfMappedThings != sortedThings.size())
        {
            throw new RuntimeException("not all elements could be sorted - source had " + sortedThings.size()
                            + " elements found sorting only for " + expectedSorting.size());
        }
        boolean result = true;
        int expectedPosition = 0;
        if (sortDirection.equals(OMSSortDirection.DESC))
        {
            expectedPosition = sortedThings.size();
        }

        for (List<Object> testThingList : expectedSorting.values())
        {
            for (Object testThing : testThingList)
            {
                Object anotherThing = null;
                switch(sortDirection)
                {
                    case DESC:
                        anotherThing = sortedThings.get(--expectedPosition);
                        break;
                    case ASC:
                    default:
                        anotherThing = sortedThings.get(expectedPosition++);
                        break;
                }
                if (!testThing.equals(anotherThing))
                {
                    log.info("That's a pity! tested '" + anotherThing.toString() + "' doesn't match '"
                                    + testThing.toString() + "' (element #" + expectedPosition + ")");
                    result = false;
                }
                else
                {
                    log.info("Passed! tested '" + tryToGetId(anotherThing) + "' does match '" + tryToGetId(testThing)
                                    + "' (element #" + expectedPosition + ")");
                }
            }
        }

        for (Object testThing : nullies)
        {
            int indexOf = sortedThings.indexOf(testThing);
            if (indexOf < 0)
            {
                log.info("That's a pity! tested '" + testThing.toString() + "' not found!");
                result = false;
            }
            switch(sortDirection)
            {
                case DESC:
                    if (indexOf > nullies.size() - 1)
                    {
                        log.info("That's a pity! Got index " + indexOf + " for '" + testThing.toString()
                                        + "' which is too large");
                        result = false;
                    }
                    else
                    {
                        log.info("Passed! tested '" + tryToGetId(testThing) + "' has index " + indexOf + " less than "
                                        + (nullies.size()) + ".");
                    }
                    break;
                case ASC:
                default:
                    if (indexOf < sortedThings.size() - nullies.size() - 1)
                    {
                        log.info("That's a pity! Got index " + indexOf + " for '" + testThing.toString()
                                        + "' which is too little");
                        result = false;
                    }
                    else
                    {
                        log.info("Passed! tested '" + tryToGetId(testThing) + "' has index " + indexOf + " at least "
                                        + (sortedThings.size() - nullies.size()) + ".");
                    }
                    break;
            }
        }
        return result;
    }

    default String tryToGetId(Object obj)
    {
        Number id = null;
        for (Method method : obj.getClass().getDeclaredMethods())
        {
            String methodName = null;
            try
            {
                methodName = method.getName();

                if (methodName.equals("getId"))
                {
                    id = (Number)method.invoke(obj);
                }
            }
            catch(InvocationTargetException x)
            {
                Throwable cause = x.getCause();
                log.info("invocation of " + methodName + " failed: " + cause.getMessage());
            }
            catch(IllegalAccessException x)
            {
                x.printStackTrace();
            }
        }
        if (id != null)
        {
            return String.valueOf(id);
        }
        return "";
    }

}
