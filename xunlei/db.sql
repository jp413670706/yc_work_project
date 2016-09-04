create sequence seq_downloadfile;

create table downloadfile(
    id int primary key,            //��¼�ı��
    downpath varchar2(1000),       //���ص��ļ���url��ַ
    threadid    int ,              //�̱߳��
    downlength  int               //��ǰ�߳������˶�������
)


--�����ص�����
insert into downloadfile values( seq_downloadfile,'', 0, 0);

--���ؽ����У����ϵ��޸�����̵߳�����(��Ҫ�����صĳ�������);
update downloadfile set downlength=xxx where downpath=? and threadid=? and id=?


--������ɺ�ɾ������̵߳ļ�¼
delete from downloadfile where id=?  and downpath=?

--���ϵ�����ʱ��Ҫ��ѯ�����Դ��ÿ���߳����ص�����
select * from downloadfile where downpath=?


1    http://www.qq.com/qq.exe  1  55
2    http://www.qq.com/qq.exe  2  100
3    http://www.qq.com/qq.exe  3  400
