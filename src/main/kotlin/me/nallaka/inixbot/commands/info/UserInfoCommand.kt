package me.nallaka.inixbot.commands.info

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "User Info",
        emoji = ":newspaper:",
        description = "Displays a Users Info",
        usage = "_userinfo",
        aliases = [""],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class UserInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val member = commandContainer.event.member
        embeddedMessageBuilder
                .setTitle("${commandContainer.author.name}${commandContainer.author.discriminator}")
                .setThumbnail(commandContainer.author.avatarUrl)
                .setImage(commandContainer.author.avatarUrl)
                .addField("User ID", commandContainer.author.id, true)
                .addField("Nickname", member.nickname, true)
                .addField("Status", "${member.onlineStatus}", true)
                .addField("Playing", "${member.game}", true)
                .addField("Mutual Guilds", "${commandContainer.author.mutualGuilds}", true)
                .addField("Roles${member.roles.size}", "${member.roles}", true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}