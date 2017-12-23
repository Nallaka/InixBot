package me.nallaka.inixbot

import com.esotericsoftware.yamlbeans.YamlReader
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.CommandRegistry
import me.nallaka.inixbot.meta.eventmeta.EventLogger
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import me.nallaka.inixbot.meta.permissionmeta.Permissions
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.User
import java.io.FileReader

class InixBot {

    companion object {
        //Set Default Command Prefix
        val DEFAULT_COMMAND_PREFIX = "_"

        //Set User Command Prefix
        var USER_COMMAND_PREFIX = "."
    }
}

fun main(args : Array<String>) {
    //YAML File Reader
    val filePath = System.getProperty("user.dir") + "/src/main/kotlin/resources/botConfig.yml"
    val yamlReader = YamlReader(FileReader(filePath))
    val botConfig = yamlReader.read() as? Map<*,*>

    //Get BotToken
    val botToken = botConfig?.get("BOT_TOKEN") as? String

    //Create JDA Bot
    val jda = JDABuilder(AccountType.BOT).setToken(botToken).buildBlocking()

    //Adding CommandHandler Listener
    jda.addEventListener(CommandHandler())

    //Adding EventLogger Listener
    jda.addEventListener(EventLogger())

    //Initialize the CommandRegistry
    val commandRegistry = CommandRegistry()
    commandRegistry.setCommandRegistry()

    //Initializing all permissions
    val permissions = Permissions()
    permissions.loadGuildUsersPermissions()
    permissions.setGuildUsersDefaultPermissions(jda)
    permissions.printPermissions()
}