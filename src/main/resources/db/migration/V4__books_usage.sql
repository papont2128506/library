
create unique index clients_id_uindex
    on clients (id);

create unique index books_id_uindex
    on books (id);

create table book_in_use
(
    id serial not null,
    client_id bigint not null constraint book_in_use_clients_id_fk references clients (id) on delete cascade,
    book_id bigint not null constraint book_in_use_books_id_fk references books (id) on delete cascade,
    in_use_from date not null
);

create unique index book_in_use_client_id_book_id_uindex
    on book_in_use (client_id, book_id);

create unique index book_in_use_id_uindex
    on book_in_use (id);


create table book_history
(
    id serial not null,
    client_id bigint not null constraint book_history_clients_id_fk references clients (id) on delete cascade,
    book_id bigint not null   constraint book_history_books_id_fk references books (id) on delete cascade,
    in_use_from date not null,
    in_use_to date not null
);

create unique index book_history_client_id_book_id_uindex
    on book_history (client_id, book_id);

create unique index book_history_use_id_uindex
    on book_history (id);


alter table books add column available bool not null default true;