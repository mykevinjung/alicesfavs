CREATE SEQUENCE SITE_ID_SEQ START WITH 1001;

CREATE TABLE SITE
(
	ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('SITE_ID_SEQ'),
	STRING_ID VARCHAR(32) NOT NULL,
	DISPLAY_NAME VARCHAR(32) NOT NULL,
	DOMAIN VARCHAR(40) NOT NULL,
	DISPLAY CHAR(1) NOT NULL,
	DISPLAY_WEIGHT INTEGER NOT NULL,
	BRAND_LEVEL INTEGER NOT NULL,
	USE_STORED_IMAGE CHAR(1) NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL,
	UPDATED_DATE TIMESTAMP NOT NULL,
	CONSTRAINT SITE_STRING_ID_UNIQUE UNIQUE (STRING_ID)
);

ALTER SEQUENCE SITE_ID_SEQ OWNED BY SITE.ID;

CREATE TRIGGER SITE_SET_CREATED_DATE BEFORE INSERT ON SITE
FOR EACH ROW EXECUTE PROCEDURE SET_CREATED_DATE();

CREATE TRIGGER SITE_SET_UPDATED_DATE BEFORE INSERT OR UPDATE ON SITE
FOR EACH ROW EXECUTE PROCEDURE SET_UPDATED_DATE();
