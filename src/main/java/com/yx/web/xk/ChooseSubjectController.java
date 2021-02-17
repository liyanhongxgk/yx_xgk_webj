package com.yx.web.xk;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChooseSubjectController {
	@Autowired
	private ChooseSubjectDao chooseSubjectDao;
	
	public Map<String, Integer> getComBineNameToId(int xkSetId){
		return chooseSubjectDao.getComBineNameToId(xkSetId);
	} 

}
