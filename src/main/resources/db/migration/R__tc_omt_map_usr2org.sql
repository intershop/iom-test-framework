CREATE OR REPLACE FUNCTION testcases.tc_omt_map_usr2org(p_usrname character varying, p_org character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
       
   DECLARE
   usr_id int;
   org_id int;

   BEGIN

	SELECT id FROM oms."UserDO" WHERE "accountName" = p_usrname INTO usr_id;

	IF usr_id is null then 
	   raise exception 'tc_omt_map_usr2org called with a non existing user (%)', p_usrname;
	END IF;

	SELECT id from oms."OrganizationDO"  where name= p_org INTO org_id;

	IF org_id is null THEN 
	  raise exception 'tc_omt_map_usr2org called with a non existing  organization (%)', p_org;
	END IF;

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);


	INSERT INTO oms."User2OrganizationDO"
			 ("organizationRef",  "userRef") 
	SELECT org_id,usr_id
	WHERE NOT EXISTS (select * from oms."User2OrganizationDO" WHERE  ("organizationRef",  "userRef")= (org_id,usr_id));

   Return 'ok';
   END;
$function$;

