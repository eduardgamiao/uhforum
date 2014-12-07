# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  topic_text                varchar(255),
  subject                   varchar(255),
  date_posted               timestamp,
  topic_id                  bigint,
  user_id                   bigint,
  constraint pk_post primary key (id))
;

create table subject (
  id                        bigint not null,
  name                      varchar(255),
  acronym                   varchar(255),
  constraint pk_subject primary key (id))
;

create table topic (
  id                        bigint not null,
  first_name                varchar(255),
  title                     varchar(255),
  tags                      varchar(255),
  topic_text                clob,
  views                     integer,
  images                    clob,
  videos                    clob,
  date_posted               timestamp,
  user_id                   bigint,
  subject_id                bigint,
  constraint pk_topic primary key (id))
;

create table user_info (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  avatar_url                varchar(255),
  constraint pk_user_info primary key (id))
;

create sequence post_seq;

create sequence subject_seq;

create sequence topic_seq;

create sequence user_info_seq;

alter table post add constraint fk_post_topic_1 foreign key (topic_id) references topic (id) on delete restrict on update restrict;
create index ix_post_topic_1 on post (topic_id);
alter table post add constraint fk_post_user_2 foreign key (user_id) references user_info (id) on delete restrict on update restrict;
create index ix_post_user_2 on post (user_id);
alter table topic add constraint fk_topic_user_3 foreign key (user_id) references user_info (id) on delete restrict on update restrict;
create index ix_topic_user_3 on topic (user_id);
alter table topic add constraint fk_topic_subject_4 foreign key (subject_id) references subject (id) on delete restrict on update restrict;
create index ix_topic_subject_4 on topic (subject_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists post;

drop table if exists subject;

drop table if exists topic;

drop table if exists user_info;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists post_seq;

drop sequence if exists subject_seq;

drop sequence if exists topic_seq;

drop sequence if exists user_info_seq;

