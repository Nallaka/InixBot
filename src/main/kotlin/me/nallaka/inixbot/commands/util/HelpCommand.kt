package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class HelpCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(event: MessageReceivedEvent, args: Array<String>) {
        embeddedMessageBuilder.setTitle("Help :gear:")
                .setDescription("Do ``_help <command>`` for more information")
        embeddedMessageBuilder.addField("Fun Commands :boom:", "``8ball``, ``hello``,", true)
        embeddedMessageBuilder.addField("Music Commands :musical_note:", "``music``, ", true)
        embeddedMessageBuilder.addField("Utility Commands :tools:", "``changeheader``, ``coin``, ``ping``, ``rolldice``", true)
        messageHandler.sendMessage(event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(event: MessageReceivedEvent, args: Array<String>) {
        messageHandler.sendHelpMessage(event, embeddedMessageBuilder, "Help", "Helps you with Help?", "help help")
    }

    override fun executed(event: MessageReceivedEvent, args: Array<String>): Boolean {
        commandLogger.logCommand(event, args)
        return true
    }
}