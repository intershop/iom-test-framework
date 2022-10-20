CREATE OR REPLACE FUNCTION testcases.tc_omt_addusr(p_usrname character varying, p_mail character varying, p_default_org character varying)
  RETURNS bigint AS
$BODY$

  DECLARE
  org_id int;
  usr_id int8;

  BEGIN

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

	  SELECT id FROM oms."UserDO" WHERE "accountName"=p_usrname INTO usr_id;

	  IF usr_id IS NOT NULL THEN

			  -- map user to default org
		  IF  p_default_org is not NULL AND length(p_default_org) > 0 THEN  

				SELECT id from oms."OrganizationDO"  where name= p_default_org INTO org_id;

				IF org_id is null THEN 
				  raise exception 'tc_omt_addusr called with a non existing organization (%)', p_default_org;
				END IF;

				INSERT INTO oms."User2OrganizationDO"
				("organizationRef",  "userRef") 
				SELECT org_id,usr_id WHERE NOT EXISTS
					 (select * from oms."User2OrganizationDO" where ("organizationRef",  "userRef")=(org_id,usr_id));

		  END IF;

		  return usr_id;

	  END IF;

		-- create user
	   INSERT INTO oms."UserDO"("id",
			"accountName",
			"active",
			"companyName",
			"firstName",
			"languageDefRef",
			"lastName",
			"modificationDate",
			"version",
			"email",
			"cryptedPassword",
			"hashSalt")
	   SELECT nextval('oms."UserDO_id_seq"'),              --"id",
		  p_usrname,                                       --"accountName",
		  true,                                            --"active",
		  '_unset_',                                       --"companyName",
		  'testcase',                                           --"firstName",
		  1,                                               --"languageDefRef",
		  p_usrname,                                         --"lastName",
		  now(),                                           --"modificationDate"
		  1,                                               --"version",
		  p_mail,                                          --"email",
		  'eFhnlmOwubLNjV3FyNzqL5N853XBhZqrLaipj85IPtY=',  --"cryptedPassword",
		  '8SgO9LdQLHtCZo7s2pAlhQ=='                      --"hashSalt"
		RETURNING id into usr_id;

		-- map user to default org
	   IF  p_default_org is not NULL AND length(p_default_org) > 0 THEN  

		  SELECT id from oms."OrganizationDO"  where name= p_default_org INTO org_id;

		  IF org_id is null THEN 
			raise exception 'tc_omt_addusr called with a non existing organization (%)', p_default_org;
		  END IF;

		  INSERT INTO oms."User2OrganizationDO"
		  ("organizationRef",  "userRef") 
		  SELECT org_id,usr_id;

	   END IF;

	  Return usr_id;
	  END;
  $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
