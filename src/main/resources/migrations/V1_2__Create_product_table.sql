create table if not exists "product" (
    "id" varchar primary key,
    "unit_price" DECIMAL(18,4) CHECK ("unit_price" > 0) DEFAULT 0 not null,
    "evaporation" DECIMAL(18, 4) not null,
    "station_id" varchar(255) references "station"("id") not null,
    "product_template_id" varchar(255) references "product_template"("id") not null,
    "updated_at" timestamp without time zone default current_timestamp not null,
    "created_at" timestamp without time zone default current_timestamp not null
);