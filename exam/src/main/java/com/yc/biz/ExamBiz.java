package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Exam;

public interface ExamBiz {

	public List<Exam> searchAllId();

	public List<Exam> searchQuestions(List<Integer> ids);

	public List<Exam> getQuestions(List<Exam> list);

}
