
CREATE OR REPLACE FUNCTION testcases.tc_omt_createrole (p_role varchar, VARIADIC p_perms int[]) 
       returns int8 AS $$
       
DECLARE
   role_id int8;
   rightlist_2add int[] = '{}';
   rec record;

BEGIN
       
	SELECT id FROM oms."RoleDO" WHERE "name"=p_role
	INTO role_id;

	IF role_id IS NULL THEN
		INSERT INTO oms."RoleDO" (id,name,description,version)
		SELECT  nextval('oms."RoleDO_id_seq"'), p_role,'test case role '||p_role,1
		RETURNING id  INTO role_id;
		rightlist_2add =  coalesce ( p_perms,'{}');
	ELSE

		rightlist_2add = ( select coalesce ( array_agg(unnest),'{}') from 
									( select unnest(p_perms) EXCEPT select "rightDefRef" FROM oms."Role2RightDO" WHERE "roleRef" = role_id )foo
							  );
	END IF;

	FOR rec IN SELECT rightlist_2add[i] as p FROM generate_subscripts(rightlist_2add, 1) g(i)
	loop

		IF  rec.p IS NOT NULL THEN
	
			  IF false = EXISTS(select * from oms."RightDefDO" where id= rec.p) THEN
				  raise exception 'tc_omt_createrole called with an non existing right (%)', rec.p;
			  END IF;

			  INSERT INTO  oms."Role2RightDO" ( "rightDefRef", "roleRef")
			  SELECT  rec.p, role_id;

		END IF;

	end loop;

 Return role_id;
 END;
$$ language plpgsql;
    

 -- same as above, but with an array rather then a variadic list of permissions
 --(workaround for the limitation of 100 parameters per function call)

CREATE OR REPLACE FUNCTION testcases.tc_omt_createrole(p_role character varying, p_perms integer[])
 RETURNS bigint
 LANGUAGE plpgsql
AS $function$
           
DECLARE
   role_id int8;
   rightlist_2add int[] = '{}';
   rec record;

BEGIN
       
	SELECT id FROM oms."RoleDO" WHERE "name"=p_role
	INTO role_id;

	IF role_id IS NULL THEN
		INSERT INTO oms."RoleDO" (id,name,description,version)
		SELECT  nextval('oms."RoleDO_id_seq"'), p_role,'test case role '||p_role,1
		RETURNING id  INTO role_id;
		rightlist_2add =  coalesce ( p_perms,'{}');
	ELSE

		rightlist_2add = ( select coalesce ( array_agg(unnest),'{}') from 
									( select unnest(p_perms) EXCEPT select "rightDefRef" FROM oms."Role2RightDO" WHERE "roleRef" = role_id )foo
							  );
	END IF;

	FOR rec IN SELECT rightlist_2add[i] as p FROM generate_subscripts(rightlist_2add, 1) g(i)
	loop

		IF  rec.p IS NOT NULL THEN
	
			  IF false = EXISTS(select * from oms."RightDefDO" where id= rec.p) THEN
				  raise exception 'tc_omt_createrole called with an non existing right (%)', rec.p;
			  END IF;

			  INSERT INTO  oms."Role2RightDO" ( "rightDefRef", "roleRef")
			  SELECT  rec.p, role_id;

		END IF;

	end loop;

 Return role_id;
 END;
$function$;

