task :default => [:build, :build_tests, :test]

desc "Build all tests from source"
task :build_tests => [:build] do
  puts `javac  -cp build:lib/junit-4.5.jar -d build test/SboxTest.java`
end

desc "Run all tests"
task :test => [:build_tests] do
  puts `java -cp build:lib/junit-4.5.jar SboxTest`
end

desc "Build all source"
task :build do
  puts `javac  src/Sbox.java -d build`
end

desc "Clean build directory"
task :clean do
  puts `rm -rfv build/*`
end