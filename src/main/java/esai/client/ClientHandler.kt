package esai.client

import io.netty.channel.SimpleChannelInboundHandler
import kotlin.Throws
import java.lang.Exception
import io.netty.channel.ChannelHandlerContext

class ClientHandler : SimpleChannelInboundHandler<String?>()
{
    @Throws(Exception::class)
    public override fun channelRead0(ctx: ChannelHandlerContext, msg: String?)
    {
        System.err.println(msg)
    }
}