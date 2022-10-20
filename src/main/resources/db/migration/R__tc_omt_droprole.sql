CREATE OR REPLACE FUNCTION testcases.tc_omt_droprole(p_role character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
       
       DECLARE
          role_id int8;
        
       BEGIN
            
          select id from oms."RoleDO" WHERE "name"=p_role INTO role_id;
          
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
          
            
          DELETE FROM oms."Role2RightDO"             WHERE "roleRef" = role_id;
          DELETE FROM oms."User2Role2OrganizationDO" WHERE "roleRef" = role_id;
          DELETE FROM oms."User2RoleDO"              WHERE "roleRef" = role_id;
          DELETE FROM oms."RoleDO"                   WHERE id = role_id;
  
       Return 'ok';
       END;
    $function$;

