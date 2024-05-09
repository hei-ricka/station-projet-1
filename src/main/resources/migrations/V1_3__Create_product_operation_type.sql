DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'product_operation_type') THEN
        CREATE TYPE "product_operation_type" AS ENUM ( 'SALE', 'PROCUREMENT' );
    END IF;
END $$;