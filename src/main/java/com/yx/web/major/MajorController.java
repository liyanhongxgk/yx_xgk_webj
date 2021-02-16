package com.yx.web.major;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("major")
public class MajorController {
	@Autowired
	private MajorDao  majorDao;
	
	@RequestMapping("/toViewSubjectList")
	public String toViewSubjectList() {
		System.err.println("科目列表");
		return "base/subjects";
	}
	
	@ResponseBody
	@RequestMapping("getSubjectList")
	public Map<String, Object> getSubjectList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = majorDao.getSubjectList();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count",10);
		map.put("data", list);
		return map;
	}
	/**
	 * 得到专业大类
	 * @return
	 */
	public List<Map<String,Object>> getMajorCategories(){
		return null;
	}
	
	/**
	 * 得到专业类别
	 * @return
	 */
	public List<Map<String,Object>> getMajorCategory(){
		return null;
	}

	
	/**
	 * 得到专业
	 * @return
	 */
	public List<Map<String,Object>> getMajors(){
		return null;
	}


}
