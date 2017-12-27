package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.entities.MessageEmbed
@ICommand(
        name = "Ping",
        emoji = ":ping_pong:",
        description = "Returns your Ping",
        usage = "_ping",
        aliases = [""],
        isAdminOnly = false,
        isOwnerOnly = false
)
class PingCommand  : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.addField(MessageEmbed.Field("Pong! :ping_pong:", java.lang.Long.toString(commandContainer.author.jda.ping) + "ms", true))
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}