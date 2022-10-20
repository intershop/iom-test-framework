CREATE OR REPLACE FUNCTION testcases.tc_omt_assert_u2r2suppliers(p_username character varying, p_rolename character varying, VARIADIC p_suppliernames character varying[])
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
              
              DECLARE
                 rec record;
                 usrid int8= (select id from oms."UserDO" where  "accountName" = p_username);
                 roleid int8= (select id from oms."RoleDO" where  "name" = p_rolename);
                 u2rid int8;
                 msg varchar= '';
               
              BEGIN
              
                IF usrid is null THEN
                  raise exception 'tc_omt_assert_u2r2suppliers: the user % does not exists',p_username; 
                END IF;
                
                IF roleid is null THEN
                  raise exception 'tc_omt_assert_u2r2suppliers: the role % does not exists',p_rolename; 
                END IF;
                
                u2rid := (select id from oms."User2RoleDO" where "roleRef"=roleid and "userRef"=usrid);
                
                IF u2rid is null THEN
                              raise exception 'tc_omt_assert_u2r2suppliers: the user2role assignment does not exists (% <-> %)',p_username,p_rolename; 
                END IF;
                
--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

                
                FOR rec in SELECT o.name as in_name,ck_name 
                           FROM oms."SupplierDO" o
                           JOIN (SELECT * FROM oms."User2Role2SupplierDO" WHERE  "user2RoleRef"= u2rid) u2o ON (u2o."supplierRef"=o.id)  
                           FULL OUTER JOIN
                           (SELECT unnest (p_suppliernames) ck_name)u
                           ON (o.name =u.ck_name)
                           WHERE o.name is null or ck_name is null
                LOOP
                
                  IF rec.in_name is null THEN
                    msg := msg || '  The  user-role pair is not assigned to the supplier '||rec.ck_name||E'.\n';
                  ELSE
                    msg := msg || '  Found extra supplier '||rec.in_name||E' for the user-role pair.\n';
                  
                  END IF;
                
                END LOOP;
              
                IF length(msg) > 0 THEN
                   msg := 'tc_omt_assert_u2r2suppliers (user '||p_username|| ', role '||p_rolename||E') :\n'||msg;
                   raise exception '%', msg;
                END IF;
              
              Return 'ok';
              END;
           $function$;

