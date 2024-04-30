insert into CONTINENT (NAME) VALUES ('EUROPE');
insert into CONTINENT (NAME) VALUES (gen_random_uuid(), 'ASIA');
insert into CONTINENT (NAME) VALUES ('300bd93c-6071-4e06-ab1a-7b185c9009de', 'AFRICA');

-- insert into CITY (NAME) VALUES ('BRUSSELS'); This is not possible
insert into CITY (ID, NAME) VALUES (gen_random_uuid(), 'ROME');
insert into CITY (ID, NAME) VALUES ('7acec20d-8d44-4445-bfc9-793037ed71ac', 'ATHENS');