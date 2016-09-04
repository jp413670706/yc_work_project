select * from user_tables;

select * from topic;

select * from news;

select * from
(select n.*, rownum rn from (select * from news order by 5) n where rownum <= pageSize * pageNum)
where rn > pageSize * (pageNum - 1);

select * from news where ntid in (1, 2, 5) order by 2, 5;

select * from (select * from news where ntid = 1 order by 5)  where rownum <= 5 union
select * from (select * from news where ntid = 2 order by 5)  where rownum <= 5 union
select * from (select * from news where ntid = 5 order by 5)  where rownum <= 5;

select * from news where nid in (select nid from (select * from news where ntid = 1 order by 5)  where rownum <= 5)
or  nid in (select nid from (select * from news where ntid = 2 order by 5)  where rownum <= 5)
or nid in (select nid from (select * from news where ntid = 5 order by 5)  where rownum <= 5);


select * from NEWS_USERS;
