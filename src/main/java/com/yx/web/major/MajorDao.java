package com.yx.web.major;

import java.util.List;
import java.util.Map;

public class MajorDao {
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
		String sql = "SELECT * FROM xgk_majors";
		StringBuffer sqlBuffer = new StringBuffer(sql);
		return null;
	}

}
