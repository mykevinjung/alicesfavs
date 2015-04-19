CREATE OR REPLACE FUNCTION SET_UPDATED_DATE()
RETURNS TRIGGER AS $$
BEGIN
   NEW.UPDATED_DATE = now();
   RETURN NEW;
END;
$$ language 'plpgsql';