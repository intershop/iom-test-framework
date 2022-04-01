package com.intershop.oms.test.businessobject.schedule;

/**
 * The attribute on which should be sorted.
 */
public enum OMSSortableScheduleAttribute
{
    ID("id"),
    ACTIVE("active"),
    KEY("key"),
    JOBNAME("jobName"),
    LASTRUN("lastRun"),
    LOCKEDSINCE("lockedSince"),
    RETRYCOUNT("retryCount"),
    MAXNOOFRETRIES("maxNoOfRetries"),
    NEXTRETRYDATE("nextRetryDate");

    private String value;

    OMSSortableScheduleAttribute(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static OMSSortableScheduleAttribute fromValue(String value)
    {
        for (OMSSortableScheduleAttribute b : OMSSortableScheduleAttribute.values())
        {
            if (b.value.equals(value))
            {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
