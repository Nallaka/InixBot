package raptor.commands.util

import raptor.handlers.CommandHandler
import raptor.utils.commandmeta.Command
import raptor.utils.commandmeta.ICommand
import raptor.utils.permissionmeta.PermissionLevel
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

@ICommand(
        name = "Search",
        emoji = ":mag:",
        description = "Searches the Interwebs",
        usage = "search <query>",
        aliases = ["search", "google"],
        commandPermissionLevel = PermissionLevel.DEFAULT,
        isOwnerOnly = false
)
class SearchCommand : Command() {
    override fun runCommand(args: Array<String>, commandContainer: CommandHandler.CommandContainer) {
        if (args.isNotEmpty()) {
            embeddedMessageBuilder.setTitle("Search Results :mag_right:")
            var searchRequest = ""
            for (arg in args) {
                searchRequest += arg + " "
            }
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

        sendMessage(commandContainer.event)
    }
}