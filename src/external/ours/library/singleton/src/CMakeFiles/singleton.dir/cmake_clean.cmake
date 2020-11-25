file(REMOVE_RECURSE
  "libsingleton.pdb"
  "libsingleton.a"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/singleton.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
