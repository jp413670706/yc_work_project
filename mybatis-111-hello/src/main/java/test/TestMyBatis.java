package test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import com.yc.bean.Student;

/***
 * 该系列的测试是基于sql语句保存在xml配置文件中，
 * 利用session来执行和操作sql语句的。
 * @author yc
 *
 */
public class TestMyBatis {

  SqlSessionFactory sqlSessionFactory;
  
  @Before
  public void before() throws Exception {
    String resource = "mybatis_config.xml";
    InputStream inputStrem = Resources.getResourceAsStream(resource);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStrem);
  }
  

  
  @Test
  public void testAdd()  {
    SqlSession session = sqlSessionFactory.openSession();
    Student student = new Student();
    student.setId(1001);
    student.setName("admin");
    student.setAge(20);
    student.setSex("n");    
    session.insert("add", student);
    session.commit();
    session.close();
  }
  
  @Test
  public void listAllStudents() {
    SqlSession session = sqlSessionFactory.openSession();    
    List<Student> students = session.selectList("com.yc.service.StudentMapper.listAllStudents");
    for(Student student : students) {
      System.out.println(student);
    }
    session.commit();
    session.close();
  }
  

  @Test
  public void testUpadte() {
    SqlSession session = sqlSessionFactory.openSession();
    Student student = session.selectOne("com.yc.service.StudentMapper.loadById", 2);
    student.setName("正儿");
    session.update("com.yc.service.StudentMapper.update", student);  
    session.commit();
    session.close();
  }
  @Test
  public void testDelete() {
    SqlSession session = sqlSessionFactory.openSession();
    session.delete("com.yc.service.StudentMapper.delete", 3);
    session.commit();
    session.close();
  }
}
