package raptor.commands.info

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Role
import java.awt.Color
import java.time.format.DateTimeFormatter

@ICommand(
        name = "Guild Info",
        emoji = ":newspaper:",
        description = "Displays Guild information",
        usage = "guildinfo",
        aliases = ["guildinfo", "guild"],
        commandPermissionLevel = PermissionLevel.DEFAULT
)
class GuildInfoCommand : Command() {
    private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    private val dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d yyyy")

    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val guild = commandContainer.event.guild

        if (args.isEmpty()) {
            val onlineMembers = guild.members.count { it.onlineStatus == OnlineStatus.ONLINE }

            embeddedMessageBuilder.setTitle(guild.name)
                    .setColor(Color.RED)
                    .setDescription("**ID:** ${guild.id}")
                    .setThumbnail(guild.iconUrl)
                    .addField("Region", guild.region.getName(), true)
                    .addField("Members [${guild.members.size}]", "$onlineMembers online", true)
                    .addField("Verification", guild.verificationLevel.name.toLowerCase(), true)
                    .addField("Categories [${guild.categories.size}]", "Channels [${guild.textChannels.size}]", true)
                    .addField("Owner", "*${guild.owner.user.name}#${guild.owner.user.discriminator}*", true)
                    .addField("Created", "${guild.creationTime.format(timeFormatter)} on ${guild.creationTime.format(dateFormatter)}", true)

            if (guild.roles.size <= 15) {
                var roles = ""

                for (role: Role in guild.roles) {
                    roles += if (role != guild.roles.last()) {
                        "${role.name}, "
                    } else {
                        role.name
                    }
                }
                embeddedMessageBuilder.addField("Roles [${guild.roles.size}]", roles, true)
            } else {
                embeddedMessageBuilder.addField("Roles [${guild.roles.size}]", "Use _guild roles to view all roles", true)
            }

            sendMessage(commandContainer.event)
        } else {
            when (args[0]) {
                "roles" -> {
                    var roles = ""

                    for (role: Role in guild.roles) {
                        roles += if (role != guild.roles.last()) {
                            "${role.name}, "
                        } else {
                            role.name
                        }
                    }

                    embeddedMessageBuilder.addField("Roles [${guild.roles.size}]", roles, true)
                    sendMessage(commandContainer.event)
                }

                else -> sendUsageMessage(commandContainer.event)
            }
        }
    }
}