package raptor

import raptor.RaptorBot.Companion.jda
import raptor.listeners.EventListener
import raptor.listeners.MessageListener
import raptor.utils.BotProperties
import raptor.utils.commandmeta.CommandRegistry
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import raptor.utils.permissionmeta.Permissions


class RaptorBot {
    companion object {
        //Create JDA Bot
        val jda: JDA = JDABuilder(AccountType.BOT).setToken("NDQ4NjE2ODYyMzgxNzY4NzA0.DeY0hQ.aJ-R0IUbbzxd4XBdkppnhF9tqio").buildBlocking()
    }
}

fun main(args: Array<String>) {
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
