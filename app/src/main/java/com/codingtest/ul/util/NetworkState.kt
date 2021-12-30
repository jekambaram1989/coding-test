package com.codingtest.ul.util

data class NetworkState (val status: Status, val data: Any) {
    companion object {
        fun success(data: Any): NetworkState {
            return NetworkState(Status.SUCCESS, data)
        }

        fun error(data: Any): NetworkState {
            return NetworkState(Status.ERROR, data)
        }
    }
}