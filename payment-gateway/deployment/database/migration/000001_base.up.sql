CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

----------------------------
------- Custom Types -------
----------------------------
CREATE TYPE card_action AS ENUM ('CREATED', 'UPDATED', 'DELETED');
CREATE TYPE payment_event AS ENUM ('CARD_TRANSFER', 'BILL', 'PHONE_CHARGE', 'NET_CHARGE',
    'WALLET_CHARGE', 'WALLET_TRANSFER', 'CHARITY');
-- TODO wallet has more than 7 events that we can add them to payment_event, but I suffice for this sample to use two of them.

----------------------------
---------- Tables ----------
----------------------------
create table user_info
(
    id           uuid,
    first_name   varchar     not null,
    last_name    varchar     not null,
    token        text        not null,
    phone_number varchar     not null,
    created_at   timestamptz not null,
    updated_at   timestamptz not null,
    primary key (id)
);

create table card
(
    id         uuid,
    number     text        not null,
    cvv2       varchar     not null,
    expire     varchar     not null,
    user_id    uuid        not null,
    created_by uuid        not null,
    deleted_at timestamptz,
    created_at timestamptz not null,
    updated_at timestamptz not null,
    primary key (id),
    constraint user_card_fk foreign key (user_id) REFERENCES user_info (id) on delete cascade
);

create table card_history
(
    id         uuid default uuid_generate_v4(),
    card_id    uuid        not null,
    number     text        not null,
    cvv2       varchar     not null,
    expire     varchar     not null,
    created_by uuid        not null,
    action     card_action not null,
    created_at timestamptz,
    primary key (id),
    constraint card_history_card_fk foreign key (card_id) REFERENCES card (id) on delete cascade
);

create table event_log
(
    id             uuid,
    user_id        uuid          not null,
    ip_address     varchar       not null,
    client_version varchar       not null,
    app_version    varchar       not null,
    event          payment_event not null,
    info           json          not null,
    created_at     timestamptz   not null,
    primary key (id)
);

----------------------------
--------- Indices ---------
----------------------------
CREATE UNIQUE INDEX card_number ON card (number);

----------------------------
--------- Triggers ---------
----------------------------
CREATE OR REPLACE FUNCTION create_card()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS
$$
BEGIN
    insert into card_history (card_id, number, cvv2, expire, created_by, action, created_at)
    values (NEW.id, NEW.number, NEW.cvv2, NEW.expire, NEW.created_by, 'CREATED', now());

    RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION update_card()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS
$$
BEGIN
    if NEW.deleted_at <> OLD.deleted_at then
        insert into card_history (card_id, number, cvv2, expire, created_by, action, created_at)
        values (NEW.id, NEW.number, NEW.cvv2, NEW.expire, NEW.created_by, 'DELETED', now());
    else
        insert into card_history (card_id, number, cvv2, expire, created_by, action, created_at)
        values (NEW.id, NEW.number, NEW.cvv2, NEW.expire, NEW.created_by, 'UPDATED', now());
    end if;

    RETURN NEW;
END;
$$;

CREATE TRIGGER insert_cards
    AFTER INSERT
    ON card
    FOR EACH ROW
EXECUTE PROCEDURE create_card();

CREATE TRIGGER update_cards
    AFTER UPDATE
    ON card
    FOR EACH ROW
EXECUTE PROCEDURE update_card();

----------------------------
------- Seed Data--------
----------------------------
insert into user_info (id, first_name, last_name, token, created_at, updated_at, phone_number)
values (uuid_generate_v4(), 'Hamed', 'Yousefi',
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c',
        now(), now(), '09023216354'),
       (uuid_generate_v4(), 'Ali', 'Zand',
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c',
        now(), now(), '09123216354');