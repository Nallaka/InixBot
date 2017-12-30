package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Hello",
        emoji = ":wave:",
        description = "Says Hello!",
        usage = "hello",
        aliases = ["hello", "hi"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class HelloCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Hello :wave:").setDescription(commandContainer.author.name)
        sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}