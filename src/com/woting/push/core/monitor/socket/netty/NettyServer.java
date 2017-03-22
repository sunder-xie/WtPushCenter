package com.woting.push.core.monitor.socket.netty;

import java.util.concurrent.TimeUnit;

import com.woting.push.config.AffirmCtlConfig;
import com.woting.push.config.PushConfig;
import com.woting.push.core.SocketHandleConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {
    PushConfig pc=null;
    SocketHandleConfig sc=null;
    AffirmCtlConfig acc=null;
    public NettyServer(PushConfig pc, SocketHandleConfig sc, AffirmCtlConfig acc) {
        this.pc=pc;
        this.sc=sc;
        this.acc=acc;
    }

    public void begin() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //加入空闲事件，为长连接做准备(In and Out) 并且处理写空闲时，传送语音控制回复
                    ch.pipeline().addLast("idleEve", new IdleStateHandler(sc.get_MonitorDelay(), acc.get_M_InternalResend(), 0, TimeUnit.MILLISECONDS));
                    //写过程(Out)
                    ch.pipeline().addLast("encodeMsg", new MsgEncoder());
                    //读过程(In)
                    ch.pipeline().addLast("delimiterPack", new DelimiterBasedFrameDecoder(1024, false, Unpooled.copiedBuffer("^^".getBytes())));
                    ch.pipeline().addLast("decodePack", new MsgDecoder());
                    ch.pipeline().addLast("bizHandler", new NettyHandler());
                    //处理发送消息事件(In and Out)
                    ch.pipeline().addLast("sendEve", new SendEventHandler());
                    //加入空闲事件，传送一般消息控制回复
                    ch.pipeline().addLast("idleEveForResendCtrAffirm_MsgNormal", new IdleStateHandler(0, acc.get_N_InternalResend(), 0, TimeUnit.MILLISECONDS));
                    ch.pipeline().addLast("ResendCtrAffirm_MsgNormal", new ResendCtrAffirmMsgNormalHandler());
                }
            });
            b.option(ChannelOption.SO_BACKLOG, 128);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);

            b.bind(pc.get_ControlTcpPort()).sync().channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
    }
}