package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.commandmeta.Command
import me.nallaka.inixbot.utils.commandmeta.ICommand
import me.nallaka.inixbot.utils.permissionmeta.PermissionLevel
@ICommand(
        name = "Eight Ball",
        emoji = ":8ball:",
        description = "Answers your Questions",
        usage = "8ball <question>",
        aliases = ["8ball", "eightball"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class EightBallCommand : Command() {
    private var responses = arrayOf(
            "It is certain", "It is decidedly so", "Without a doubt", "Yes definitely", "You may rely on it",
            "As I see it, yes", "Most likely", "Outlook good", "Yes", "Signs point to yes",
            "Reply hazy try again", "Ask again later", "Better not tell you now", "Cannot predict now", "Concentrate and ask again",
            "Don't count on it", "My reply is no", "My sources say no", "Outlook not so good", "Very doubtful"
    )
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        val eightBallResponse = if (args.isNotEmpty()) {
            responses[(Math.random() * 20 + 1).toInt()]
        } else {
            "I'll need a question"
        }
        embeddedMessageBuilder.addField(":8ball: says", eightBallResponse, true)
        sendMessage(commandContainer.event, embeddedMessageBuilder)
    }
}