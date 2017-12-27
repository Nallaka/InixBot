package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Help",
        emoji = ":gear:",
        description = "Helps You",
        usage = "_help <command>",
        aliases = [""],
        isAdminOnly = false,
        isOwnerOnly = false
)
class HelpCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Help :gear:")
                .setDescription("Do ``_help <command>`` for more information")
        embeddedMessageBuilder.addField("Fun Commands :boom:", "``8ball``, ``hello``,", true)
        embeddedMessageBuilder.addField("Music Commands :musical_note:", "``music``, ", true)
        embeddedMessageBuilder.addField("Utility Commands :tools:", "``changeprefix``, ``coinflip``, ``ping``, ``rng``", true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}