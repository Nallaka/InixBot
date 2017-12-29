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
        usage = "ping",
        aliases = [""],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class PingCommand  : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.addField(MessageEmbed.Field("Pong! :ping_pong:", java.lang.Long.toString(commandContainer.author.jda.ping) + "ms", true))
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}