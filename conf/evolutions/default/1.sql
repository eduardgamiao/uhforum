# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  topic_text                varchar(255),
  subject                   varchar(255),
  constraint pk_post primary key (id))
;

create table topic (
  id                        bigint not null,
  first_name                varchar(255),
  title                     varchar(255),
  tags                      varchar(255),
  topic_text                varchar(255),
  subject                   varchar(255),
  views                     integer,
  constraint pk_topic primary key (id))
;

create table user_info (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user_info primary key (id))
;

create sequence post_seq;

create sequence topic_seq;

create sequence user_info_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists post;

drop table if exists topic;

drop table if exists user_info;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists post_seq;

drop sequence if exists topic_seq;

drop sequence if exists user_info_seq;

