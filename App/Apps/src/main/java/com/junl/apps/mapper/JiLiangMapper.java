package com.junl.apps.mapper;
import java.util.List;
import java.util.Map;

import com.junl.apps.model.JiLiangModel;
import com.junl.apps.model.JiLiangTuiHuiModel;
import com.junl.apps.model.JiLiangXiMuModel;
import com.junl.frame.core.BaseMapper;
/**
 * 
* @ClassName: JiLiangMapper
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dfj
* @date 2016年8月26日 下午5:21:04
*
 */
public interface JiLiangMapper extends BaseMapper<Object>{
	
	
	/**
	 * 根据报验单编号查询计量的数据 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryJiLiangByBaoYanIds(Map<String, Object> map) throws Exception;
	
	
	
	
	
	/**
	 * 修改计量的状态(细目)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateJiLiangStateWithIds_xiMu(Map<String, Object> params) throws Exception;
	/**
	 * 修改计量的状态（退回）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateJiLiangStateWithIds_tuiHui(Map<String, Object> params) throws Exception;
	/**
	 * 根据ids获取计量的数据
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> querySimpleByIds(Map<String, Object> params) throws Exception;
	/**
	 * 计量细目的新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertJiLiangXiMu(JiLiangXiMuModel model) throws Exception;
	/**
	 * 计量的退回
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertJiLiangTuiHui(JiLiangTuiHuiModel model) throws Exception;
	/**
	 * 确认已计量
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateJiLiang(Map<String, Object> params) throws Exception;
	
	
	
	/**
	 * 计量新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertJiLiang(JiLiangModel model) throws Exception;
	/**
	 * 获取计量列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryJiLiangListPage(Map<String, Object> map) throws Exception;
	/**
	 * 根据报验的ids获取维修的数量（中间计量中的计量页面上的维修数量）
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryWeiXiuNumByBaoYanIds(Map<String, Object> map) throws Exception;
}
