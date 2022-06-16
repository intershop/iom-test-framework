package com.intershop.oms.test.businessobject.transmission;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class OMSTransmissionFilter
{
    private OffsetDateTime creationDateFrom;
    private OffsetDateTime creationDateTo;
    private OMSTransmissionTypeGroup transmissionTypeGroup;
    private List<String> transmissionTypeNames = new ArrayList<>();
    private List<OMSTransmissionStatus> transmissionStatuses = new ArrayList<>();
    private List<OMSTransmissionResponseStatus> transmissionResponseStatuses = new ArrayList<>();
    private List<OMSReceiverType> receiverTypes = new ArrayList<>();
    private List<Long> shopIds = new ArrayList<>();
    private List<Long> supplierIds = new ArrayList<>();
    private List<String> shopOrderNumbers = new ArrayList<>();
    private List<String> invoiceNumbers = new ArrayList<>();
}
