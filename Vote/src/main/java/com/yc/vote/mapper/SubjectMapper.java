package com.yc.vote.mapper;

import java.util.List;

import com.yc.vote.entity.Subject;

public interface SubjectMapper {

	List<Subject> getSubjectAll();

	Subject getSubjectById(int vsId);

}
