create table users(
	id int primary key,
	name varchar2(20) not null,
	sex varchar2(4)
)
select * from users;
drop table users;

create sequence seq_users_id start with  1001;
drop sequence seq_users_id;

insert into users values(seq_users_id.nextval,'nana','女');

create table courses(
	courseId int primary key,
	courseName varchar2(20) not null,
	userid int
		constraint FK_userid references users(id)
)
drop table courses;
select * from courses;

insert into courses values(101,'计算机网络',1001);
insert into courses values(102,'操作系统',1001);
insert into courses values(103,'数据结构',1001);
insert into courses values(104,'编译原理',1001);

SELECT id, name,sex FROM users where id=1001

SELECT SEQ_USERS_ID.NEXTVAL FROM DUAL;

--http://blog.csdn.net/luanlouis/article/details/35780175
