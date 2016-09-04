
--创建用户表
create table users(
	id integer primary key,
	username varchar2(20) not null unique,
	password varchar2(40) not null
);

--插入一条记录
delete from users;
insert into users values(1001, 'admin', '6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2');

--查看当前用户下的所有表
select * from user_tables;
select * from users;

--创建序列
create sequence seq_users start with 1002;

--模拟数据 
insert into users
select seq_users.nextval, dbms_random.string('l', dbms_random.value(3, 20)), 
'6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2' from dual connect by level < 1000; 

select * from (select * from users order by 1) where rownum < 20 ;

select ceil(1000/20) from dual;

alter table users
add picPath varchar2(100);