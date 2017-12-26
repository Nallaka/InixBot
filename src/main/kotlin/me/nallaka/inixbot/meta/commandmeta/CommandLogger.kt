package me.nallaka.inixbot.meta.commandmeta

import me.nallaka.inixbot.handlers.CommandHandler
import java.sql.Timestamp
import java.util.*

class CommandLogger {
    private var date = Date()
    //Prints out called command with arguments
    fun logCommand(commandContainer: CommandHandler.CommandContainer) {
        var stringToLog = ("[${Timestamp(date.time)}] [Command] ${commandContainer.invoke} [Author] ${commandContainer.author.id} [Args] ")
        for (arg in commandContainer.args) {
            stringToLog += arg + " "
        }
        println(stringToLog)
    }
}
