package com.ies.spd.spdservices.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Java属性文件操作工具类
 * <p>由于每个人的需求不一样，所以本工具只提供常用的功能（包括根据键名获取键值，根据键名修改键值），
 * 如果不满足，自个完全可以修改，在本工具类中，我们假设属性文件已经存在，并且文件中已经存在我们要操作的key</p>
 * @author HsuChan
 * @version v1.0.0 2015-06-19
 */
public class PropertiesUtil {
    private static Properties properties= new Properties();
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);
    /**测试用 属性文件名*/
    private static final String PROPERTIES_FILE_PATH="/application.properties";
    
    
    /**
     * 初始化properties
     * @param path 属性文件路径,如:/config.properties
     */
    private static void initProperties(String path) {
        
        try {
            InputStream ipsm = PropertiesUtil.class.getResourceAsStream(path);
            properties.load(ipsm);
            ipsm.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 根据键名key获取属性文件中对应的键值
     * @param path 属性文件路径,如:/config.properties
     * @param key 属性文件 键名
     * @return
     */
    public static String getValueByKey(String key){
        //如果properties为空，则初始化
        if(properties.isEmpty())
            initProperties(PROPERTIES_FILE_PATH);
        return properties.getProperty(key);
    }
    
    /**
     * 通过键名key修改其对应的值为value
     * @param path 属性文件路径,如:/config.properties
     * @param key  属性文件 键名
     * @param value 属性文件 键值
     */
    public static void modifyValueByKey(String key,String value) {
        //如果properties为空，则初始化
        if(properties.isEmpty())
            initProperties(PROPERTIES_FILE_PATH);
        //修改值
        properties.setProperty(key, value);
       
        try {
            //获取文件路径
            URL url = PropertiesUtil.class.getResource(PROPERTIES_FILE_PATH);
            FileOutputStream fos = new FileOutputStream(new File(url.toURI()));
            //保存到文件
            properties.store(fos, "modify the value of key");
            fos.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
}