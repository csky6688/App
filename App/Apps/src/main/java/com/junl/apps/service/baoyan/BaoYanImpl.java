package com.junl.apps.service.baoyan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junl.apps.common.PageInfoSetCondition;
import com.junl.apps.form.BaoYanForm;
import com.junl.apps.mapper.BaoYanMapper;
import com.junl.apps.model.BaoYanModel;
import com.junl.apps.model.BaoYanRelateModel;
import com.junl.apps.model.JiLiangModel;
import com.junl.apps.service.fzhl.IFzhl;
import com.junl.apps.service.jiaotong.IJiaoTong;
import com.junl.apps.service.jiliang.JiLiangImpl;
import com.junl.apps.service.liefeng.ILieFeng;
import com.junl.apps.service.lumian.ILuMian;
import com.junl.apps.service.rwtask.RwTaskImpl;
import com.junl.apps.service.yanghu.IYangHu;
import com.junl.apps.service.youwu.IYouWu;
import com.junl.frame.core.common.page.PageInfo;
import com.junl.frame.tools.UUIDGenerator;
import com.junl.frame.tools.string.StringUtils;


/**
 * 
 * @author dfj
 * @date 2016年8月19日下午2:07:57 
 * @description
 *
 */
@Service
public class BaoYanImpl implements IBaoYan{

	
	
	@Autowired
	private BaoYanMapper mapper;
	
	@Autowired
	private RwTaskImpl taskImpl;
	@Autowired
	private JiLiangImpl jlImpl;
	
	
	@Autowired
	private IFzhl fzhlImpl;
	@Autowired
	private IJiaoTong jiaotongImpl;
	@Autowired
	private ILieFeng liefengImpl;
	@Autowired
	private ILuMian lumianImpl;
	@Autowired
	private IYangHu yanghuImpl;
	@Autowired
	private IYouWu youwuImpl;
	
	
	
	
	
	
	
	
	
	
	
	public PageInfo queryBaoYanListPage(BaoYanForm form) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sdate", form.getSdate());
		params.put("edate", form.getEdate());
		//设置分页参数
		PageInfoSetCondition.SetCondition(form.getPageInfo(), "createTime", "desc");
		params.put("page", form.getPageInfo());
		List<Map<String, Object>> list = mapper.queryBaoYanListPage(params);
		form.getPageInfo().setResults(list);
		return form.getPageInfo();
	}


	/**
	 * 获取当前需要新增的任务单编号
	 */
	public Map<String, Object> queryTaskNoInThisMonth() throws Exception {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Map<String, Object> map=mapper.queryTaskNoInThisMonth();
		String byNum="";
		if (map==null) {
			map =new HashMap<>();
			byNum="";
		}else{
			byNum=StringUtils.changNull(map.get("byNum"));
		}
		if (byNum.equals("")) {
			map.put("byNo", "XZYHWX"+formatter.format(currentTime)+"-001");
		}else{
			map.put("byNo", "XZYHWX"+formatter.format(currentTime)+"-"+StringUtils.intToStrong(Integer.valueOf(byNum)));
		}
		map.put("byPre", "XZYHWX");
		return map;
	}
	public static void main(String[] args) {
		
	}

	/**
	 * 新增关联表
	 */
	public int insertBaoYanRelate(BaoYanRelateModel model) throws Exception {
		//设置主键
		model.setIds(UUIDGenerator.generate());
		return mapper.insertBaoYanRelate(model);
	}


	/**
	 * 删除关联表
	 */
	public int deleteBaoYanRelate(String byIds) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("byIds", byIds);
		return mapper.deleteBaoYanRelate(map);
	}


	/**
	 * 新增
	 */
	public int insertBaoYan(BaoYanModel model) throws Exception {
		int res=-1;
		//设置主键
		model.setIds(UUIDGenerator.generate());
		model.setByFNum(model.getWeiXiuIds().length);
		res=mapper.insertBaoYan(model);
		if (res>0) {
			 //维修记录和报验建立关系
			 fzhlImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			 jiaotongImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			 liefengImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			 lumianImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			 yanghuImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			 youwuImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
			//创建计量数据
			JiLiangModel jl=new JiLiangModel();
			jl.setByIds(model.getIds());
			jl.setIds(UUIDGenerator.generate());
			jlImpl.insertJiLiang(jl);
		}
		
		return res;
	}


	/**
	 * 修改
	 */
	public int updateBaoYan(BaoYanModel model) throws Exception {
		
		 model.setByFNum(model.getWeiXiuIds().length);
		 //维修记录和报验解除关系
		 fzhlImpl.updateWithBaoYanReleaseContact(model.getIds());
		 jiaotongImpl.updateWithBaoYanReleaseContact(model.getIds());
		 liefengImpl.updateWithBaoYanReleaseContact(model.getIds());
		 lumianImpl.updateWithBaoYanReleaseContact(model.getIds());
		 yanghuImpl.updateWithBaoYanReleaseContact(model.getIds());
		 youwuImpl.updateWithBaoYanReleaseContact(model.getIds());
		
		 //维修记录和报验建立关系
		 fzhlImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 jiaotongImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 liefengImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 lumianImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 yanghuImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 youwuImpl.updateWithBaoYanEstablishContact(model.getIds(),model.getWeiXiuIds());
		 
		return mapper.updateBaoYan(model);
	}

	/**
	 * 根据报验主键获取任务的数据
	 */
	public List<Map<String, Object>> queryTaskBaoYanIds(String byIds) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("byIds", byIds);
		return mapper.queryTaskBaoYanIds(map);
	}


	@Override
	public int updateBaoYanState(Map<String, Object> map) throws Exception {
		return mapper.updateBaoYanState(map);
	}


	/**
	 * 查询需要维修报验的维修记录
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryWeiXiuView() throws Exception {
		return mapper.queryWeiXiuView();
	}


	/**
	 * 根据报验的主键获取维修记录
	 */
	public List<Map<String, Object>> queryWeiXiuViewByBaoyanId(String baoyanId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("baoyanId", baoyanId);
		return mapper.queryWeiXiuViewByBaoyanId(map);
	}
	

	

}
