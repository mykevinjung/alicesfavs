CREATE SEQUENCE CATEGORY_ID_SEQ START WITH 100001;

CREATE TABLE CATEGORY
(
	ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('CATEGORY_ID_SEQ'),
	SITE_ID BIGINT NOT NULL,
	NAME_EXTRACT1 VARCHAR(128) NOT NULL,
	URL_EXTRACT1 VARCHAR(512) NOT NULL,
	NAME_EXTRACT2 VARCHAR(128) NULL,
	URL_EXTRACT2 VARCHAR(512) NULL,
	NAME_EXTRACT3 VARCHAR(128) NULL,
	URL_EXTRACT3 VARCHAR(512) NULL,
	DISPLAY_ORDER INTEGER NOT NULL,
	EXTRACT_STATUS INTEGER NOT NULL,
	EXTRACT_JOB_ID BIGINT NOT NULL,
	EXTRACTED_DATE TIMESTAMP NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL,
	UPDATED_DATE TIMESTAMP NOT NULL,
	FOREIGN KEY (SITE_ID) REFERENCES SITE (ID)
);

ALTER SEQUENCE CATEGORY_ID_SEQ OWNED BY CATEGORY.ID;

CREATE UNIQUE INDEX CATEGORY_UNIQUE_IDX1 ON CATEGORY (NAME_EXTRACT1)
WHERE NAME_EXTRACT1 IS NOT NULL;

CREATE UNIQUE INDEX CATEGORY_UNIQUE_IDX2 ON CATEGORY (NAME_EXTRACT1, NAME_EXTRACT2)
WHERE NAME_EXTRACT1 IS NOT NULL AND NAME_EXTRACT2 IS NOT NULL;

CREATE UNIQUE INDEX CATEGORY_UNIQUE_IDX3 ON CATEGORY (NAME_EXTRACT1, NAME_EXTRACT2, NAME_EXTRACT3)
WHERE NAME_EXTRACT1 IS NOT NULL AND NAME_EXTRACT2 IS NOT NULL AND NAME_EXTRACT3 IS NOT NULL;

CREATE TRIGGER CATEGORY_SET_CREATED_DATE BEFORE INSERT ON CATEGORY
FOR EACH ROW EXECUTE PROCEDURE SET_CREATED_DATE();

CREATE TRIGGER CATEGORY_SET_UPDATED_DATE BEFORE INSERT OR UPDATE ON CATEGORY
FOR EACH ROW EXECUTE PROCEDURE SET_UPDATED_DATE();
