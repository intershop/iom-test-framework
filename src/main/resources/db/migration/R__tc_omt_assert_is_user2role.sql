CREATE OR REPLACE FUNCTION testcases.tc_omt_assert_is_user2role(p_username character varying, p_rolename character varying)
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
                  raise exception 'tc_omt_assert_is_user2role: the user % does not exists',p_username; 
                END IF;
                
                IF roleid is null THEN
                  raise exception 'tc_omt_assert_is_user2role: the role % does not exists',p_rolename; 
                END IF;
                
                u2rid := (select id from oms."User2RoleDO" where "roleRef"=roleid and "userRef"=usrid);
                
                IF u2rid is null THEN
                              raise exception 'tc_omt_assert_is_user2role: the user2role assignment does not exist (% <-> %)',p_username,p_rolename; 
                END IF;
              
              Return 'ok';
              END;
           $function$;

