package org.openjdev.screen.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple Voip Server
 * 
 * @author Suraj Kumar <k975@live.co.uk>
 * @version 1.0
 */
public class VoipServer {
	/**
	 * The Logger instance
	 */
	private static final Logger logger = LoggerFactory.getLogger(VoipServer.class);
	/**
	 * The port the server will listen on
	 */
	private static final int PORT = Integer.parseInt(System.getProperty("port", "5060"));

	private EventLoopGroup boss;
	private EventLoopGroup worker;
	/**
	 * @see <a href="http://netty.io/5.0/api/io/netty/channel/group/ChannelGroup.html">ChannelGroup</a>
	 */
	private ChannelGroup group;

	/**
	 * Creates a new VoipServer
	 */
	public VoipServer() {
		boss = new NioEventLoopGroup();
		worker = new NioEventLoopGroup();
		group = new DefaultChannelGroup("All-Channels",	GlobalEventExecutor.INSTANCE);
	}

	/**
	 * Binds the server to a {@link com.swagger.VoipServer#PORT}.
	 * 
	 * @param port
	 *            The port to bind to.
	 * 
	 * @throws Exception
	 *             If we could not bind on the specified
	 *             {@link com.swagger.VoipServer#PORT}.
	 */
	public void bind(int port) throws Exception {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();

			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				public void initChannel(SocketChannel channel) {
					channel.pipeline().addLast(new VoipServerHandler(group));
				}

			});
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = bootstrap.bind(port).sync();
			logger.info("Successfully binded server to port:" + PORT);

			future.channel().closeFuture().sync();
		} finally {
			worker.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}

	/**
	 * Launches the server
	 * 
	 * @param args
	 *            Runtime arguments
	 * @throws Exception
	 *             If we could not start the server
	 */
	public static void main(String[] args) throws Exception {
		new VoipServer().bind(PORT);
	}
}