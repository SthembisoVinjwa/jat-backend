alter default privileges grant all on tables to jobapptracker;
alter default privileges grant all on sequences to jobapptracker;

create table users(
    user_id integer primary key not null,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    email varchar(30) not null,
    password text not null
);

create table applications(
    application_id integer primary key not null,
    user_id integer not null,
    company varchar(60) not null,
    role varchar(60) not null,
    status varchar(20) default 'Not-Applied',
    link text not null,
    date_of_application date,
    notes text not null
);

alter table applications add constraint user_app_fk
foreign key (user_id) references users(user_id);

create sequence users_sequence increment 1 start 1;
create sequence applications_sequence increment 1 start 1;

