create table clients
(
    id bigserial not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(250) not null,
    phone varchar(15) not null
);

create unique index client_email_uindex on clients(email);

create unique index client_phone_uindex on clients(phone);
