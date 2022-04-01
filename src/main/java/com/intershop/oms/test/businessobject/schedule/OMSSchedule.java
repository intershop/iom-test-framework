package com.intershop.oms.test.businessobject.schedule;

import java.util.Date;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

/**
 * Represents the current state of the schedule including basic and runtime configuration.&lt;br&gt; The basic configuration parameters have the suffix &#x60;Configured&#x60;.&lt;br&gt; The runtime configuration parameters have the suffix &#x60;Runtime&#x60;. If not set, the basic configuration will be used.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSSchedule extends OMSBusinessObject
{
    private Integer id;

    private Boolean activeConfigured;

    private Boolean activeRuntime;

    private Integer configId;

    private String key;

    private String cronConfigured;

    private String cronRuntime;

    private Boolean reactivateOnStartConfigured;

    private Boolean reactivateOnStartRuntime;

    private String jobName;

    private Date lastRun;

    private Date lockedSince;

    private Integer retryCount;

    private Integer maxNoOfRetriesConfigured;

    private Integer maxNoOfRetriesRuntime;

    private String retryDelayConfigured;

    private String retryDelayRuntime;

    private Date nextRetryDate;

    private Date fixedNextRun;

    private String description;

    public OMSSchedule id(Integer id)
    {
        this.id = id;
        return this;
    }

    public OMSSchedule activeConfigured(Boolean activeConfigured)
    {
        this.activeConfigured = activeConfigured;
        return this;
    }

    public OMSSchedule activeRuntime(Boolean activeRuntime)
    {
        this.activeRuntime = activeRuntime;
        return this;
    }

    public OMSSchedule configId(Integer configId)
    {
        this.configId = configId;
        return this;
    }

    public OMSSchedule key(String key)
    {
        this.key = key;
        return this;
    }

    public OMSSchedule cronConfigured(String cronConfigured)
    {
        this.cronConfigured = cronConfigured;
        return this;
    }

    public OMSSchedule cronRuntime(String cronRuntime)
    {
        this.cronRuntime = cronRuntime;
        return this;
    }

    public OMSSchedule reactivateOnStartConfigured(Boolean reactivateOnStartConfigured)
    {
        this.reactivateOnStartConfigured = reactivateOnStartConfigured;
        return this;
    }

    public OMSSchedule reactivateOnStartRuntime(Boolean reactivateOnStartRuntime)
    {
        this.reactivateOnStartRuntime = reactivateOnStartRuntime;
        return this;
    }

    public OMSSchedule jobName(String jobName)
    {
        this.jobName = jobName;
        return this;
    }

    public OMSSchedule lastRun(Date lastRun)
    {
        this.lastRun = lastRun;
        return this;
    }

    public OMSSchedule lockedSince(Date lockedSince)
    {
        this.lockedSince = lockedSince;
        return this;
    }

    public OMSSchedule retryCount(Integer retryCount)
    {
        this.retryCount = retryCount;
        return this;
    }

    public OMSSchedule maxNoOfRetriesConfigured(Integer maxNoOfRetriesConfigured)
    {
        this.maxNoOfRetriesConfigured = maxNoOfRetriesConfigured;
        return this;
    }

    public OMSSchedule maxNoOfRetriesRuntime(Integer maxNoOfRetriesRuntime)
    {
        this.maxNoOfRetriesRuntime = maxNoOfRetriesRuntime;
        return this;
    }

    public OMSSchedule retryDelayConfigured(String retryDelayConfigured)
    {
        this.retryDelayConfigured = retryDelayConfigured;
        return this;
    }

    public OMSSchedule retryDelayRuntime(String retryDelayRuntime)
    {
        this.retryDelayRuntime = retryDelayRuntime;
        return this;
    }

    public OMSSchedule nextRetryDate(Date nextRetryDate)
    {
        this.nextRetryDate = nextRetryDate;
        return this;
    }

    public OMSSchedule fixedNextRun(Date fixedNextRun)
    {
        this.fixedNextRun = fixedNextRun;
        return this;
    }

    public OMSSchedule description(String description)
    {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OMSSchedule schedule = (OMSSchedule) o;
        return Objects.equals(this.id, schedule.id) &&
                        Objects.equals(this.activeConfigured, schedule.activeConfigured) &&
                        Objects.equals(this.activeRuntime, schedule.activeRuntime) &&
                        Objects.equals(this.configId, schedule.configId) &&
                        Objects.equals(this.key, schedule.key) &&
                        Objects.equals(this.cronConfigured, schedule.cronConfigured) &&
                        Objects.equals(this.cronRuntime, schedule.cronRuntime) &&
                        Objects.equals(this.reactivateOnStartConfigured, schedule.reactivateOnStartConfigured) &&
                        Objects.equals(this.reactivateOnStartRuntime, schedule.reactivateOnStartRuntime) &&
                        Objects.equals(this.jobName, schedule.jobName) &&
                        Objects.equals(this.lastRun, schedule.lastRun) &&
                        Objects.equals(this.lockedSince, schedule.lockedSince) &&
                        Objects.equals(this.retryCount, schedule.retryCount) &&
                        Objects.equals(this.maxNoOfRetriesConfigured, schedule.maxNoOfRetriesConfigured) &&
                        Objects.equals(this.maxNoOfRetriesRuntime, schedule.maxNoOfRetriesRuntime) &&
                        Objects.equals(this.retryDelayConfigured, schedule.retryDelayConfigured) &&
                        Objects.equals(this.retryDelayRuntime, schedule.retryDelayRuntime) &&
                        Objects.equals(this.nextRetryDate, schedule.nextRetryDate) &&
                        Objects.equals(this.fixedNextRun, schedule.fixedNextRun) &&
                        Objects.equals(this.description, schedule.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, activeConfigured, activeRuntime, configId, key, cronConfigured, cronRuntime, reactivateOnStartConfigured, reactivateOnStartRuntime, jobName, lastRun, lockedSince, retryCount, maxNoOfRetriesConfigured, maxNoOfRetriesRuntime, retryDelayConfigured, retryDelayRuntime, nextRetryDate, fixedNextRun, description);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Schedule {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    activeConfigured: ").append(toIndentedString(activeConfigured)).append("\n");
        sb.append("    activeRuntime: ").append(toIndentedString(activeRuntime)).append("\n");
        sb.append("    configId: ").append(toIndentedString(configId)).append("\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
        sb.append("    cronConfigured: ").append(toIndentedString(cronConfigured)).append("\n");
        sb.append("    cronRuntime: ").append(toIndentedString(cronRuntime)).append("\n");
        sb.append("    reactivateOnStartConfigured: ").append(toIndentedString(reactivateOnStartConfigured)).append("\n");
        sb.append("    reactivateOnStartRuntime: ").append(toIndentedString(reactivateOnStartRuntime)).append("\n");
        sb.append("    jobName: ").append(toIndentedString(jobName)).append("\n");
        sb.append("    lastRun: ").append(toIndentedString(lastRun)).append("\n");
        sb.append("    lockedSince: ").append(toIndentedString(lockedSince)).append("\n");
        sb.append("    retryCount: ").append(toIndentedString(retryCount)).append("\n");
        sb.append("    maxNoOfRetriesConfigured: ").append(toIndentedString(maxNoOfRetriesConfigured)).append("\n");
        sb.append("    maxNoOfRetriesRuntime: ").append(toIndentedString(maxNoOfRetriesRuntime)).append("\n");
        sb.append("    retryDelayConfigured: ").append(toIndentedString(retryDelayConfigured)).append("\n");
        sb.append("    retryDelayRuntime: ").append(toIndentedString(retryDelayRuntime)).append("\n");
        sb.append("    nextRetryDate: ").append(toIndentedString(nextRetryDate)).append("\n");
        sb.append("    fixedNextRun: ").append(toIndentedString(fixedNextRun)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
