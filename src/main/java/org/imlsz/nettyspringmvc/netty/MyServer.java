package org.imlsz.nettyspringmvc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {

	private final int port;

	public MyServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		ServerBootstrap server = new ServerBootstrap();
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			server.group(boss, work)
					.channel(NioServerSocketChannel.class).localAddress(port)
					.childHandler(new DispatcherServletChannelInitializer());
			server.bind().sync().channel().closeFuture().sync();
		}
		finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new MyServer(port).run();
	}
}
