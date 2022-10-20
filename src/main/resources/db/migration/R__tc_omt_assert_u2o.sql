CREATE OR REPLACE FUNCTION testcases.tc_omt_assert_u2o(p_username character varying, VARIADIC p_organizations character varying[])
 RETURNS character varying
 LANGUAGE plpgsql
AS $$

/*
Will raise an error when the organisation list does not match exactly the one of the user
*/

  DECLARE
	 rec record;
	 usrid int8= (select id from oms."UserDO" where  "accountName" = p_username);
	 msg varchar= '';

  BEGIN

	IF usrid is null THEN
	  raise exception 'tc_omt_assert_u2o: the user % does not exists',p_username; 
	END IF;
	
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);


	FOR rec in SELECT o.name as in_name,ck_name 
			   FROM oms."OrganizationDO" o
			   JOIN (SELECT * FROM oms."User2OrganizationDO" WHERE  "userRef"= usrid) u2o ON (u2o."organizationRef"=o.id)  
			   FULL OUTER JOIN
			   (SELECT unnest (p_organizations) ck_name)u
			   ON (o.name =u.ck_name)
			   WHERE o.name is null or ck_name is null
	LOOP

	  IF rec.in_name is null THEN
		msg := msg || '  The  user is not assigned to the organization '||rec.ck_name||E'.\n';
	  ELSE
		msg := msg || '  Found extra organization '||rec.in_name||E' assigned to the user.\n';

	  END IF;

	END LOOP;

	IF length(msg) > 0 THEN
	   msg := 'tc_omt_assert_u2o (user '||p_username|| E') :\n'||msg;
	   raise exception '%', msg;
	END IF;

  Return 'ok';
  END;
$$;

