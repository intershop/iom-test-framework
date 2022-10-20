CREATE OR REPLACE FUNCTION testcases.tc_omt_dropshop(p_shopname character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
       
DECLARE
  shop_id int8;

BEGIN

  SELECT id from oms."ShopDO" WHERE "name"=p_shopname INTO shop_id;
  
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
  

  DELETE FROM product."ArchiveImportArticleDO" WHERE "shopRef" = shop_id;


	DELETE FROM oms."Shop2DunningLevelDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ContactMediumDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."OrderExportConfigDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."BarCodeGenDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2TaxTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ScoringSourceDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ReturnTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ResponseTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ResponseStateCodeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ReductionUnitDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2PaymentDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderValidationRuleDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderSupplierEvaluationRuleDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderOptimizeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderDeliveryDateTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ImmaterialItemActionDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2FinanceControllerDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2EntryRangeDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2DeliveryTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2DeliveryOptionDefDO" WHERE "shopRef" = shop_id;
	IF (admin.is_table('oms','Shop2DeliveryFormDefDO')) THEN
		DELETE FROM oms."Shop2DeliveryFormDefDO" WHERE "shopRef" = shop_id;
	END IF;
	DELETE FROM oms."Shop2CustomerPropertyTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ChargeTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2CarrierDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ArticleIdTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2AddressTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."PrepareDocumentConfigDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Import_ReturnReasonMapping" WHERE "shopRef" = shop_id;
	DELETE FROM oms."DocumentTransformerConfigDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ReturnStatesDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderStatesDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderPosStatesDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2OrderNoteHeaderDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2CountryDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ApprovalResponseDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2Supplier2ApprovalTypeDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2ReturnReasonDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ProcessControlConfigDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ShopCustomerScoreBandDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2PaymentProvider2PaymentDefDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."StockReservationDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."DocumentTransmissionDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."FileDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ReturnAnnouncementDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."CSVImportDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ReturnRequestDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."EventRegistryEntryDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."PartnerReferrerDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."Shop2SupplierDO" WHERE "shopRef" = shop_id;
	
	DELETE FROM oms."User2OrganizationDO" WHERE "organizationRef" IN (SELECT id FROM oms."OrganizationDO" WHERE "shopId" = shop_id);
	DELETE FROM oms."OrganizationDO" WHERE "shopId" = shop_id;
	
	DELETE FROM oms."ResponseDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."DispatchDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ReturnDO" WHERE "shopRef" = shop_id;
	DELETE FROM oms."ShopCustomerContactReasonDO" WHERE "shopRef" = shop_id;


	DELETE FROM customer."CustomerDO" WHERE "shopRef" = shop_id;
	DELETE FROM customer."CustomerExportConfigDO" WHERE "shopRef" = shop_id;


	DELETE FROM oms."OrderDO" WHERE "shopRef" = shop_id;

	DELETE FROM product."ArchiveImportArticleDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."Article2ShopDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleAmountClassDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleCollectionForAggregationUpdateDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleDependencyDef2ShopArticleDependencyDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleExportConfigDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleInfoArticleAO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleInfoSalesBulkPriceAO" WHERE "shopRef" = shop_id;
	
	DELETE FROM "ArticleInfoShop2DeliveryTypeDefAO"    WHERE "articleInfoShopRef" IN (SELECT ID FROM product."ArticleInfoShopAO" WHERE "shopRef" = shop_id);
	DELETE FROM "ArticleInfoShop2DeliveryOptionDefAO"  WHERE "articleInfoShopRef" IN (SELECT ID FROM product."ArticleInfoShopAO" WHERE "shopRef" = shop_id);
	DELETE FROM "ArticleInfoShop2SupplierAO"           WHERE "articleInfoShopRef" IN (SELECT ID FROM product."ArticleInfoShopAO" WHERE "shopRef" = shop_id);

	DELETE FROM product."ArticleInfoShopAO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleRegistrationForArticleExportDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleSalesPriceDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleShopFactAttributeDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleShopVariationDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ArticleSupplierShopDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."BlockedStockAO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ClassificationElement2ShopDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ExportBasicArticleDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."FactAttribute2AssortmentDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ImportArticleErrorDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ImportArticleUnknownManufacturerDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ImportAssortmentFactAttributeErrorDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ImportDatapackDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."InvalidImportArticleDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."Manufacturer2ShopDO" WHERE "shopRef" = shop_id;
	IF ( admin.is_table('product','PriceComparisonDO') ) THEN
		DELETE FROM product."PriceComparisonDO" WHERE "shopRef" = shop_id;
	END IF;
	DELETE FROM product."SalesPriceCalculationConfigurationDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ShopAtpAO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ShopClassificationSystemDO" WHERE "shopRef" = shop_id;
	DELETE FROM product."ShopVariationSystemDO" WHERE "shopRef" = shop_id;



	DELETE FROM oms."ShopDO" WHERE "id" = shop_id;

	DELETE FROM oms."User2RoleDO"
	WHERE NOT   EXISTS ( SELECT * FROM oms."User2Role2OrganizationDO"
				 WHERE ("roleRef", "userRef")=( "User2RoleDO"."roleRef", "User2RoleDO"."userRef"));

   Return 'ok';
   END;
$function$;
