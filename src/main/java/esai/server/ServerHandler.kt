package esai.server

import io.netty.channel.SimpleChannelInboundHandler
import kotlin.Throws
import java.lang.Exception
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.group.ChannelGroup
import io.netty.channel.group.DefaultChannelGroup
import io.netty.util.concurrent.GlobalEventExecutor

class ServerHandler : SimpleChannelInboundHandler<String>()
{
    @Throws(Exception::class)
    override fun handlerAdded(ctx: ChannelHandlerContext)
    {
        val incoming = ctx.channel()
        channels.add(incoming)

        println("""[INFO] - (${incoming.remoteAddress()}) has joined!""")
        println("""[INFO] - Total connected: ${channels.size}""")

        for (channel in channels)
        {
            channel.writeAndFlush("""[SERVER] - (${incoming.remoteAddress()}) has joined!""" + "\n")
        }
    }

    @Throws(Exception::class)
    override fun handlerRemoved(ctx: ChannelHandlerContext)
    {
        val incoming = ctx.channel()
        channels.remove(incoming) ; ctx.close()

        println("""[INFO] - (${incoming.remoteAddress()}) has left!""")
        println("""[INFO] - Total connected: ${channels.size}""")

        for (channel in channels)
        {
            channel.writeAndFlush("""[SERVER] - (${incoming.remoteAddress()}) has left!""" + "\n")
        }
    }

    @Throws(Exception::class)
    public override fun channelRead0(ctx: ChannelHandlerContext, msg: String)
    {
        val incoming = ctx.channel()

        for (channel in channels)
        {
            if (channel != incoming)
            {
                channel.writeAndFlush("""(${incoming.remoteAddress()}): $msg""" + "\n")
            }
        }
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {}

    companion object
    {
        val channels: ChannelGroup = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    }
}