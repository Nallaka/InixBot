package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.commandmeta.CommandRegistry
import me.nallaka.inixbot.meta.permissionmeta.Permissions
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class CommandHandler : ListenerAdapter() {
    //Create CommandRegistry Instance
    private var commandRegistry = CommandRegistry()

    //Create Permissions Instance
    private var permissions = Permissions()

    //Create the commandContainer data class
    data class CommandContainer(val event: MessageReceivedEvent, val rawMessage: Message, val content: String, val author: User, val beheadedCommand: String, val splitBeheadedCommand: Array<String>, val invoke: String, val command: Command?, val args: Array<String>)

    //Parses a given command
    fun parseCommand(event: MessageReceivedEvent) : CommandContainer {
        val rawMessage = event.message
        val content = rawMessage.content
        val author = event.author
        val beheadedCommand = event.message.content.replaceFirst(InixBot.USER_COMMAND_PREFIX, "").replaceFirst(InixBot.DEFAULT_COMMAND_PREFIX, "").toLowerCase()
        val splitBeheadedCommand = beheadedCommand.split("\\s".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val invoke = splitBeheadedCommand[0]
        val command = commandRegistry.getCommand(invoke)
        val args: Array<String> = splitBeheadedCommand.copyOfRange(1, splitBeheadedCommand.size)
        return CommandContainer(event, rawMessage, content, author, beheadedCommand, splitBeheadedCommand, invoke, command, args)
    }

    //Checks if command with key invoke exists in CommandRegistry
    fun isCommand(event: MessageReceivedEvent, invoke: String) : Boolean =
            event.author.jda.selfUser != event.author && commandRegistry.registryContainsKey(invoke) && invoke != "help"

    //Checks if command with key invoke is a "help" Command
    fun isHelpCommand(event: MessageReceivedEvent, invoke: String) : Boolean =
            event.author.jda.selfUser != event.author && invoke == "help"

    //Checks command type and executes
    fun executeCommand(commandContainer: CommandContainer) {
        val user = commandContainer.author
        val event = commandContainer.event
        val args = commandContainer.args
        val invoke = commandContainer.invoke
        val command = commandContainer.command
        if (isCommand(event, invoke) || isHelpCommand(event, invoke) && commandContainer.splitBeheadedCommand.isNotEmpty() && permissions.userHasCommandPermission(user, command)) {
            command?.runCommand(args, commandContainer)
            command?.executed(commandContainer)
        } else if (isHelpCommand(event, invoke)) {
            commandRegistry.getCommand(args[0])?.runHelpCommand(args, commandContainer)
            command?.executed(commandContainer)
        }
    }
}