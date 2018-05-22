package raptor.commands.util

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.entities.MessageEmbed

@ICommand(
        name = "Ping",
        emoji = ":ping_pong:",
        description = "Returns your Ping",
        usage = "ping",
        aliases = ["ping"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class PingCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.addField(MessageEmbed.Field("Pong! :ping_pong:", java.lang.Long.toString(commandContainer.author.jda.ping) + "ms", true))

        sendMessage(commandContainer.event)
    }
}