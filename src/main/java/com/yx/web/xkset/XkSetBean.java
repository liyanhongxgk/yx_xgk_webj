package com.yx.web.xkset;
/**
 * 选科设置对象
 * @author xiaog
 *
 */
public class XkSetBean {
	private int setId;
	private Integer setName;
	private String descriptions;
	private String pGivenIds;//首选科目主键
	private String aGivenIds;//再选科目主键
	private String pGivenNames;
	private String aGivenNames;
	private String givenIds;//选择的科目主键
	private String givenNames;
	private int pMustSelectNum;
	private int aMustSelectNum;
	private int mustSelectNum;
	private int selectSubMode;
	
	public int getSelectSubMode() {
		return selectSubMode;
	}
	public void setSelectSubMode(int selectSubMode) {
		this.selectSubMode = selectSubMode;
	}
	public int getSetId() {
		return setId;
	}
	public void setSetId(int setId) {
		this.setId = setId;
	}
	public Integer getSetName() {
		return setName;
	}
	public void setSetName(Integer setName) {
		this.setName = setName;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getpGivenIds() {
		return pGivenIds;
	}
	public void setpGivenIds(String pGivenIds) {
		this.pGivenIds = pGivenIds;
	}
	public String getaGivenIds() {
		return aGivenIds;
	}
	public void setaGivenIds(String aGivenIds) {
		this.aGivenIds = aGivenIds;
	}
	public String getpGivenNames() {
		return pGivenNames;
	}
	public void setpGivenNames(String pGivenNames) {
		this.pGivenNames = pGivenNames;
	}
	public String getaGivenNames() {
		return aGivenNames;
	}
	public void setaGivenNames(String aGivenNames) {
		this.aGivenNames = aGivenNames;
	}
	public String getGivenIds() {
		return givenIds;
	}
	public void setGivenIds(String givenIds) {
		this.givenIds = givenIds;
	}
	public String getGivenNames() {
		return givenNames;
	}
	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}
	public int getpMustSelectNum() {
		return pMustSelectNum;
	}
	public void setpMustSelectNum(int pMustSelectNum) {
		this.pMustSelectNum = pMustSelectNum;
	}
	public int getaMustSelectNum() {
		return aMustSelectNum;
	}
	public void setaMustSelectNum(int aMustSelectNum) {
		this.aMustSelectNum = aMustSelectNum;
	}
	public int getMustSelectNum() {
		return mustSelectNum;
	}
	public void setMustSelectNum(int mustSelectNum) {
		this.mustSelectNum = mustSelectNum;
	}
	
	
	

}
