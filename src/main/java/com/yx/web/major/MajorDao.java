package com.yx.web.major;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MajorDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
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
	
	public List<Map<String, Object>> getSubjectList() {
		String sql = "SELECT id,name FROM xgk_subject ";
		return jdbcTemplate.queryForList(sql);
		
		
	}

}
