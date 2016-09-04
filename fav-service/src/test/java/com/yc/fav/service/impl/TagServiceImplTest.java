package com.yc.fav.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.fav.entity.Tag;
import com.yc.fav.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TagServiceImplTest {
	@Autowired
	private TagService tagService;
	
	@Test
	public void testFindAllTags() {
		List<Tag> tags = tagService.findAllTags();
		System.out.println(tags);
		assertNotNull(tags);
	}

}
