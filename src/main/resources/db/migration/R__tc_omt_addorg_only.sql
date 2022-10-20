CREATE OR REPLACE FUNCTION testcases.tc_omt_addorg_only(p_org character varying, p_parent_org character varying)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
     
     DECLARE
       p_org_id int;
       parent_org_id int;
       parent_org_name varchar;

     BEGIN
     
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
          
          SELECT id,"parentId" FROM oms."OrganizationDO" 
          WHERE "name"=p_org 
          AND  "parentId"=(SELECT id FROM oms."OrganizationDO" WHERE "name"=p_parent_org )
          INTO p_org_id,parent_org_id;
          
          IF p_org_id IS NOT NULL THEN
            IF parent_org_id = (SELECT id FROM oms."OrganizationDO" WHERE "name"=p_parent_org ) THEN
               return p_org_id;
            ELSE
              parent_org_name := (SELECT name FROM oms."OrganizationDO" WHERE id=parent_org_id);
              raise exception 'tc_omt_addorg_only: trying to add an organization (%) with "%" as parent, but this org. already exists with "%" as parent.',p_org,p_parent_org,parent_org_name; 
            END IF;
          END IF;

     
       SELECT id from oms."OrganizationDO"  where name= p_parent_org INTO p_org_id;
               
       IF p_org_id is null THEN 
         raise exception 'tc_omt_addorg_only called with a non existing parent organization (%)', p_parent_org;
       END IF;
     
      INSERT INTO oms."OrganizationDO"
     (
      "OrganizationTypeDefRef" ,
      "parentId" ,
      name,
      description
     )
      SELECT 
       (select id from oms."OrganizationTypeDefDO" WHERE "organizationType" = 'ORGANIZATION_ONLY'),
       p_org_id,
       p_org,
       'created by tc_omt_addorg_only at '||now();

     Return p_org_id;
     END;
  $function$;

