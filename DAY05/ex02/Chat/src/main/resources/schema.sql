create table if not exists users (
    id             serial primary key,
    login          text not null,
    passwd         text
);

create table if not exists room (
    id              serial primary key ,
    chat_name       text not null ,
    chat_owner      int not null references users(id)
);

create table if not exists messages (
    id               serial primary key ,
    room_id          int not null references room(id) ,
    author           int not null references users(id) ,
    message         text not null ,
    time            timestamp
);