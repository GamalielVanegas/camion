package com.gammadesv.camiontracker.utils

object Constants {
    const val MAX_LOCATION_RETRIES = 3
    const val LOCATION_REQUEST_INTERVAL = 10000L
    const val DEFAULT_BRANCH_RADIUS = 100 // metros

    object Collections {
        const val USERS = "users"
        const val MOVEMENTS = "movements"
        const val BRANCHES = "branches"
    }

    object Roles {
        const val DRIVER = "driver"
        const val SUPERVISOR = "supervisor"
    }
}