package me.darthwithap.android.compass.util

sealed class AppResult<T>(
    val data: T? = null, val error: AppException? = null
) {
  class Success<T>(data: T) : AppResult<T>(data)
  class Error<T>(e: AppException) : AppResult<T>(error = e)
}