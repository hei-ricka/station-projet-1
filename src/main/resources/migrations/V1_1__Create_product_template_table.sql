create table if not exists "product_template" (
    "id" varchar primary key,
    "name" varchar not null,
    "created_at" timestamp without time zone default current_timestamp not null,
    "updated_at" timestamp without time zone default current_timestamp not null
);

insert into "product_template"("id", "name") values ('id1', 'pétrole'), ('id2', 'gasoil'), ('id3', 'essence');