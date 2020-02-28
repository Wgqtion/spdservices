package com.ies.spd.spdservices.tcp.core;

import io.netty.channel.ChannelHandlerContext;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端连接集合
 * @author XiangXiaoLin
 *
 */
public class ClientMap {
	/**
	 * 地锁网关连接网关获取
	 */
	public static ConcurrentHashMap<String, ChannelHandlerContext> lockMap = new ConcurrentHashMap<String, ChannelHandlerContext>();
	/**
	 * 地锁网关连接Ip获取
	 */
	public static ConcurrentHashMap<SocketAddress, String> lockIpMap = new ConcurrentHashMap<SocketAddress, String>();
}
