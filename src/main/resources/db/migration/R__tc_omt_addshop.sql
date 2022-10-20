CREATE OR REPLACE FUNCTION testcases.tc_omt_addshop(p_name character varying, p_parent_shop character varying, p_parent_org character varying)
 RETURNS bigint
 LANGUAGE plpgsql
AS $function$

   DECLARE
     p_parent_shop_id  int = (select id from oms."ShopDO" where name=p_parent_shop);
     p_parent_org_id   int = (select id from oms."OrganizationDO" where name=p_parent_org);
     p_shop_id         int8;
     p_neworg_id       int8;

   BEGIN


      IF p_parent_shop is not null and p_parent_org is not null THEN
         raise exception 'tc_omt_addshop: you must call it with either a parent shop or a parent organization. Not both.';
      END IF;


      IF p_parent_shop_id is null and p_parent_org_id is null THEN
         raise exception 'tc_omt_addshop: The parent shop or organization does not exist.';
      END IF;
      
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
      
      SELECT id from oms."OrganizationDO" 
      WHERE "shopId"=(select id FROM oms."ShopDO" where name=p_name)
      AND case when p_parent_org_id is not null
             then "parentId"= p_parent_org_id 
          else    "parentId" = (select id from oms."OrganizationDO" where "shopId"=p_parent_shop_id)
          end
      INTO p_neworg_id;
      
      IF p_neworg_id IS NOT NULL THEN 
        return p_neworg_id;
      END IF;

      INSERT INTO oms."ShopDO"(
      "id", 
      "active", 
      "availabilityTolerance", 
      "isB2B", 
      "countryDefRef", 
      "mapArticleId", 
      "modificationDate", 
      "name", 
      "orderOptimizeDefRef", 
      "overwriteSelectedSupplierAllowed", 
      "parentRef", 
      "returnDeadline", 
      "shopName", 
      "shopOrderSequenceName", 
      "shopOrderValidationRef", 
      "hasSupplierPrefix", 
      "internalShopName", 
      "shopCustomerSequenceName", 
      "preferredSupplierOnly", 
      "orderProcessingDelay", 
      "shopOrderSequenceNumberFormatString", 
      "orderTokenValidityDuration", 
      "salesPriceCalculatorBeanDefRef", 
      "amountDaysForPaymentReminderMailOfPrepaidOrders",
      "amountDaysForAutoCancellationOfPrepaidOrders"
      )
      SELECT 
      nextval('oms."ShopDO_id_seq"'),    --id,    
      true,             --active,
      0,             --availabilityTolerance,
      false,               --isB2B,
      4,             --countryDefRef,
      true,             --mapArticleId,
      now(),                              --modificationDate,
      p_name,           --name,
      2,             --orderOptimizeDefRef,
      false,               --overwriteSelectedSupplierAllowed,
      p_parent_shop_id,             --parentRef,
      NULL,             --returnDeadline,
      p_name,           --shopName,
      NULL,             --shopOrderSequenceName,
      NULL,             --shopOrderValidationRef,
      false,               --hasSupplierPrefix,
      p_name,           --internalShopName,
      NULL,             --shopCustomerSequenceName,
      false,               --preferredSupplierOnly,
      NULL,             --orderProcessingDelay,
      NULL,             --shopOrderSequenceNumberFormatString,
      NULL,             --orderTokenValidityDuration,
      NULL,             --salesPriceCalculatorBeanDefRef,
      NULL,             --amountDaysForPaymentReminderMailOfPrepaidOrders,
      NULL              --amountDaysForAutoCancellationOfPrepaidOrders
      returning id INTO p_shop_id
      ;
      
      
      IF p_parent_org_id is not null THEN
         
           UPDATE oms."OrganizationDO" set "parentId"= p_parent_org_id where "shopId"=p_shop_id;
         
         END IF;


   /* covered by trigger push_shop2organization_tr 

      IF p_parent_shop_id is not null THEN
         p_parent_org_id = (select id from oms."OrganizationDO" where "shopId"= p_parent_shop_id);
      END IF;


      INSERT INTO oms."OrganizationDO"
      (
      "OrganizationTypeDefRef" ,
      "parentId" ,
      "shopId",
      name,
      description
      )
      SELECT 
      (select id from oms."OrganizationTypeDefDO" WHERE "organizationType" = 'SHOP'),
      p_parent_org_id,
      p_shop_id,
      p_name,
      'created by tc_omt_addshop at '||now();
   */



      Return (select id from oms."OrganizationDO"  where "shopId"=p_shop_id);


   END;
   $function$;

