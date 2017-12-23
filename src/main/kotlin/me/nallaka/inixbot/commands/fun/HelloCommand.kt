package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class HelloCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        embeddedMessageBuilder.setTitle("Hello :wave:").setDescription(event.message.author.name)
        messageHandler.sendMessage(event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Hello", "Simply Says Hello", "hello")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}