package me.nallaka.inixbot.meta.eventmeta

import me.nallaka.inixbot.meta.permissionmeta.PermissionLevel
import me.nallaka.inixbot.meta.permissionmeta.Permissions
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