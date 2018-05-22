package raptor.commands.`fun`

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel

@ICommand(
        name = "Coin Flip",
        emoji = "",
        description = "Flips a Coin",
        usage = "coinflip",
        aliases = ["coinflip", "flipcoin"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class CoinFlipCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val random = (Math.random() * 1000).toInt()
        val flip: String
        flip = if (random % 2 == 0) {
            "Heads"
        } else {
            "Tails"
        }
        embeddedMessageBuilder.addField("You Flipped - ", flip, true)
        sendMessage(commandContainer.event)
    }
}