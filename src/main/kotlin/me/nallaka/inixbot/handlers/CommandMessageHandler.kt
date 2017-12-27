package me.nallaka.inixbot.handlers

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
    fun sendHelpMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder, helpTitle: String, helpDescription: String, helpUsage: String, requiredPermissionLevel: String) {
        embedBuilder.setTitle(helpTitle)
                .setDescription(helpDescription)
                .addField("Usage", "``" + InixBot.DEFAULT_COMMAND_PREFIX + helpUsage + "``", true)
                .addField("Required Permission", requiredPermissionLevel, true )
        sendMessage(event, embedBuilder)
        clearEmbeddedBuilder(embedBuilder)
    }

    //Clears the current EmbedMessage
    fun clearEmbeddedBuilder(embedBuilder: EmbedBuilder) = embedBuilder.setTitle(null).setDescription(null).clearFields()
}