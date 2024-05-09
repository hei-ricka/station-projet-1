create table if not exists "dummy" (
       "id" varchar primary key,
       name varchar not null
);

insert into "dummy" ("id", "name")
values
    ('dummy_id1', 'my_dummy_table_1'),
    ('dummy_id2', 'my_dummy_table_2'),
    ('dummy_id3', 'my_dummy_table_3')
on conflict ("id") do nothing;