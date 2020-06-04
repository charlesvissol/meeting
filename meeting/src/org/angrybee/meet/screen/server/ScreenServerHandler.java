package org.angrybee.meet.screen.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler for the Screen server: relays the images of the screen sent by 
 * sender and captured by receivers connected
 * @author Charles Vissol
 *
 */
public class ScreenServerHandler  extends ChannelHandlerAdapter {
	/**
	 * Logger instance
	 */
	private static final Logger logger = LoggerFactory.getLogger(ScreenServerHandler.class);
	/**
	 * @see <a href="http://netty.io/5.0/api/io/netty/channel/group/ChannelGroup.html">ChannelGroup</a>
	 */
	private ChannelGroup group;

	/**
	 * Creates a new ScreenServerHandler
	 */
	public ScreenServerHandler(ChannelGroup group) {
		this.group = group;
	}

	/**
	 * When a client is connected, this method registers their channel
	 */
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		group.add(ctx.channel());
		logger.info("Connection received:" + ctx.channel().localAddress());
	}

	/**
	 * Reads incoming data and sends it to stored channels in the
	 * {@link org.angrybee.sound.server.swagger.server.VoipServerHandler}
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf buffer = (ByteBuf) msg;
		try {
			group.writeAndFlush(buffer.copy());
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	/**
	 * If an exception occurs, this method prints a stacktrace and closes the
	 * faulty channel.
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
