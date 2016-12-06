package com.junl.apps.service.yanghu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junl.apps.common.PageInfoSetCondition;
import com.junl.apps.form.YangHuForm;
import com.junl.apps.mapper.WXYangHuMapper;
import com.junl.apps.model.WXYangHuModel;
import com.junl.apps.service.role.RoleImpl;
import com.junl.frame.core.common.page.PageInfo;
import com.junl.frame.tools.UUIDGenerator;


/**
 * 
 * @author dfj
 * @date 2016年8月18日下午7:59:59 
 * @description
 *
 */
@Service
public class YangHuImpl implements IYangHu{

	
	@Autowired
	private RoleImpl roleImpl;
	@Autowired
	private WXYangHuMapper mapper;
	
	
	public PageInfo queryWXYangHuListPage(YangHuForm form) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wzName", form.getWzName());
		params.put("createUserId", form.getCreateUserId());
		//设置分页参数
		PageInfoSetCondition.SetCondition(form.getPageInfo(), "weiXiuTime", "desc");
		params.put("page", form.getPageInfo());
		//设置权限
		params.put("rolesql", roleImpl.getZHbyUserId(form.getCreateUserId(),"startZHK","startZHM"));
		List<Map<String, Object>> list = mapper.queryWXYangHuListPage(params);
		form.getPageInfo().setResults(list);
		return form.getPageInfo();
	}


	/**
	 * 循环查询各个维修记录表
	 * 首先根据报验的主键ids到报验外键表获取任务（任务表的外键表）的ids
	 * 根据获取到的任务（任务表的外键表）的ids获取维修表的记录数 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> queryForEach(Map<String, Object> params) throws Exception {
		return mapper.queryForEach(params);
	}

	/**
	 * 抽检(通过)
	 */
	public List<Map<String, Object>> queryForEachAccept(Map<String, Object> map) throws Exception {
		return mapper.queryForEachAccept(map);
	}
	/**
	 * 抽检(未通过)
	 */
	public List<Map<String, Object>> queryForEachNoAccept(Map<String, Object> map) throws Exception {
		return mapper.queryForEachNoAccept(map);
	}


	/**
	 * 新增
	 */
	public int insertYangHu(WXYangHuModel model) throws Exception {
		//设置主键
		model.setIds(UUIDGenerator.generate());
		return mapper.insertYangHu(model);
	}


	/**
	 * 修改
	 */
	public int updateYangHu(WXYangHuModel model) throws Exception {
		return mapper.updateYangHu(model);
	}

	/**
	 * 更新维修记录的状态（计量）
	 */
	public int updateStateAndCalcForEach(Map<String, Object> map) throws Exception {
		return mapper.updateStateAndCalcForEach(map);
	}

	
	/**
	 * 抽检管理
	 */
	public List<Map<String, Object>> queryForEachChouJianGuanLi(Map<String, Object> map) throws Exception {
		return mapper.queryForEachChouJianGuanLi(map);
	}
	/**
	 *  抽检管理中的验收是否完成 
	 */
	public int updateWithYanShou(Map<String, Object> map) throws Exception {
		return mapper.updateWithYanShou(map);
	}
	/**
	 * 解除维修记录和报验的联系
	 */
	public int updateWithBaoYanReleaseContact(String baoyanId) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("baoyanId", baoyanId);
		return mapper.updateWithBaoYanReleaseContact(params);
	}
	/**
	 * 建立维修记录和报验的联系
	 */
	public int updateWithBaoYanEstablishContact(String baoyanId,String[] ids) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("baoyanId", baoyanId);
		params.put("ids", ids);
		return mapper.updateWithBaoYanEstablishContact(params);
	}
}
