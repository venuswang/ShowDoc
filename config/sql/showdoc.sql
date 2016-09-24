#创建数据库showdoc
create database showdoc;

#使用刚刚创建的数据库showdoc
use showdoc;

#创建用户的账号密码表voucher
#statu:0表示该用户废除，1表示该用户的状态是激活   loginmethod:0表示其是系统注册用户，1表示qq登录用户
create table voucher(
id int(11) primary key auto_increment,
username varchar(255) unique not null,
password varchar(50),
statu int(11) default 1,
loginmethod int(11) default 0
);

#创建用户的信息表voucher_info
#picture:用户头像地址  skill:擅长的编程语言
create table voucher_info(
voucherid int(11) primary key,
picture varchar(255) default '/pic/default.jpg',
skill varchar(255),
email varchar(150),
registdate  date,
constraint info_voucher_key_id foreign key(voucherid) references voucher(id)
);

#创建项目表project
#projectpassword 私密项目设置密码  sortid 在展示中，数字越大，排序越后 pstatu:表示该项目是否删除，0表示删除，1表示存在
create table project(
id int(11) primary key auto_increment,
projectname varchar(255),
projectdesc varchar(255),
authorname varchar(255),
projectpassword varchar(50),
sortid int(11) default 99,
pstatu int(11) default 1,
pdate date
);

#子项目表
#sublevel(0-99 0表示根目录)  parentid：如果是根目录则为-1，否则上级为subprojectid
#subsortid(0-99 数字越大，排序越后) subpstatu: 表示该项目是否删除，0表示删除，1表示存在
#hassubproject 是否有子目录  0表示没有  1表示存在子目录
create table subproject(
subprojectid int(11) primary key auto_increment,
subprojectname varchar(255),
projectid int(11) not null,
sublevel int(11),
parentid int(11),
subsortid int(11) default 99,
subpstatu int(11) default 1,
subauthorname varchar(255),
subprojectpassword varchar(50),
subdate date,
hassubproject int(11),
constraint subproject_project_key_projectid foreign key(projectid) references project(id)
);

#创建页面表page
create table page (
pageid int(11) primary key auto_increment,
pageprojectid int(11) not null,
pagesubprejectid int(11) not null,
pageprojectpassword varchar(50),
pagestatu int(11) default 1,
pagedate date,
pagetitle varchar(255),
pagecontext MEDIUMTEXT,
pagesortid int(11) default 99,
pageauthorname varchar(255),
constraint page_project_key_pageprojectid foreign key(pageprojectid) references project(id),
constraint page_subproject_key_pagesubprejectid foreign key(pagesubprejectid) references subproject(subprojectid)
);

#创建项目与用户的对应关系
create table username_project(
pid int(11),
vname varchar(255),
primary key(pid,vname),
constraint K_V_ID foreign key(pid) references project(id),
constraint K_V_NAME foreign key(vname) references voucher(username)
);

insert into project(projectname) values ("test");
insert into subproject(subprojectname,projectid) values ("test",1);
update subproject set subprojectid = -1 where subprojectid=1;