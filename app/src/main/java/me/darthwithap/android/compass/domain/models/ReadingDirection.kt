package me.darthwithap.android.compass.domain.models

enum class ReadingDirection(val direction: String, val symbol: String) {
  North("North", "N"),
  NorthEast("North-East", "NE"),
  NorthWest("North-West", "NW"),
  East("East", "E"),
  West("West", "W"),
  SouthEast("South-East", "SE"),
  SouthWest("South-West", "SW"),
  South("South", "S"),
  UnknownDirection("Unknown", "U");

  companion object {
    fun getDirectionBySymbol(symbol: String) {
      when (symbol) {
        "N" -> North
        "NE" -> NorthEast
        "NW" -> NorthWest
        "E" -> East
        "W" -> West
        "S" -> South
        "SE" -> SouthEast
        "SW" -> SouthWest
        else -> UnknownDirection
      }
    }
  }
}