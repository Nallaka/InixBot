package me.nallaka.inixbot.listeners

import me.nallaka.inixbot.InixBot.Companion.jda
import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.utils.BotProperties
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {
    private var commandHandler = CommandHandler()

    //onMessageReceived: Runs on MessageRecieved Event. Checks type of command and executes
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val content = event.message.content.toLowerCase()
        val rawContent = event.message.rawContent
        if (content.startsWith(BotProperties.DEFAULT_COMMAND_PREFIX) || content.startsWith(BotProperties.USER_COMMAND_PREFIX) && event.author != jda.selfUser) {
            commandHandler.executeCommand(commandHandler.parseCommand(event))
        }
    }
}