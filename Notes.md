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
        - This will be added in the root level under config folder

10. Run './gradlew detekt' on your local to see if there are any issues
    - We will see issues related to magic numbers in compose files
    - Add the following on top of Color.kt file
    - @file:Suppress("MagicNumber")

11. Rename Lint checks to the following and update it to include detekt
    - name: Static Analysis
      run: ./gradlew ktlintCheck detekt
    - or, Copy the file in this project to do it.

## Initial Setup 4 - Git Hooks
We can do certain actions pre, post push and stuff
.git is private, so we won't be able to share it directly. We should do the following

12. Create a new directory in the project root level and call it 'git-hooks'
    - create a file 'pre-commit.sh'
    - This is a bash file

13. Copy the pre-commit code from this project
    - This will run ktlint before committing code
    - This particular piece of code will run only on changed kotlin files
    
14. Create a file 'pre-push.sh'
    - This is a bash file, and copy the contents from this project
    
15. Go to buildscripts -> Create 'git-hooks.gradle'
    - Copy contents from this project
    - This will only work on linux and mac
    - This will copy the git hooks and paste it and make it runnable in git hooks
    
16. Go to build.gradle app module
    - Add the below above subprojects
    - apply from: "buildscripts/git-hooks.gradle"
    - This will run on the broader project
    
17. Go to build.gradle app module, and after task clean do the following
    - afterEvaluate {
      tasks['clean'].dependsOn installGitHooks
      }
    - This would basically mean whenever someone runs a clean, githooks will be added to their project
    - Sounds like an overkill, but since it runs lighting fast, it is ok.
        
        
        
-----------
Files to update(In short):
1. Folder: root -> .github -> workflows
    - android_build.yml, android_ui_tests.yml, danger_checks.yml
2. Folder: root -> buildscripts
    - detekt.gradle, git-hooks.gradle, ktlint.gradle
3. Folder: root -> config -> detekt
    - detekt.yml
4. Folder: root -> git-hooks
    - pre-commit.sh, pre-push.sh
5. Folder: root
    - Dangerfile, Gemfile
6. In Build.gradle app level file
    - Add these in ext
        // https://github.com/jlleitschuh/ktlint-gradle/releases
        ktlintPluginVersion = "10.2.1"

        // https://github.com/detekt/detekt/releases
        detektVersion = "1.17.0" 
    - In repositories add maven { url "https://plugins.gradle.org/m2/" }
    - In dependencies add
        -  classpath "org.jlleitschuh.gradle:ktlint-gradle:$ktlintPluginVersion"
           classpath "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion"       
    - Add the below above clean
        - apply from: "buildscripts/git-hooks.gradle"
        - subprojects {
            apply from: "../buildscripts/ktlint.gradle"
            apply from: "../buildscripts/detekt.gradle"
            }
    - Add the below after clean
        - afterEvaluate { 
            tasks['clean'].dependsOn installGitHooks
            }







---------
Other issues:
- @Preview in compose might cause detekt to throw UnusedPrivateMember error.
    - Go to detekt.yml file, search for allowedNames, and add
    - .*Preview
        

    
   
