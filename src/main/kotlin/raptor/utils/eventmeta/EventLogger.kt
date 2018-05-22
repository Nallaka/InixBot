package raptor.utils.eventmeta

import net.dv8tion.jda.core.events.Event
import java.sql.Timestamp
import java.util.*

class EventLogger {

    companion object {
        //Date Class
        private var date = Date()

        //Event Logger
        fun logEvent(event: Event?) {
            println("[${Timestamp(date.time)}] [Event Logger] $event")
        }
    }
}