package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSReadReturnRequestPosition extends OMSBusinessObject
{
    private List<OMSLink> links = new ArrayList<>();
    private Integer positionNumber;
    private String productNumber;
    private String reason;
    private Integer quantity;
    private Long id;
    private String productName;
    private String supplierProductNumber;
    private List<OMSCustomAttribute> customAttributes = new ArrayList<>();

    public Map<String, String> getCustomAttributesAsMap()
    {
        return customAttributes.stream()
                        .collect(
                                Collectors.toMap(
                                                OMSCustomAttribute::getKey,
                                                OMSCustomAttribute::getValue,
                                                (t, u) -> t, LinkedHashMap::new));
    }
    
    public OMSReadReturnRequestPosition addLinksItem(OMSLink linksItem)
    {
        links.add(linksItem);
        return this;
    }
}
