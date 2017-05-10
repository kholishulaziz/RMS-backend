-- dummy data for rms.User table
INSERT INTO USER (USER_ID, EMPLOYEE_ID, USERNAME, PASSWORD, ENABLED) VALUES
	('user_01', 'dummy-empl-01', 'aziz@mitrais.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
	('user_02', 'dummy-empl-03', 'yoga@mitrais.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);


-- dummy data for rms.Employee table
INSERT INTO EMPLOYEE (
    EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, DOB, NATIONALITY,
    MARITAL_STATUS, PHONE, SUB_DIVISION, STATUS, HIRE_DATE,
    GRADE, DIVISION, EMAIL, OFFICE, ACTIVE )
VALUES 
    ('dummy-empl-01', 'Kholishul', 'Aziz', 'M', DATE '1991-04-01', 'IND',
    'M', '085730323302', 'Java Bootcamp', 'P', DATE '2013-11-18',
    'PG', 'JWT', 'Kholishul.aziz@mitrais.com', 'JOG',
    TRUE),
    ('dummy-empl-02', 'Abizar Athaillah', 'Aziz', 'M', DATE '2017-01-21', 'IND',
    'S', '081234567890', 'React Bootcamp', 'N', DATE '2017-01-21',
    'JP', 'JWT', 'abizarathaillah.aziz@mitrais.com', 'DPS',
    FALSE),
    ('dummy-empl-03', 'Yoga', 'Kurniawan', 'M', DATE '2017-01-21', 'IND',
    'S', '081234567890', 'React Bootcamp', 'N', DATE '2017-01-21',
    'JP', 'JWT', 'yoga.kurniawan@mitrais.com', 'DPS',
    FALSE);



-- dummy data for rms.History table
INSERT INTO HISTORY (
    HISTORY_ID, START_DATE, END_DATE, PROJECT_NAME, PROJECT_ROLE, JOB_DESCRIPTION, EMPLOYEE_ID )
VALUES 
    ('dummy-hist-01', '2014-01-01', '2016-09-01', 'MMS', 'TEST', 'MMS Description', 'dummy-empl-01'),
    ('dummy-hist-02', '2016-09-01', '2016-12-01', 'BTPN', 'DEV', 'BTPN Description', 'dummy-empl-01'),
    ('dummy-hist-03', '2014-01-01', NULL , 'CDC', 'DEV', 'CDC Description', 'dummy-empl-02'),
    ('dummy-hist-04', '2016-12-01', NULL , 'CDC', 'DEV', 'CDC Description', 'dummy-empl-01');



-- dummy data for rms.Lookup table
INSERT INTO LOOKUP(LOOKUP_ID, DATA_TYPE, DATA_CODE, DATA_DESC) VALUES
    ('dummy-lkp-01', 'GRADE', 'JP', 'Junior Programmer'),
    ('dummy-lkp-02', 'GRADE', 'PG', 'Programmer'),
    ('dummy-lkp-03', 'GRADE', 'AP', 'Analyst Programmer'),
    ('dummy-lkp-04', 'GRADE', 'AN', 'Analyst'),
    ('dummy-lkp-05', 'GENDER', 'M', 'Male'),
    ('dummy-lkp-06', 'GENDER', 'F', 'Female'),
    ('dummy-lkp-07', 'NATION', 'IND', 'Indonesia'),
    ('dummy-lkp-08', 'NATION', 'AUS', 'Australia'),
    ('dummy-lkp-09', 'MARITAL', 'S', 'Single'),
    ('dummy-lkp-10', 'MARITAL', 'M', 'Married'),
    ('dummy-lkp-11', 'EMPSTAT', 'C', 'Contract'),
    ('dummy-lkp-12', 'EMPSTAT', 'P', 'Permanent'),
    ('dummy-lkp-13', 'EMPSTAT', 'N', 'Not Active'),
    ('dummy-lkp-14', 'DIV', 'JWT', 'Java Web Technology'),
    ('dummy-lkp-15', 'DIV', 'CDC', 'CDC'),
    ('dummy-lkp-16', 'OFFICE', 'JOG', 'Yogyakarta'),
    ('dummy-lkp-17', 'OFFICE', 'SUB', 'Surabaya'),
    ('dummy-lkp-18', 'OFFICE', 'DPS', 'Denpasar'),
    ('dummy-lkp-19', 'ROLE', 'DEV', 'Developer'),
    ('dummy-lkp-20', 'ROLE', 'TEST', 'Tester');