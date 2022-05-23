package com.intershop.oms.test.businessobject.communication;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSDispatchPosition extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private Integer orderPositionNumber;

    private Integer dispatchedQuantity;

    private OMSProduct product;

    private List<OMSDispatchItem> items = null;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    private long id;
    private String articleNo;

    /**
     * CAUTION: This is NO DEEP COPY! Lists and the product are only referenced!
     * Only to be used to change dispatched quantity...
     *
     * @param dispatchPosition the source dispatch position
     */
    public OMSDispatchPosition(OMSDispatchPosition dispatchPosition)
    {
        setProduct(dispatchPosition.getProduct());
        setDispatchedQuantity(dispatchPosition.getDispatchedQuantity());
        setItems(dispatchPosition.getItems());
        this.propertyGroups = dispatchPosition.getPropertyGroups();
        setOrderPositionNumber(dispatchPosition.getOrderPositionNumber());
        setId(dispatchPosition.getId());
        setArticleNo(dispatchPosition.getArticleNo());
    }

    public OMSDispatchPosition()
    {
        // FIXME check, should be empty constructor
        setItems(Arrays.asList(new OMSDispatchItem()));
    }

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}