package com.junl.apps.service.xunchainfo;


import java.util.List;
import java.util.Map;

import com.junl.apps.form.XunChaInfoForm;
import com.junl.apps.model.XunChaInfoRelateModel;
import com.junl.apps.model.XunChaInfoXCModel;
import com.junl.frame.core.common.page.PageInfo;

public interface IXunChaInfo {
	/**
	 * 部件新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertXunInfoFzhlRelate(XunChaInfoRelateModel model) throws Exception;
	
	
	/**
	 * 根据巡查信息的ids删除部件
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteXunInfoFzhlRelateByXunChaInfoId(String xunChaInfoId) throws Exception;
	/**
	 * 获取巡查信息列表
	 * @return
	 * @throws Exception
	 */
	public PageInfo queryXunChaInfoListPage(XunChaInfoForm form) throws Exception;
	/**
	 * 根据巡查信息的ids获取维修项信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryFzhlTable(String ids) throws Exception;
	
	

	/**
	 * 新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertXunInfo(XunChaInfoXCModel model) throws Exception;
	
	/**
	 * 修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateXunChaInfo(XunChaInfoXCModel model) throws Exception;
}
