package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSReadReturnRequestPosition extends OMSBusinessObject
{
    private List<OMSLink> links = null;

    private Integer positionNumber;

    private String productNumber;

    private String reason;

    private Integer quantity;

    private Long id;

    private String productName;

    private String supplierProductNumber;

    public OMSReadReturnRequestPosition links(List<OMSLink> links)
    {
        this.links = links;
        return this;
    }

    public OMSReadReturnRequestPosition addLinksItem(OMSLink linksItem)
    {
        if (this.links == null)
        {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
        return this;
    }

    public OMSReadReturnRequestPosition positionNumber(Integer positionNumber)
    {
        this.positionNumber = positionNumber;
        return this;
    }

    public OMSReadReturnRequestPosition productNumber(String productNumber)
    {
        this.productNumber = productNumber;
        return this;
    }

    public OMSReadReturnRequestPosition reason(String reason)
    {
        this.reason = reason;
        return this;
    }

    public OMSReadReturnRequestPosition quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSReadReturnRequestPosition id(Long id)
    {
        this.id = id;
        return this;
    }

    public OMSReadReturnRequestPosition productName(String productName)
    {
        this.productName = productName;
        return this;
    }

    public OMSReadReturnRequestPosition supplierProductNumber(String supplierProductNumber)
    {
        this.supplierProductNumber = supplierProductNumber;
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
        OMSReadReturnRequestPosition readReturnRequestPosition = (OMSReadReturnRequestPosition) o;
        return Objects.equals(this.links, readReturnRequestPosition.links) &&
                        Objects.equals(this.positionNumber, readReturnRequestPosition.positionNumber) &&
                        Objects.equals(this.productNumber, readReturnRequestPosition.productNumber) &&
                        Objects.equals(this.reason, readReturnRequestPosition.reason) &&
                        Objects.equals(this.quantity, readReturnRequestPosition.quantity) &&
                        Objects.equals(this.id, readReturnRequestPosition.id) &&
                        Objects.equals(this.productName, readReturnRequestPosition.productName) &&
                        Objects.equals(this.supplierProductNumber, readReturnRequestPosition.supplierProductNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(links, positionNumber, productNumber, reason, quantity, id, productName, supplierProductNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSReadReturnRequestPosition {\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("    positionNumber: ").append(toIndentedString(positionNumber)).append("\n");
        sb.append("    productNumber: ").append(toIndentedString(productNumber)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
        sb.append("    supplierProductNumber: ").append(toIndentedString(supplierProductNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
