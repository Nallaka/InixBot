package me.nallaka.inixbot.meta.commandmeta

import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class CommandLogger {
    //Prints out called command with arguments
    fun logCommand(event: MessageReceivedEvent, commandArgs: Array<String>) {
        println("[Command Called] " + commandArgs[0] + " command executed by " + event.author.name + " with the following args: ")
        for (commandArg in commandArgs) {
            print(commandArg + " ")
        }
        println()
    }
}
