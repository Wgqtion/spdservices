package com.ies.spd.spdservices.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统设置
 * @author 付翀
 * @version 2012-6-20
 */
public class SystemSettings {
	//默认的系统配置属性文件名
//	private static final String DEFAULT_STRATEGIES_PATH = "vsc_system_settings.properties";
	//默认的文章存放相对路径
//	public static final String TXT_ROOT_FOLDER_KEY = "article_base_folder";
	//当前的应用服务器根目录
//	public static final String FILE_SERVER_FOLDER_KEY = "file_server_folder";

	private Properties properties;

	private static class SingletonHolder {
		/** 
		 * 单例对象实例 
		 */
//		static final SystemSettings INSTANCE = new SystemSettings();
	}

//	public static SystemSettings getInstance() {
//		return SingletonHolder.INSTANCE;
//	}

//	private SystemSettings() {
//		this(DEFAULT_STRATEGIES_PATH);
//	}

	private SystemSettings(String fileName) {
		try {
			ClassPathResource resource = new ClassPathResource(fileName, this.getClass().getClassLoader());
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException ex) {
			throw new IllegalStateException("Could not load '"+fileName+"': " + ex.getMessage());
		}
	}

	/**
	 * 
	 * @return 文章文本存放的相对地址
	 */
//	public String getServerFolder() {
//		return properties.getProperty(FILE_SERVER_FOLDER_KEY);
//	}

	/**
	 * 设置文章文本存放的相对地址
	 * @param txtRoot
	 */
//	public void setServerFolder(String folder) {
//		Assert.hasLength(folder, "服务器目录存储参数错误");
//		properties.setProperty(FILE_SERVER_FOLDER_KEY, folder);
//	}
	
	
	/**
	 * 
	 * @return 文章文本存放的相对地址
	 */
//	public String getTxtFolder() {
//		return properties.getProperty(TXT_ROOT_FOLDER_KEY);
//	}

	/**
	 * 设置文章文本存放的相对地址
	 * @param txtRoot
	 */
//	public void setTxtFolder(String txtRoot) {
//		Assert.hasLength(txtRoot, "文章相对存放路径参数错误");
//		properties.setProperty(TXT_ROOT_FOLDER_KEY, txtRoot);
//	}
}
