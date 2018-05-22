package raptor.commands.info

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

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