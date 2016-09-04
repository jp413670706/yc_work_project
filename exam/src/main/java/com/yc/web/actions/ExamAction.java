package com.yc.web.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.bean.Exam;
import com.yc.bean.JsonModel;
import com.yc.biz.ExamBiz;
import com.yc.biz.impl.ExamBizImpl;
import com.yc.common.YcConstants;
import com.yc.web.actions.model.ResultModel;
import com.yc.web.actions.model.ScoreModel;

public class ExamAction extends BaseAction implements ModelDriven<Exam> {
	private static final long serialVersionUID = 1010376690376126207L;

	private ExamBiz examBiz;
	private Exam favorite;
	

	public void getQuestions() throws IOException {
		List<Exam> list = (List<Exam>) application
				.getAttribute(YcConstants.ALLEXAM);
		List<Exam> examlist = examBiz.getQuestions(list); // 抽题.
		// session.put("examlist", examlist);
		Map<Integer, Exam> map = null;
		if (application.getAttribute("selectedQuestions") != null) {
			map = (Map<Integer, Exam>) application
					.getAttribute("selectedQuestions");
		} else {
			map = new HashMap<Integer, Exam>();
			application.setAttribute("selectedQuestions", map);
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (int i=0;i<examlist.size();i++) {
			Exam el=examlist.get(i);
			if (map.containsKey(el.getEid())) {
				el = map.get(el.getEid());
				examlist.set(i, el);
			} else {
				ids.add(el.getEid());
			}
		}
		if (ids != null && ids.size() > 0) {
			List<Exam> searchResult = examBiz.searchQuestions(ids);
			for (Exam sr : searchResult) {
				map.put(sr.getEid(), sr); // 将题目信息存到 map中，表示已经查过了
				for (int i = 0; i < examlist.size(); i++) {
					if (sr.getEid().equals(examlist.get(i).getEid())) {
						examlist.set(i, sr);
						continue;
					}
				}
			}
		}
		//以上循环保证了 examlist存了所有被 抽中的题目，全部都有答案.
		//以下去答案.
		for( int i=0;i<examlist.size();i++){
			try {
				Exam e=new Exam();
				BeanUtils.copyProperties(e, examlist.get(i));
				e.setAnswer(null);
				examlist.set(i, e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jsonModel = new JsonModel();
		jsonModel.setCode(1);
		ResultModel rm = new ResultModel();
		rm.setTimes(    ExamBizImpl.count*10000);
		rm.setObj(examlist);
		jsonModel.setObj(rm);
		
		super.outJsonCors(jsonModel);
		
	}

	public void getresult() throws IOException {
		int right = 0;

		String[] question = this.favorite.getAnswer().split(","); // xxx_1 xxx_2
		Map<Integer, Exam> map = (Map<Integer, Exam>) application
				.getAttribute("selectedQuestions");
		for (String q : question) {
			String[] answer = q.split("_");
			if (map.containsKey(Integer.parseInt(answer[0]))) {
				Exam exam = map.get(Integer.parseInt(answer[0]));
				String an = exam.getAnswer();
				if (an.equals(answer[1])) {
					right++;
				}
			}
		}
		ScoreModel sm = new ScoreModel();
		sm.setTotal(100);
		sm.setScore((int) ((right / (double) question.length) * 100));
		
		jsonModel = new JsonModel();
		jsonModel.setCode(1);
		jsonModel.setObj(sm);
		
		super.outJsonCors(jsonModel);


	}

	public void setExamBiz(ExamBiz examBiz) {
		this.examBiz = examBiz;
	}

	

	public Exam getModel() {
		favorite = new Exam();
		return favorite;
	}

	

}
