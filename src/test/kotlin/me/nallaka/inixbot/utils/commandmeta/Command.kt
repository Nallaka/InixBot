package me.nallaka.inixbot.utils.commandmeta

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.handlers.CommandMessageHandler
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.EmbedBuilder
import java.awt.Color

abstract class Command(val commandPermissionLevel: PermissionLevel) {
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

    abstract fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer)

    abstract fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer)

    abstract fun executed(commandContainer: CommandHandler.CommandContainer): Boolean

}