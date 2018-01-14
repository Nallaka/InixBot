package me.nallaka.inixbot

import me.nallaka.inixbot.listeners.EventListener
import me.nallaka.inixbot.listeners.MessageListener
import me.nallaka.inixbot.utils.BotProperties
import me.nallaka.inixbot.utils.commandmeta.CommandRegistry
import me.nallaka.inixbot.utils.permissionmeta.Permissions
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder


class InixBot {
    companion object {
        //Create JDA Bot
        val jda: JDA = JDABuilder(AccountType.BOT).setToken(BotProperties.BOT_TOKEN).buildBlocking()
    }
}

fun main(args : Array<String>) {
    //Creating main JDA object
    val jda: JDA = JDABuilder(AccountType.BOT).setToken(BotProperties.BOT_TOKEN).buildBlocking()

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
