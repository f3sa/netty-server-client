package esai

import esai.client.Client
import esai.server.Server
import kotlin.jvm.JvmStatic
import java.lang.InterruptedException
import java.io.IOException
import java.util.*

object Main
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val scanner = Scanner(System.`in`)
        val input = scanner.nextLine()

        try
        {
            if (input == "server") Server() else Client()
        }
        catch (e: InterruptedException)
        {
            e.printStackTrace()
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
    }
}