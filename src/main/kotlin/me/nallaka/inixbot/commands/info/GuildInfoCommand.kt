package me.nallaka.inixbot.commands.info

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Role

@ICommand (
        name = "Guild Info",
        emoji = ":newspaper:",
        description = "Displays Guild information",
        usage = "guildinfo",
        aliases = ["guildinfo", "guild"],
        commandPermissionLevel = PermissionLevel.DEFAULT
)
class GuildInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val guild = commandContainer.event.guild
        if (args.isEmpty()) {
            val onlineMembers = guild.members.count { it.onlineStatus == OnlineStatus.ONLINE }

            embeddedMessageBuilder.setTitle(guild.name)
                    .setDescription("**ID:** ${guild.id}")
                    .setThumbnail(guild.iconUrl)
                    .addField("Region", guild.region.getName(), true)
                    .addField("Members [${guild.members.size}]", "Online [$onlineMembers]", true)

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
                embeddedMessageBuilder.addField("Roles [${guild.roles.size}]", "Use _guildinfo roles to view all roles", true)
            }
            embeddedMessageBuilder.addField("Verification", guild.verificationLevel.name, true)
                    .addField("Categories [${guild.categories.size}]", "Channels [${guild.textChannels.size}", true)
                    .addField("Owner", "${guild.owner.user.name}${guild.owner.user.discriminator}", true)

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