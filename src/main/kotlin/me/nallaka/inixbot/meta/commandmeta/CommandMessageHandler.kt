package me.nallaka.inixbot.meta.commandmeta

import me.nallaka.inixbot.InixBot
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class CommandMessageHandler {

    //Sends Embedded Message
    fun sendMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder) {
        event.textChannel.sendMessage(embedBuilder.build()).queue()
        clearEmbeddedBuilder(embedBuilder)
    }

    //Sends Embedded Help Message
    fun sendHelpMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder, helpTitle: String, helpDescription: String, helpUsage: String) {
        embedBuilder.setTitle(helpTitle)
                .setDescription(helpDescription)
                .addField("Usage", "``" + InixBot.USER_COMMAND_PREFIX + helpUsage + "``", true)
        sendMessage(event, embedBuilder)
        clearEmbeddedBuilder(embedBuilder)
    }

    //Clears the current EmbedMessage
    fun clearEmbeddedBuilder(embedBuilder: EmbedBuilder) = embedBuilder.setTitle(null).setDescription(null).clearFields()
}