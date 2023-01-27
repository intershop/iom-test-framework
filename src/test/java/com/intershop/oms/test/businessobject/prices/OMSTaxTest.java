package com.intershop.oms.test.businessobject.prices;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class OMSTaxTest
{
    @Test
    void testNewObjectOnChangedAmount() throws Exception
    {
        OMSTax tax1 = new OMSTax("test", BigDecimal.valueOf(19), BigDecimal.valueOf(19), "DE");
        OMSTax tax2 = tax1.amount(BigDecimal.valueOf(38));
        assertTrue(tax1.getAmount().compareTo(tax2.getAmount()) < 0);
    }
}
