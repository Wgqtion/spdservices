package com.ies.spd.spdservices.tcp.core;

import com.ies.spd.spdservices.entity.Gateway;
import com.ies.spd.spdservices.service.EquipmentEventLogService;
import com.ies.spd.spdservices.service.GatewayService;
import com.ies.spd.spdservices.tcp.entity.TcpMsg;
import com.ies.spd.spdservices.utils.Log4jUtils;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * 业务处理
 * 
 * @author XiangXiaoLin
 *
 */
@Component
@Sharable
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	@Autowired
	private EquipmentEventLogService equipmentEventLogService;
	@Autowired
	private GatewayService gatewayService;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		// 获取网关IP和端口
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
				.remoteAddress();
		Log4jUtils.tcpHandler.info("用户建立连接:" + ctx.channel().remoteAddress());
		Gateway gateway = new Gateway();
		gateway.setHostAddress(insocket.getAddress().getHostAddress());
		gateway.setPort(insocket.getPort());
		gateway.setUpdateTime(new Date());
		gateway.setOnline(true);
		// 更新网关状态
		gatewayService.save(gateway);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
		String ipAddress=ClientMap.lockIpMap.get(ctx.channel().remoteAddress());
		ClientMap.lockMap.remove(ipAddress);
//		TcpServerHandler.equipmentService.downlineAllBy(ipAddress);
		Log4jUtils.tcpHandler.info("不活跃的用户:" + ctx.channel().remoteAddress());
	}

	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			TcpMsg tcpMsg = (TcpMsg) msg;
//			ClientMap.lockMap.put(tcpMsg.getIpAddress(), ctx);
//			ClientMap.lockIpMap.put(ctx.channel().remoteAddress(),tcpMsg.getIpAddress());
			equipmentEventLogService.tcpReceipt(tcpMsg);
		}catch(Exception e){
			Log4jUtils.tcpError.info("exception:" + this.getClass() + ",Message:" + e.getMessage());
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 当出现异常就关闭连接
		Log4jUtils.tcpError.info("异常断开连接的用户:" + ctx.channel().remoteAddress() + ",Message:" + cause.getMessage());
		ctx.close();
		// 获取网关IP和端口
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
				.remoteAddress();
		Gateway gateway = new Gateway();
		gateway.setHostAddress(insocket.getAddress().getHostAddress());
		gateway.setPort(insocket.getPort());
		gateway.setUpdateTime(new Date());
		gatewayService.disGateway(gateway);
	}
}
