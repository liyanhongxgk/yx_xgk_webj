package com.yx.web.region;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/region")
public class RegionController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 省份列表跳转
	 * @return
	 */
	@PostMapping("/toViewProviceList")
	public String toViewProviceList() {
		return "base/province";
	}
	
	@ResponseBody
	@PostMapping("getProviceList")
	public List<Map<String, Object>> getProviceList() {
		String sql = "SELECT id,name FROM xgk_provice ";
		return jdbcTemplate.queryForList(sql);
		
		
	}
	
	
	

}
