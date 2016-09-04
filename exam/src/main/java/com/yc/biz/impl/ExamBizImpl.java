package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.yc.bean.Exam;
import com.yc.biz.ExamBiz;
import com.yc.dao.ExamDao;

public class ExamBizImpl implements ExamBiz {
	
	private ExamDao examDao;
	public static final int count=20;

	public List<Exam> searchAllId() {
		return examDao.searchAllId();
	}

	public List<Exam> searchQuestions(List<Integer> ids) {
		return examDao.searchQuestions(ids);
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}

	public List<Exam> getQuestions(List<Exam> list) {
		Random r=new Random();
		List<Exam> result=new ArrayList<Exam>();
		Exam t=null;
		for( int i=0;i<count;i++){
			int index=r.nextInt(   list.size()-i );
			t=list.get(index);
			result.add( t );
			list.set(index,    list.get(list.size()-1-i));
			list.set(list.size()-1-i, t);
		}
		return result;
	}

	
	public static void main(String[] args){
		List<Exam> list=new ArrayList<Exam>();
		for( int i=1;i<12;i++)
			list.add( new Exam( i ) );
		
		ExamBizImpl bii=new ExamBizImpl();
		List<Exam> l=bii.getQuestions(    list );
		System.out.println(  l );
	}
	

}
