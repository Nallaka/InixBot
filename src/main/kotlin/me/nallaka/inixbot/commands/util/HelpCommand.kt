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
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class HelpCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Help :gear:")
                .setDescription("Do ``_help <command>`` for more information")
        embeddedMessageBuilder.addField("Fun Commands :boom:", "``8ball``, ``hello``,", true)
                .addField("Music Commands :musical_note:", "``music``, ", true)
                .addField("Utility Commands :tools:", "``coinflip``, ``ping``, ``rng``", true)
                .addField("Admin Commands :red_circle:", "``changeprefix``, ``shutdown``", true)

        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}