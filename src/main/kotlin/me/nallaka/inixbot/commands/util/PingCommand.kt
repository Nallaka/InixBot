package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.entities.MessageEmbed
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class PingCommand  : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        embeddedMessageBuilder.addField(MessageEmbed.Field("Pong! :ping_pong:", java.lang.Long.toString(event.author.jda.ping) + "ms", true))
        messageHandler.sendMessage(event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Ping :ping_pong:", "Returns Latency", "ping")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}