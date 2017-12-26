package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.utils.commandmeta.CommandRegistry
import me.nallaka.inixbot.utils.permissionmeta.Permissions
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class CommandHandler : ListenerAdapter() {
    //Create CommandRegistry Instance
    private var commandRegistry = CommandRegistry()

    //Create Permissions Instance
    var permissions = Permissions()

    //Checks if command with key "commandArgs[0]" exists in CommandRegistry
    fun isCommand(event: MessageReceivedEvent, commandArgs: Array<String>) : Boolean =
            event.author.jda.selfUser != event.author && commandRegistry.registryContainsKey(commandArgs[0])

    //Checks if command with key "commandArgs[0]" is a "help" Command
    fun isHelpCommand(event: MessageReceivedEvent, commandArgs: Array<String>) : Boolean =
            event.author.jda.selfUser != event.author && commandArgs[0] === "help"

    //Checks command type and executes
    fun executeCommand(event: MessageReceivedEvent, commandArgs: Array<String>) {
        /*val user = event.author
        val command = commandRegistry.getCommand(commandArgs[0])
        if (isCommand(event, commandArgs) || isHelpCommand(event, commandArgs) && commandArgs.size == 1 && permissions.userHasCommandPermission(user, command)) {
            command?.runCommand(event, commandArgs)
            command?.executed(event, commandArgs)
        } else if (isHelpCommand(event, commandArgs) && permissions.userHasCommandPermission(user, command)) {
            commandRegistry.getCommand(commandArgs[1])?.runHelpCommand(event, commandArgs)
            command?.executed(event, commandArgs)
        }*/
    }

    //Runs on MessageRecieved Event. Checks type of command and executes
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.content.startsWith(InixBot.DEFAULT_COMMAND_PREFIX) || event.message.content.startsWith(InixBot.USER_COMMAND_PREFIX)) {
            var beheadedCommand: String
            beheadedCommand = event.message.content.replaceFirst(InixBot.USER_COMMAND_PREFIX.toRegex(), "").toLowerCase()
            beheadedCommand = event.message.content.replaceFirst(InixBot.DEFAULT_COMMAND_PREFIX.toRegex(), "")
            val commandArgs = beheadedCommand.split("\\s".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

            executeCommand(event, commandArgs)
        }
    }

}