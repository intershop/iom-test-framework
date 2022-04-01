package com.intershop.oms.test.businessobject.schedule;

import java.util.Date;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

/**
 * Represents the current state of the schedule.&lt;br&gt; For attributes having a runtime equivalent those - when set - will be used for the search instead of the basic configuration.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSScheduleState extends OMSBusinessObject
{
    private Integer id;

    private Boolean active;

    private Integer configId;

    private String key;

    private String cron;

    private Boolean reactivateOnStart;

    private String jobName;

    private Date lastRun;

    private Date lockedSince;

    private Integer retryCount;

    private Integer maxNoOfRetries;

    private String retryDelay;

    private Date nextRun;

    private String description;


    public OMSScheduleState id(Integer id)
    {
        this.id = id;
        return this;
    }

    public OMSScheduleState active(Boolean active)
    {
        this.active = active;
        return this;
    }

    public OMSScheduleState configId(Integer configId)
    {
        this.configId = configId;
        return this;
    }

    public OMSScheduleState key(String key)
    {
        this.key = key;
        return this;
    }

    public OMSScheduleState cron(String cron)
    {
        this.cron = cron;
        return this;
    }

    public OMSScheduleState reactivateOnStart(Boolean reactivateOnStart)
    {
        this.reactivateOnStart = reactivateOnStart;
        return this;
    }

    public OMSScheduleState jobName(String jobName)
    {
        this.jobName = jobName;
        return this;
    }

    public OMSScheduleState lastRun(Date lastRun)
    {
        this.lastRun = lastRun;
        return this;
    }

    public OMSScheduleState lockedSince(Date lockedSince)
    {
        this.lockedSince = lockedSince;
        return this;
    }

    public OMSScheduleState retryCount(Integer retryCount)
    {
        this.retryCount = retryCount;
        return this;
    }

    public OMSScheduleState maxNoOfRetries(Integer maxNoOfRetries)
    {
        this.maxNoOfRetries = maxNoOfRetries;
        return this;
    }

    public OMSScheduleState retryDelay(String retryDelay)
    {
        this.retryDelay = retryDelay;
        return this;
    }

    public OMSScheduleState nextRun(Date nextRun)
    {
        this.nextRun = nextRun;
        return this;
    }

    public OMSScheduleState description(String description)
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
        OMSScheduleState scheduleState = (OMSScheduleState) o;
        return Objects.equals(this.id, scheduleState.id) &&
                        Objects.equals(this.active, scheduleState.active) &&
                        Objects.equals(this.configId, scheduleState.configId) &&
                        Objects.equals(this.key, scheduleState.key) &&
                        Objects.equals(this.cron, scheduleState.cron) &&
                        Objects.equals(this.reactivateOnStart, scheduleState.reactivateOnStart) &&
                        Objects.equals(this.jobName, scheduleState.jobName) &&
                        Objects.equals(this.lastRun, scheduleState.lastRun) &&
                        Objects.equals(this.lockedSince, scheduleState.lockedSince) &&
                        Objects.equals(this.retryCount, scheduleState.retryCount) &&
                        Objects.equals(this.maxNoOfRetries, scheduleState.maxNoOfRetries) &&
                        Objects.equals(this.retryDelay, scheduleState.retryDelay) &&
                        Objects.equals(this.nextRun, scheduleState.nextRun) &&
                        Objects.equals(this.description, scheduleState.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, active, configId, key, cron, reactivateOnStart, jobName, lastRun, lockedSince, retryCount, maxNoOfRetries, retryDelay, nextRun, description);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ScheduleState {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    configId: ").append(toIndentedString(configId)).append("\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
        sb.append("    cron: ").append(toIndentedString(cron)).append("\n");
        sb.append("    reactivateOnStart: ").append(toIndentedString(reactivateOnStart)).append("\n");
        sb.append("    jobName: ").append(toIndentedString(jobName)).append("\n");
        sb.append("    lastRun: ").append(toIndentedString(lastRun)).append("\n");
        sb.append("    lockedSince: ").append(toIndentedString(lockedSince)).append("\n");
        sb.append("    retryCount: ").append(toIndentedString(retryCount)).append("\n");
        sb.append("    maxNoOfRetries: ").append(toIndentedString(maxNoOfRetries)).append("\n");
        sb.append("    retryDelay: ").append(toIndentedString(retryDelay)).append("\n");
        sb.append("    nextRun: ").append(toIndentedString(nextRun)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}