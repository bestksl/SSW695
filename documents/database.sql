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

create table sys_permission
(
    id        int auto_increment
        primary key,
    perm_name varchar(50) null,
    permTag   varchar(50) null
);

create table sys_role
(
    id        int auto_increment
        primary key,
    role_name varchar(50) not null,
    role_desc int         null
);

create table sys_role_permission
(
    role_id int null,
    perm_id int null,
    constraint sys_role_permission_sys_permission_id_fk
        foreign key (perm_id) references sys_permission (id),
    constraint sys_role_permission_sys_role_id_fk
        foreign key (role_id) references sys_role (id)
);

create table user
(
    id                      int auto_increment
        primary key,
    nick_name               varchar(50)   not null,
    first_name              varchar(50)   not null,
    last_name               varchar(50)   not null,
    email                   varchar(50)   not null,
    pass_word               varchar(200)  not null,
    dob                     date          null,
    gender                  varchar(50)   null,
    hobbies                 varchar(50)   null,
    events                  varchar(100)  null,
    status                  varchar(50)   null,
    last_login_time         int           null,
    enabled                 int default 1 null,
    credentials_non_expired int default 1 null,
    account_not_locked      int default 1 null,
    account_not_expired     int default 1 null,
    constraint user_email_uindex
        unique (email)
);

create table blog
(
    blog_id     int auto_increment
        primary key,
    title       varchar(50)  not null,
    content     varchar(500) not null,
    hobby_id    int          not null,
    user_id     int          not null,
    create_date varchar(50)  null,
    constraint blog_hobby_hobby_id_fk
        foreign key (hobby_id) references hobby (hobby_id),
    constraint blog_user_id_fk
        foreign key (user_id) references user (id)
);

create table comment
(
    id      int auto_increment
        primary key,
    content varchar(500) not null,
    user_id int          not null,
    blog_id int          not null,
    time    varchar(50)  null,
    constraint comment_blog_blog_id_fk
        foreign key (blog_id) references blog (blog_id),
    constraint comment_user_id_fk
        foreign key (user_id) references user (id)
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

create table sys_user_role
(
    user_id int null,
    role_id int null,
    constraint sys_user_role_sys_role_id_fk
        foreign key (role_id) references sys_role (id),
    constraint sys_user_role_user_id_fk
        foreign key (user_id) references user (id)
);

