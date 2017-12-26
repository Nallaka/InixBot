package me.nallaka.inixbot.commands.`fun`

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel

class EightBallCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        var eightBallResponse = ""
        if (args.size > 1) {
            when ((Math.random() * 20 + 1).toInt()) {
                1 -> eightBallResponse = "It is certain"
                2 -> eightBallResponse = "It is decidedly so"
                3 -> eightBallResponse = "Without a doubt"
                4 -> eightBallResponse = "Yes definitely"
                5 -> eightBallResponse = "You may rely on it"
                6 -> eightBallResponse = "As I see it, yes"
                7 -> eightBallResponse = "Most likely"
                8 -> eightBallResponse = "Outlook good"
                9 -> eightBallResponse = "Yes"
                10 -> eightBallResponse = "Signs point to yes"
                11 -> eightBallResponse = "Reply hazy try again"
                12 -> eightBallResponse = "Ask again later"
                13 -> eightBallResponse = "Better not tell you now"
                14 -> eightBallResponse = "Cannot predict now"
                15 -> eightBallResponse = "Concentrate and ask again"
                16 -> eightBallResponse = "Don't count on it"
                17 -> eightBallResponse = "My reply is no"
                18 -> eightBallResponse = "My sources say no "
                19 -> eightBallResponse = "Outlook not so good"
                20 -> eightBallResponse = "Very doubtful"
            }
        } else {
            eightBallResponse = "I'll need a question"
        }
        embeddedMessageBuilder.addField(":8ball: says", eightBallResponse, true)
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "8 Ball :8ball:", "You Ask, It Answers", "8ball")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return false
    }
}