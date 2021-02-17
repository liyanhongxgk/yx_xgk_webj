package com.yx.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.yx.web.xkset.XkSetBean;


public class CombineUtils {
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
			int curIndex,int hasNum,List<Map<String, String>> combineList,Stack<String> combineArr,Stack<String> combineIdArr) {
		
		if(giveArr==null||giveArr.length<selectNum||selectNum==0) {//给定的元素数目为空或者小于给定的数目
			return;
		}
		if(givenIdArr1!=null&&giveArr.length!=givenIdArr1.length) {
			return;
		}
		if(giveArr.length==selectNum) {//返回给定的结果  给定的元素数目等于要选择的元素数组
			//返回结果
			Map<String, String> map = new HashMap<String, String>();
			map.put("combineName", StringUtils.join(giveArr,","));
			if(givenIdArr1!=null) {
				map.put("combineSubIds", StringUtils.join(givenIdArr1,","));
			}
			combineList.add(map);
			return;
		}
		if(hasNum==selectNum) {//已经选择的元素等于要求选择的元素数目
			Map<String, String> map = new HashMap<String, String>();
			map.put("combineName", StringUtils.join(combineArr,"+"));
			if (givenIdArr1!=null) {
				map.put("combineSubIds", StringUtils.join(combineIdArr,","));
			}
			
			System.err.println(StringUtils.join(combineArr,","));
			System.err.println(StringUtils.join(combineIdArr,","));
			combineList.add(map);
			return;
		}
		
		for (int i = curIndex; i < giveArr.length; i++) {//giveArr.length-selectNum+1是第一个元素的截止位置
			if(!combineArr.contains(giveArr[i])) {
				combineArr.add(giveArr[i]);
				combineIdArr.add(givenIdArr1[i]);
				createCombines(giveArr, givenIdArr1,selectNum, i,hasNum+1, combineList,combineArr,combineIdArr);
				combineArr.pop();
				combineIdArr.pop();
			}
		}
	}
	
	public Map<String, String> validateGivenSub(String givenNames,int mustSelectNum,String subs,int subsCount,String msg) {
		Map<String, String> subMap  = new HashMap<String, String>();
		if(StringUtils.isBlank(subs)) {//选科需求给定的科目为空
			subs=givenNames.replaceAll(",", "\\|");
		}
		subMap.put("subs", subs);
		if(subsCount>mustSelectNum) {
			msg+="选科需求给定科目必选数"+subsCount+"大于选科设置的必选科目数";
			subMap.put("msg", msg);
			return subMap;
		}
		if (StringUtils.isNotBlank(subs)&&subs.split("\\|").length<subsCount) {
			msg+="选科需求给定科目'"+subs+"'少于必选科目数";
			subMap.put("msg", msg);
			return subMap;
		}
		subMap.put("msg", msg);
		subMap.put("msg", "success");
		return subMap;
	}
	
	/**
	 * 生成必选科目组合 分情况讨论
	 * @param xkSetBean  选科设置给定的科目选择
	 * @param pSubs 选科需求给定的首选科目
	 * @param pSubsCount 选科需求给定的首选科目必选数
	 * @param aSubs 选科需求给定的再选科目
	 * @param aSubsCount 选科需求给定的再选科目必选数
	 * @param subs 选科需求给定的科目
	 * @param subsCount 选科需求给定的科目必选数
	 */
	public Map<String, Object>  createMustSelectSubCombine(XkSetBean xkSetBean,String pSubs,int pSubsCount,String aSubs,int aSubsCount,String subs,int subsCount){
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String msg ="success" ;
		String state="1";
		if(xkSetBean.getSelectSubMode()==1) {//一次选
			//验证选科需求给定的科目范围是否在选科设置给定的范围  如果有不在的，返回错误
			map = validateGivenSub(xkSetBean.getpGivenNames(), xkSetBean.getpMustSelectNum(), pSubs, pSubsCount,"首选-");
			if ("success".equals(map.get("msg"))) {
				map = validateGivenSub(xkSetBean.getaGivenNames(), xkSetBean.getaMustSelectNum(), aSubs, aSubsCount,"首选-");
				//生成首选必选组合和再选必选组合
				List<Map<String, String>> pList = new ArrayList<Map<String,String>>();
				Stack<String> stack = new Stack<String>();
				createCombines(map.get("subs").split("\\|"), null, pSubsCount, 0, 0, pList, stack, null);
				if("success".equals(map.get("msg"))) {
					List<Map<String, String>> aList = new ArrayList<Map<String,String>>();
					Stack<String> stack1 = new Stack<String>();
					createCombines(map.get("subs").split("\\|"), null, aSubsCount, 0, 0,aList, stack1, null);
					for (Map<String, String> pMap : pList) {
						if (aList==null||aList.size()==0) {
							list.add(pMap.get("combineName"));
						}else {
							for (Map<String, String> aMap : aList) {
								list.add(pMap.get("combineName")+"+"+aMap.get("combineName"));
							}
						}
						
					}
				}else {
					map.put("msg",msg);
					map.put("state", "0");
					resultMap.put("warnInfo", map);
					return resultMap;
				}
			}else {
				map.put("msg",msg);
				map.put("state", "0");
				resultMap.put("warnInfo", map);
				return resultMap;
			}
			
			
			
		}
		if(xkSetBean.getSelectSubMode()==2) {//分两步选
			map=validateGivenSub(xkSetBean.getGivenNames(), xkSetBean.getMustSelectNum(), subs, subsCount, "");
			Stack<String> stack = new Stack<String>();
			List<Map<String, String>> list1=new ArrayList<Map<String,String>>();
			if ("success".equals(map.get("msg"))) {
				createCombines(map.get("subs").split("\\|"), null, subsCount, 0, 0, list1, stack, null);
				for (Map<String, String> combine : list1) {
					list.add(combine.get("combineName"));
				}
			}else {
				map.put("msg",msg);
				map.put("state", "0");
				resultMap.put("warnInfo", map);
				return resultMap;
			}
		}
		map.put("msg",msg);
		map.put("state", state);
		resultMap.put("warnInfo", map);
		resultMap.put("resultList", list);
		return resultMap;
	}
	/**
	 * 根据选科设置给定科目数和必选科目数 生成 科目组合
	 * @param givenNames
	 * @param givenIds
	 * @param mustSelectNum
	 * @param list
	 */
	public static void createXkSettingGivenSubCombios(String givenNames,String givenIds,int mustSelectNum,List<Map<String, String>> list) {
		Stack<String> combineArr = new Stack<String>();
		Stack<String> combineIdArr = new Stack<String>();
		createCombines(givenNames.split(","), givenIds.split(","), mustSelectNum, 0, 0, list, combineArr, combineIdArr);
	}
	
	public static List<Map<String, String>> dealTwoStepCreateXkSettingCombines(List<Map<String, String>> pList,List<Map<String, String>> aList) {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (Map<String, String> pmap : pList) {
			String pName = pmap.get("combineName");
			String pIds =pmap.get("combineSubIds");
			for (Map<String, String> amap : aList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("combineName", pName+"+"+amap.get("combineName"));
				map.put("combineSubIds",pIds+","+ amap.get("combineSubIds"));
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * 生成必选科目组合 分情况讨论
	 * @param xkSetBean  选科设置给定的科目选择
	 * @param pSubs 选科需求给定的首选科目
	 * @param pSubsCount 选科需求给定的首选科目必选数
	 * @param aSubs 选科需求给定的再选科目
	 * @param aSubsCount 选科需求给定的再选科目必选数
	 * @param subs 选科需求给定的科目
	 * @param subsCount 选科需求给定的科目必选数
	 */
	public static void createPosibleSelectSubCombine(XkSetBean xkSetBean,String pSubs,
			int pSubsCount,String aSubs,int aSubsCount,String subs,int subsCount){
		//得到首选科目  
		
	}
	/**
	 * 元素排序  
	 * @param subjectList 排序后的科目  按照此科目排序，分情况讨论  
	 */
	public void sortElement(List<Map<String, Object>> subjectList) {
		//
	}
	/**
	 * 生成所有的选科组合  根据选科设置生成
	 * @param xkSetBean
	 */
	public static List<Map<String, String>> createAllSelectSubCombines(XkSetBean xkSetBean) {
		List<Map<String, String>> list =  new ArrayList<Map<String,String>>();
		if(xkSetBean.getSelectSubMode()==1) {//直接从n个中选m个
			int mustSelectNum = xkSetBean.getMustSelectNum();
			String givenNames = xkSetBean.getGivenNames();
			String givenIds = xkSetBean.getGivenIds();
			if (StringUtils.isNotBlank(givenIds)&&StringUtils.isNotBlank(givenNames)) {
				createXkSettingGivenSubCombios(givenNames, givenIds, mustSelectNum, list);
			}
		}
		if (xkSetBean.getSelectSubMode()==2) {//分步选 两个给定列表  给定列表的元素不重复 从n1个中m1个，从不包含n1的n2中选m2个 。。。。
			int pMustSelectNum = xkSetBean.getpMustSelectNum();
			int aMustSelectNum = xkSetBean.getaMustSelectNum();
			String pGivenIds = xkSetBean.getpGivenIds();
			String pGivenNames = xkSetBean.getpGivenNames();
			String aGivenIds = xkSetBean.getaGivenIds();
			String aGivenNames = xkSetBean.getpGivenNames();
			List<Map<String, String>> pList = new ArrayList<Map<String,String>>();
			List<Map<String, String>> aList = new ArrayList<Map<String,String>>();
			
			if (StringUtils.isNotBlank(pGivenIds)&&StringUtils.isNotBlank(pGivenNames)) {
				createXkSettingGivenSubCombios(pGivenNames, pGivenIds, pMustSelectNum, pList);
			}
			if (StringUtils.isNotBlank(aGivenIds)&&StringUtils.isNotBlank(aGivenNames)) {
				createXkSettingGivenSubCombios(aGivenNames, aGivenIds, aMustSelectNum, aList);
			}
			list = dealTwoStepCreateXkSettingCombines(pList, aList);
        }
		return list;
	}
	
	
	public static void main(String[] args) {
		/*List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Stack<String> combineArr = new Stack<String>();
		Stack<String> combineIdArr = new Stack<String>();
		String[] giveArr= {"物理","化学","生物","历史","地理","政治"};
		String[] givenIdArr1= {"1","2","3","4","5","6"};
		createCombines(giveArr, givenIdArr1, 3, 0, 0, list, combineArr, combineIdArr);
		System.err.println(list.size());*/
	}
	
	
	
	

}
