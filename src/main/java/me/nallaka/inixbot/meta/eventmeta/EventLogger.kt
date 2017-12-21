package me.nallaka.inixbot.meta.eventmeta

import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.sql.Timestamp
import java.util.*

class EventLogger : ListenerAdapter() {
    //Date Class
    var date = Date()

    //onReady: When bot is ready
    override fun onReady(readyEvent: ReadyEvent) {
        logEvent(readyEvent)
    }

    private fun logEvent(event: Event) {
        println("[${Timestamp(date.time)}] [Event Logger] $event")
    }
}