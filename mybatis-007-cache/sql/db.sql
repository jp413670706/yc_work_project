
create table blog(
	id Integer primary key,
	title varchar2(20),
	author varchar2(20)
)


insert into blog values(1, 'mybatis好玩', 'jp');

select * from blog;

-- 1 对 多 的关系rerere
select * from student;
select * from teacher;


---根据老师编号(1001)查找， 老师的指导学生, 获得老师和学生信息
--1).子查询的方式
select * from teacher where id = 1001;
select * from student where supervisorid=1001;

--2).连接查询
select  t.*, s.id sid, s.name sname, s.gender sgender, s.*  
from teacher t, student s where  t.id = s.supervisorid and  t.id = 1001;

select  t.*, s.id sid, s.name sname, s.gender sgender, s.*  
from teacher t, student s where t.id = 1001  and  t.id = s.supervisorid;

select  t.*, s.id sid, s.name sname, s.gender sgender, s.*  
from teacher t join student s  on  t.id = s.supervisorid where  t.id = 1001;

select  t.*, s.id sid, s.name sname, s.gender sgender, s.*  
from teacher t join student s  on  t.id = s.supervisorid and  t.id = 1001;

select  t.*, s.id sid, s.name sname, s.gender sgender, s.*  
from teacher t join student s  on    t.id = 1001 and t

--2.多对1
---根据学生编号(1)查找， 学生的指导老师, 获得老师和学生信息
--嵌套查询
	select * from student where  id = 1; --获得学生信息和老师编号 supervisorid 1001
	select * from teacher where id = 1001;	
	
--嵌套结果
	select * from student s join teacher t on s.id=1 and s.supervisorid = t.id;


select * from student;
select * from course;
select * from studentcourse;

--通过课程(1)找学生, 获得课程和学生信息
--嵌套查询
	select * from course where id = 1;
	select * from student where id in (select studentid from STUDENTCOURSE where courseid = 1);--(studentid : 4 6 7 8)
	
--嵌套结果

	
--通过课程(1)找学生, 通过学生找到指导老师, 获得课程,学生, 指导老师信息
	
	
---通过title模糊查询blog记录
	select * from blog where title like '%玩%';
	
	--分页操作: 页码， 页的数据条数
	
	--一页的数据, 最大页数
	
create sequence seq_blog start with 1000;
	
insert into blog 
select seq_blog.nextval,  
dbms_random.string('l', dbms_random.value(6, 20)),   
dbms_random.string('l', dbms_random.value(2, 6)) from dual
connect by level <= 1000;


select * from blog;

select * from
(select n.*, rownum rn from 
(select * from  blog) n where rownum <= numsize * numpage) where rn > numsize * (numpage - 1)


select count(1) from blog;

select (select count(1) from blog) totalSize, nn.* from
(select n.*, rownum rn from 
(select * from  blog) n where rownum <= 10 * 1) nn where rn > 10 * (1 - 1);

