# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                            varchar(255) not null,
  task_id                       bigint,
  content                       varchar(255),
  constraint pk_comment primary key (id)
);

create table member (
  id                            varchar(255) not null,
  project_id                    bigint not null,
  user_id                       bigint not null,
  role                          integer,
  constraint ck_member_role check (role in (0,1)),
  constraint pk_member primary key (id)
);

create table project (
  id                            bigint not null,
  name                          varchar(255) not null,
  description                   varchar(255),
  constraint pk_project primary key (id)
);
create sequence project_seq;

create table task (
  id                            bigint not null,
  title                         varchar(255),
  contents                      varchar(255),
  project_id                    bigint not null,
  member_id                     varchar(255),
  status                        integer,
  constraint ck_task_status check (status in ()),
  constraint pk_task primary key (id)
);
create sequence task_seq;

create table user (
  id                            bigint not null,
  account_id                    varchar(255) not null,
  password                      varchar(255),
  constraint uq_user_account_id unique (account_id),
  constraint pk_user primary key (id)
);
create sequence user_seq;

alter table comment add constraint fk_comment_task_id foreign key (task_id) references task (id) on delete restrict on update restrict;
create index ix_comment_task_id on comment (task_id);

alter table member add constraint fk_member_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_member_project_id on member (project_id);

alter table member add constraint fk_member_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_member_user_id on member (user_id);

alter table task add constraint fk_task_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_id on task (project_id);

alter table task add constraint fk_task_member_id foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_task_member_id on task (member_id);


# --- !Downs

alter table comment drop constraint if exists fk_comment_task_id;
drop index if exists ix_comment_task_id;

alter table member drop constraint if exists fk_member_project_id;
drop index if exists ix_member_project_id;

alter table member drop constraint if exists fk_member_user_id;
drop index if exists ix_member_user_id;

alter table task drop constraint if exists fk_task_project_id;
drop index if exists ix_task_project_id;

alter table task drop constraint if exists fk_task_member_id;
drop index if exists ix_task_member_id;

drop table if exists comment;

drop table if exists member;

drop table if exists project;
drop sequence if exists project_seq;

drop table if exists task;
drop sequence if exists task_seq;

drop table if exists user;
drop sequence if exists user_seq;

