package com.zhongger.zchat.nitty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {
    Logger logger= LoggerFactory.getLogger(this.getClass());

    EventLoopGroup bossGroup=new NioEventLoopGroup();
    EventLoopGroup workGroup=new NioEventLoopGroup();
    public ChannelFuture start(String host, int port){
        ChannelFuture channelFuture=null;
        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decode",new StringDecoder());//解码器
                            socketChannel.pipeline().addLast("encode",new StringEncoder());
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });
            channelFuture=bootstrap.bind(host,port).sync();
            logger.info("服务启动成功");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  channelFuture;
    }
    public void  close(){
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        logger.info("关闭");
    }
}
