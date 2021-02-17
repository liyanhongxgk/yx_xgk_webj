package com.yx.web.xk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yx.web.utils.SnowflakeIdWorker;
@Repository
public class ChooseSubjectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 获取选科设置所有组合名称和主键映射关系
	 * @param xkSetId
	 * @return
	 */
	public Map<String, Integer> getComBineNameToId(int xkSetId){
		Map<String, Integer> resulMap = new HashMap<String, Integer>();
		String sql = "SELECT id,name FROM xgk_xk_setting_combines WHERE pk_xk_setting_1 = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, xkSetId);
		for (Map<String, Object> map : list) {
			resulMap.put(map.get("name").toString(), Integer.parseInt(map.get("id").toString()));
		}
		return resulMap;
	}
	
	private void save() {
		// TODO Auto-generated method stub
		SnowflakeIdWorker  snowflakeIdWorker= new SnowflakeIdWorker();
		long id = snowflakeIdWorker.snowflakeId();
	
	}
	
	

}
