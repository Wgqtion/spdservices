package com.ies.spd.spdservices.constants;

import com.ies.spd.spdservices.utils.PropertiesUtil;
import com.ies.spd.spdservices.utils.SystemSettings;

public class Constants {
	/**
	 * 路径分隔符
	 */
	public static final char SPT = '/';
	public static String SYS_NAME = "上海宜事智能停车管理平台";
	/**
	 * 系统表名前缀
	 */
	public static final String TABLE_PREFIX="t_";
	/**
	 * 视图表前缀
	 */
	public static final String VIEW_PREFIX="v_";

	
	/**
	 * 附件上传存放的相对文件夹名称
	 */
	public static String UPLOAD_ROOT_FOLDER = "D://ies//upload";
	
	/**
	 * 全视频照片上传存放的相对文件夹名称
	 */
	public static String UPLOAD_CAMERA_FOLDER = "D://ies//camera";
	
	/**
	 * 二维码图片路径
	 */
	public static String QRCODE_ROOT_FOLDER = "D://ies//qrcode";

	/**
	 * 字符串  转  数组分隔符
	 */
	public static final String SPT_ARRAY = ",";

	/**
	 * 文件保存编码设置
	 */
	public static final String ENCODING = "UTF-8";

	/**
	 * 用户路径
	 */
	public static final String USER_BASE = "user_base";

	/**
	 * WEB-INF
	 */
	public static final String WEBINF = "WEB-INF";

	public static final String USER_ROOT = SPT + WEBINF + SPT + USER_BASE + SPT;

	/**
	 * 获取系统配置
	 * @return
	 */
//	public static SystemSettings getSystemSetting() {
//		return  SystemSettings.getInstance();
//	}

	/**
	 * 图像文件的类型
	 */
	public static final String[] IMG_TYPE = { "gif", "jpg", "png", "jpe", "jpeg" };


	/**
	 * 加载配置文件
	 */
	static{
		Constants.SYS_NAME=PropertiesUtil.getValueByKey("system.name");
		Constants.UPLOAD_ROOT_FOLDER=PropertiesUtil.getValueByKey("attach.upload.path");
		Constants.UPLOAD_CAMERA_FOLDER=PropertiesUtil.getValueByKey("camera.upload.path");
		Constants.QRCODE_ROOT_FOLDER=PropertiesUtil.getValueByKey("qrCode.path");
	}
	
	/**
	 * 预约提示状态
	 */
	public static final String[] RESERVE_MESSAGE_STATUS = { "预约成功","有未支付订单","已有进行中的订单","车位已被其他人使用","不在可预约时间段","今天已达取消预约次数上限，不可预约"};
	
	/**
	 * 解锁提示状态
	 */
	public static final String[] UNLOCK_MESSAGE_STATUS = { "解锁成功","有未支付订单","已有进行中的订单","车位已被其他人使用"};
	
	/**
	 * 取消预约提示状态
	 */
	public static final String[] CANCEL_RESERVE_MESSAGE_STATUS = { "取消成功","当前无预约信息","取消成功","取消成功"};
	
	/**
	 * 上锁提示状态
	 */
	public static final String[] LOCK_MESSAGE_STATUS = { "上锁成功","上锁成功","上锁成功","上锁成功"};
	
	/**
	 * 支付提示状态
	 */
	public static final String[] PAY_MESSAGE_STATUS = { "支付成功","当前无订单信息"};
	
}
