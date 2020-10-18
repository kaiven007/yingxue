/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/9/22 10:33:20                           */
/*==============================================================*/


drop table if exists yx_admin;

drop table if exists yx_attention;

drop table if exists yx_category;

drop table if exists yx_collect;

drop table if exists yx_comment;

drop table if exists yx_feedback;

drop table if exists yx_group;

drop table if exists yx_history;

drop table if exists yx_img;

drop table if exists yx_like;

drop table if exists yx_play;

drop table if exists yx_user;

drop table if exists yx_video;

/*==============================================================*/
/* Table: yx_admin  管理员表                                            */
/*==============================================================*/
create table yx_admin
(
    id                   varchar(32) not null ,
    username             varchar(255) not null,
    password             varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_admin where id=1;
insert into yx_admin values();


/*==============================================================*/
/* Table: yx_attention  关注表                                         */
/*==============================================================*/
create table yx_attention
(
    id                   varchar(32) not null,
    from_user_id         varchar(32) not null,
    to_user_id           varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_attention;
insert into yx_attention values();



/*==============================================================*/
/* Table: yx_category  类别表                                          */
/*==============================================================*/
create table yx_category
(
    id                   varchar(32) not null,
    name                 varchar(255) not null,
    level                char(1),
    p_id                 varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_category;
insert into yx_category values();

/*==============================================================*/
/* Table: yx_collect   收藏表                                          */
/*==============================================================*/
create table yx_collect
(
    id                   varchar(32) not null,
    user_id              varchar(32) not null,
    source_id            varchar(32),
    collect_time         datetime,
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_collect;
insert into yx_collect values();

/*==============================================================*/
/* Table: yx_comment      评论表                                      */
/*==============================================================*/
create table yx_comment
(
    id                   varchar(32) not null,
    user_id              varchar(32) not null,
    source_id            varchar(32),
    content              varchar(1024),
    create_at            datetime,
    interact_id          varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_comment;
insert into yx_comment values();

/*==============================================================*/
/* Table: yx_feedback       反馈表                                    */
/*==============================================================*/
create table yx_feedback
(
    id                   varchar(32) not null,
    title                varchar(255) not null,
    content              varchar(1024),
    create_at            datetime,
    user_id              varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

/*==============================================================*/
/* Table: yx_group          分组表                                    */
/*==============================================================*/
create table yx_group
(
    id                   varchar(32) not null,
    name                 varchar(32) not null,
    user_id              varchar(32),
    video_num            int,
    create_at            datetime,
    primary key (id)
)engine=Innodb default charset=utf8;

select * from yx_group;
insert into yx_group values ('4','PPPP','2',null,'2020-9-15');


/*==============================================================*/
/* Table: yx_history              历史表                              */
/*==============================================================*/
create table yx_history
(
    id                   varchar(32) not null,
    user_id              varchar(32) not null,
    source_id            varchar(32),
    browse_time          datetime,
    primary key (id)
)engine=Innodb default charset=utf8;

/*==============================================================*/
/* Table: yx_img               图片表                                 */
/*==============================================================*/
create table yx_img
(
    id                   varchar(32) not null,
    img_url              varchar(255) not null,
    intro                varchar(1024),
    publish_time         datetime,
    user_id              varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

/*==============================================================*/
/* Table: yx_like               点赞表                                */
/*==============================================================*/
create table yx_like
(
    id                   varchar(32) not null,
    user_id              varchar(32) not null,
    source_id            varchar(32),
    like_num             int,
    create_at            datetime,
    primary key (id)
)engine=Innodb default charset=utf8;

/*==============================================================*/
/* Table: yx_play               播放表                                */
/*==============================================================*/
create table yx_play
(
    id                   varchar(32) not null,
    video_id             varchar(32) not null,
    play_num             int,
    primary key (id)
)engine=Innodb default charset=utf8;

/*==============================================================*/
/* Table: yx_user                  用户表                             */
/*==============================================================*/
create table yx_user
(
    id                   varchar(32) not null,
    username             varchar(255) not null,
    mobile               varchar(11),
    sign                 varchar(64),
    head_show            varchar(255),
    status               char(1),
    reg_time             datetime,
    score                double,
    wechat               varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

select  * from yx_user;
insert into yx_user values ('1','飞鸟','123654879','非常好','1.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('2','飞鸟','123654879','非常好','2.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('3','飞鸟','123654879','非常好','3.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('4','飞鸟','123654879','非常好','4.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('5','飞鸟','123654879','非常好','5.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('6','飞鸟','123654879','非常好','6.jpg','1','2020-9-15',10,'123695847');
insert into yx_user values ('7','飞鸟','123654879','非常好','7.jpg','1','2020-9-15',10,'123695847');

/*==============================================================*/
/* Table: yx_video                视频表                              */
/*==============================================================*/
create table yx_video
(
    id                   varchar(32) not null,
    title                varchar(255) not null,
    intro                varchar(1024),
    cover_url            varchar(255),
    video_url            varchar(255),
    create_at            datetime,
    user_id              varchar(32),
    cid                  varchar(32),
    grp_id               varchar(32),
    primary key (id)
)engine=Innodb default charset=utf8;

insert into yx_video values ('3','广东','环境对对对','5.jpg','2.mp4','2020-9-15','3','3','3');


select v.id,v.title,v.intro,v.cover_url,v.video_url,v.create_at,u.username,c.name,g.name
from  yx_video v left join yx_user u
                           on v.id=u.id
                 left join yx_category c
                           on v.id=c.id
                 left join yx_group g
                           on v.id=g.id
    limit 0,2;


select * from yx_video;

alter table yx_attention add constraint FK_REFERENCE_YX_USER_FROM foreign key (from_user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_attention add constraint FK_REFERENCE_YX_USER_TO foreign key (to_user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_collect add constraint FK_Reference_15 foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_collect add constraint FK_Reference_16 foreign key (source_id)
    references yx_video (id) on delete restrict on update restrict;

alter table yx_collect add constraint FK_Reference_17 foreign key (source_id)
    references yx_img (id) on delete restrict on update restrict;

alter table yx_comment add constraint FK_fk_source_id foreign key (source_id)
    references yx_img (id) on delete restrict on update restrict;

alter table yx_comment add constraint FK_fk_source_id foreign key (source_id)
    references yx_video (id) on delete restrict on update restrict;

alter table yx_comment add constraint FK_fk_user_id foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_feedback add constraint FK_Reference_14 foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_history add constraint FK_Reference_11 foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_history add constraint FK_Reference_12 foreign key (source_id)
    references yx_video (id) on delete restrict on update restrict;

alter table yx_history add constraint FK_Reference_13 foreign key (source_id)
    references yx_img (id) on delete restrict on update restrict;

alter table yx_like add constraint FK_fk_like_user_id foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_like add constraint FK_fk_source_id foreign key (source_id)
    references yx_img (id) on delete restrict on update restrict;

alter table yx_like add constraint FK_fk_source_id foreign key (source_id)
    references yx_video (id) on delete restrict on update restrict;

alter table yx_play add constraint FK_fk_video_id foreign key (video_id)
    references yx_video (id) on delete restrict on update restrict;

alter table yx_video add constraint FK_fk_group_id foreign key (grp_id)
    references yx_group (id) on delete restrict on update restrict;

alter table yx_video add constraint FK_fk_user_id foreign key (user_id)
    references yx_user (id) on delete restrict on update restrict;

alter table yx_video add constraint FK_fk_video_category foreign key (cid)
    references yx_category (id) on delete restrict on update restrict;

