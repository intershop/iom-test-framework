CREATE OR REPLACE FUNCTION testcases.tc_has_same_order_prices(shop_id int8, orderno_1 text, orderno_2 text)
 RETURNS boolean
 LANGUAGE plpgsql
AS $function$

DECLARE

pos_count int;
pos_count_distinct int;
ord_count int;
ord_count_distinct int;
charge_count int;
charge_count_distinct int;

BEGIN
	SELECT count(*) FROM
	(
		SELECT distinct
		 "articleRef",
		 "shopPosGross",
		 "shopPosNet",
		 "shopPosNetDiscounted",
		 "shopPosGrossDiscounted",
		 "recalculatedShopPosNetDiscounted",
		 "recalculatedShopPosGrossDiscounted"
		FROM oms."OrderPosDO"
		WHERE "orderRef" IN (select id from "OrderDO" where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2))
	)foo
	INTO pos_count_distinct;

	SELECT count(*)
	FROM oms."OrderPosDO"
	WHERE "orderRef" IN (select id from "OrderDO" where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2))
	INTO pos_count;


	SELECT count(*) FROM
	(
		SELECT distinct
		 "shopSubTotalGross",
		 "shopSubTotalNet",
		 "shopTotalGross",
		 "shopTotalNet",
		 "recalculatedShopSubTotalGross",
		 "recalculatedShopSubTotalNet",
		 "recalculatedShopTotalGross",
		 "recalculatedShopTotalNet",
		 "shopSubTotalGrossDiscounted",
		 "shopSubTotalNetDiscounted",
		 "recalculatedShopSubTotalGrossDiscounted",
		 "recalculatedShopSubTotalNetDiscounted"
		FROM oms."OrderDO"
		 where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2)
	)foo
	INTO ord_count_distinct;

	SELECT count(*) 
	FROM oms."OrderDO"
		 where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2)
	INTO ord_count;
	
	
	SELECT  count(*) FROM
	(
		SELECT distinct "chargeTypeDefRef", "shopSumGross", "shopSumNet",  "shopSumNetDiscounted", "shopSumGrossDiscounted"
	FROM oms."OrderChargeDO"
	WHERE "orderRef" IN (select id from "OrderDO" where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2))
	)foo
	INTO charge_count_distinct;

	SELECT  count(*) 
	FROM oms."OrderChargeDO"
	WHERE "orderRef" IN (select id from "OrderDO" where "shopRef"= shop_id AND ("shopOrderNo" = orderno_1 OR "shopOrderNo" = orderno_2))
	INTO charge_count;

	
	RETURN 	ord_count=2 
				AND ord_count_distinct=1 
				AND pos_count = 2*pos_count_distinct
				AND charge_count = 2*charge_count_distinct;

END;
$function$;