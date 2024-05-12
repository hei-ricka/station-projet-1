DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'operation_product_type') THEN
        CREATE TYPE "operation_product_type" AS ENUM ( 'SALE', 'PROCUREMENT' );
    END IF;
END $$;