create table if not exists "stock_history" (
    "id" varchar primary key,
    "created_at" timestamp without time zone default current_timestamp not null,
    "quantity" DECIMAL(18,4) check("quantity" >= 0) not null,
    "product_id" varchar(255) references "product"("id") not null
);
