package com.intershop.oms.test.util;

/**
 * Class provides constants related to default jobs/schedules in IOM
 */
public interface OMSPlatformSchedules
{
    interface Invoicing {
        String AGGREGATE_DISPATCH_INVOICES_DAILY         = "aggregate_invoices4dispatches_daily";
        String AGGREGATE_DISPATCH_INVOICES_WEEKLY        = "aggregate_invoices4dispatches_weekly";
        String AGGREGATE_DISPATCH_INVOICES_MONTHLY       = "aggregate_invoices4dispatches_monthly";
        String AGGREGATE_DISPATCH_INVOICES_CLEANUP_WEEKLY = "aggregate_invoices4dispatches_weekly_cleanup";
        String AGGREGATE_DISPATCH_INVOICES_CLEANUP_DISABLED = "aggregate_invoices4dispatches_disabled_cleanup";
    }
}
