package org.example.controller;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.apache.dubbo.remoting.transport.netty4.NettyServerHandler;

import java.nio.channels.SocketChannel;


public class ServerChannelInitializer extends ChannelInitializer<Channel> {
//    @Override
//    protected void initChannel(SocketChannel socketChannel) throws Exception {
//            //添加编解码
//            socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//            socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//            socketChannel.pipeline().addLast(new NettyServerHandler());
//    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //添加编解码
        channel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new NettyServerHandle());
    }
}
