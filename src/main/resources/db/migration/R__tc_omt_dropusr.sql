	
CREATE OR REPLACE FUNCTION testcases.tc_omt_dropusr(p_usrname character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $$
       
DECLARE
   users record;
BEGIN

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

 FOR users IN SELECT id FROM oms."UserDO" WHERE "accountName" = p_usrname
 LOOP
   PERFORM testcases.tc_omt_dropusr(users.id);
 END LOOP;
 Return 'ok';
END;
$$;
comment on function testcases.tc_omt_dropusr(varchar) is'delete  all data bound to the local user(s) with the given accountName';


CREATE OR REPLACE FUNCTION testcases.tc_omt_drop_sso_user(p_email character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $$
       
DECLARE
   users record;
BEGIN

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);

 FOR users IN SELECT id FROM oms."UserDO" WHERE "externalId" is not null and email=p_email
 LOOP
   PERFORM testcases.tc_omt_dropusr(users.id);
 END LOOP;
 Return 'ok';
END;
$$;

comment on function testcases.tc_omt_drop_sso_user is'delete  all data bound to the sso user(s) with the given email';



CREATE OR REPLACE FUNCTION testcases.tc_omt_dropusr(p_userid int8)
 RETURNS void
 LANGUAGE plpgsql
AS $$
              
BEGIN


   DELETE FROM "CSVReturnImportRowDO"              WHERE  "csvImportRef"          IN (SELECT id FROM "CSVImportDO"          WHERE "userRef"=p_userid);
   DELETE FROM "ApprovalResponseStateHistoryDO"    WHERE  "approvalResponseRef"   IN (SELECT id FROM "ApprovalResponseDO"   WHERE "userRef"=p_userid);
   DELETE FROM "CSVDispatchImportRowDO"            WHERE  "csvImportRef"          IN (SELECT id FROM "CSVImportDO"          WHERE "userRef"=p_userid);

   DELETE FROM oms."ApprovalResponseDO"                            WHERE "userRef" =p_userid;
   DELETE FROM oms."ApprovalResponseStateHistoryDO"                WHERE "userRef" =p_userid;
   DELETE FROM oms."CSVImportDO"                                   WHERE "userRef" =p_userid;
   DELETE FROM oms."DispatchStateHistoryDO"                        WHERE "userRef" =p_userid;
   DELETE FROM oms."DispatchTransmissionStateHistoryDO"            WHERE "userRef" =p_userid;
   DELETE FROM oms."DocumentStateHistoryDO"                        WHERE "userRef" =p_userid;
   DELETE FROM oms."DocumentTransmissionStateHistoryDO"            WHERE "userRef" =p_userid;
   DELETE FROM oms."EventControlStateHistoryDO"                    WHERE "userRef" =p_userid;
   DELETE FROM oms."InvoicingStateHistoryDO"                       WHERE "userRef" =p_userid;
   DELETE FROM oms."InvoicingTransmissionStateHistoryDO"           WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderCancelStateHistoryDO"                     WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderNoteDO"                                   WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderPosStateHistoryDO"                        WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderStateHistoryDO"                           WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderSupplierEvaluationStateHistoryDO"         WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderTransmissionStateHistoryDO"               WHERE "userRef" =p_userid;
   DELETE FROM oms."PaymentNotificationStateHistoryDO"             WHERE "userRef" =p_userid;
   DELETE FROM oms."PaymentNotificationTransmissionStateHistoryDO" WHERE "userRef" =p_userid;
   DELETE FROM oms."ProcessControlStateHistoryDO"                  WHERE "userRef" =p_userid;
   DELETE FROM oms."ResponseStateHistoryDO"                        WHERE "userRef" =p_userid;
   DELETE FROM oms."ResponseTransmissionStateHistoryDO"            WHERE "userRef" =p_userid;
   DELETE FROM oms."ReturnAnnouncementTransmissionStateHistoryDO"  WHERE "userRef" =p_userid;
   DELETE FROM oms."ReturnRequestStateHistoryDO"                   WHERE "userRef" =p_userid;
   DELETE FROM oms."ReturnRequestTransmissionStateHistoryDO"       WHERE "userRef" =p_userid;
   DELETE FROM oms."ReturnStateHistoryDO"                          WHERE "userRef" =p_userid;
   DELETE FROM oms."ReturnTransmissionStateHistoryDO"              WHERE "userRef" =p_userid;
   DELETE FROM oms."ShopCustomerMailTransmissionStateHistoryDO"    WHERE "userRef" =p_userid;
   DELETE FROM oms."User2OrganizationDO"                           WHERE "userRef" =p_userid;
   DELETE FROM oms."User2Role2OrganizationDO"                      WHERE "userRef" =p_userid;
   DELETE FROM oms."User2RoleDO"                                   WHERE "userRef" =p_userid;
   DELETE FROM oms."UserLoginHistoryDO"                            WHERE "userRef" =p_userid;
   DELETE FROM oms."UserPreferencesDO"                             WHERE "userRef" =p_userid;
   DELETE FROM oms."UserSessionDO"                                 WHERE "userRef" =p_userid;
 
   DELETE FROM oms."ReturnAnnouncementStateHistoryDO"              WHERE "userRef" =p_userid;
  
   DELETE FROM omt."Page2Widget4UserDTO"                           WHERE "userRef" =p_userid;
   DELETE FROM omt."Table2Column4UserDTO"                          WHERE "userRef" =p_userid;

   DELETE FROM oms."OrderChangeRequestStateHistoryDO"              WHERE "userRef" =p_userid;
   DELETE FROM oms."OrderChangeRequestDO"                          WHERE "userRef" =p_userid;
   DELETE FROM customer."CustomerDeletionHistoryDO"                WHERE "userRef" =p_userid;
   DELETE FROM oms."UserDO"                                        WHERE id = p_userid;


END;
$$;
