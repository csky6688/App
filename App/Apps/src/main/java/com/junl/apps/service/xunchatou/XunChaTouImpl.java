package com.junl.apps.service.xunchatou;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junl.apps.common.PageInfoSetCondition;
import com.junl.apps.form.XunChaTouForm;
import com.junl.apps.mapper.XunChaTouMapper;
import com.junl.apps.model.XunChaTouXCModel;
import com.junl.apps.service.role.RoleImpl;
import com.junl.frame.core.common.page.PageInfo;
import com.junl.frame.tools.UUIDGenerator;



@Service
public class XunChaTouImpl implements IXunChaTou{

	
	@Autowired
	private RoleImpl roleImpl;
	@Autowired
	private XunChaTouMapper mapper;
	
	/*
	 * (non-Javadoc)
	 * @see com.junl.apps.service.xunchatou.IXunChaTou#queryXunChaTouListPage(java.util.Map)
	 */
	public PageInfo queryXunChaTouListPage(XunChaTouForm form) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("xunChaType", form.getXunChaType());
		params.put("sdate", form.getSdate());
		params.put("edate", form.getEdate());
		params.put("createUserId", form.getCreateUserId());
		//设置分页参数
		PageInfoSetCondition.SetCondition(form.getPageInfo(), "xunChaStartTime", "desc");
		params.put("page", form.getPageInfo());
		//设置权限
		params.put("rolesql", roleImpl.getZHbyUserId(form.getCreateUserId(),"xct.xunChaStartZHK1","xct.xunChaStartZHM1"));
		List<Map<String, Object>> list = mapper.queryXunChaTouListPage(params);
		form.getPageInfo().setResults(list);
		
		return form.getPageInfo();
	}
	/*
	 * (non-Javadoc)
	 * @see com.junl.apps.service.xunchatou.IXunChaTou#queryByIds(java.lang.String)
	 */
	public Map<String, Object> queryByIds(String ids) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		return mapper.queryByIds(params);
	}
	/*
	 * (non-Javadoc)
	 * @see com.junl.apps.service.xunchatou.IXunChaTou#insertXunChaTou(com.junl.apps.model.XunChaTouXCModel)
	 */
	public int insertXunChaTou(XunChaTouXCModel model) throws Exception {
		//设置主键
		model.setIds(UUIDGenerator.generate());
		return mapper.insertXunChaTou(model);
	}
	/**
	 * 修改
	 */
	public int updateXunChaTou(XunChaTouXCModel model) throws Exception {
		return mapper.updateXunChaTou(model);
	}
	/**
	 * 修改巡查登记的状态
	 */
	public int updateXunChaTouState(Map<String, Object> map) throws Exception {
		return mapper.updateXunChaTouState(map);
	}
	
	
	

}
