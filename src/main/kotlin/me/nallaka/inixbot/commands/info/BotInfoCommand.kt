package me.nallaka.inixbot.commands.info

import me.nallaka.inixbot.InixBot.Companion.jda
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Bot Info",
        emoji = ":newspaper:",
        description = "Displays Bot Info",
        usage = "botinfo",
        aliases = [""],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class BotInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder
                .setTitle("Inix Bot")
                .setDescription("Made with **{JDA}** by Nallaka#9253")
                .setThumbnail(jda.selfUser.avatarUrl)
                .addField("Bot ID", "Coming Soon", true) //395413174456942592
                .addField("Guilds", "${jda.guilds.size}", true)
                .addField("Users", "${jda.users.size}", true)
                .addField("Invite Link", "Coming Soon", true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}