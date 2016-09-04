package com.yc.web.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.bean.Exam;
import com.yc.biz.ExamBiz;
import com.yc.common.YcConstants;

//       implements ServletContextListener
public class InitListener extends  CommonListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ExamBiz tb=(ExamBiz) super.getObjectFromApplication(arg0.getServletContext(), "examBiz");
		List<Exam> list=tb.searchAllId();
		arg0.getServletContext().setAttribute(YcConstants.ALLEXAM, list);
	}

}
