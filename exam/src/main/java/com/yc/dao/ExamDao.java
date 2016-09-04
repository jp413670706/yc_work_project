package com.yc.dao;

import java.util.List;

import com.yc.bean.Exam;

public interface ExamDao {
	
	public List<Exam> searchAllId();
	
	
	public List<Exam> searchQuestions(List<Integer> ids);
}
