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
include engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/depend.make

# Include the progress variables for this target.
include engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/progress.make

# Include the compile flags for this target's objects.
include engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/flags.make

# Object files for target sharedFoundationTypes
sharedFoundationTypes_OBJECTS =

# External object files for target sharedFoundationTypes
sharedFoundationTypes_EXTERNAL_OBJECTS =

engine/shared/library/sharedFoundationTypes/src/libsharedFoundationTypes.a: engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/build.make
engine/shared/library/sharedFoundationTypes/src/libsharedFoundationTypes.a: engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Linking CXX static library libsharedFoundationTypes.a"
	cd /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src && $(CMAKE_COMMAND) -P CMakeFiles/sharedFoundationTypes.dir/cmake_clean_target.cmake
	cd /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/sharedFoundationTypes.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/build: engine/shared/library/sharedFoundationTypes/src/libsharedFoundationTypes.a

.PHONY : engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/build

engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/requires:

.PHONY : engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/requires

engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/clean:
	cd /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src && $(CMAKE_COMMAND) -P CMakeFiles/sharedFoundationTypes.dir/cmake_clean.cmake
.PHONY : engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/clean

engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/depend:
	cd /home/swg/swg-main/src && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/swg/swg-main/src /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src /home/swg/swg-main/src /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src /home/swg/swg-main/src/engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : engine/shared/library/sharedFoundationTypes/src/CMakeFiles/sharedFoundationTypes.dir/depend

