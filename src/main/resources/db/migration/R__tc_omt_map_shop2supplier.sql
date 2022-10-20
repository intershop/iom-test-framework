SET client_min_messages=error;

DROP FUNCTION IF EXISTS testcases.tc_omt_map_supplier2shop(p_suppliername character varying, p_shopname character varying);
--- obsolete
DROP FUNCTION IF EXISTS testcases.tc_omt_map_shop2supplier(p_shopname character varying, p_suppliername character varying);
/*
CREATE OR REPLACE FUNCTION testcases.tc_omt_map_shop2supplier(p_shopname character varying, p_suppliername character varying)
  RETURNS int8 
 AS $function$
 
 
    DECLARE
      p_shop_id     int8 =  (select id from oms."ShopDO"     where name=p_shopname);
      p_supplier_id int8 =  (select id from oms."SupplierDO" where name=p_suppliername);
      ret int8;
 
    BEGIN
 
 
       IF p_shop_id is null THEN
          raise exception 'tc_omt_map_supplier2shop called with a non existing shop: %',p_shopname;
       END IF;
 
 
       IF p_supplier_id is null  THEN
          raise exception 'tc_omt_map_supplier2shop called with a non existing supplier: %',p_suppliername;
       END IF;
       
       ret:= (select id from "Shop2SupplierDO" where ("shopRef", "supplierRef") = (p_shop_id, p_supplier_id));

       IF ret IS NULL THEN
       
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
       

			INSERT INTO "Shop2SupplierDO"(
				"id", 
				"active", 
				"modificationDate", 
				"reservationTimeInDays", 
				"sendOffTimeInHours", 
				"shopAccount", 
				"shopPassword", 
				"shopSupplierName", 
				"splitShipmentAllowed", 
				"supplierAccount", 
				"supplierPassword", 
				"supplierShopName", 
				"shopRef", 
				"supplierRef", 
				"supplierShopKey", 
				"sendOrderCoupon", 
				"supplierSupportsCOD", 
				"eolDetectionTimeInterval", 
				"resetStockOnImport", 
				"flatRateShipping", 
				"isDropShipment", 
				"immaterialArticleUrlValidityDuration", 
				"returnCarrierRef", 
				"leadCode", 
				"useSupplierArticleNoAsShopArticleNo", 
				"shopArticleNoPrefix")
			SELECT 
				nextval('"Shop2SupplierDO_id_seq"'), 			--"id", 
				TRUE, 											--"active", 
				now(), 											--"modificationDate", 
				0, 												--"reservationTimeInDays", 
				0, 												--"sendOffTimeInHours", 
				'', 											--"shopAccount", 
				'', 											--"shopPassword", 
				p_suppliername, 						--"shopSupplierName", 
				TRUE, 											--"splitShipmentAllowed", 
				'', 											--"supplierAccount", 
				'', 											--"supplierPassword", 
				p_shopname, 								--"supplierShopName", 
				p_shop_id,               						--"shopRef", 
				p_supplier_id,                		--"supplierRef", 
				null, 											--"supplierShopKey", 
				FALSE, 											--"sendOrderCoupon", 
				TRUE, 											--"supplierSupportsCOD", 
				null, 											--"eolDetectionTimeInterval", 
				FALSE, 											--"resetStockOnImport", 
				null, 											--"flatRateShipping", 
				FALSE, 											--"isDropShipment", 
				null, 											--"immaterialArticleUrlValidityDuration", 
				null,									--"returnCarrierRef", 
				null, 											--"leadCode", 
				null, 											--"useSupplierArticleNoAsShopArticleNo", 
				null											--"shopArticleNoPrefix")
			 RETURNING id INTO ret;
	END IF;
	
	RETURN ret;
	
END;

$function$
language plpgsql;
*/