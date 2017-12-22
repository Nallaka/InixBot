package me.nallaka.inixbot.meta.eventmeta

import net.dv8tion.jda.core.events.*
import net.dv8tion.jda.core.events.guild.GuildBanEvent
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.sql.Timestamp
import java.util.*

class EventLogger : ListenerAdapter() {
    //Date Class
    private var date = Date()

    /*
     * JDA Related Events
     */


    //onReady: When bot is ready
    override fun onReady(readyEvent: ReadyEvent) {
        logEvent(readyEvent)
    }

    //onDisconnect: When bot disconnects from WebSocket
    override fun onDisconnect(event: DisconnectEvent?) {
        logEvent(event)
    }

    //onReconnect: When bot re-establishes connection to Web Socket
    override fun onReconnect(event: ReconnectedEvent?) {
        logEvent(event)
    }

    //onStatusChange: When a bot's status changes
    override fun onStatusChange(event: StatusChangeEvent?) {
        logEvent(event)
    }

    //onGuildMemberJoin: When a new user joins the Guild, add information to permission registry
    override fun onGuildMemberJoin(event: GuildMemberJoinEvent?) {
        logEvent(event)
        //TODO: Add new user to permission registry
    }

    //onGuildMemberLeave: When a user leaves the Guild, remove them from the permission registry
    override fun onGuildMemberLeave(event: GuildMemberLeaveEvent?) {
        logEvent(event)
        //TODO: Remove user from permission registry
    }

    //onGuildBan: When a user is banned from the Guild, remove them from the permission registry
    override fun onGuildBan(event: GuildBanEvent?) {
        logEvent(event)
        //TODO: Remove user from permission registry
    }

    //onGuildUnban: When a user is unbanned from the Guild, add them to the permission registry
    override fun onGuildUnban(event: GuildUnbanEvent?) {
        logEvent(event)
        //TODO: Add user to permission registry
    }

    /*
    * Voice Related Events
    */
    //onGuildVoiceJoin: When a user joins a voice channel
    override fun onGuildVoiceJoin(event: GuildVoiceJoinEvent?) {
        logEvent(event)
    }

    //onGuildVoiceLeave: When a user leaves a voice channel
    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent?) {
        logEvent(event)
    }

    //onGuildVoiceMove: When a user changes voice channels
    override fun onGuildVoiceMove(event: GuildVoiceMoveEvent?) {
        logEvent(event)
    }

    //Event Logger
    private fun logEvent(event: Event?) {
        println("[${Timestamp(date.time)}] [Event Logger] $event")
    }
}