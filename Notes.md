## Initial Setup - Github Actions and Danger, Ktlint

1. Create .github package in the project root folder.
2. Create another package inside .github and name it 'workflows'
3. Create the below files in it, and copy the contents from the files in this project 
   - android_build.yml
   - android_ui_tests.yml
   - danger_checks.yml
4. Create the below files in the project root directory, and copy the contents from the files in this project 
   - Gemfile
   - Dangerfile
5. Go to Github -> Settings -> Branches -> Set Branch Protection Rules for the master or develop branch. 
   - Select Static checks to pass before merging
   - Give the below static checks as required to pass
        - danger, android-test, build
6. Do the following for KtLint
   - Add the following in build.gradle app level
        - // https://github.com/jlleitschuh/ktlint-gradle/releases
        - ktlintPluginVersion = "10.2.1"
        - This should be in ext under build script
   - Add the following in build.gradle app level dependencies
        - classpath "org.jlleitschuh.gradle:ktlint-gradle:$ktlintPluginVersion"
        - In Repositories, add 
          maven {
          url "https://plugins.gradle.org/m2/"
          }
   - In the Project root level
        - Create a directory named 'buildscripts'
        - Create a file called 'ktlint.gradle'
        - Copy the file from this project
   - In build.gradle app level, do the following
        - subprojects {
          apply from: "../buildscripts/ktlint.gradle"
          }
   - This will fail in the first run, because.. 
     - We shouldn't have wildcards
     - To avoid getting this issue do,
        - Go to Preferences -> search for imports -> Editors -> Code Style -> Kotlin and select use single name import
     - Remove these wildcards from the errors
7. Go to android_build.yml
    - Add a Link checks code there.
    - Check this project for example
    
    
   