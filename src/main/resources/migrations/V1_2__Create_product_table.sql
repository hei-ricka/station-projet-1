create table if not exists "product" (
    "id" varchar primary key,
    "name" varchar(255) not null,
    "unit_price" DECIMAL(18,4) CHECK ("unit_price" > 0) DEFAULT 0 not null,
    "stock" DECIMAL(18,4) CHECK ("stock" >= 0) DEFAULT 0 not null,
    "updated_at" timestamp without time zone default current_timestamp not null,
    "created_at" timestamp without time zone default current_timestamp not null,
    "station_id" varchar(255) references "station"("id") not null
);