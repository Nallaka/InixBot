package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.handlers.CommandMessageHandler
import net.dv8tion.jda.core.EmbedBuilder
import java.awt.Color

abstract class Command {
    //Command Logger
    var commandLogger = CommandLogger()

    //Message Builder
    var embeddedMessageBuilder: EmbedBuilder = EmbedBuilder().setColor(Color.CYAN).clearFields().setTitle(null).setDescription(null)

    //Command Message Handler
    var commandMessageHandler = CommandMessageHandler()

    //User Defined Prefix
    protected var userCommandPrefix = InixBot.USER_COMMAND_PREFIX

    //Default Prefix
    protected var defaultCommandPrefix = InixBot.DEFAULT_COMMAND_PREFIX

    //runCommand: Runs the command
    abstract fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer)

    //executed: Logs command and returns if executed completely
    fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }

    //runHelpCommand: Send help message using annotation properties
    fun runHelpCommand(commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "${getCmdProperties()?.name} ${getCmdProperties()?.emoji}", "${getCmdProperties()?.description}", "${getCmdProperties()?.usage}", "${getCmdProperties()?.commandPermissionLevel}")
    }

    //getCmdProperties: Returns the annotation properties
    fun getCmdProperties(): ICommand? {
        return if (this.javaClass.isAnnotationPresent(ICommand::class.java))
            this.javaClass.getAnnotation(ICommand::class.java)
        else
            null
    }
}