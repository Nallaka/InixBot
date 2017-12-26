package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.InixBot
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class MessageHandler : ListenerAdapter() {
    private var commandHandler = CommandHandler()

    //Runs on MessageRecieved Event. Checks type of command and executes
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.content.startsWith(InixBot.DEFAULT_COMMAND_PREFIX) || event.message.content.startsWith(InixBot.USER_COMMAND_PREFIX)) {
            commandHandler.executeCommand(commandHandler.parseCommand(event))
        }
    }
}