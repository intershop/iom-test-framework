package com.intershop.oms.test.businessobject.transmission;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.intershop.oms.test.businessobject.OMSLink;
import com.intershop.oms.test.businessobject.OMSObjectReference;

/**
 * OMSTransmission
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class OMSTransmission
{
    private String id;
    private OMSTransmissionTypeGroup transmissionTypeGroup;
    private String transmissionType;
    private OMSTransmissionStatus status;
    private OMSTransmissionResponseStatus responseStatus;
    private OffsetDateTime creationDate;
    private OffsetDateTime modificationDate;
    private List<OMSObjectReference> orderReferences = new ArrayList<>();
    private Long shopId;
    private String shopName;
    private Long supplierId;
    private String supplierName;
    private OMSReceiverType receiverType;
    private Integer retryCount;
    private OffsetDateTime retryDate;
    private OffsetDateTime nextRetryDate;
    private String errorText;
    private OMSObjectReference invoiceReference;
    private Collection<OMSLink> links = new ArrayList<OMSLink>();

    /**
     * Note: for convenience reasons, transmissions are treated as entities here
     * - hence they are considered equal when they have the same id. Other
     * attributes are not considered.
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj != null && obj instanceof OMSTransmission && StringUtils.equals(this.id, ((OMSTransmission)obj).id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

}
