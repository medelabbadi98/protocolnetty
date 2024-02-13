package com.protocol.protocolnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {

    @Value("${gps.server.port}")
    private int nettyServerPort; // You can define this property in your application.properties or application.yml

    @Autowired
    private NettyServerInitializer nettyServerInitializer;

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        return serverBootstrap;
    }

    @Bean
    public io.netty.channel.ChannelFuture tcpServer(ServerBootstrap serverBootstrap) {
        try {
            return serverBootstrap
                    .childHandler(nettyServerInitializer)
                    .bind(nettyServerPort)
                    .sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("Error starting Netty server", e);
        }
    }
}
