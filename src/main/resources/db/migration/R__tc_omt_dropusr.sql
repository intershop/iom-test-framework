CREATE OR REPLACE FUNCTION testcases.tc_omt_dropusr(p_usrname character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $$
       
DECLARE
   usr_id int;
   users record;
       
BEGIN

--to reduce the risk of FK exceptions during parallel tests.
PERFORM pg_advisory_xact_lock(5,5);


 FOR users IN SELECT id FROM oms."UserDO" WHERE "accountName" = p_usrname
 LOOP




   DELETE FROM "CSVReturnImportRowDO"              WHERE  "csvImportRef"          IN (SELECT id FROM "CSVImportDO"          WHERE "userRef"=users.id);
   DELETE FROM "ApprovalResponseStateHistoryDO"    WHERE  "approvalResponseRef"   IN (SELECT id FROM "ApprovalResponseDO"   WHERE "userRef"=users.id);
   DELETE FROM "CSVDispatchImportRowDO"            WHERE  "csvImportRef"          IN (SELECT id FROM "CSVImportDO"          WHERE "userRef"=users.id);

   DELETE FROM oms."ApprovalResponseDO"                            WHERE "userRef" =users.id;
   DELETE FROM oms."ApprovalResponseStateHistoryDO"                WHERE "userRef" =users.id;
   DELETE FROM oms."CSVImportDO"                                   WHERE "userRef" =users.id;
   DELETE FROM oms."DispatchStateHistoryDO"                        WHERE "userRef" =users.id;
   DELETE FROM oms."DispatchTransmissionStateHistoryDO"            WHERE "userRef" =users.id;
   DELETE FROM oms."DocumentStateHistoryDO"                        WHERE "userRef" =users.id;
   DELETE FROM oms."DocumentTransmissionStateHistoryDO"            WHERE "userRef" =users.id;
   DELETE FROM oms."EventControlStateHistoryDO"                    WHERE "userRef" =users.id;
   DELETE FROM oms."InvoicingStateHistoryDO"                       WHERE "userRef" =users.id;
   DELETE FROM oms."InvoicingTransmissionStateHistoryDO"           WHERE "userRef" =users.id;
   DELETE FROM oms."OrderCancelStateHistoryDO"                     WHERE "userRef" =users.id;
   DELETE FROM oms."OrderNoteDO"                                   WHERE "userRef" =users.id;
   DELETE FROM oms."OrderPosStateHistoryDO"                        WHERE "userRef" =users.id;
   DELETE FROM oms."OrderStateHistoryDO"                           WHERE "userRef" =users.id;
   DELETE FROM oms."OrderSupplierEvaluationStateHistoryDO"         WHERE "userRef" =users.id;
   DELETE FROM oms."OrderTransmissionStateHistoryDO"               WHERE "userRef" =users.id;
   DELETE FROM oms."PaymentNotificationStateHistoryDO"             WHERE "userRef" =users.id;
   DELETE FROM oms."PaymentNotificationTransmissionStateHistoryDO" WHERE "userRef" =users.id;
   DELETE FROM oms."ProcessControlStateHistoryDO"                  WHERE "userRef" =users.id;
   DELETE FROM oms."ResponseStateHistoryDO"                        WHERE "userRef" =users.id;
   DELETE FROM oms."ResponseTransmissionStateHistoryDO"            WHERE "userRef" =users.id;
   DELETE FROM oms."ReturnAnnouncementTransmissionStateHistoryDO"  WHERE "userRef" =users.id;
   DELETE FROM oms."ReturnRequestStateHistoryDO"                   WHERE "userRef" =users.id;
   DELETE FROM oms."ReturnRequestTransmissionStateHistoryDO"       WHERE "userRef" =users.id;
   DELETE FROM oms."ReturnStateHistoryDO"                          WHERE "userRef" =users.id;
   DELETE FROM oms."ReturnTransmissionStateHistoryDO"              WHERE "userRef" =users.id;
   DELETE FROM oms."ShopCustomerMailTransmissionStateHistoryDO"    WHERE "userRef" =users.id;
   DELETE FROM oms."User2OrganizationDO"                           WHERE "userRef" =users.id;
   DELETE FROM oms."User2Role2OrganizationDO"                      WHERE "userRef" =users.id;
   DELETE FROM oms."User2RoleDO"                                   WHERE "userRef" =users.id;
   DELETE FROM oms."UserLoginHistoryDO"                            WHERE "userRef" =users.id;
   DELETE FROM oms."UserPreferencesDO"                             WHERE "userRef" =users.id;
   DELETE FROM oms."UserSessionDO"                                 WHERE "userRef" =users.id;
 
   DELETE FROM oms."ReturnAnnouncementStateHistoryDO"              WHERE "userRef" =users.id;
  
   DELETE FROM omt."Page2Widget4UserDTO"                           WHERE "userRef" =users.id;
   DELETE FROM omt."Table2Column4UserDTO"                          WHERE "userRef" =users.id;

   DELETE FROM oms."OrderChangeRequestStateHistoryDO"              WHERE "userRef" =users.id;
   DELETE FROM oms."OrderChangeRequestDO"                          WHERE "userRef" =users.id;
   DELETE FROM customer."CustomerDeletionHistoryDO"                WHERE "userRef" =users.id;
   DELETE FROM oms."UserDO"                                        WHERE id = users.id;

 END LOOP;

 Return 'ok';

END;
$$;

