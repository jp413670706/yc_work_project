create table tag
(
	tid number primary key,
	tname varchar2(100) not null,
	tcount number check(tcount > 0)
);

create table favorite(
	fid number primary key,
	flabel varchar2(100) not null,
	furl varchar2(255) not null,
	ftags varchar2(100),
	fdesc varchar2(1024)
);

insert into tag
select 1, 'java', 2 from dual union
select 2, 'struts', 1 from dual union
select 3, 'oracle', 1 from dual ;

insert into favorite 
select 1, 'Apache Struts', 'http://struts.apache.org', 'java,struts', 'struts官方网站' from dual union
select 2, 'Oracle', 'http://www.oracle.com', 'java,oracle', 'oracle官方网站' from dual;

select * from tag;
select * from favorite;