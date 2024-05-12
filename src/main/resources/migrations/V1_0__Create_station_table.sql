create table if not exists "station" (
    "id" varchar(255) primary key,
    "location" varchar(255) not null,
    "created_at" timestamp without time zone default current_timestamp not null ,
    "updated_at" timestamp without time zone default current_timestamp not null
);
