SET client_min_messages=error;


DROP FUNCTION IF EXISTS testcases.tc_omt_add_usr2rol2org(p_usr character varying, p_role character varying, p_org character varying);

CREATE OR REPLACE FUNCTION testcases.tc_omt_add_usr2rol2org(p_usr character varying, p_role character varying, p_org character varying, force boolean default false)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$

   DECLARE
	  org_id int = (select id from oms."OrganizationDO" where name=p_org);
	  rol_id int8= (select id from oms."RoleDO" where name=p_role);
	  usr_id int8= (select id from oms."UserDO" where "accountName" =p_usr);

   BEGIN

	IF usr_id is null THEN
	  raise exception 'tc_omt_add_usr2rol2org called with a non existing accountName: %', p_usr;
	END IF;

	IF org_id is null THEN
	  raise exception 'tc_omt_add_usr2rol2org called with a non existing organization: %', p_org;
	END IF;

	IF rol_id is null THEN
	  raise exception 'tc_omt_add_usr2rol2org called with a non existing role: %', p_role;
	END IF;

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
	
	IF force THEN
	
		INSERT INTO oms."User2OrganizationDO" ("organizationRef", "userRef")
		SELECT  org_id, usr_id
		WHERE NOT EXISTS (select * from oms."User2OrganizationDO"  where ("organizationRef", "userRef")= (org_id, usr_id));
	
	END IF;

	INSERT INTO oms."User2RoleDO" (id, "roleRef", "userRef")
	SELECT nextval('oms."User2RoleDO_id_seq"'), rol_id, usr_id
	WHERE NOT EXISTS (select * from oms."User2RoleDO"  where ("roleRef", "userRef")= (rol_id, usr_id));

	INSERT INTO oms."User2Role2OrganizationDO" ("organizationRef", "roleRef", "userRef")
	SELECT org_id, rol_id, usr_id
	WHERE NOT EXISTS (select * from oms."User2Role2OrganizationDO"  where ("organizationRef", "roleRef", "userRef")= (org_id, rol_id, usr_id));

   Return 'ok';
   END;
$function$;
