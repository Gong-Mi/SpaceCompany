# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.18.1)

# Debug prints
message(STATUS "ANDROID variable is: ${ANDROID}")
message(STATUS "CMAKE_SYSTEM_NAME is: ${CMAKE_SYSTEM_NAME}")

# Declares the project name.
project("spacecompany")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle packages libraries with your APK.
add_library( # Sets the name of the library.
             spacecompany-native

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.c
             src/main/cpp/libs/cJSON/cJSON.c
             src/main/cpp/game_data.c
             src/main/cpp/tech_data.c )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

if(EXISTS "/data/data/com.termux/files/usr")
    message(STATUS "Detected Termux environment. Using system libraries and mock headers.")
    # Local Build (e.g. Termux)

    if(EXISTS "/system/lib64/liblog.so")
        message(STATUS "Found /system/lib64/liblog.so")
        set(log-lib "/system/lib64/liblog.so")
    elseif(EXISTS "/system/lib/liblog.so")
        message(STATUS "Found /system/lib/liblog.so")
        set(log-lib "/system/lib/liblog.so")
    else()
        message(FATAL_ERROR "Could not find /system/lib/liblog.so or /system/lib64/liblog.so")
    endif()

    # Include cJSON AND mock headers for jni.h/android/log.h
    target_include_directories(spacecompany-native PRIVATE 
        src/main/cpp/libs/cJSON
        src/main/cpp/mock_includes
    )

else()
    message(STATUS "Standard Android NDK environment assumed.")
    # Standard Android NDK Build
    find_library( # Sets the name of the path variable.
                  log-lib

                  # Specifies the name of the NDK library that
                  # you want CMake to locate.
                  log )
    
    # Include only cJSON, NDK handles the rest
    target_include_directories(spacecompany-native PRIVATE src/main/cpp/libs/cJSON)
endif()

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries( # Specifies the target library.
                       spacecompany-native

                       # Links the target library to the log library
                       # specified in the find_library() call.
                       ${log-lib} )
