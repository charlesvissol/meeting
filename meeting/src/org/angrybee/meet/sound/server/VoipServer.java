package org.angrybee.meet.sound.server;

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
		
		this.boss = new NioEventLoopGroup();
		this.worker = new NioEventLoopGroup();
		/**
		 * NioEventLoopGroup is a multithreaded event loop that handles I/O operation. 
		 * Netty provides various EventLoopGroup implementations for different kind of transports. 
		 * We are implementing a server-side application in this example, and therefore two NioEventLoopGroup 
		 * will be used. The first one, often called 'boss', accepts an incoming connection. 
		 * The second one, often called 'worker', handles the traffic of the accepted connection once 
		 * the boss accepts the connection and registers the accepted connection to the worker. 
		 * How many Threads are used and how they are mapped to the created Channels depends on the 
		 * EventLoopGroup implementation and may be even configurable via a constructor.
		 */
		
		this.group = new DefaultChannelGroup("All-Channels", GlobalEventExecutor.INSTANCE);
		
	}

	/**
	 * Binds the server to a {@link org.angrybee.sound.server.swagger.VoipServer#PORT}.
	 * 
	 * @param port
	 *            The port to bind to.
	 * 
	 * @throws Exception
	 *             If we could not bind on the specified
	 *             {@link org.angrybee.sound.server.swagger.VoipServer#PORT}.
	 */
	public void bind(int port) throws Exception {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			/**
			 * ServerBootstrap is a helper class that sets up a server. 
			 * You can set up the server using a Channel directly. 
			 * However, please note that this is a tedious process, 
			 * and you do not need to do that in most cases.
			 */
			
			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			/**
			 * Here, we specify to use the NioServerSocketChannel class which is used to instantiate a new Channel 
			 * to accept incoming connections.
			 */
			
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				public void initChannel(SocketChannel channel) {
					channel.pipeline().addLast(new VoipServerHandler(group));
				}

			});
			/**
			 * The handler specified here will always be evaluated by a newly accepted Channel. 
			 * The ChannelInitializer is a special handler that is purposed to help a user configure a new Channel. 
			 * It is most likely that you want to configure the ChannelPipeline of the new Channel by adding some 
			 * handlers such as DiscardServerHandler to implement your network application. 
			 * As the application gets complicated, it is likely that you will add more handlers to 
			 * the pipeline and extract this anonymous class into a top level class eventually.
			 */
			
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