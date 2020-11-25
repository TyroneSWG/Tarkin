file(REMOVE_RECURSE
  "libsharedMathArchive.pdb"
  "libsharedMathArchive.a"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/sharedMathArchive.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
