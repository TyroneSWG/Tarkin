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
include engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/depend.make

# Include the progress variables for this target.
include engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/progress.make

# Include the compile flags for this target's objects.
include engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o: engine/server/application/MetricsServer/src/shared/ConfigMetricsServer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/ConfigMetricsServer.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/ConfigMetricsServer.cpp > CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/ConfigMetricsServer.cpp -o CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o


engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o: engine/server/application/MetricsServer/src/shared/FirstMetricsServer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/FirstMetricsServer.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/FirstMetricsServer.cpp > CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/FirstMetricsServer.cpp -o CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o


engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o: engine/server/application/MetricsServer/src/shared/MetricsGatheringConnection.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsGatheringConnection.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsGatheringConnection.cpp > CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsGatheringConnection.cpp -o CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o


engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o: engine/server/application/MetricsServer/src/shared/MetricsServer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsServer.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsServer.cpp > CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/MetricsServer.cpp -o CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o


engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o: engine/server/application/MetricsServer/src/shared/TaskConnection.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/TaskConnection.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/TaskConnection.cpp > CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/shared/TaskConnection.cpp -o CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o


engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/flags.make
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o: engine/server/application/MetricsServer/src/linux/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/MetricsServer.dir/linux/main.cpp.o -c /home/swg/swg-main/src/engine/server/application/MetricsServer/src/linux/main.cpp

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/MetricsServer.dir/linux/main.cpp.i"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/swg/swg-main/src/engine/server/application/MetricsServer/src/linux/main.cpp > CMakeFiles/MetricsServer.dir/linux/main.cpp.i

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/MetricsServer.dir/linux/main.cpp.s"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/swg/swg-main/src/engine/server/application/MetricsServer/src/linux/main.cpp -o CMakeFiles/MetricsServer.dir/linux/main.cpp.s

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.requires:

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.provides: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.requires
	$(MAKE) -f engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.provides.build
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.provides

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.provides.build: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o


# Object files for target MetricsServer
MetricsServer_OBJECTS = \
"CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o" \
"CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o" \
"CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o" \
"CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o" \
"CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o" \
"CMakeFiles/MetricsServer.dir/linux/main.cpp.o"

# External object files for target MetricsServer
MetricsServer_EXTERNAL_OBJECTS =

bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build.make
bin/MetricsServer: engine/shared/library/sharedCompression/src/libsharedCompression.a
bin/MetricsServer: engine/shared/library/sharedDebug/src/libsharedDebug.a
bin/MetricsServer: engine/shared/library/sharedFile/src/libsharedFile.a
bin/MetricsServer: engine/shared/library/sharedFoundation/src/libsharedFoundation.a
bin/MetricsServer: engine/shared/library/sharedLog/src/libsharedLog.a
bin/MetricsServer: engine/shared/library/sharedMath/src/libsharedMath.a
bin/MetricsServer: engine/shared/library/sharedMessageDispatch/src/libsharedMessageDispatch.a
bin/MetricsServer: engine/shared/library/sharedNetwork/src/libsharedNetwork.a
bin/MetricsServer: engine/shared/library/sharedNetworkMessages/src/libsharedNetworkMessages.a
bin/MetricsServer: engine/shared/library/sharedRandom/src/libsharedRandom.a
bin/MetricsServer: engine/shared/library/sharedSynchronization/src/libsharedSynchronization.a
bin/MetricsServer: engine/shared/library/sharedThread/src/libsharedThread.a
bin/MetricsServer: engine/shared/library/sharedUtility/src/libsharedUtility.a
bin/MetricsServer: engine/server/library/serverNetworkMessages/src/libserverNetworkMessages.a
bin/MetricsServer: engine/server/library/serverUtility/src/libserverUtility.a
bin/MetricsServer: external/ours/library/archive/src/libarchive.a
bin/MetricsServer: external/ours/library/fileInterface/src/libfileInterface.a
bin/MetricsServer: external/ours/library/localization/src/liblocalization.a
bin/MetricsServer: external/ours/library/localizationArchive/src/liblocalizationArchive.a
bin/MetricsServer: external/ours/library/unicode/src/libunicode.a
bin/MetricsServer: external/ours/library/unicodeArchive/src/libunicodeArchive.a
bin/MetricsServer: external/3rd/library/platform/projects/MonAPI2/libMonAPI2.a
bin/MetricsServer: engine/shared/library/sharedLog/src/libsharedLog.a
bin/MetricsServer: external/3rd/library/udplibrary/libudplibrary.a
bin/MetricsServer: engine/shared/library/sharedSynchronization/src/libsharedSynchronization.a
bin/MetricsServer: engine/shared/library/sharedMessageDispatch/src/libsharedMessageDispatch.a
bin/MetricsServer: engine/shared/library/sharedNetworkMessages/src/libsharedNetworkMessages.a
bin/MetricsServer: engine/shared/library/sharedUtility/src/libsharedUtility.a
bin/MetricsServer: engine/server/library/serverNetworkMessages/src/libserverNetworkMessages.a
bin/MetricsServer: engine/shared/library/sharedFile/src/libsharedFile.a
bin/MetricsServer: engine/shared/library/sharedFoundation/src/libsharedFoundation.a
bin/MetricsServer: engine/shared/library/sharedMath/src/libsharedMath.a
bin/MetricsServer: engine/shared/library/sharedFile/src/libsharedFile.a
bin/MetricsServer: engine/shared/library/sharedFoundation/src/libsharedFoundation.a
bin/MetricsServer: engine/shared/library/sharedMath/src/libsharedMath.a
bin/MetricsServer: engine/shared/library/sharedCompression/src/libsharedCompression.a
bin/MetricsServer: /usr/lib64/libz.so
bin/MetricsServer: engine/shared/library/sharedDebug/src/libsharedDebug.a
bin/MetricsServer: /usr/lib64/libncurses.so
bin/MetricsServer: external/ours/library/fileInterface/src/libfileInterface.a
bin/MetricsServer: engine/shared/library/sharedRandom/src/libsharedRandom.a
bin/MetricsServer: external/ours/library/archive/src/libarchive.a
bin/MetricsServer: external/ours/library/localization/src/liblocalization.a
bin/MetricsServer: external/ours/library/localizationArchive/src/liblocalizationArchive.a
bin/MetricsServer: external/ours/library/unicode/src/libunicode.a
bin/MetricsServer: external/ours/library/unicodeArchive/src/libunicodeArchive.a
bin/MetricsServer: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/swg/swg-main/src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX executable ../../../../../bin/MetricsServer"
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/MetricsServer.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build: bin/MetricsServer

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/build

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/ConfigMetricsServer.cpp.o.requires
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/FirstMetricsServer.cpp.o.requires
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsGatheringConnection.cpp.o.requires
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/MetricsServer.cpp.o.requires
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/shared/TaskConnection.cpp.o.requires
engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires: engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/linux/main.cpp.o.requires

.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/requires

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/clean:
	cd /home/swg/swg-main/src/engine/server/application/MetricsServer/src && $(CMAKE_COMMAND) -P CMakeFiles/MetricsServer.dir/cmake_clean.cmake
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/clean

engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/depend:
	cd /home/swg/swg-main/src && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/swg/swg-main/src /home/swg/swg-main/src/engine/server/application/MetricsServer/src /home/swg/swg-main/src /home/swg/swg-main/src/engine/server/application/MetricsServer/src /home/swg/swg-main/src/engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : engine/server/application/MetricsServer/src/CMakeFiles/MetricsServer.dir/depend
