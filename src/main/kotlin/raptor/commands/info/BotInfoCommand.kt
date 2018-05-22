package raptor.commands.info

import raptor.RaptorBot.Companion.jda
import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import net.dv8tion.jda.core.EmbedBuilder
import java.awt.Color

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
            .setColor(Color.RED)
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