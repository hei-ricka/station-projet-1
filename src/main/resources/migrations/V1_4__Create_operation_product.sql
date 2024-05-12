create table if not exists "product_operation" (
    "id" varchar(255) primary key,
    "operation_datetime" timestamp without time zone default current_timestamp not null,
    "quantity" DECIMAL(18,4) check("quantity" > 0) not null,
    "type" "operation_product_type" not null,
    "product_id" varchar(255) references "product"("id") not null
);