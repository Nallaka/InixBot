package me.nallaka.inixbot

import com.esotericsoftware.yamlbeans.YamlReader
import me.nallaka.inixbot.InixBot.Companion.jda
import me.nallaka.inixbot.listeners.EventListener
import me.nallaka.inixbot.listeners.MessageListener
import me.nallaka.inixbot.utils.commandmeta.CommandRegistry
import me.nallaka.inixbot.utils.permissionmeta.Permissions
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import java.io.FileReader


class InixBot {

    companion object {
        //Set Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //Set User Command Prefix
        var USER_COMMAND_PREFIX = "_"
        //YAML File Reader
        private val filePath = System.getProperty("user.dir") + "/botConfig.yml"
        private val yamlReader = YamlReader(FileReader(filePath))
        private val botConfig = yamlReader.read() as? Map<*,*>

        //Get BotToken
        private val botToken = botConfig?.get("BOT_TOKEN") as? String

        //Create JDA Bot
        val jda = JDABuilder(AccountType.BOT).setToken(botToken).buildBlocking()
    }
}

fun main(args : Array<String>) {

    //Adding MessageListener Listener
    jda.addEventListener(MessageListener())

    //Adding EventLogger Listener
    jda.addEventListener(EventListener())

    //Initialize the CommandRegistry
    val commandRegistry = CommandRegistry()
    commandRegistry.setCommandRegistry()

    //Initializing all permissions
    val permissions = Permissions()
    permissions.loadGuildUsersPermissions()
    permissions.setGuildUsersDefaultPermissions(jda)
    permissions.printPermissions()
}