--创建库
create database examination;
--使用库
use examination;
--在这个库中创建表
create table exam(
	eid int primary key auto_increment unique,
	question varchar(3000),
	a varchar(3000),
	b varchar(3000),
	c varchar(3000),
	d varchar(3000),
	answer int
);


insert into exam(question,a,b,c,d,answer) values( '以下()表达式产生一个0~7之间(含0,7)的随机整数. ','Math.floor(Math.random()*6) ','Math.floor(Math.random()*7)','Math. floor(Math.random()*8)','Math.ceil(Math.random()*8)',3    );
insert into exam(question,a,b,c,d,answer) values('产生当前日期的方法是','Now()','Date()','new Date()','new Now()',3 );
insert into exam(question,a,b,c,d,answer) values('在Javascript中要改变页面文档的背景色，需要修改ocument对象的（）属性。', 'BackColor','BackgroundColor','BgColor','Background',3);
insert into exam(question,a,b,c,d,answer) values('在Javascript浏览器对象模型中，window对象的（）属性用来指定浏览器状态栏中显示的临时消息。','status','screen','history ','document',1);
insert into exam(question,a,b,c,d,answer) values('下列JS的判断语句中( )是正确的','if(i==0)','if(i=0)	','if i==0 then','if i=0 then', 1);
insert into exam(question,a,b,c,d,answer) values('JS特性不包括','解释性','用于客户端','基于对象','面向对象', 4);
insert into exam(question,a,b,c,d,answer) values('下列选项中,( )不是网页中的事件','onclick','onmouseover','onsubmit','onpressbutton',4 );
insert into exam(question,a,b,c,d,answer) values('JS语句  <br />var a1=10;<br />var a2=20;<br />alert(“a1+a2=”+a1+a2)<br />将显示( )结果','a1+a2=30','a1+a2=1020','a1+a2=a1+a2','都不显示',2 );
insert into exam(question,a,b,c,d,answer) values('如果想在网页显示后,动态地改变网页的标题','是不可能的','通过document.write(“新的标题内容”)	','通过document.title=(“新的标题内容”)','通过document.changeTitle(“新的标题内容”)',3 );
insert into exam(question,a,b,c,d,answer) values('HTML文档的树状结构中，（）标签为文档的根节点，位于结构中的最顶层','html','head','body','table', 1 );


--添加10w个题目...
insert into exam(question,a,b,c,d,answer)
select  concat('question contents: ',FLOOR(RAND() * 9999999)) as question,
                  left(uuid(),3),
                  right(uuid(),3),
                 FLOOR(RAND() * 9999),
                 FLOOR(RAND() * 99999),
                 FLOOR(1 + (RAND() * 4));
                 
                 
 -----------创建存储过程.               
 create procedure proc1()
 begin
	 declare v_cnt decimal(10) default 0;
	 dd:loop
	  insert into exam(question,a,b,c,d,answer)
				select  concat('question contents: ',FLOOR(RAND() * 9999999)) as question,
                  	left(uuid(),3),
                  	right(uuid(),3),
                	FLOOR(RAND() * 9999),
                 	FLOOR(RAND() * 99999),
                 	FLOOR(1 + (RAND() * 4));
        commit;
        set v_cnt=v_cnt+1;
        if  v_cnt=12 then leave dd;
        end if;
     end loop dd;
 end;
 
 --调用存储过程
 call proc1();
	 
 
 
 select count(*) from exam;

           
