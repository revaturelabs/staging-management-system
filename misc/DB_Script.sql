DROP USER sms CASCADE;

CREATE USER sms
IDENTIFIED BY swordfish
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp;

GRANT connect to sms;
GRANT resource to sms;
GRANT CREATE SESSION TO sms;
GRANT CREATE TABLE TO sms;
GRANT CREATE VIEW TO sms;
GRANT CREATE MATERIALIZED VIEW TO sms;
GRANT create session to sms;
ALTER USER sms QUOTA 10m ON users;

conn sms/swordfish

CREATE TABLE CREDENTIALS (
  CREDENTIAL_ID NUMBER PRIMARY KEY,
  CREDENTIAL_USERNAME VARCHAR2(64) UNIQUE,
  CREDENTIAL_PASSWORD VARCHAR2(128)
);
CREATE SEQUENCE CREDENTIAL_ID_SEQ;

CREATE TABLE PERMISSIONS (
  PERMISSION_ID NUMBER PRIMARY KEY,
  PERMISSION_LEVEL VARCHAR2(16)
);
CREATE SEQUENCE PERMISSION_ID_SEQ;

CREATE TABLE MANAGERS (
  MANAGER_ID NUMBER PRIMARY KEY,
  MANAGER_NAME VARCHAR2(64),
  CREDENTIAL_ID NUMBER,
  PERMISSION_ID NUMBER,
  MANAGER_ACTIVE NUMBER(1,0), --Disabled or not (to avoid deleting, because of report forcasting)
  CONSTRAINT FK_ManagerCredential FOREIGN KEY (CREDENTIAL_ID) REFERENCES CREDENTIALS(CREDENTIAL_ID),
  CONSTRAINT FK_ManagerPermission FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSIONS(PERMISSION_ID)
);
CREATE SEQUENCE MANAGER_ID_SEQ;

CREATE TABLE BATCH_TYPES (
  BATCH_TYPE_ID NUMBER PRIMARY KEY,
  BATCH_TYPE_VALUE VARCHAR2(64)
);
CREATE SEQUENCE BATCH_TYPE_ID_SEQ;

CREATE TABLE LOCATIONS (
  LOCATION_ID NUMBER PRIMARY KEY,
  LOCATION_NAME VARCHAR2(64),
  LOCATION_COUNTRY VARCHAR2(64),
  LOCATION_STATE VARCHAR2(64),
  LOCATION_CITY VARCHAR2(64)
);
CREATE SEQUENCE LOCATION_ID_SEQ;

CREATE TABLE INTERNAL_PROJECT (
  PROJECT_ID NUMBER PRIMARY KEY,
  PROJECT_NAME VARCHAR2(50),
  PROJECT_DESCRIPTION VARCHAR2(240),
  PROJECT_STATUS VARCHAR2(10)
);
CREATE SEQUENCE PROJECT_ID_SEQ;

CREATE TABLE BATCHES (
  BATCH_ID NUMBER PRIMARY KEY,
  BATCH_TYPE_ID NUMBER,
  BATCH_START_DATE TIMESTAMP,
  BATCH_END_DATE TIMESTAMP,
  LOCATION_ID NUMBER,
  CONSTRAINT FK_BatchBatchtype FOREIGN KEY (BATCH_TYPE_ID) REFERENCES BATCH_TYPES(BATCH_TYPE_ID),
  CONSTRAINT FK_BatchLocation FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS(LOCATION_ID)
);
CREATE SEQUENCE BATCH_ID_SEQ;

CREATE TABLE ASSOCIATES (
  ASSOCIATE_ID NUMBER PRIMARY KEY,
  CREDENTIAL_ID NUMBER,
  ASSOCIATE_NAME VARCHAR2(64),
  ASSOCIATE_PORTFOLIO_LINK VARCHAR2(128),
  BATCH_ID NUMBER,
  PROJECT_ID NUMBER,
  ASSOCIATE_ACTIVE NUMBER(1,0), --Disabled or not (to avoid deleting, because of report forcasting)
  CLIENT_ID NUMBER, --ID of a priority client. Known as lockedTo to an associate.
  CONSTRAINT FK_AssociateCredential FOREIGN KEY (CREDENTIAL_ID) REFERENCES CREDENTIALS(CREDENTIAL_ID),
  CONSTRAINT FK_AssociateBatch FOREIGN KEY (BATCH_ID) REFERENCES BATCHES(BATCH_ID),
  CONSTRAINT FK_AssociateProject FOREIGN KEY (PROJECT_ID) REFERENCES INTERNAL_PROJECT(PROJECT_ID)
);
CREATE SEQUENCE ASSOCIATE_ID_SEQ;

CREATE TABLE CHECKINS (
  CHECKIN_ID NUMBER PRIMARY KEY,
  CHECKIN_IN_TIME TIMESTAMP,
  CHECKIN_OUT_TIME TIMESTAMP,
  MANAGER_ID NUMBER, --Known as approvedBy to an associate.
  CHECKIN_APPROVE_TIME TIMESTAMP,
  ASSOCIATE_ID NUMBER,
  CONSTRAINT FK_CheckinApprovedby FOREIGN KEY (MANAGER_ID) REFERENCES MANAGERS(MANAGER_ID),
  CONSTRAINT FK_CheckinAssociate FOREIGN KEY (ASSOCIATE_ID) REFERENCES ASSOCIATES(ASSOCIATE_ID)
);
CREATE SEQUENCE CHECKIN_ID_SEQ;

CREATE TABLE TRAINERS (
  TRAINER_ID NUMBER PRIMARY KEY,
  TRAINER_NAME VARCHAR2(64),
  TRAINER_ACTIVE NUMBER(1,0) --Disabled or not (to avoid deleting, because of report forcasting)
);
CREATE SEQUENCE TRAINER_ID_SEQ;

CREATE TABLE BATCH_TRAINER (
  TRAINER_ID NUMBER,
  BATCH_ID NUMBER,
  CONSTRAINT FK_BatchtrainerTrainer FOREIGN KEY (TRAINER_ID) REFERENCES TRAINERS(TRAINER_ID),
  CONSTRAINT FK_BatchtrainerBatch FOREIGN KEY (BATCH_ID) REFERENCES BATCHES(BATCH_ID)
);

CREATE TABLE CLIENTS (
  CLIENT_ID NUMBER PRIMARY KEY,
  CLIENT_NAME VARCHAR2(64),
  CLIENT_PRIORITY NUMBER(1,0), --Associates mapped to this client will be locked in to the client.
  CLIENT_ACTIVE NUMBER(1,0) --Disabled or not (to avoid deleting, because of report forcasting)
);
CREATE SEQUENCE CLIENT_ID_SEQ;

CREATE TABLE INTERVIEW_STATUSES (
  INTERVIEW_STATUS_ID NUMBER PRIMARY KEY,
  INTERVIEW_STATUS_VALUE VARCHAR2(16)
);
CREATE SEQUENCE INTERVIEW_STATUS_ID_SEQ;

CREATE TABLE MARKETERS (
  MARKETER_ID NUMBER PRIMARY KEY,
  MARKETER_NAME VARCHAR2(64),
  CREDENTIAL_ID NUMBER,
  CONSTRAINT FK_MarketerCredential FOREIGN KEY (CREDENTIAL_ID) REFERENCES CREDENTIALS(CREDENTIAL_ID)
);
CREATE SEQUENCE MARKETER_ID_SEQ;

CREATE TABLE INTERVIEWS (
  INTERVIEW_ID NUMBER PRIMARY KEY,
  ASSOCIATE_ID NUMBER,
  CLIENT_ID NUMBER,
  MARKETER_ID NUMBER,
  INTERVIEW_STATUS_ID NUMBER,
  INTERVIEW_TIME TIMESTAMP,
  INTERVIEW_COMMENT VARCHAR2(256),
  CONSTRAINT FK_InterviewAssociate FOREIGN KEY (ASSOCIATE_ID) REFERENCES ASSOCIATES(ASSOCIATE_ID),
  CONSTRAINT FK_InterviewClient FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS(CLIENT_ID),
  CONSTRAINT FK_InterviewInterviewstatus FOREIGN KEY (INTERVIEW_STATUS_ID) REFERENCES INTERVIEW_STATUSES(INTERVIEW_STATUS_ID),
  CONSTRAINT FK_InterviewMarketer FOREIGN KEY (MARKETER_ID) REFERENCES MARKETERS(MARKETER_ID)
);
CREATE SEQUENCE INTERVIEW_ID_SEQ;

CREATE TABLE JOBS(
  JOB_ID NUMBER PRIMARY KEY,
  ASSOCIATE_ID NUMBER,
  CLIENT_ID NUMBER,
  JOB_START_DATE TIMESTAMP,
  JOB_PROJECTED_END_DATE TIMESTAMP,
  JOB_ACTUAL_END_DATE TIMESTAMP,
  JOB_BUYOUT_DATE TIMESTAMP,
  JOB_CONFIRMED_DATE TIMESTAMP,
  CONSTRAINT FK_JobAssociate FOREIGN KEY (ASSOCIATE_ID) REFERENCES ASSOCIATES(ASSOCIATE_ID),
  CONSTRAINT FK_JobClient FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS(CLIENT_ID)
);
CREATE SEQUENCE JOB_ID_SEQ;

CREATE TABLE INTERVIEW_QUESTIONS (
  INTERVIEW_QUESTION_ID NUMBER PRIMARY KEY,
  BATCH_TYPE_ID NUMBER,
  INTERVIEW_QUESTION_VALUE VARCHAR2(256),
  CONSTRAINT FK_InterviewquestionBatchtype FOREIGN KEY (BATCH_TYPE_ID) REFERENCES BATCH_TYPES(BATCH_TYPE_ID)
);
CREATE SEQUENCE INTERVIEW_QUESTION_ID_SEQ;

CREATE TABLE CLIENT_QUESTIONS (
  CLIENT_QUESTION_ID NUMBER PRIMARY KEY,
  CLIENT_ID NUMBER,
  INTERVIEW_QUESTION_ID NUMBER,
  ASSOCIATE_ID NUMBER,
  CONSTRAINT FK_ClientquestionsClient FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS(CLIENT_ID),
  CONSTRAINT FK_ClientquestionsIQ FOREIGN KEY (INTERVIEW_QUESTION_ID) REFERENCES INTERVIEW_QUESTIONS(INTERVIEW_QUESTION_ID)
);
CREATE SEQUENCE CLIENT_QUESTION_ID_SEQ;

CREATE TABLE SKILLS (
  SKILL_ID NUMBER PRIMARY KEY,
  SKILL_VALUE VARCHAR2(64)
);
CREATE SEQUENCE SKILL_ID_SEQ;

CREATE TABLE BATCH_TYPE_SKILLS (
  BATCH_TYPE_ID NUMBER,
  SKILL_ID NUMBER,
  CONSTRAINT FK_BatchtypeskillBatchtype FOREIGN KEY (BATCH_TYPE_ID) REFERENCES BATCH_TYPES(BATCH_TYPE_ID),
  CONSTRAINT FK_BatchtypeskillSkill FOREIGN KEY (SKILL_ID) REFERENCES SKILLS(SKILL_ID)
);

CREATE TABLE ASSOCIATE_SKILLS (
  ASSOCIATE_ID NUMBER,
  SKILL_ID NUMBER,
  CONSTRAINT FK_AssociateskillAssociate FOREIGN KEY (ASSOCIATE_ID) REFERENCES ASSOCIATES(ASSOCIATE_ID),
  CONSTRAINT FK_AssociateskillSkill FOREIGN KEY (SKILL_ID) REFERENCES SKILLS(SKILL_ID)
);

CREATE TABLE PANEL (
    PANEL_ID NUMBER PRIMARY KEY,
    ASSOCIATE_ID NUMBER,
    MANAGER_ID NUMBER,
    COMMENTS VARCHAR2(200),
    STATUS_DATE TIMESTAMP(6),
    STATUS VARCHAR2(10) DEFAULT('PENDING'),
    CONSTRAINT FK_ASSOCIATE_ID FOREIGN KEY (ASSOCIATE_ID) REFERENCES ASSOCIATES(ASSOCIATE_ID),
    CONSTRAINT FK_MANAGER_ID FOREIGN KEY (MANAGER_ID) REFERENCES MANAGERS(MANAGER_ID)
);
CREATE SEQUENCE PANEL_ID_SEQ;

CREATE OR REPLACE VIEW ASSOCIATE_STATE_INFO AS
    select 
    ASSOCIATES.ASSOCIATE_ID, ASSOCIATES.ASSOCIATE_NAME, ASSOCIATES.ASSOCIATE_PORTFOLIO_LINK, 
    JOBS.JOB_START_DATE, JOBS.JOB_ACTUAL_END_DATE, JOBS.JOB_BUYOUT_DATE, 
    BATCHES.BATCH_START_DATE, BATCHES.BATCH_END_DATE,
    (select count(*) from JOBS where ASSOCIATE_ID=ASSOCIATES.ASSOCIATE_ID) AS JOB_COUNT
    from associates LEFT JOIN JOBS ON ASSOCIATES.ASSOCIATE_ID=JOBS.ASSOCIATE_ID INNER JOIN BATCHES ON ASSOCIATES.BATCH_ID=BATCHES.BATCH_ID;
/

CREATE OR REPLACE TYPE STAGGING_ASSOC_TYPE AS OBJECT(
    ASSOCIATE_ID NUMBER,
    ASSOCIATE_NAME VARCHAR(64),
    PORTFOLIO_LINK VARCHAR(128),
    CHECKIN_DATE_TIME TIMESTAMP
);
/

CREATE OR REPLACE TYPE STAGGING_ASSOC_TABLE IS TABLE OF STAGGING_ASSOC_TYPE;
/


create or replace FUNCTION GET_STAGGING_ASSOC_BY_DATE (V_DATE VARCHAR2)
  RETURN STAGGING_ASSOC_TABLE
IS
  RET_VAL STAGGING_ASSOC_TABLE := STAGGING_ASSOC_TABLE();
BEGIN
    RET_VAL.EXTEND();
    SELECT STAGGING_ASSOC_TYPE(ID, ASSOCIATE_NAME, ASSOCIATE_PORTFOLIO_LINK, CHECKIN_IN_TIME) 
    BULK COLLECT INTO RET_VAL 
    FROM (SELECT ASSOCIATE_ID AS ID, ASSOCIATE_NAME, ASSOCIATE_PORTFOLIO_LINK FROM
            (SELECT ASSOCIATE_ID, ASSOCIATE_NAME, ASSOCIATE_PORTFOLIO_LINK, JOB_COUNT, COUNT(ASSOCIATE_ID) AS OCCURANCE_COUNT FROM 
                (SELECT * FROM ASSOCIATE_STATE_INFO
                    WHERE JOB_BUYOUT_DATE IS NULL 
                        AND (TO_CHAR(TO_TIMESTAMP (JOB_START_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD') > TO_CHAR(TO_TIMESTAMP (V_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD') 
                                OR TO_CHAR(TO_TIMESTAMP (JOB_ACTUAL_END_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD') < TO_CHAR(TO_TIMESTAMP (V_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD') 
                                OR JOB_START_DATE IS NULL) 
                        AND (TO_CHAR(TO_TIMESTAMP (BATCH_END_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD')  < TO_CHAR(TO_TIMESTAMP (V_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'YY-MM-DD')))
            GROUP BY ASSOCIATE_ID, ASSOCIATE_NAME, ASSOCIATE_PORTFOLIO_LINK, JOB_COUNT)
        WHERE OCCURANCE_COUNT=JOB_COUNT OR (OCCURANCE_COUNT=1 AND JOB_COUNT=0))
    LEFT JOIN 
        (SELECT * FROM CHECKINS WHERE TO_CHAR(checkins.CHECKIN_IN_TIME, 'RR-MM-DD')=TO_CHAR(TO_TIMESTAMP (V_DATE, 'DD-Mon-RR HH:MI:SS.FF AM'), 'RR-MM-DD')) 
    on ID=ASSOCIATE_ID
    ORDER BY ASSOCIATE_NAME;

  RETURN RET_VAL;
END;
/

CREATE OR REPLACE TYPE ATTENDANCE_TYPE AS OBJECT(
    ID NUMBER,
    CHECKED_IN NUMBER,
    TOTAL NUMBER,
    ON_DATE TIMESTAMP
);
/

CREATE OR REPLACE TYPE ATTENDANCE_TABLE IS TABLE OF ATTENDANCE_TYPE;
/


create or replace FUNCTION GET_ATTENDANCE_REPORT (V_START_DATE VARCHAR2, V_END_DATE VARCHAR2)
  RETURN ATTENDANCE_TABLE
IS
    CURR_CHAR VARCHAR2(10);
    CURR_DATE TIMESTAMP;
    END_DATE TIMESTAMP;
    
    p_ID NUMBER;
    P_CHECKED_IN NUMBER;
    P_TOTAL NUMBER;
    P_ON_DATE TIMESTAMP;
    
    RET_VAL ATTENDANCE_TABLE := ATTENDANCE_TABLE();
BEGIN
    CURR_DATE := TO_TIMESTAMP(V_START_DATE);
    END_DATE := TO_TIMESTAMP(V_END_DATE);
    
    IF CURR_DATE > END_DATE THEN
        CURR_DATE := END_DATE;
        END_DATE := TO_TIMESTAMP(V_START_DATE);
    END IF;
        
    WHILE CURR_DATE < END_DATE
    LOOP    
        CURR_CHAR := TO_CHAR(CURR_DATE, 'DD-Mon-YY');
        SELECT CHECKED_IN, TOTAL, CURR_DATE
        INTO P_CHECKED_IN, P_TOTAL, P_ON_DATE FROM
            (SELECT 
                (SELECT Count(*) FROM TABLE(GET_STAGGING_ASSOC_BY_DATE(CURR_CHAR)) where checkin_date_time is not null) AS CHECKED_IN,
                (SELECT Count(*) FROM TABLE(GET_STAGGING_ASSOC_BY_DATE(CURR_CHAR))) AS TOTAL
                
            FROM DUAL);
        
        RET_VAL.EXTEND();
        RET_VAL(RET_VAL.COUNT) := ATTENDANCE_TYPE(RET_VAL.COUNT, P_CHECKED_IN, P_TOTAL, P_ON_DATE); 
        CURR_DATE := CURR_DATE + 1;
    END LOOP;

  RETURN RET_VAL;
END;
/