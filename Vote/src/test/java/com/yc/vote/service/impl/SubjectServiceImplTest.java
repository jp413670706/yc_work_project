package com.yc.vote.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.vote.entity.Subject;
import com.yc.vote.service.SubjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SubjectServiceImplTest {
	
	@Autowired
	private SubjectService subjectService;
	
	@Test
	public void testListAll() {
		List<Subject> subjects = subjectService.listAll();
		System.out.println(subjects);
		assertNotNull(subjects);
	}
	
	@Test
	public void testListSubjectById() {
		Subject subject = subjectService.listSubjectById(103);
		System.out.println(subject);
		assertNotNull(subject);
	}

}
