package me.nallaka.inixbot.commands.info

import me.nallaka.inixbot.InixBot.Companion.jda
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.EmbedBuilder

@ICommand(
        name = "Bot Info",
        emoji = ":newspaper:",
        description = "Displays Bot Info",
        usage = "botinfo",
        aliases = ["botinfo"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class BotInfoCommand : Command() {
    private val embedMessage = EmbedBuilder()
            .setTitle("Inix Bot")
            .setDescription("Made with **{JDA}** by Nallaka#9253")
            .setThumbnail(jda.selfUser.avatarUrl)
            .addField("Bot ID", jda.selfUser.id, true)
            .addField("Guilds", "${jda.guilds.size}", true)
            .addField("Users", "${jda.users.size}", true)
            .addField("Invite Link", "Coming Soon", true)
            .addField("GitHub Repo", "Coming Soon", true)!!

    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) = sendMessage(commandContainer.event, embedMessage)
}