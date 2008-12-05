require 'rake/clean'

task :default => [:build, :build_tests, :test]

CLOBBER.add('build', 'doc')

CLASSES = FileList['build','build/tests','lib/junit-4.5.jar']
CLASSPATH = "-cp #{CLASSES.join(':')}"
@test_package = 'edu.okstate.cs.des.tests.'

file TESTS = FileList['test/*.java']
file SOURCES = FileList['src/*.java']

task :run => ['build'] do
  sh "java #{CLASSPATH} edu.okstate.cs.des.DESEncrypt"
end

desc "Build all source"
directory 'build'
file 'build' => SOURCES do
  sh "javac -d build #{SOURCES}"
end

# classes = 
# 
# rule '.java' => ['.class'] do |t|
#   sh "javac #{CLASSPATH} -d build src/#{t}"
# end


# rule '.java' => [
#     proc { |tn| tn.sub(/\.class$/, '.java').sub(/^classes\//, 'src/') }
#   ] do |t|
#     java_compile(t.source, t.name)
# end

desc "Build all tests from source"
directory 'build/tests'
file 'build/tests' => ['build', TESTS] do
  sh "javac #{CLASSPATH} -d build/tests #{TESTS}"
end

desc "Run all tests"
task :test => ['build/tests'] do
  test_classes = TESTS.collect{|t|
    t.sub(/\.java$/, '').sub(/^test\//, @test_package)
  }
  test_classes(test_classes)
end

def test_classes(classes)
  sh "java #{CLASSPATH} org.junit.runner.JUnitCore #{classes.join(' ')}"
end

desc "Generate documentation" 
directory 'doc'
file 'doc' => [SOURCES] do
  sh "javadoc -d doc"
end