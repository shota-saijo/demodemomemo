# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                            bigint not null,
  task_id                       bigint,
  content                       varchar(255),
  user_id                       bigint,
  is_closed                     boolean,
  constraint pk_comment primary key (id)
);
create sequence comment_seq;

create table label (
  id                            bigint not null,
  content                       varchar(255),
  project_id                    bigint,
  constraint pk_label primary key (id)
);
create sequence label_seq;

create table member (
  id                            bigint not null,
  project_id                    bigint,
  user_id                       bigint not null,
  role                          varchar(6),
  constraint ck_member_role check (role in ('admin','public')),
  constraint pk_member primary key (id)
);
create sequence member_seq;

create table milestone (
  id                            bigint not null,
  project_id                    bigint,
  start_date                    date,
  end_date                      date,
  constraint pk_milestone primary key (id)
);
create sequence milestone_seq;

create table project (
  id                            bigint not null,
  name                          varchar(255) not null,
  description                   varchar(1020),
  user_id                       bigint not null,
  constraint pk_project primary key (id)
);
create sequence project_seq;

create table task (
  id                            bigint not null,
  title                         varchar(255),
  contents                      varchar(1020),
  status                        varchar(12),
  project_id                    bigint,
  user_id                       bigint,
  milestone_id                  bigint,
  constraint ck_task_status check (status in ('complete','before_start','processing','before_check','start','checking','checked')),
  constraint uq_task_milestone_id unique (milestone_id),
  constraint pk_task primary key (id)
);
create sequence task_seq;

create table task_label (
  id                            bigint not null,
  task_id                       bigint,
  label_id                      bigint,
  constraint pk_task_label primary key (id)
);
create sequence task_label_seq;

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

alter table comment add constraint fk_comment_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_comment_user_id on comment (user_id);

alter table label add constraint fk_label_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_label_project_id on label (project_id);

alter table member add constraint fk_member_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_member_project_id on member (project_id);

alter table member add constraint fk_member_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_member_user_id on member (user_id);

alter table milestone add constraint fk_milestone_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_milestone_project_id on milestone (project_id);

alter table project add constraint fk_project_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_project_user_id on project (user_id);

alter table task add constraint fk_task_project_id foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_id on task (project_id);

alter table task add constraint fk_task_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_task_user_id on task (user_id);

alter table task add constraint fk_task_milestone_id foreign key (milestone_id) references milestone (id) on delete restrict on update restrict;

alter table task_label add constraint fk_task_label_task_id foreign key (task_id) references task (id) on delete restrict on update restrict;
create index ix_task_label_task_id on task_label (task_id);

alter table task_label add constraint fk_task_label_label_id foreign key (label_id) references label (id) on delete restrict on update restrict;
create index ix_task_label_label_id on task_label (label_id);


# --- !Downs

alter table comment drop constraint if exists fk_comment_task_id;
drop index if exists ix_comment_task_id;

alter table comment drop constraint if exists fk_comment_user_id;
drop index if exists ix_comment_user_id;

alter table label drop constraint if exists fk_label_project_id;
drop index if exists ix_label_project_id;

alter table member drop constraint if exists fk_member_project_id;
drop index if exists ix_member_project_id;

alter table member drop constraint if exists fk_member_user_id;
drop index if exists ix_member_user_id;

alter table milestone drop constraint if exists fk_milestone_project_id;
drop index if exists ix_milestone_project_id;

alter table project drop constraint if exists fk_project_user_id;
drop index if exists ix_project_user_id;

alter table task drop constraint if exists fk_task_project_id;
drop index if exists ix_task_project_id;

alter table task drop constraint if exists fk_task_user_id;
drop index if exists ix_task_user_id;

alter table task drop constraint if exists fk_task_milestone_id;

alter table task_label drop constraint if exists fk_task_label_task_id;
drop index if exists ix_task_label_task_id;

alter table task_label drop constraint if exists fk_task_label_label_id;
drop index if exists ix_task_label_label_id;

drop table if exists comment;
drop sequence if exists comment_seq;

drop table if exists label;
drop sequence if exists label_seq;

drop table if exists member;
drop sequence if exists member_seq;

drop table if exists milestone;
drop sequence if exists milestone_seq;

drop table if exists project;
drop sequence if exists project_seq;

drop table if exists task;
drop sequence if exists task_seq;

drop table if exists task_label;
drop sequence if exists task_label_seq;

drop table if exists user;
drop sequence if exists user_seq;

