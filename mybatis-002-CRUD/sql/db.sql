
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
--1.子查询的方式
select * from teacher where id = 1001;
select * from student where supervisorid=1001;

--2.连接查询
select * from teacher t join student s  on t.id = 1001 and t.id = s.supervisorid;



