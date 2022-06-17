package com.intershop.oms.test.util;

/**
 * Class provides constants related to default jobs/schedules in IOM
 */
public interface OMSPlatformSchedules
{
    enum Invoicing
    {
        AGGREGATE_DISPATCH_INVOICES_INVALID          ( "aggregate_invoices4dispatches_invalid"),
        AGGREGATE_DISPATCH_INVOICES_DAILY            ( "aggregate_invoices4dispatches_daily"),
        AGGREGATE_DISPATCH_INVOICES_WEEKLY           ( "aggregate_invoices4dispatches_weekly"),
        AGGREGATE_DISPATCH_INVOICES_MONTHLY          ( "aggregate_invoices4dispatches_monthly"),
        AGGREGATE_DISPATCH_INVOICES_CLEANUP_WEEKLY   ( "aggregate_invoices4dispatches_weekly_cleanup"),
        AGGREGATE_DISPATCH_INVOICES_CLEANUP_DISABLED ( "aggregate_invoices4dispatches_disabled_cleanup");

        private String value;

        Invoicing(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return value;
        }
    }
}
