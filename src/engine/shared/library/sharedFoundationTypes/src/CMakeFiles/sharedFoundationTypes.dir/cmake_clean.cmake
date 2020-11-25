file(REMOVE_RECURSE
  "libsharedFoundationTypes.pdb"
  "libsharedFoundationTypes.a"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/sharedFoundationTypes.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
