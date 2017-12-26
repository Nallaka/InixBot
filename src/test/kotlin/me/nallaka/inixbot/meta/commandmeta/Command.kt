package me.nallaka.inixbot.meta.commandmeta

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandMessageHandler
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.awt.Color

abstract class Command(val commandPermissionLevel: PermissionLevel) {
    var commandLogger = CommandLogger()
    var embeddedMessageBuilder = EmbedBuilder().setColor(Color.CYAN).clearFields().setTitle(null).setDescription(null)
    var messageHandler = CommandMessageHandler()
    protected var userCommandPrefix = InixBot.USER_COMMAND_PREFIX
    protected var defaultCommandPrefix = InixBot.DEFAULT_COMMAND_PREFIX

    abstract fun runCommand(event: MessageReceivedEvent, args: Array<String>)

    abstract fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>)

    abstract fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean
}