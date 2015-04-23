CREATE OR REPLACE FUNCTION SET_CREATED_DATE()
RETURNS TRIGGER AS $$
BEGIN
   NEW.CREATED_DATE = now();
   RETURN NEW;
END;
$$ language 'plpgsql';