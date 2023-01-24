package esai.server

import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.socket.nio.NioServerSocketChannel

class Server
{
    init
    {
        val group: EventLoopGroup = NioEventLoopGroup()

        try
        {
            val bootstrap = ServerBootstrap()
                .group(group)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(ServerInitializer())

            bootstrap.bind(PORT).sync().channel().closeFuture().syncUninterruptibly()
        }
        finally
        {
            group.shutdownGracefully()
        }
    }

    companion object
    {
        val PORT = System.getProperty("port", "8992").toInt()
    }
}