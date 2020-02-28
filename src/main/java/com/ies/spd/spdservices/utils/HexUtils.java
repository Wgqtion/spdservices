package com.ies.spd.spdservices.utils;

import com.ies.spd.spdservices.entity.Area;
import com.ies.spd.spdservices.tcp.entity.CRC16M;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HexUtils {
	/**
	 * 字节转16进制
	 * @param bytes
	 * @return
	 */
	public static String getHexStr(byte[] bytes) {
		String ret = "";
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toLowerCase();
			if(hex.length()>4&&"0d0a".equals(hex.substring(hex.length()-4))){
				break;
			}
		}
		return ret;
	}

	public static String addAreaName(String s,Area area){
		s += area.getName()+">>";
		if(area.getAreaParent() != null){
			s = addAreaName(s,area.getAreaParent());
		}else {
			s = s.substring(0,s.length()-2);
			return s;
		}
//		System.out.println(s);
		return s;
	}
}
