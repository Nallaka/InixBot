package raptor.commands.`fun`

import raptor.handlers.CommandHandler.CommandContainer
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

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
    override fun runCommand(args: Array<String>, commandContainer: CommandContainer) {
        embeddedMessageBuilder.setTitle("Hello :wave:").setDescription(commandContainer.author.name)
        sendMessage(commandContainer.event)
    }
}