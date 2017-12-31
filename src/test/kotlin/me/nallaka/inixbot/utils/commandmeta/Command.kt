package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.BotProperties
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.awt.Color

abstract class Command {
    //Command Logger
    private var commandLogger = CommandLogger()

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
        sendHelpMessage(commandContainer.event)
    }

    //getCmdProperties: Returns the annotation properties
    fun getCmdProperties(): ICommand? {
        return if (this.javaClass.isAnnotationPresent(ICommand::class.java))
            this.javaClass.getAnnotation(ICommand::class.java)
        else
            null
    }

    //Sends Embedded Message
    fun sendMessage(event: MessageReceivedEvent) {
        event.textChannel.sendMessage(embeddedMessageBuilder.build()).queue()
        clearEmbeddedBuilder(embeddedMessageBuilder)
    }

    //Sends Formatted Embedded Help Message
    fun sendHelpMessage(event: MessageReceivedEvent) {
        val cmdProperties = this.getCmdProperties()
        embeddedMessageBuilder.setTitle("${cmdProperties?.name} ${cmdProperties?.emoji}")
                .setDescription(cmdProperties?.description)
                .addField("Usage", "``${BotProperties.DEFAULT_COMMAND_PREFIX}${cmdProperties?.usage}``", true)
                .addField("Aliases", cmdProperties?.aliases?.contentToString(), true)
                .addField("Required Permission", "${cmdProperties?.commandPermissionLevel}", true )
        sendMessage(event)
        clearEmbeddedBuilder(embeddedMessageBuilder)
    }

    fun sendUsageMessage(event: MessageReceivedEvent) {
        val cmdProperties = this.getCmdProperties()
        embeddedMessageBuilder.setTitle("${cmdProperties?.name} ${cmdProperties?.emoji}")
                .setDescription(cmdProperties?.description)
                .addField("Usage", "``${BotProperties.DEFAULT_COMMAND_PREFIX}${cmdProperties?.usage}``", true)
        sendMessage(event)
    }

    //Clears the current EmbedMessage
    fun clearEmbeddedBuilder(embeddedMessageBuilder: EmbedBuilder) = embeddedMessageBuilder.setTitle(null).setDescription(null).clearFields()
}