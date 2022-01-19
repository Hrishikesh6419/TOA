## Initial Setup - Github Actions and Danger

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

## Initial Setup 2 - Ktlint

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
    - Add a Lint checks code there.
    - Check this project for example

## Initial Setup 3 - Detekt
This runs other static analysis for us, like
- Is my method too long?
- Am I consuming too many parameters
- Do I have too many functions in a file
- Am I referencing a magic number?

8. Do the following
   - Add the following in build.gradle app level, in ext under buildscripts
      - // https://github.com/detekt/detekt/releases
        detekVersion = "1.17.0"
   - Add the following in build.gradle app level, under repositories
      - classpath "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion"
   - In the Project root level
      - Create a directory named 'buildscripts'(If it not already exists)
      - Create a file called 'detekt.gradle'
      - Copy the file from this project
   - In build.gradle app level, do the following
      - subprojects {
        apply from: "../buildscripts/detekt.gradle"
        }
        
9. When we add detekt plugin, we get a detektGenerateConfig file,
    - It will automatically generate a default detekt file
    - Do the following to run it
        - Goto app - tasks -> verification -> detektGenerateConfig file
        - Or, hit ctrl ctrl and serch for ./gradlew detektGenerateConfig
    - This will create a detekt file
        - Which we can mostly use out of the box
    
10. Run './gradlew detekt' on your local to see if there are any issues
    - We will see issues related to magic numbers in compose files
    - Add the following on top of Color.kt file
    - @file:Suppress("MagicNumber")

11. Rename Lint checks to the following and update it to include detekt
    - name: Static Analysis
      run: ./gradlew ktlintCheck detekt
    - or, Copy the file in this project to do it.
    

        
        

    
   