
create table blog(
	id Integer primary key,
	title varchar2(20),
	author varchar2(20)
)


insert into blog values(1, 'mybatis好玩', 'jp');

select * from blog;