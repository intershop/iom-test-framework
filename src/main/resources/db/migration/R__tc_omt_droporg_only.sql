CREATE OR REPLACE FUNCTION testcases.tc_omt_droporg_only(p_org character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
         
 DECLARE
 org_id int;
 org_list varchar;

 BEGIN

  IF p_org = 'OmsSystem' THEN
	raise exception 'Are you kidding? Don''t ever think of dropping the root organization !!!';
  END IF;

  SELECT id FROM oms."OrganizationDO" WHERE "name" = p_org INTO org_id;

  IF org_id is null then 
	return 'ok';
  end if;
  
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
 

 DELETE FROM oms."User2Role2OrganizationDO" WHERE "organizationRef" = org_id;
 DELETE FROM oms."User2OrganizationDO"      WHERE "organizationRef" = org_id;
 DELETE FROM oms."OrganizationDO"           WHERE id = org_id;

 Return 'ok';
 END;
$function$;

