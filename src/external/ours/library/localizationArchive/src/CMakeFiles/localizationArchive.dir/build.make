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
include external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/depend.make

# Include the progress variables for this target.
include external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/progress.make

# Include the compile flags for this target's objects.
include external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/flags.make

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/flags.make
external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o: external/ours/library/localizationArchive/src/shared/StringIdArchive.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o"
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o -c /home/swg/swg-main/src/external/ours/library/localizationArchive/src/shared/StringIdArchive.cpp

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.i"
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/external/ours/library/localizationArchive/src/shared/StringIdArchive.cpp > CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.i

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.s"
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/external/ours/library/localizationArchive/src/shared/StringIdArchive.cpp -o CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.s

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.requires:

.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.requires

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.provides: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.requires
	$(MAKE) -f external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/build.make external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.provides.build
.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.provides

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.provides.build: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o


# Object files for target localizationArchive
localizationArchive_OBJECTS = \
"CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o"

# External object files for target localizationArchive
localizationArchive_EXTERNAL_OBJECTS =

external/ours/library/localizationArchive/src/liblocalizationArchive.a: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o
external/ours/library/localizationArchive/src/liblocalizationArchive.a: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/build.make
external/ours/library/localizationArchive/src/liblocalizationArchive.a: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX static library liblocalizationArchive.a"
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && $(CMAKE_COMMAND) -P CMakeFiles/localizationArchive.dir/cmake_clean_target.cmake
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/localizationArchive.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/build: external/ours/library/localizationArchive/src/liblocalizationArchive.a

.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/build

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/requires: external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/shared/StringIdArchive.cpp.o.requires

.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/requires

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/clean:
	cd /home/swg/swg-main/src/external/ours/library/localizationArchive/src && $(CMAKE_COMMAND) -P CMakeFiles/localizationArchive.dir/cmake_clean.cmake
.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/clean

external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/depend:
	cd /home/swg/swg-main/src && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/swg/swg-main/src /home/swg/swg-main/src/external/ours/library/localizationArchive/src /home/swg/swg-main/src /home/swg/swg-main/src/external/ours/library/localizationArchive/src /home/swg/swg-main/src/external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : external/ours/library/localizationArchive/src/CMakeFiles/localizationArchive.dir/depend

