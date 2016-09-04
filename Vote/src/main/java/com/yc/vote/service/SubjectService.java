package com.yc.vote.service;

import java.util.List;

import com.yc.vote.entity.Subject;

public interface SubjectService {

	List<Subject> listAll();

	Subject listSubjectById(int vsId);

}
