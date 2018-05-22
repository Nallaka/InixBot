package raptor.commands.admin

import raptor.RaptorBot
import raptor.handlers.CommandHandler
import raptor.utils.BotProperties
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Shutdown",
        emoji = ":radio_button:",
        description = "Shutsdown the bot",
        usage = "shutdown",
        aliases = ["shutdown", "kill"],
        commandPermissionLevel = PermissionLevel.OWNER,
        isOwnerOnly = true
)
class ShutdownCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.addField("Shutting Down :radio_button:", "Bye", true)
        sendMessage(commandContainer.event)
        BotProperties.botConfigYamlReader.close()
        RaptorBot.jda.shutdown()
        System.exit(0)
    }
}