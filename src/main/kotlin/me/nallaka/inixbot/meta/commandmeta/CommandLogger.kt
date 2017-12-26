package me.nallaka.inixbot.meta.commandmeta

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.sql.Timestamp
import java.util.*

class CommandLogger {
    private var date = Date()
    //Prints out called command with arguments
    fun logCommand(event: MessageReceivedEvent, commandArgs: Array<String>) {
        var stringToLog = ("[${Timestamp(date.time)}] [Command] ${commandArgs[0]} [Author] ${event.author.id} [Args] ")
        for (commandArg in commandArgs) {
            stringToLog += commandArg + " "
        }
        println(stringToLog)
    }
}
