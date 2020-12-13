package com.ies.spd.spdservices.tcp.core;

import com.ies.spd.spdservices.entity.Gateway;
import com.ies.spd.spdservices.service.GatewayService;
import com.ies.spd.spdservices.utils.Log4jUtils;
import com.ies.spd.spdservices.utils.PropertiesUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * tcp服务
 * @author XiangXiaoLin
 *
 */
@Component
public class TcpServer {
	private static final String IP;
	private static final int PORT;
	/**用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup;
	/**
	 * 创建bootstrap
	 */
	private ServerBootstrap serverBootstrap = new ServerBootstrap();

	@Resource
	private TcpServerHandler tcpServerHandler;
	@Autowired
	private GatewayService gatewayService;
	
	static{
		IP=PropertiesUtil.getValueByKey("tcp.ip");
		PORT=Integer.valueOf(PropertiesUtil.getValueByKey("tcp.port"));
		workerGroup=new NioEventLoopGroup(Integer.valueOf(PropertiesUtil.getValueByKey("biz.thread.size")));
	}
	//启动
	public void run() {
		gatewayService.disAllGateway();
		Log4jUtils.tcpLog.info("开始启动TCP服务器...");
		try {
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TcpDecode());  
					ch.pipeline().addLast(tcpServerHandler);
				}
			});
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f=serverBootstrap.bind(PORT).sync();
			Log4jUtils.tcpLog.info("TCP服务器已启动,PORT:"+PORT);
			f.channel().closeFuture().sync();
		}
		catch (InterruptedException e) {
			stop();
		}catch (Exception e) {
			Log4jUtils.tcpError.info("exception:"+TcpServer.class + ",Message:" + e.getMessage());
		}finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
	//停止
	public static void stop(){
		workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        Log4jUtils.tcpLog.info("TCP服务器已停止");
	}
}