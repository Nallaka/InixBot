package me.nallaka.inixbot.commands.info

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Dev Info",
        emoji = ":computer:",
        description = "Provides Info for Devs",
        usage = "devinfo",
        aliases = ["devinfo"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false

)
class DevInfoCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
    }
}