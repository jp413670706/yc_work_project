create sequence seq_downloadfile;

create table downloadfile(
    id int primary key,            //记录的编号
    downpath varchar2(1000),       //下载的文件的url地址
    threadid    int ,              //线程编号
    downlength  int               //当前线程下载了多少数据
)


--刚下载的数据
insert into downloadfile values( seq_downloadfile,'', 0, 0);

--下载进行中，不断的修改这个线程的数据(主要是下载的长度数据);
update downloadfile set downlength=xxx where downpath=? and threadid=? and id=?


--下载完成后，删除这个线程的记录
delete from downloadfile where id=?  and downpath=?

--当断点续传时，要查询这个资源的每个线程下载的数据
select * from downloadfile where downpath=?


1    http://www.qq.com/qq.exe  1  55
2    http://www.qq.com/qq.exe  2  100
3    http://www.qq.com/qq.exe  3  400
