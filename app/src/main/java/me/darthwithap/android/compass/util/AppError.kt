package me.darthwithap.android.compass.util

enum class AppError(val errorMsg: String) {
    ERROR_DB("Error with database"),
    ERROR_GENERAL("Some error occurred"),
    ERROR_UNKNOWN("Unknown error occurred")

}

class AppException(val error: AppError) : Exception(error.name)