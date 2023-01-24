package esai.client

import kotlin.Throws
import java.lang.Exception
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

class ClientInitializer : ChannelInitializer<SocketChannel>()
{
    @Throws(Exception::class)
    public override fun initChannel(socketChannel: SocketChannel)
    {
        val pipeline: ChannelPipeline = socketChannel.pipeline()

        pipeline.addLast(DelimiterBasedFrameDecoder(8192, *Delimiters.lineDelimiter()))
        pipeline.addLast(StringDecoder())
        pipeline.addLast(StringEncoder())
        pipeline.addLast(ClientHandler())
    }
}