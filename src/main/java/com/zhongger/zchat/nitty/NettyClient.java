package com.zhongger.zchat.nitty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class NettyClient {




        public  void stray() throws Exception {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast("decode",new StringDecoder());//解码器
                                socketChannel.pipeline().addLast("encode",new StringEncoder());
                                socketChannel.pipeline().addLast( new ClientHandler());

                            }

                });
                // 绑定端口并同步等待
                ChannelFuture channelFuture = bootstrap.connect("localhost", 7070).sync();

                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

                while (true){
                    channelFuture.channel().writeAndFlush(input.readLine() + "\n");
                }


            } finally {
                group.shutdownGracefully().sync();
            }
        }

    }

