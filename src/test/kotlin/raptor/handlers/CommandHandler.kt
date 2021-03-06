package raptor.handlers

import raptor.RaptorBot.Companion.jda
import raptor.utils.BotProperties
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.CommandRegistry
import raptor.utils.permissionmeta.Permissions
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.awt.Color

class CommandHandler : ListenerAdapter() {
    //Create CommandRegistry Instance
    private var commandRegistry = CommandRegistry()

    //Create Permissions Instance
    private var permissions = Permissions()

    //Create the commandContainer data class
    data class CommandContainer(val event: MessageReceivedEvent, val rawMessage: Message, val content: String, val author: User, val beheadedCommand: String, val splitBeheadedCommand: Array<String>, val invoke: String, val command: Command?, val args: Array<String>)

    //Parses a given command
    fun parseCommand(event: MessageReceivedEvent): CommandContainer {
        val rawMessage = event.message
        val content = rawMessage.content
        val author = event.author
        val beheadedCommand = event.message.content.replaceFirst(BotProperties.USER_COMMAND_PREFIX, "").replaceFirst(BotProperties.DEFAULT_COMMAND_PREFIX, "").toLowerCase()
        val splitBeheadedCommand = beheadedCommand.split("\\s".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val invoke = splitBeheadedCommand[0]
        val command = commandRegistry.getCommand(invoke)
        val args: Array<String> = splitBeheadedCommand.copyOfRange(1, splitBeheadedCommand.size)
        return CommandContainer(event, rawMessage, content, author, beheadedCommand, splitBeheadedCommand, invoke, command, args)
    }

    //Checks command type and executes
    fun executeCommand(commandContainer: CommandContainer) {
        val user = commandContainer.author
        val event = commandContainer.event
        val args = commandContainer.args
        val invoke = commandContainer.invoke
        val command = commandContainer.command
        val messageBuilder = EmbedBuilder().setColor(Color.CYAN)

        if (command != null) {
            jda.asBot().jda.getTextChannelById(commandContainer.event.textChannel.id).sendTyping().queue()
            if (command.getCmdProperties()!!.isOwnerOnly && user.id != "131068934907494400") {
                messageBuilder.addField("ERROR :no_entry:", "You Don't Have Permission!", true)
                event.textChannel.sendMessage(messageBuilder.build()).queue()
            } else {
                if (isCommand(event, invoke) && commandContainer.splitBeheadedCommand.isNotEmpty()) {
                    command.runCommand(args, commandContainer)
                    command.executed(commandContainer)
                } else if (isHelpCommand(event, invoke)) {
                    if (args.isNotEmpty()) {
                        commandRegistry.getCommand(args[0])?.runHelpCommand(commandContainer)
                        command.executed(commandContainer)
                    } else {
                        command.runCommand(args, commandContainer)
                        command.executed(commandContainer)
                    }
                } else if (isCommand(event, invoke)) {
                    messageBuilder.addField("ERROR :no_entry:", "You Don't Have Permission!", true)
                    event.textChannel.sendMessage(messageBuilder.build()).queue()
                }
            }
        }
    }

    //Checks if command with key invoke exists in CommandRegistry
    fun isCommand(event: MessageReceivedEvent, invoke: String): Boolean =
            event.author.jda.selfUser != event.author && commandRegistry.getCommandRegistry().containsKey(invoke) && invoke != "help"

    //Checks if command with key invoke is a "help" Command
    fun isHelpCommand(event: MessageReceivedEvent, invoke: String): Boolean =
            event.author.jda.selfUser != event.author && invoke == "help"
}