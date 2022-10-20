SET client_min_messages=error;

DROP FUNCTION IF EXISTS testcases.tc_omt_map_shop2tax(p_shopname character varying, p_taxname character varying);

CREATE OR REPLACE FUNCTION testcases.tc_omt_map_shop2tax(p_shopname character varying, p_taxname character varying DEFAULT 'NoTax', p_taxtype character varying DEFAULT 'NO_TAX')
  RETURNS int8 
  LANGUAGE plpgsql
 AS $function$
 
 
    DECLARE
      shop_id     int8 = (SELECT id FROM oms."ShopDO"      WHERE name=p_shopname);
      tax_id      int8 = (SELECT id FROM oms."TaxTypeDefDO" WHERE "taxType" = p_taxtype);
      shop2tax_id int8 = (SELECT id FROM oms."Shop2TaxTypeDefDO" 
                                    WHERE "shopRef" = shop_id 
                                    AND "taxTypeDefRef" = tax_id
                         );
 
    BEGIN

      IF shop_id IS NULL THEN
        RAISE EXCEPTION 'tc_omt_map_shop2tax called with a non existing shop (%)', p_shopname;
      END IF;
 
 
      IF tax_id IS NULL THEN
        RAISE EXCEPTION  'tc_omt_map_shop2tax called with a non existing tax (%)', p_taxtype;
      END IF;
       
      IF shop2tax_id IS NOT NULL THEN 
        RETURN shop2tax_id;
      END IF;

      INSERT INTO oms."Shop2TaxTypeDefDO"
      (
        id,
        "shopTaxTypeName",
        "taxTypeDefRef",
        "shopRef",
        "modificationDate"
      )      
      SELECT 
        nextval('oms."Shop2TaxTypeDefDO_id_seq"'),
        p_taxname,
        tax_id,
        shop_id,
        now()
      RETURNING id into shop2tax_id;

 RETURN shop2tax_id;
 
END;
$function$;