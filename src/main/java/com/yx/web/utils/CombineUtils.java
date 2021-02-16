package com.yx.web.utils;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import com.yx.web.xkset.XkSetBean;


public class CombineUtils {
	/** 
	 * 组合选择 
	 * @param dataList 待选列表 
	 * @param dataIndex 待选开始索引 
	 * @param resultList 前面（resultIndex-1）个的组合结果 
	 * @param resultIndex 选择索引，从0开始 
	 */ 
	private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;  
	    int resultCount = resultIndex + 1;  
	    if (resultCount > resultLen) { // 全部选择完时，输出组合结果  
	        System.out.println(Arrays.asList(resultList));  
	        return;  
	    }  
	  
	    // 递归选择下一个  
	    for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
	        resultList[resultIndex] = dataList[i];  
	        combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
	    }  
	}  
	/**
	 * 生成组合结果
	 * @param giveArr 要选择元素的数组
	 * *@param givenIdArr1 要选择元素主键的数组
	 * @param selectNum 要选择的元素个数
	 * @param curIndex  要选择的数组开始的下标
	 * @param hasNum 已经选择的个数
	 * @param combineList 存放组合结果集
	 * * @param combineArr 每一次组合存放结果
	 * @param combineArr 每一次组合存放结果主键结果集
	 */
	public static void createCombines(String[] giveArr,String[] givenIdArr1,int selectNum,
			int curIndex,int hasNum,List<Map<String, Object>> combineList,Stack<String> combineStack,String[] combineArr) {
		if(giveArr==null||giveArr.length<selectNum) {//给定的元素数目为空或者小于给定的数目
			return;
		}
		if(giveArr.length==selectNum) {//返回给定的结果  给定的元素数目等于要选择的元素数组
			//返回结果
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("combineName", StringUtils.join(giveArr, "+"));
			map.put("combineSubIds", givenIdArr1);
			combineList.add(map);
			return;
		}
		if(hasNum==selectNum) {//已经选择的元素等于要求选择的元素数目
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("combineName", StringUtils.join(combineStack, "+"));
			map.put("combineSubIds", combineArr);
			combineList.add(map);
			return;
		}
		
		for (int i = curIndex; i < giveArr.length; i++) {//giveArr.length-selectNum+1是第一个元素的截止位置
			if (!combineStack.contains(giveArr[i])) {
				combineStack.add(giveArr[i]);
				combineArr[hasNum]=givenIdArr1[i];
				createCombines(giveArr, givenIdArr1,selectNum, i,hasNum+1, combineList,combineStack,combineArr);
				combineStack.pop();
			}
		}
	}
	/**
	 * 生成必选科目组合
	 */
	public static void createMustSelectSubCombine() {
		
	}
	/**
	 * 生成可能的组合数  根据给定科目要求
	 */
	public static void createPosibleSelectSubCombine() {
		
	}
	/**
	 * 生成所有的选科组合  根据选科设置生成
	 * @param xkSetBean
	 */
	public static List<Map<String, Object>> createAllSelectSubCcombines(XkSetBean xkSetBean) {
		List<Map<String, Object>> list =  new ArrayList<Map<String,Object>>();
		if(xkSetBean.getSelectSubMode()==1) {//直接从n个中选m个
			int mustSelectNum = xkSetBean.getMustSelectNum();
			String givenNames = xkSetBean.getGivenNames();
			String givenIds = xkSetBean.getGivenIds();
			if (StringUtils.isNotBlank(givenIds)&&StringUtils.isNotBlank(givenNames)) {
				Stack<String> stack = new Stack<String>();
				String[] combineArr = new String[mustSelectNum];
				createCombines(givenNames.split(","), givenIds.split(","), mustSelectNum, 0, 0, list, stack, combineArr);
			}
		}
		if (xkSetBean.getSelectSubMode()==2) {//分步选 两个给定列表  给定列表的元素不重复 从n1个中m1个，从不包含n1的n2中选m2个 。。。。
			int pMustSelectNum = xkSetBean.getpMustSelectNum();
			int aMustSelectNum = xkSetBean.getaMustSelectNum();
			
		}
		return list;
	}
	
	public static void main(String[] args) {
		
	}
	
	
	
	

}
