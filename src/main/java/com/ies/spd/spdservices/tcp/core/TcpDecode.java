package com.ies.spd.spdservices.tcp.core;

import com.ies.spd.spdservices.tcp.entity.TcpMsg;
import com.ies.spd.spdservices.utils.Log4jUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

/**
 * 解码器
 * @author XiangXiaoLin
 *
 */
public class TcpDecode extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] reds=new byte[27];
		byte red=0;
		int f=0;
		while(in.isReadable()){
//			Log4jUtils.tcpLog.info("f:"+f);
			int len=in.readableBytes();
			if(f==0&&len<27){
				return;
			}
			red=in.readByte();
			reds[f]=red;
			//开头错误数据
			/**
			 * 头校验字节占两个字节校验码0x7F
			 */
			if(reds[0]!=0x7F||(f>0&&reds[1]!=0x7F)){
				Log4jUtils.tcpError.info("headError:"+Arrays.toString(reds));
				reds=new byte[27];
				f=0;
				continue;
			}
			//正常开头与结尾数据
			/**
			 * 结尾校验占用两个自己分别是0x0D/0x0A
			 */
			if(f==26&&reds[f-1]==0x0D && reds[f]==0x0A){
				// 获取网关IP和端口
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
						.remoteAddress();
				TcpMsg tcpMsg=new TcpMsg(reds,insocket.getAddress().getHostAddress(),insocket.getPort());
				out.add(tcpMsg);

				Log4jUtils.tcpDecode.info(insocket.getAddress().getHostAddress()+"IP---端口号"+insocket.getPort()+"ok:"+tcpMsg.getHexMsg());
				reds=new byte[27];
				f=0;
				continue;
			}
			//错误数据
			if(f==26){
				Log4jUtils.tcpError.info("endError:"+Arrays.toString(reds));
				reds=new byte[27];
				f=0;
				continue;
			}
			f++;
		}

	}
}
