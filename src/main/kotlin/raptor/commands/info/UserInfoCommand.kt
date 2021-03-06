package raptor.commands.info

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.entities.Role
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.exceptions.ErrorResponseException
import java.awt.Color

@ICommand(
        name = "User Info",
        emoji = ":newspaper:",
        description = "Displays a Users Info",
        usage = "userinfo [user-mention | user-id]",
        aliases = ["userinfo", "user"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class UserInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        var userRoles = ""
        var user: User = commandContainer.author

        if (commandContainer.rawMessage.mentionedUsers.isNotEmpty()) {
            user = commandContainer.rawMessage.mentionedUsers[0]
        } else if (args.isNotEmpty()) {
            try {
                user = commandContainer.event.jda.retrieveUserById(args[0]).complete()
            } catch (e: ErrorResponseException) {
                e.printStackTrace()
                embeddedMessageBuilder.addField("ERROR :no_entry:", "User with ID ${args[0]} doesn't exist", true)

                sendMessage(commandContainer.event)
                return
            }
        }
        val member = commandContainer.event.guild.getMember(user)

        for (role: Role in member.roles) {
            userRoles += if (role != member.roles[member.roles.size - 1]) {
                "${role.name}, "
            } else {
                role.name
            }
        }

        embeddedMessageBuilder
                .setColor(Color.RED)
                .setTitle("${user.name}#${user.discriminator}")
                .setDescription("Playing ${member.game}")
                .setThumbnail(user.avatarUrl)
                .addField("Nickname", member.effectiveName, true)
                .addField("User ID", user.id, true)
                .addField("Status", member.onlineStatus.toString().toLowerCase(), true)
                .addField("Mutual Guilds", "${user.mutualGuilds.size}", true)
                .addField("Roles [${member.roles.size}]", userRoles, true)

        sendMessage(commandContainer.event)
    }
}