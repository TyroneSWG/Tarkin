# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.10

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/swg/swg-main/src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/swg/swg-main/src

# Include any dependencies generated for this target.
include external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/depend.make

# Include the progress variables for this target.
include external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/progress.make

# Include the compile flags for this target's objects.
include external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/flags.make

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/flags.make
external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o: external/3rd/library/udplibrary/UdpLibrary.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o"
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o -c /home/swg/swg-main/src/external/3rd/library/udplibrary/UdpLibrary.cpp

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/udplibrary.dir/UdpLibrary.cpp.i"
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/external/3rd/library/udplibrary/UdpLibrary.cpp > CMakeFiles/udplibrary.dir/UdpLibrary.cpp.i

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/udplibrary.dir/UdpLibrary.cpp.s"
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/external/3rd/library/udplibrary/UdpLibrary.cpp -o CMakeFiles/udplibrary.dir/UdpLibrary.cpp.s

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.requires:

.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.requires

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.provides: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.requires
	$(MAKE) -f external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/build.make external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.provides.build
.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.provides

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.provides.build: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o


# Object files for target udplibrary
udplibrary_OBJECTS = \
"CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o"

# External object files for target udplibrary
udplibrary_EXTERNAL_OBJECTS =

external/3rd/library/udplibrary/libudplibrary.a: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o
external/3rd/library/udplibrary/libudplibrary.a: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/build.make
external/3rd/library/udplibrary/libudplibrary.a: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX static library libudplibrary.a"
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && $(CMAKE_COMMAND) -P CMakeFiles/udplibrary.dir/cmake_clean_target.cmake
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/udplibrary.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/build: external/3rd/library/udplibrary/libudplibrary.a

.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/build

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/requires: external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/UdpLibrary.cpp.o.requires

.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/requires

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/clean:
	cd /home/swg/swg-main/src/external/3rd/library/udplibrary && $(CMAKE_COMMAND) -P CMakeFiles/udplibrary.dir/cmake_clean.cmake
.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/clean

external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/depend:
	cd /home/swg/swg-main/src && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/swg/swg-main/src /home/swg/swg-main/src/external/3rd/library/udplibrary /home/swg/swg-main/src /home/swg/swg-main/src/external/3rd/library/udplibrary /home/swg/swg-main/src/external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : external/3rd/library/udplibrary/CMakeFiles/udplibrary.dir/depend
