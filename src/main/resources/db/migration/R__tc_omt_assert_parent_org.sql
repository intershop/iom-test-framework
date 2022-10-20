CREATE OR REPLACE FUNCTION testcases.tc_omt_assert_parent_org(p_orgname character varying, p_parentname character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
          
          DECLARE
             mycheck int;
             o_id int= (select id from oms."OrganizationDO" where  "name" = p_orgname);
             p_id int= (select id from oms."OrganizationDO" where  "name" = p_parentname);
             
             msg varchar= '';
           
          BEGIN
          
            IF o_id is null THEN
              raise exception 'tc_omt_assert_parent_org: the organization % does not exists',p_orgname; 
            END IF;
            
            IF p_id is null THEN
              raise exception 'tc_omt_assert_parent_org: the parent organization % does not exists',p_parentname; 
            END IF;
            
            
            select count(*) from oms."OrganizationDO" where id=o_id and "parentId"=p_id 
            INTO mycheck; 
            
            IF mycheck <> 1 THEN
               raise exception 'tc_omt_assert_parent_org: % is not the parent organization of %',p_parentname,p_orgname; 
            END IF;
            
          Return 'ok';
          END;
       $function$;

