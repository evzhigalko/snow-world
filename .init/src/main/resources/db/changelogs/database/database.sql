-- liquibase formatted sql
-- changeset zhigalko:snow_world_liquibase dbms:postgresql
-- precondition
CREATE SCHEMA IF NOT EXISTS "rental";
-- rollback DROP SCHEMA "rental" CASCADE;