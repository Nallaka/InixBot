package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.utils.Consts
import me.nallaka.inixbot.utils.commandmeta.ICommand
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class CommandMessageHandler {

    //Sends Embedded Message
    fun sendMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder) {
        event.textChannel.sendMessage(embedBuilder.build()).queue()
        clearEmbeddedBuilder(embedBuilder)
    }

    //Sends Embedded Help Message
    fun sendHelpMessage(event: MessageReceivedEvent, embedBuilder: EmbedBuilder, cmdProperties: ICommand?) {

        embedBuilder.setTitle("${cmdProperties?.name} ${cmdProperties?.emoji}")
                .setDescription(cmdProperties?.description)
                .addField("Usage", "``${Consts.DEFAULT_COMMAND_PREFIX}${cmdProperties?.usage}``", true)
                .addField("Aliases", cmdProperties?.aliases?.contentToString(), true)
                .addField("Required Permission", "${cmdProperties?.commandPermissionLevel}", true )
        sendMessage(event, embedBuilder)
        clearEmbeddedBuilder(embedBuilder)
    }

    //Clears the current EmbedMessage
    fun clearEmbeddedBuilder(embedBuilder: EmbedBuilder) = embedBuilder.setTitle(null).setDescription(null).clearFields()
}