CREATE OR REPLACE FUNCTION testcases.log_pg_config(msg text)
 RETURNS boolean
 LANGUAGE plpgsql
AS $function$

DECLARE
pgconfig text;

BEGIN
	
	pgconfig = (select string_agg(cast(json_build_array (p."name",p.setting) as text), '\n') from pg_settings p);

	raise log'% (logged with testcases.log_pg_config):\n%', msg, pgconfig;
	return true;

END;
$function$;


comment on function testcases.log_pg_config(text) is'Trigger the logging of the full postgres configuration to the postgres log.
Use the msg parameter to define a context.
Always returns true.';
