# Sets the minimum version of CMake required to build your native library.
cmake_minimum_required(VERSION 3.4.1)
# Project name for when setting up CMake build configurations in build.gradle
project("wmm-lib")

# Specifies a library name, specifies whether the library is STATIC or SHARED,
# and provides relative paths to the source code.
add_library( # Specifies the name of the library.
    wmm-lib  # The name of the .so file that'll be outputted.

    # Sets the library as a shared library.
    SHARED

    # Provides a relative path to your source file(s).
    wmm_file.c
    wmm_grid.c
    wmm_point.c
    GeomagnetismLibrary.c)

# Searches for a specified prebuilt library and stores the path as a variable.
find_library( # Sets the name of the path variable.
    log-lib

    # Specifies the name of the NDK library that
    # CMake needs to locate.
    log)

# Specifies libraries CMake should link to your target library.
target_link_libraries( # Specifies the target library.
    wmm-lib

    # Links the log library to the target library.
    ${log-lib})