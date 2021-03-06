package raptor.commands.`fun`

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Random Number Generator",
        emoji = ":game_die:",
        description = "Generates a Number",
        usage = "rng <max_value>",
        aliases = ["rng"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class RngCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (args.isNotEmpty()) {
            val diceArg = Integer.parseInt(args[0])
            if (diceArg <= 1000) {
                embeddedMessageBuilder.addField("RNG :game_die:", Integer.toString((Math.random() * Integer.parseInt(args[0])).toInt()), true)
            } else {
                embeddedMessageBuilder.addField("ERROR :game_die:", "Value too high. Please try again", true)
            }
            sendMessage(commandContainer.event)
        } else {
            sendUsageMessage(commandContainer.event)
        }
    }
}