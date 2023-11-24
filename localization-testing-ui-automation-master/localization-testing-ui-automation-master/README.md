# ProjectName
	Localization Testing Automation

# Overview
	This project is created to automate UI localization testing. Used Selenium, Java and maven with Unicode approach.

# Prerequisite
    1. Java 11 or greater should be installed.
    2. Maven should be installed.

# Browser compatiblity
* Chrome 
* Edge 
* Firefox 
	
# User Language compatiblity
	Folder "src/main/resources" consists properties file for different languages. As of now project supports arabic,chinese,english,french,hindi,spanish languages.If user wants to test application for new language then User need to add corresponding language property/csv file of unicodes under src/main/resources folder.

# config.properties
	File contains default test browser, default test language and multi language report parameter.
	
# Maven commands to run the tests
1.Execute all the Tests in deafult browser chrome and default language english
  ```
mvn clean test
  ```
2.Execute all the Tests in specific browser and specific language
  ```
mvn clean test -Dbrowser=chrome -Dlanguage=chinese
  ```	
3.Execute specific group of Tests in specific browser and specific language
  ```
mvn clean test -Dbrowser=chrome -Dlanguage=chinese -Dgroups=Smoke
  ```
4.Execute Single Test Class in specific browser and specific language 
  ```
 mvn clean test -Dbrowser=chrome -Dlanguage=chinese -Dtest=SignInPageTest
  ```
5.Execute Single Test Method in specific browser and specific language 
  ```
mvn clean test -Dbrowser=chrome -Dlanguage=chinese -Dtest=HomePageTest#searchMobileTest
  ```
6.Execute Multiple Test Classes in specific browser and specific language
  ``` 
mvn clean test -Dbrowser=chrome -Dlanguage=chinese -Dtest="HomePageTest, SignInPageTest"
  ```
7.Execute Multiple Test methods in specific browser and specific language 
  ```
mvn clean test -Dbrowser=chrome -Dlanguage=chinese -Dtest="HomePageTest#cartButtonLabelTest+searchMobileTest"
   ```

# Reports and Logs
	Post executing maven command, Reports and Logs folder will be created.
	
# Author     
sunil.sharma@thepsi.com