package com.junl.apps.service.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.junl.apps.common.FileUtils;
import com.junl.apps.form.AppForm;
import com.junl.apps.mapper.AppMapper;
import com.junl.frame.tools.ConfigUtil;
import com.junl.frame.tools.PropertiesUtil;
import com.junl.frame.tools.UUIDGenerator;


/**
 * 
 * @author dfj
 * @date 2016年8月19日下午2:07:57 
 * @description
 *
 */
@Service
public class AppImpl implements IApp{

	
	
	@Autowired
	private AppMapper mapper;
	
	/**
	 * 新增
	 */
/*	public int insertApp(AppForm form) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids",UUIDGenerator.generate());
		params.put("version", form.getVersion());
		params.put("realName", form.getRealName());
		params.put("fileName", form.getFileName());
		params.put("showPath", form.getShowPath());
		params.put("realPath", form.getRealPath());
		params.put("createTime", new Timestamp(System.currentTimeMillis()));
		params.put("createPeople", form.getCreatePeople());
		return mapper.insertApp(params);
	}*/

	@Override
	public int insertApp(String version, MultipartFile file) {
		Map<String, Object> params = new HashMap<String, Object>();
		//获取配置项中的保存路径
		String save_path=FileUtils.getProjectServerRootPath()+ConfigUtil.getValue("save_path");
		String show_path=ConfigUtil.getValue("show_path");
		
		try {
			//获取文件名称
			String fileName=file.getOriginalFilename();
			//截取字符串获取后缀
			String suffix=fileName.substring(fileName.lastIndexOf("."), fileName.length());
			//设置保存在服务器上的文件的文件名称
			String realName=UUIDGenerator.generate();
			if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                File folder = new File(save_path);
                //判断文件夹是否存在,如果不存在则创建文件夹
                if (!folder.exists()) {
                	folder.mkdir();
                }
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(save_path+realName+suffix)));
                stream.write(bytes);
                stream.close();
		     } 
			params.put("ids",UUIDGenerator.generate());
			params.put("version", version);
			params.put("fileName", fileName);
			params.put("realName", realName+suffix);
			params.put("showPath", show_path);
			params.put("realPath", save_path);
			params.put("createTime", new Timestamp(System.currentTimeMillis()));
			params.put("createPeople", "admins");
			return mapper.insertApp(params);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 *  下载最新的文件
	 */
	public Map<String, Object> queryNewestFile() throws Exception {
		return mapper.queryNewestFile();
	}


	
	

}
