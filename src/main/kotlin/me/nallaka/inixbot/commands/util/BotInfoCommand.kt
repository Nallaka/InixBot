package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Bot Info",
        emoji = ":newspaper:",
        description = "Displays Bot Info",
        usage = "_botinfo",
        aliases = [""],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class BotInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
    }
}