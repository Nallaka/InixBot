package me.nallaka.inixbot.utils.permissionmeta

enum class PermissionLevel {
    //PermissionLevels in order of precedence
    DEFAULT, LOW, MEDIUM, HIGH, ADMIN, OWNER;

    fun getPermissionLevelOrdinal(): Int {
        return this.ordinal
    }
}