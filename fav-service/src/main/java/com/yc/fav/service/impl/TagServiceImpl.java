package com.yc.fav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.fav.entity.Tag;
import com.yc.fav.mapper.TagMapper;
import com.yc.fav.service.TagService;


@Service("tagService")  
//@Component我是一个bean， @Controller我是一个控制层的bean, @Repository我是一个数据访问层的bean, @Service我是一个业务处理层的bean
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagMapper tagMapper;
	
	
	public List<Tag> findAllTags() {
		return tagMapper.getAllTags();
	}

}
