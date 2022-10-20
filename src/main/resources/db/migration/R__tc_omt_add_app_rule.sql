SET client_min_messages=error;

DROP FUNCTION IF EXISTS testcases.tc_omt_add_app_rule(p_shopname varchar);

DROP FUNCTION IF EXISTS  testcases.tc_omt_add_app_rule(p_shopname varchar, p_rulename varchar);

CREATE OR REPLACE FUNCTION testcases.tc_omt_add_app_rule(p_shopname varchar, p_rulename varchar DEFAULT 'General', p_manualapproval boolean DEFAULT FALSE)
  RETURNS integer
  LANGUAGE plpgsql
AS $function$
     
DECLARE
  shop_id     int8 = (SELECT id FROM oms."ShopDO" WHERE "shopName" = p_shopname);
  rule_id     int  = (SELECT id FROM oms."ApprovalTypeDefDO" WHERE name = p_rulename);
  app_rule_id int8 = (SELECT id FROM oms."Shop2Supplier2ApprovalTypeDefDO" 
                                WHERE "shopRef" = shop_id 
                                AND "approvalTypeDefRef" = rule_id
                     );
BEGIN

   IF shop_id IS NULL THEN 
     RAISE EXCEPTION 'tc_omt_add_app_rule called with a non existing shop (%)', p_shopname;
   END IF;

   IF rule_id IS NULL THEN 
     RAISE EXCEPTION 'tc_omt_add_app_rule called with a non existing rule (%)', p_rulename;
   END IF;

   IF app_rule_id IS NOT NULL THEN 
     RETURN app_rule_id;
   END IF;

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

   INSERT INTO oms."Shop2Supplier2ApprovalTypeDefDO"
   (
    id,
    active,
    "isAffectingDOSEonChange",
    "approvalRank",
    "approvalTypeDefRef",
    "isOrderTransmission",
    "supplierApprovalTypeName",
    "shopRef",
    "supplierRef",
    "paymentProviderRef",
    "decisionBeanDefRef",
    "sendOrderApproval",
    "supplierApprovalTypeDescription",
	"manualApproval"
   )
   SELECT 
    nextval('oms."Shop2Supplier2ApprovalTypeDefDO_id_seq"'),
    TRUE,
    FALSE,
    1,
    rule_id,
    TRUE,
    'Test Approval Rule: '|| p_rulename,
    shop_id,
    1,                               -- internal supplier
    NULL,
    NULL,                            -- without a decision bean, all orders go into the approval state
    FALSE,
    'TEST Approval Rule '||p_rulename||' Description',
	p_manualapproval
   RETURNING id into app_rule_id;

 RETURN app_rule_id;
 
END;
$function$;
