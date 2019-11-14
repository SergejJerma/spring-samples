create table comment (id int8 not null, body varchar(255), create_date timestamp, user_id int8, message_id int4, primary key (id))
create table message (id int4 not null, filename varchar(255), tag varchar(255), text varchar(255), user_id int8, primary key (id))
create table user_role (user_id int8 not null, roles varchar(255))
create table usr (id int8 not null, activation_code varchar(255), active boolean not null, email varchar(255), password varchar(255), username varchar(255), primary key (id))
alter table if exists comment add constraint FKgcgdcgly6u49hf4g8y2di3g4p foreign key (user_id) references usr
alter table if exists comment add constraint FKatlrxw2dnvma9h401t2ql2ri8 foreign key (message_id) references message
alter table if exists message add constraint FK70bv6o4exfe3fbrho7nuotopf foreign key (user_id) references usr
alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr