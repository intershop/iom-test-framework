CREATE OR REPLACE FUNCTION testcases.tc_omt_dropsupplier(p_suppliername character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
       
   DECLARE
	  supplier_id int8;

   BEGIN

	  select id from oms."SupplierDO" WHERE "name"=p_suppliername INTO supplier_id;
	  
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
	  

	  DELETE FROM product."ArchiveImportArticleDO" WHERE "supplierRef" = supplier_id;


	  DELETE FROM oms."Shop2SupplierDO" WHERE "supplierRef" = supplier_id;
	  DELETE FROM oms."User2OrganizationDO" WHERE "organizationRef" IN (SELECT id FROM oms."OrganizationDO" WHERE "supplierId" = supplier_id);
	  DELETE FROM oms."OrganizationDO" WHERE "supplierId" = supplier_id;
	  DELETE FROM oms."SupplierDO" WHERE "id" = supplier_id;

	  DELETE FROM oms."User2RoleDO"
	  WHERE NOT EXISTS ( SELECT * FROM oms."User2Role2OrganizationDO"
					 WHERE ("roleRef", "userRef")=( "User2RoleDO"."roleRef", "User2RoleDO"."userRef"));

   Return 'ok';
   END;
$function$;

