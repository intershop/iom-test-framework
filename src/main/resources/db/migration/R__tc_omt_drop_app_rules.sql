CREATE OR REPLACE FUNCTION testcases.tc_omt_drop_app_rules(p_shopname character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$

  DECLARE

    shop_id int8 = (SELECT id FROM oms."ShopDO" WHERE "shopName" = p_shopname);

  BEGIN

    IF shop_id IS NULL THEN 
      RAISE EXCEPTION 'tc_omt_add_app_rule called with a non existing shop (%)', p_shopname;
    END IF;
 
 --to reduce the risk of FK exceptions during parallel tests.
 PERFORM pg_advisory_xact_lock(5,5);

    DELETE FROM oms."OrderApprovalReasonDO" WHERE "shop2Supplier2ApprovalTypeDefRef" IN 
      (SELECT id  FROM oms."Shop2Supplier2ApprovalTypeDefDO" WHERE "shopRef" = shop_id);
    
    DELETE FROM oms."Shop2Supplier2ApprovalTypeDefDO" WHERE "shopRef" = shop_id;
      
    RETURN 'ok';
  END;
$function$;

