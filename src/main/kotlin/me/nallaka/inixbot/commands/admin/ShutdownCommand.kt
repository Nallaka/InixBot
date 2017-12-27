package me.nallaka.inixbot.commands.admin

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Shutdown",
        emoji = ":radio_button:",
        description = "Shutsdown the bot",
        usage = "_shutdown",
        aliases = [],
        isAdminOnly = false,
        isOwnerOnly = true
)
class ShutdownCommand : Command(PermissionLevel.ADMIN) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.addField("Shutting Down :radio_button:", "Bye", true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
        InixBot.jda.shutdown()
        System.exit(0)
    }
}