package com.intershop.oms.test.util;

/**
 * Class provides the possible aggregation intervals for invoices
 */
public enum InvoiceAggregationInterval
{
    AGGREGATE_INVOICES_DAILY(1),
    AGGREGATE_INVOICES_WEEKLY(2),
    AGGREGATE_INVOICES_MONTHLY(3);

    private int id;

    InvoiceAggregationInterval(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
