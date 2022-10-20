CREATE OR REPLACE FUNCTION testcases.tc_omt_assert_u2r2o(p_username character varying, p_rolename character varying, VARIADIC p_organizations character varying[])
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
          
          DECLARE
             rec record;
             usrid int8= (select id from oms."UserDO" where  "accountName" = p_username);
             roleid int8= (select id from oms."RoleDO" where  "name" = p_rolename);
             msg varchar= '';
           
          BEGIN
          
            IF usrid is null THEN
              raise exception 'tc_omt_assert_u2r2o: the user % does not exists',p_username; 
            END IF;
            
            IF roleid is null THEN
              raise exception 'tc_omt_assert_u2r2o: the role % does not exists',p_rolename; 
            END IF;
            
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);
            
            
            FOR rec in SELECT o.name as in_name,ck_name 
                       FROM oms."OrganizationDO" o
                       JOIN (SELECT * FROM oms."User2Role2OrganizationDO" WHERE  "userRef"= usrid AND "roleRef"= roleid) u2o ON (u2o."organizationRef"=o.id)  
                       FULL OUTER JOIN
                       (SELECT unnest (p_organizations) ck_name)u
                       ON (o.name =u.ck_name)
                       WHERE o.name is null or ck_name is null
            LOOP
            
              IF rec.in_name is null THEN
                msg := msg || '  The  user-role pair is not assigned to the organization '||rec.ck_name||E'.\n';
              ELSE
                msg := msg || '  Found extra organization '||rec.in_name||E' for the user-role pair.\n';
              
              END IF;
            
            END LOOP;
          
            IF length(msg) > 0 THEN
               msg := 'tc_omt_assert_u2r2o (user '||p_username|| ', role '||p_rolename||E') :\n'||msg;
               raise exception '%', msg;
            END IF;
          
          Return 'ok';
          END;
       $function$;

