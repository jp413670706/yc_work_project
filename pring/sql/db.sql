--book
create table book(
	isbn varchar2(40) primary key,
	bookname varchar2(40),
	price number
);

--bookstock
create table bookstock(
	isbn  varchar2(40) primary key,
	stock number  check(stock > 0)
);

--account
create table account(
	username varchar2(20) primary key,
	balance number check(balance > 0)
);


insert into book values('yc10000', 'Java編程', 50);
insert into book values('yc10001', 'SSM整合大全', 150);

insert into bookstock values('yc10000', 2);
insert into bookstock values('yc10001', 3);

insert into ACCOUNT values('admin', 120);

select * from book;
select * from ACCOUNT;
select * from bookstock;

update bookstock set stock = 3;

update account set balance = 120 where username = 'admin';