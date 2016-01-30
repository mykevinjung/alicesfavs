CREATE SEQUENCE PRODUCT_ID_SEQ START WITH 10000001;

CREATE TABLE PRODUCT
(
	ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('PRODUCT_ID_SEQ'),
	SITE_ID BIGINT NOT NULL,
	ID_EXTRACT VARCHAR(128) NOT NULL,
	NAME_EXTRACT VARCHAR(128) NOT NULL,
	PRICE_EXTRACT VARCHAR(64) NOT NULL,
	WAS_PRICE_EXTRACT VARCHAR(64) NULL,
	URL_EXTRACT VARCHAR(512) NOT NULL,
	IMAGE_URL_EXTRACT VARCHAR(512) NOT NULL,
	IMAGE_URL_EXTRACT2 VARCHAR(512) NULL,
	DESCRIPTION_EXTRACT VARCHAR(1024) NULL,
	PRICE NUMERIC(14,2) NOT NULL,
	WAS_PRICE NUMERIC(14,2) NULL,
	PRICE_CHANGED_DATE TIMESTAMP NOT NULL,
	SALE_START_DATE TIMESTAMP NULL,
	SOLD_OUT NUMERIC(1,0) NULL,
	EXTRACT_STATUS INTEGER NOT NULL,
	EXTRACT_JOB_ID BIGINT NOT NULL,
	EXTRACTED_DATE TIMESTAMP NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL,
	UPDATED_DATE TIMESTAMP NOT NULL,
	FOREIGN KEY (SITE_ID) REFERENCES SITE (ID)
);

ALTER SEQUENCE PRODUCT_ID_SEQ OWNED BY PRODUCT.ID;

CREATE UNIQUE INDEX PRODUCT_SITE_UNIQUE_IDX ON PRODUCT (SITE_ID, ID_EXTRACT);

CREATE INDEX SITE_ID_IDX ON PRODUCT (SITE_ID);

CREATE INDEX PRODUCT_SALE_START_DATE_IDX ON PRODUCT (SALE_START_DATE);

CREATE TRIGGER PRODUCT_SET_CREATED_DATE BEFORE INSERT ON PRODUCT
FOR EACH ROW EXECUTE PROCEDURE SET_CREATED_DATE();

CREATE TRIGGER PRODUCT_SET_UPDATED_DATE BEFORE INSERT OR UPDATE ON PRODUCT
FOR EACH ROW EXECUTE PROCEDURE SET_UPDATED_DATE();
