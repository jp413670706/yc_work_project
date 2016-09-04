package com.yc.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yc.bean.Exam;
import com.yc.dao.ExamDao;

public class ExamDaoMybatisImpl implements ExamDao {

	private SqlSessionTemplate sqlSession;
	


	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Exam> searchAllId() {
		return sqlSession.selectList("com.yc.dao.examDaoMapper.searchAllId");
	}

	public List<Exam> searchQuestions(List<Integer> ids) {
		return sqlSession.selectList("com.yc.dao.examDaoMapper.searchQuestions", ids);
	}


	
	
	

}
