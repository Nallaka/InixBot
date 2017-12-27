package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Hello",
        emoji = ":wave:",
        description = "Says Hello!",
        usage = "_hello",
        aliases = [""],
        isAdminOnly = false,
        isOwnerOnly = false
)
class HelloCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Hello :wave:").setDescription(commandContainer.author.name)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}