package com.junl.apps.mapper;
import java.util.List;
import java.util.Map;

import com.junl.apps.model.ChouJianModel;
import com.junl.frame.core.BaseMapper;
/**
 * 
* @ClassName: ChouJianMapper
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dfj
* @date 2016年8月29日 下午6:12:25
*
 */
public interface ChouJianMapper extends BaseMapper<Object>{
	
	
	
	
	/**
	 * 新增抽检关系
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertChouJianRelate(Map<String , Object> map) throws Exception;
	
	/**
	 * 抽检管理
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryForEachChouJianGuanLi(Map<String, Object> map) throws Exception;
	/**
	 * 修改抽检的状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateChouJianState(Map<String, Object> params) throws Exception;
	/**
	 * 新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertChouJian(ChouJianModel model) throws Exception;
	/**
	 * 获取抽检列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryChouJianListPage(Map<String, Object> map) throws Exception;
}
