package me.nallaka.inixbot.commands.util

import me.nallaka.inixbot.handlers.CommandHandler
import me.nallaka.inixbot.meta.commandmeta.Command
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

class SearchCommand : Command(PermissionLevel.DEFAULT) {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        embeddedMessageBuilder.setTitle("Search Results :mag_right:")
        if (args.size > 1) {
            var searchRequest = ""
            args
                    .filterNot { it.equals("google", ignoreCase = true) }
                    .forEach { searchRequest = it + " " }

            var links: Elements? = null
            try {
                val googleURL = "http://www.google.com/search?q="
                val charset = "UTF-8"
                val userClient = "(+http://google.com/)"
                links = Jsoup.connect(googleURL + URLEncoder.encode(searchRequest, charset)).userAgent(userClient).get().select(".g>.r>a")
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (links != null) {
                for (link in links) {
                    val title = link.text()
                    var url = link.absUrl("href")
                    try {
                        url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8")
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }

                    if (!url.startsWith("http")) {
                        continue
                    }

                    embeddedMessageBuilder.addField(title, url, true)
                }
            }
        } else {
            embeddedMessageBuilder.addField("ERROR :no_entry:", "Input a Search", false)
        }
        commandMessageHandler.sendMessage(commandContainer.event, embeddedMessageBuilder)
    }

    override fun runHelpCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        commandMessageHandler.sendHelpMessage(commandContainer.event, embeddedMessageBuilder, "Google Search :mag_right:", "Search Google", "google <search>")
    }

    override fun executed(commandContainer: CommandHandler.CommandContainer): Boolean {
        commandLogger.logCommand(commandContainer)
        return false
    }
}