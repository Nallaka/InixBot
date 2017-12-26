package me.nallaka.inixbot.handlers

import me.nallaka.inixbot.meta.eventmeta.EventLogger
import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import me.nallaka.inixbot.meta.permissionmeta.Permissions
import net.dv8tion.jda.core.events.DisconnectEvent
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.events.ReconnectedEvent
import net.dv8tion.jda.core.events.StatusChangeEvent
import net.dv8tion.jda.core.events.guild.GuildBanEvent
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class EventHandler : ListenerAdapter() {
    private var permissions = Permissions()

    /*
    * JDA Related Events
    */

    //onReady: When bot is ready
    override fun onReady(readyEvent: ReadyEvent) {
        EventLogger.logEvent(readyEvent)
    }

    //onDisconnect: When bot disconnects from WebSocket
    override fun onDisconnect(event: DisconnectEvent?) {
        EventLogger.logEvent(event)
    }

    //onReconnect: When bot re-establishes connection to Web Socket
    override fun onReconnect(event: ReconnectedEvent?) {
        EventLogger.logEvent(event)
    }

    //onStatusChange: When a bot's status changes
    override fun onStatusChange(event: StatusChangeEvent?) {
        EventLogger.logEvent(event)
    }

    //onGuildMemberJoin: When a new user joins the Guild, add information to permission registry
    override fun onGuildMemberJoin(event: GuildMemberJoinEvent?) {
        EventLogger.logEvent(event)
        val user = event?.user
        if (user != null) {
            permissions.setUserPermissionLevel(user, PermissionLevel.DEFAULT)
        }
    }

    //onGuildMemberLeave: When a user leaves the Guild, remove them from the permission registry
    override fun onGuildMemberLeave(event: GuildMemberLeaveEvent?) {
        EventLogger.logEvent(event)
        val user = event?.user
        if (user != null) {
            permissions.removeUser(user)
        }
    }

    //onGuildBan: When a user is banned from the Guild, remove them from the permission registry
    override fun onGuildBan(event: GuildBanEvent?) {
        EventLogger.logEvent(event)
        val user = event?.user
        if (user != null) {
            permissions.removeUser(user)
        }
    }

    //onGuildUnban: When a user is unbanned from the Guild, add them to the permission registry
    override fun onGuildUnban(event: GuildUnbanEvent?) {
        EventLogger.logEvent(event)
        val user = event?.user
        if (user != null) {
            permissions.setUserPermissionLevel(user, PermissionLevel.DEFAULT)
        }
    }

    /*
    * Voice Related Events
    */

    //onGuildVoiceJoin: When a user joins a voice channel
    override fun onGuildVoiceJoin(event: GuildVoiceJoinEvent?) {
        EventLogger.logEvent(event)
    }

    //onGuildVoiceLeave: When a user leaves a voice channel
    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent?) {
        EventLogger.logEvent(event)
    }

    //onGuildVoiceMove: When a user changes voice channels
    override fun onGuildVoiceMove(event: GuildVoiceMoveEvent?) {
        EventLogger.logEvent(event)
    }
}