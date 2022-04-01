package com.intershop.oms.test.businessobject.transmission;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSLink;

/**
 * OMSTransmission
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSTransmission
{
    private String id = null;

    private OMSTransmissionType transmissionType = null;

    private String transmissionSubtype = null;

    private OMSTransmissionStatus status = null;

    private OMSTransmissionResponseStatus responseStatus = null;

    private OffsetDateTime creationDate = null;

    private OffsetDateTime modificationDate = null;

    private Long orderId = null;

    private String shopOrderNumber = null;

    private Long shopId = null;

    private String shopName = null;

    private Long supplierId = null;

    private String supplierName = null;

    private OMSReceiverType receiverType = null;

    private Integer retryCount = null;

    private OffsetDateTime retryDate = null;

    private OffsetDateTime nextRetryDate = null;

    private String errorText = null;

    //since 1_1 only
    private Collection<OMSLink> links = new ArrayList<OMSLink>();


    public OMSTransmission id(String id)
    {
        this.id = id;
        return this;
    }

    public OMSTransmission transmissionType(OMSTransmissionType transmissionType)
    {
        this.transmissionType = transmissionType;
        return this;
    }

    public OMSTransmission transmissionSubtype(String transmissionSubtype)
    {
        this.transmissionSubtype = transmissionSubtype;
        return this;
    }

    public OMSTransmission status(OMSTransmissionStatus status)
    {
        this.status = status;
        return this;
    }

    public OMSTransmission responseStatus(OMSTransmissionResponseStatus responseStatus)
    {
        this.responseStatus = responseStatus;
        return this;
    }

    public OMSTransmission creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSTransmission modificationDate(OffsetDateTime modificationDate)
    {
        this.modificationDate = modificationDate;
        return this;
    }

    public OMSTransmission orderId(Long orderId)
    {
        this.orderId = orderId;
        return this;
    }

    public OMSTransmission shopOrderNumber(String shopOrderNumber)
    {
        this.shopOrderNumber = shopOrderNumber;
        return this;
    }
    public OMSTransmission shopId(Long shopId)
    {
        this.shopId = shopId;
        return this;
    }

    public OMSTransmission shopName(String shopName)
    {
        this.shopName = shopName;
        return this;
    }

    public OMSTransmission supplierId(Long supplierId)
    {
        this.supplierId = supplierId;
        return this;
    }

    public OMSTransmission supplierName(String supplierName)
    {
        this.supplierName = supplierName;
        return this;
    }

    public OMSTransmission receiverType(OMSReceiverType receiverType)
    {
        this.receiverType = receiverType;
        return this;
    }

    public OMSTransmission retryCount(Integer retryCount)
    {
        this.retryCount = retryCount;
        return this;
    }

    public OMSTransmission retryDate(OffsetDateTime retryDate)
    {
        this.retryDate = retryDate;
        return this;
    }

    public OMSTransmission nextRetryDate(OffsetDateTime nextRetryDate)
    {
        this.nextRetryDate = nextRetryDate;
        return this;
    }

    public OMSTransmission errorText(String errorText)
    {
        this.errorText = errorText;
        return this;
    }

    public OMSTransmission links(Collection<OMSLink> links)
    {
        this.links = links;
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
      OMSTransmission transmission = (OMSTransmission) o;

      // OMSLink comparison: the 1_1 Links collection contains a single element.
      OMSLink myLink = null;
      OMSLink theirLink = null;

      if (this.links.iterator().hasNext())
      {
          myLink = this.links.iterator().next();
      }
      if (transmission.links.iterator().hasNext())
      {
          theirLink = transmission.links.iterator().next();
      }

      boolean checkLink = ( (this.links.isEmpty() && transmission.links.isEmpty() )
                           || (null == myLink && null == theirLink)
                           || (myLink.equals(theirLink))
                          );

      return checkLink &&
          Objects.equals(this.id, transmission.id) &&
          Objects.equals(this.transmissionType, transmission.transmissionType) &&
          Objects.equals(this.transmissionSubtype, transmission.transmissionSubtype) &&
          Objects.equals(this.status, transmission.status) &&
          Objects.equals(this.responseStatus, transmission.responseStatus) &&
          Objects.equals(this.creationDate, transmission.creationDate) &&
          Objects.equals(this.modificationDate, transmission.modificationDate) &&
          Objects.equals(this.orderId, transmission.orderId) &&
          Objects.equals(this.shopOrderNumber, transmission.shopOrderNumber) &&
          Objects.equals(this.shopId, transmission.shopId) &&
          Objects.equals(this.shopName, transmission.shopName) &&
          Objects.equals(this.supplierId, transmission.supplierId) &&
          Objects.equals(this.supplierName, transmission.supplierName) &&
          Objects.equals(this.receiverType, transmission.receiverType) &&
          Objects.equals(this.retryCount, transmission.retryCount) &&
          Objects.equals(this.retryDate, transmission.retryDate) &&
          Objects.equals(this.nextRetryDate, transmission.nextRetryDate) &&
          Objects.equals(this.errorText, transmission.errorText);
    }

    @Override
    public int hashCode()
    {
      return java.util.Objects.hash(id, transmissionType, transmissionSubtype, status, responseStatus, creationDate, modificationDate, orderId, shopOrderNumber, shopId, shopName, supplierId, supplierName, receiverType, retryCount, retryDate, nextRetryDate, errorText, links);
    }

    public boolean isContainedById(Collection<OMSTransmission> transmissionCollection)
    {
        if (transmissionCollection == null)
        {
            throw new NullPointerException("transmissionCollection must not be null");
        }

        for (OMSTransmission collectionTransmission : transmissionCollection)
        {
            if (collectionTransmission.getId().equals(getId()))
            {
                return true;
            }
        }
        return false;
    }
}
