DROP TABLE IF EXISTS EMPLOYEE; 
CREATE TABLE EMPLOYEE (ID INT NOT NULL, 
    FIRST_NAME VARCHAR(30) NOT NULL,
    LAST_NAME VARCHAR(30) NOT NULL,  
    DATE_OF_BIRTH  NOT NULL,
	START_DATE  NOT NULL,	
    TITLE VARCHAR(20) NOT NULL,
	MANAGER_ID INT, 
    COMMENTS VARCHAR(400) ,
    PRIMARY KEY (ID));