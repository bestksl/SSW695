create table hobby
(
    hobby_id       int auto_increment
        primary key,
    name           varchar(50) null,
    description    varchar(50) null,
    classification varchar(50) null,
    constrains     varchar(50) null,
    blogs          varchar(50) null,
    status         varchar(50) null,
    hobby_image    varchar(50) null,
    constraint hobby_name_uindex
        unique (name)
);

create table events
(
    events_id    int auto_increment
        primary key,
    events_title char(50)     null,
    events_time  datetime     null,
    location     char(50)     null,
    description  varchar(255) null,
    fee          char(50)     null,
    holder       char(50)     null,
    events_image varchar(200) null,
    hobby_id     int          null,
    constraint events_hobby_hobby_id_fk
        foreign key (hobby_id) references hobby (hobby_id)
            on update cascade on delete cascade
);

create table user
(
    id         int auto_increment
        primary key,
    nick_name  varchar(50)  not null,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    email      varchar(50)  not null,
    pass_word  varchar(50)  not null,
    dob        date         null,
    gender     varchar(50)  null,
    status     varchar(50)  null,
    hobbies    varchar(50)  null,
    events     varchar(100) null,
    constraint user_email_uindex
        unique (email)
);

create table join_events
(
    join_events_id int auto_increment
        primary key,
    user_id        int null,
    events_id      int null,
    constraint join_events_events_events_id_fk
        foreign key (events_id) references events (events_id)
            on update cascade on delete cascade,
    constraint join_events_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);


