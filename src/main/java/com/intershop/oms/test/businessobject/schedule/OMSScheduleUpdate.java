package com.intershop.oms.test.businessobject.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intershop.oms.test.businessobject.OMSBusinessObject;

/**
 * Attributes of a schedule a runtime configuration can be set for.&lt;br&gt; The runtime configuration will be then used instead of the basic configuration.&lt;br&gt; To reset an attribute back to the  basic configurations use the &#x60;resetList&#x60;.&lt;br&gt; &#x60;Note&#x60; that &#x60;lockedSinceUse&#x60; can&#39;t be set.&lt;br&gt; &#x60;Note&#x60; that &#x60;fixedNextRun&#x60; has no basic configuration.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSScheduleUpdate extends OMSBusinessObject
{
    private Boolean active;

    private String cron;

    private Boolean reactivateOnStart;

    private Integer maxNoOfRetries;

    private String retryDelay;

    private Date fixedNextRun;

    private Boolean removeLock;

    /**
     * Gets or Sets resetList
     */
    public enum ResetListEnum
    {
        ACTIVE("active"),

        CRON("cron"),

        REACTIVATEONSTART("reactivateOnStart"),

        MAXNOOFRETRIES("maxNoOfRetries"),

        RETRYDELAY("retryDelay"),

        FIXEDNEXTRUN("fixedNextRun");

        private String value;

        ResetListEnum(String value)
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

        public static ResetListEnum fromValue(String value)
        {
            for (ResetListEnum b : ResetListEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        public static class Adapter extends TypeAdapter<ResetListEnum>
        {
            @Override
            public void write(final JsonWriter jsonWriter, final ResetListEnum enumeration) throws IOException
            {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public ResetListEnum read(final JsonReader jsonReader) throws IOException
            {
                String value = jsonReader.nextString();
                return ResetListEnum.fromValue(value);
            }
        }
    }

    private List<ResetListEnum> resetList = null;

    public OMSScheduleUpdate active(Boolean active)
    {
        this.active = active;
        return this;
    }

    public OMSScheduleUpdate cron(String cron)
    {
        this.cron = cron;
        return this;
    }

    public OMSScheduleUpdate reactivateOnStart(Boolean reactivateOnStart)
    {
        this.reactivateOnStart = reactivateOnStart;
        return this;
    }

    public OMSScheduleUpdate maxNoOfRetries(Integer maxNoOfRetries)
    {
        this.maxNoOfRetries = maxNoOfRetries;
        return this;
    }

    public OMSScheduleUpdate retryDelay(String retryDelay)
    {
        this.retryDelay = retryDelay;
        return this;
    }

    public OMSScheduleUpdate fixedNextRun(Date fixedNextRun)
    {
        this.fixedNextRun = fixedNextRun;
        return this;
    }

    public OMSScheduleUpdate removeLock(Boolean removeLock)
    {
        this.removeLock = removeLock;
        return this;
    }

    public OMSScheduleUpdate resetList(List<ResetListEnum> resetList)
    {
        this.resetList = resetList;
        return this;
    }

    public OMSScheduleUpdate addResetListItem(ResetListEnum resetListItem)
    {
        if (this.resetList == null)
        {
            this.resetList = new ArrayList<>();
        }
        this.resetList.add(resetListItem);
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
        OMSScheduleUpdate scheduleUpdate = (OMSScheduleUpdate) o;
        return Objects.equals(this.active, scheduleUpdate.active) &&
                        Objects.equals(this.cron, scheduleUpdate.cron) &&
                        Objects.equals(this.reactivateOnStart, scheduleUpdate.reactivateOnStart) &&
                        Objects.equals(this.maxNoOfRetries, scheduleUpdate.maxNoOfRetries) &&
                        Objects.equals(this.retryDelay, scheduleUpdate.retryDelay) &&
                        Objects.equals(this.fixedNextRun, scheduleUpdate.fixedNextRun) &&
                        Objects.equals(this.removeLock, scheduleUpdate.removeLock) &&
                        Objects.equals(this.resetList, scheduleUpdate.resetList);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(active, cron, reactivateOnStart, maxNoOfRetries, retryDelay, fixedNextRun, removeLock, resetList);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ScheduleUpdate {\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    cron: ").append(toIndentedString(cron)).append("\n");
        sb.append("    reactivateOnStart: ").append(toIndentedString(reactivateOnStart)).append("\n");
        sb.append("    maxNoOfRetries: ").append(toIndentedString(maxNoOfRetries)).append("\n");
        sb.append("    retryDelay: ").append(toIndentedString(retryDelay)).append("\n");
        sb.append("    fixedNextRun: ").append(toIndentedString(fixedNextRun)).append("\n");
        sb.append("    removeLock: ").append(toIndentedString(removeLock)).append("\n");
        sb.append("    resetList: ").append(toIndentedString(resetList)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}