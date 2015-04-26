CREATE SEQUENCE USER_EMAIL_VERIFICATION_ID_SEQ START WITH 1001;

CREATE TABLE USER_EMAIL_VERIFICATION
(
	ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('USER_EMAIL_VERIFICATION_ID_SEQ'),
	USER_ID BIGINT NOT NULL,
	EMAIL_ADDRESS VARCHAR(128) NOT NULL,
	VERIFICATION_CODE VARCHAR(128) NOT NULL,
	EXPIRATION_DATE TIMESTAMP NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL,
	UPDATED_DATE TIMESTAMP NOT NULL,
	FOREIGN KEY (USER_ID) REFERENCES USER (ID)
);

ALTER SEQUENCE USER_EMAIL_VERIFICATION_ID_SEQ OWNED BY USER_EMAIL_VERIFICATION.ID;

CREATE TRIGGER USER_EMAIL_VERIFICATION_SET_CREATED_DATE BEFORE INSERT ON USER_EMAIL_VERIFICATION
FOR EACH ROW EXECUTE PROCEDURE SET_CREATED_DATE();

CREATE TRIGGER USER_EMAIL_VERIFICATION_SET_UPDATED_DATE BEFORE INSERT OR UPDATE ON USER_EMAIL_VERIFICATION
FOR EACH ROW EXECUTE PROCEDURE SET_UPDATED_DATE();
