CREATE TABLE Task
(
    id BIGSERIAL PRIMARY key not null,
    name CHARACTER VARYING(100) not null,
    description CHARACTER VARYING(300) not null,
    deadline DATE not null,
    user_id BIGINT not null,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);