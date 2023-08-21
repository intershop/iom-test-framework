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
    
    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition links(List<OMSLink> links)
    {
        this.links = links;
        return this;
    }

    public OMSReadReturnRequestPosition addLinksItem(OMSLink linksItem)
    {
        links.add(linksItem);
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition positionNumber(Integer positionNumber)
    {
        this.positionNumber = positionNumber;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition productNumber(String productNumber)
    {
        this.productNumber = productNumber;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition reason(String reason)
    {
        this.reason = reason;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition id(Long id)
    {
        this.id = id;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition productName(String productName)
    {
        this.productName = productName;
        return this;
    }

    /**
     * @deprecated use set...()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSReadReturnRequestPosition supplierProductNumber(String supplierProductNumber)
    {
        this.supplierProductNumber = supplierProductNumber;
        return this;
    }
}
