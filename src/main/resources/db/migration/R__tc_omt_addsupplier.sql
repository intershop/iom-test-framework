CREATE OR REPLACE FUNCTION testcases.tc_omt_addsupplier(p_suppliername character varying, p_parent_supplier character varying, p_parent_org character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$

   DECLARE
     p_parent_supplier_id  int = (select id from oms."SupplierDO" where name=p_parent_supplier);
     p_parent_org_id   int = (select id from oms."OrganizationDO" where name=p_parent_org);
     p_supplier_id         int;
     p_neworg_id       int8;

   BEGIN


      IF p_parent_supplier is not null and p_parent_org is not null THEN
         raise exception 'tc_omt_addsupplier: you must call it with either a parent supplier or a parent organization. Not both.';
      END IF;


      IF p_parent_supplier_id is null and p_parent_org_id is null THEN
         raise exception 'tc_omt_addsupplier: The parent supplier or organization does not exist.';
      END IF;

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

            
            SELECT id from oms."OrganizationDO" 
            WHERE "supplierId"=(select id FROM oms."SupplierDO" where name=p_suppliername)
            AND case when p_parent_org_id is not null
                   then "parentId"= p_parent_org_id 
                else    "parentId" = (select id from oms."OrganizationDO" where "supplierId"=p_parent_supplier_id)
                end
            INTO p_neworg_id;
            
            IF p_neworg_id IS NOT NULL THEN 
              return p_neworg_id;
            END IF;



   INSERT INTO oms."SupplierDO"
   (
   "id", 
   "active", 
   "contentSupplier", 
   "countryCodeDefRef", 
   "countryDefRef", 
   "cutOffTime", 
   "deliveringSupplier", 
   "legal", 
   "modificationDate", 
   "name", 
   "supplierName", 
   "supportsReservation", 
   "supportsResponse", 
   "singlePositionArticle", 
   "internalSupplierName", 
   "parentSupplierRef", 
   "cleanParentOnImport", 
   "ignoreAdditionalPositions", 
   "businessObjectProcessingDelay", 
   "supplierTypeDefRef"
   )
   SELECT 

   nextval('oms."SupplierDO_id_seq"'), -- "id", 
   true,             -- "active", 
   false,               -- "contentSupplier", 
   1,             -- "countryCodeDefRef", 
   29,               -- "countryDefRef", 
   6 pm,             -- "cutOffTime", 
   true,             -- "deliveringSupplier", 
   true,             -- "legal", 
   now(),               -- "modificationDate", 
   p_suppliername,            -- "name", 
   p_suppliername,            -- "supplierName", 
   true,             -- "supportsReservation", 
   false,               -- "supportsResponse", 
   true,             -- "singlePositionArticle", 
   p_suppliername,            -- "internalSupplierName", 
   p_parent_supplier_id,         -- "parentSupplierRef", 
   false,               -- "cleanParentOnImport", 
   false,               -- "ignoreAdditionalPositions", 
   NULL,             -- "businessObjectProcessingDelay", 
   1             -- "supplierTypeDefRef"
   RETURNING id INTO p_supplier_id;

   IF p_parent_org_id is not null THEN
   
     UPDATE oms."OrganizationDO" set "parentId"= p_parent_org_id where "supplierId"=p_supplier_id;
   
   END IF;

   return (select id from oms."OrganizationDO"  where "supplierId"=p_supplier_id);


   END;
   $function$;

