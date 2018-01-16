package me.nallaka.inixbot.commands.admin

import me.nallaka.inixbot.InixBot
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.BotProperties
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Shutdown",
        emoji = ":radio_button:",
        description = "Shutsdown the bot",
        usage = "shutdown",
        aliases = ["shutdown", "kill"],
        commandPermissionLevel = PermissionLevel.OWNER,
        isOwnerOnly = true
)
class ShutdownCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (commandContainer.author.id == "131068934907494400") {
            embeddedMessageBuilder.addField("Shutting Down :radio_button:", "Bye", true)
            sendMessage(commandContainer.event)
            BotProperties.botConfigYamlReader.close()
            InixBot.jda.shutdown()
            System.exit(0)
        } else {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Only <@131068934907494400> can use this command", true)
            sendMessage(commandContainer.event)
        }
    }
}