CREATE TABLE Meeting
(
    id BIGSERIAL PRIMARY key,
    title CHARACTER VARYING(100),
    description CHARACTER VARYING(300) NOT NULL,
    date_time DATE NOT NULL,
    created_by BIGINT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES "user" (id)
);

ALTER TABLE Meeting ALTER COLUMN date_time type TIMESTAMP;
ALTER TABLE Meeting ADD partisipants BIGSERIAL NOT NULL;
ALTER TABLE Meeting ALTER COLUMN title SET NOT NULL;
ALTER TABLE Meeting DROP COLUMN partisipants;
ALTER TABLE Meeting ADD COLUMN partisipants BIGINT ARRAY;
ALTER TABLE Meeting ALTER COLUMN partisipants SET NOT NULL;


