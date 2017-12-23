package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.meta.commandmeta.CommandRegistry
import me.nallaka.inixbot.meta.permissionmeta.Permissions
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class CommandHandler : ListenerAdapter() {
    //Create CommandRegistry Instance
    private var commandRegistry = CommandRegistry()

    //Create Permissions Instance
    private var permissions = Permissions()

    //Checks if command with key "commandArgs[0]" exists in CommandRegistry
    fun isCommand(event: MessageReceivedEvent, commandArgs: Array<String>) : Boolean =
            event.author.jda.selfUser != event.author && commandRegistry.registryContainsKey(commandArgs[0])

    //Checks if command with key "commandArgs[0]" is a "help" Command
    fun isHelpCommand(event: MessageReceivedEvent, commandArgs: Array<String>) : Boolean =
            event.author.jda.selfUser != event.author && commandArgs[0] == "help"

    //Checks command type and executes
    fun executeCommand(event: MessageReceivedEvent, commandArgs: Array<String>) {
        val user = event.author
        val command = commandRegistry.getCommand(commandArgs[0])
        if (isCommand(event, commandArgs) || isHelpCommand(event, commandArgs) && commandArgs.size == 1 && permissions.userHasCommandPermission(user, command)) {
            print("iscommand")
            command?.runCommand(event, commandArgs)
            command?.executed(event, commandArgs)
        } else if (isHelpCommand(event, commandArgs) && permissions.userHasCommandPermission(user, commandRegistry.getCommand(commandArgs[1]))) {
            println("ishelpcommand")
            commandRegistry.getCommand(commandArgs[1])?.runHelpCommand(event, commandArgs)
            command?.executed(event, commandArgs)
        }
    }

    //Runs on MessageRecieved Event. Checks type of command and executes
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.content.startsWith(InixBot.DEFAULT_COMMAND_PREFIX) || event.message.content.startsWith(InixBot.USER_COMMAND_PREFIX)) {
            val beheadedCommand = event.message.content.removeRange(0, 1)
            val commandArgs = beheadedCommand.split("\\s".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            executeCommand(event, commandArgs)
            //Testing:
            print("TESTING: ")
            for (arg: String in commandArgs) {
                print("$arg ")
            }
            println()
        }
    }

}