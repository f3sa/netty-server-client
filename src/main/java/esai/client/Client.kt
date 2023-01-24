package esai.client

import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.bootstrap.Bootstrap
import java.util.*

class Client
{
    init
    {
        val group: EventLoopGroup = NioEventLoopGroup()

        try
        {
            val bootstrap = Bootstrap()
                .group(group)
                .channel(NioSocketChannel::class.java)
                .handler(ClientInitializer())

            val channel = bootstrap.connect(HOST, PORT).sync().channel()
            val scanner = Scanner(System.`in`)

            while (true)
            {
                val input: String = scanner.nextLine()
                channel.writeAndFlush(input + "\r\n")
            }
        }
        finally
        {
            group.shutdownGracefully()
        }
    }

    companion object
    {
        val HOST: String = System.getProperty("host", "127.0.0.1")
        val PORT: Int = System.getProperty("port", "8992").toInt()
    }
}