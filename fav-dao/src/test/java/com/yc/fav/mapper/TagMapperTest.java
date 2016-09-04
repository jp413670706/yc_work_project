package com.yc.fav.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.fav.entity.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
// 指明使用spring的测试框架来进行单元测试
@ContextConfiguration("classpath:spring.xml")
// 使用的spring的配制文件
public class TagMapperTest {
	
	@Autowired
	private TagMapper tagMapper;

	@Test
	public void testGetAllTags() {
		List<Tag> tags = tagMapper.getAllTags();
		System.out.println(tags);
		assertNotNull(tags);
	}

}
