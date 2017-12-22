package me.nallaka.inixbot

import com.esotericsoftware.yamlbeans.YamlReader
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.eventmeta.EventLogger
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import java.io.FileReader

class InixBot {


    companion object {
        //Set Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //Set User Command Prefix
        var USER_COMMAND_PREFIX = "_"

        @JvmStatic
        fun main(args : Array<String>) {
            //YAML File Reader
            val yamlReader = YamlReader(FileReader("src/resources/botConfig.yml"))
            val botConfig = yamlReader.read() as? Map<*,*>

            //Get BotToken
            val botToken = botConfig?.get("BOT_TOKEN") as? String

            //Create JDA Bot
            val jda = JDABuilder(AccountType.BOT).setToken(botToken).buildBlocking()

            //Adding CommandHandler Listener
            jda.addEventListener(CommandHandler())

            //Adding EventLogger Listener
            jda.addEventListener(EventLogger())

            //TODO: Initialize CommandRegistry

            //TODO: Initialize Permissions
        }
    }
}