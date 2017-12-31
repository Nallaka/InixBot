package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.BotProperties
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.awt.Color

abstract class Command {
    //Command Logger
    var commandLogger = CommandLogger()

    //Message Builder
    var embeddedMessageBuilder: EmbedBuilder = EmbedBuilder().setColor(Color.CYAN).clearFields().setTitle(null).setDescription(null)

    //runCommand: Runs the command
    abstract fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer)

    //executed: Logs command and returns if executed completely
    fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }

    //runHelpCommand: Send help message using annotation properties
    fun runHelpCommand(commandContainer: CommandHandler.CommandContainer) {
        sendHelpMessage(commandContainer.event, embeddedMessageBuilder, getCmdProperties())
    }

    //getCmdProperties: Returns the annotation properties
    fun getCmdProperties(): ICommand? {
        return if (this.javaClass.isAnnotationPresent(ICommand::class.java))
            this.javaClass.getAnnotation(ICommand::class.java)
        else
            null
    }

    //Sends Embedded Message
    fun sendMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder) {
        event.textChannel.sendMessage(embedBuilder.build()).queue()
        clearEmbeddedBuilder(embedBuilder)
    }

    //Sends Formatted Embedded Help Message
    fun sendHelpMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder, cmdProperties: ICommand?) {

        embedBuilder.setTitle("${cmdProperties?.name} ${cmdProperties?.emoji}")
                .setDescription(cmdProperties?.description)
                .addField("Usage", "``${BotProperties.DEFAULT_COMMAND_PREFIX}${cmdProperties?.usage}``", true)
                .addField("Aliases", cmdProperties?.aliases?.contentToString(), true)
                .addField("Required Permission", "${cmdProperties?.commandPermissionLevel}", true )
        sendMessage(event, embedBuilder)
        clearEmbeddedBuilder(embedBuilder)
    }

    //Clears the current EmbedMessage
    fun clearEmbeddedBuilder(embedBuilder: EmbedBuilder) = embedBuilder.setTitle(null).setDescription(null).clearFields()
}