create schema KEYGEN;

create table KEYGEN.CONTINENT (ID UUID primary key default gen_random_uuid(),
                               NAME varchar);
create table KEYGEN.COUNTRY (ID UUID primary key default gen_random_uuid(),
                             NAME varchar);
create table KEYGEN.REGION (ID serial primary key,
                            NAME varchar);
create table KEYGEN.CITY (ID UUID primary key,
                          NAME varchar);
create table KEYGEN.STREET (ID UUID primary key,
                            NAME varchar);