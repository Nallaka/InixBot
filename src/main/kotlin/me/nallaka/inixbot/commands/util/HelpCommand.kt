package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel

class HelpCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Help :gear:")
                .setDescription("Do ``_help <command>`` for more information")
        embeddedMessageBuilder.addField("Fun Commands :boom:", "``8ball``, ``hello``,", true)
        embeddedMessageBuilder.addField("Music Commands :musical_note:", "``music``, ", true)
        embeddedMessageBuilder.addField("Utility Commands :tools:", "``changeprefix``, ``coinflip``, ``ping``, ``rolldice``", true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Help", "Helps you with Help?", "help help")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return true
    }
}