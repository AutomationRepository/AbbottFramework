# Abbott Automation Framework

This project allows the testers to add, and run test cases in parallel while keeping the execution thread safe.The Project Integrates TestNg, Maven, ExtendReport and Log4j and uses Java with Selenium as it's primary language for UI Automation.

## Working of the Project


## Table of contents

1. [src_main_java](#src_main_java)
2. [src_main_resources](#src_main_resources)
3. [src_test_java](#src_test_java)
4. [src_test_resources](#src_test_resources)
5. [Reports](#Reports)
6. [pom.xml](#POM_File)
7. [testNG.xml](#testNG_File)


## src_main_java
There are 3 packages under src/main/java

 ![image](https://user-images.githubusercontent.com/110168437/198846592-c7960101-97cf-42dc-9c99-07eba41e9197.png)

1. abbottLibreViewPages - This package contains the classes for each page, each page follows the page object model, hence all the objects related to the UI of the page are stored in this package. 

  ![image](https://user-images.githubusercontent.com/110168437/198846846-7102e62d-e2e7-458b-9a1b-91ade13ec8d1.png)

        1.  ChooseCountryLanguagePage - Object for Modal that contains country and language selection dropdown.
        2.  CookiePopupPage - Object for the cookie pop-up at the bottom.
        3.  HomePage - Object for homePage of the application.

2. abbottLibreViewSetup - This package contains all the setup files needed to support seemless execution of test cases.
![image](https://user-images.githubusercontent.com/110168437/198848102-2fd66026-a37c-4ed6-9322-849cf02bf388.png)

      1. BrowserFactory - It contains a method createBrowserInstance() that allows you to Instantiate the driver object based on the type(chrome/firefox/ie) of the  browser.
      2. DriverFactory -  It contain methods getDriver(), setDriver() and closeDriver() that enables you to use driver instance while maintaining the               synchronization during parallel test execution.
      3. ExtendFactory - It contain methods getExtend(), setExtend() and removeExtend() that enables you use the extend report while maintaining the synchronization  during parallel tes execution.
      4. ExtendReport - It contains a method setupExtendReport(), it creates the object of Extend report and attaches the sparkReport to it.
      5. MyLogger - It contain methods that enable you to use the logging function of Log4J to log the events to a file at runtime.
      6. SetUp - This class contians the beforeMethod and AfterMethod Annotation from TestNG to launch the browser and application on execution. 
 3. utilities - This package contains all the classes that are not indirectly supporting the execution.
  ![image](https://user-images.githubusercontent.com/110168437/198849518-7a2102e2-c841-4e23-a737-1cc71b3bdae2.png)

      1. CommonEngine - It Contians all the common method like selecting an element using JSexecutor etc that are used at multiple places to increase reusability.
      2. JsonDataReader - It  contians a method that is used to read the test data that is stored in the form of Json file and set it to a Dataprovider of TestNG.
      3. ListenerForEachTest - This class implements the ITestListener Interface of TestNG to achive things like taking screenshot only during the failure, attaching various test phases to the extend report etc.
      4. PropertiesOperations - It reads the configurable value from the property file under test resources.
      5. TestRetryAnalyzer - This class implements the IRetryAnalyzer Interface of TestNG that executes the method twice on failure.
      6. TestRetryAnalyzer - This class implements the IAnnotationTransformer Interface of TestNG that enable the user to execute retry logic written in TestRetryAnalyzer class at method level instead of just class level.
  
## src_main_resources
![image](https://user-images.githubusercontent.com/110168437/198849573-1112a8db-924a-4acd-abd5-d06f470cde15.png)
 
 it contains the log4j.xml that indicates the routing location,pattern layout and the level of logging required.
## src_test_java
![image](https://user-images.githubusercontent.com/110168437/198850386-371c4769-c128-4e33-9f6e-7add96abe223.png)
The test cases related to the SRS document shared for Paitient and Professional tab under header has been placed under tests package and HomePageHeader.java file.

Test Cases.

| TestCase Name                                     | TestSteps                                            |Test Data     |
| ---------------------------------                 | -------------                                        | ----------   |
|1.Validate patient is loaded by default and link(patient) is selected     | i. Load the URL                                      | US/English   |
|                                                   | ii. Select country and language from pop-up window    
|                                                   | iii. Click on submit
|                                                   | iv. Click on Accept cookies button[Conditional]
|                                                   | v. Validate that the link loaded is for patient||
|                                                   | vi. Close the browser | |
|2.Validate that on clicking professional new window opens and link(professional) is selected    | i. Load the URL | US/English  |
|                                                   | ii. Select country and language from pop-up window    
|                                                   | iii. Click on submit
|                                                   | iv. Click on Accept cookies buttom[Conditional]
| | v. Store the selected country and language||
|                                                   | vi. Click on Professional ||
| | vii. Navigate to the next tab ||
|| viii. Validate Professional link is selected ||
|| ix. Validate the country and language is same as the value stored in previous step ||
|             | x. Close the browser | |
|3.Validate correct data is displayed in header on selection  | i. Load the URL                                       | US/English  |
|                                                   | ii. Select country and language from pop-up window    |France/french |
|                                                   | iii. Click on submit
|                                                   | iv. Click on Accept cookies buttom[Conditional]
|                                                   | v. Validate selected data and data displayed on homepage is same |                |
 |             | vi. Close the browser | |
|4. Validate that on selection of Franch/French link for patient and professional is not visible |  
|       | i. Load the URL                                         | France/fench  |
|                                                   | ii. Select country and language from pop-up window      | |
|                                                   | iii. Click on submit | |
|                                                   | iv. Click on Accept cookies buttom[Conditional] | |
|                                                   | v. Validate selected data and data displayed on homepage is same |
|                                                   | vi. Validate that patient and professional are not present in the header      | | 
|             | vii. Close the browser | |
|5. Validate nothing changes on the home page on opening, and closing modal without changes  | i. Load the URL   | Us/English  |
|                                                   | ii. Select country and language from pop-up window      |  France/french |
|                                                   | iii. Click on submit | |
|                                                   | iv. Click on Accept cookies buttom[Conditional] | |
|                                                   | v. check the data displayed on homepage for country and language |
|                                                   | vi. Click on country: language button in the header      | |
|                                                   | vii. Click on x in the modal pop-up window |
|                                                   | viii. Validate data on home page is unchanged      | |
|             | ix. Close the browser | |
|6. Validate nothing changes on the home page on opening modal, inputting values a different value in dropdown, and closing without submit  | i. Load the URL   | Us/English  |
|                                                   | ii. Select country and language from pop-up window      |  France/french |
|                                                   | iii. Click on submit | |
|                                                   | iv. Click on Accept cookies buttom[Conditional] | |
|                                                   | v. check the data displayed on homepage for country and language |
|                                                   | vi. Click on country: language button in the header      | |
|                                                   | Vii select country/language from drop down
|                                                   | viii. Click on x in the modal pop-up window |
|                                                   | ix. Validate data on home page is unchanged      | |
|             | vii. Close the browser | |
|7. Validate that on selecting the same values from header as during the page load, nothing changes on the home page | i. Load the URL   | Us/English  |
|                                                   | ii. Select country and language from pop-up window      | France/french| 
|                                                   | iii. Click on submit | |
|                                                   | iv. Click on Accept cookies buttom[Conditional] | |
|                                                   | v. Click on country: language button in the header      | |
|                                                   | Vi. select country/language from drop down[same as previous]
|                                                   | vii. click on submit.
|                                                   | viii. Validate data on home page is unchanged      | |
|             | ix. Close the browser | |





## src_test_resources

![image](https://user-images.githubusercontent.com/110168437/198849733-44bcc8a1-0fbb-4099-a21c-a36a982c4039.png)

It consits of testdata folder that has json file containing test data and config.properties file that contians configurations like browser type, url etc.
![image](https://user-images.githubusercontent.com/110168437/198850403-6cda7bcd-46da-455e-9ce5-f549d2579654.png)

![image](https://user-images.githubusercontent.com/110168437/198850433-d7f2fac0-fa8e-45a0-b5f5-9b29c270aa54.png)


## Reports
![image](https://user-images.githubusercontent.com/110168437/198849904-10a6ba3b-9949-46a1-ac85-7b020a425b28.png)

Extend Report is generated under this folder.



## POM_File
This file contains all the dependencies,plugin and configuration required for running this project. The configuration of surefire enables us to point to the testNG.xml file that contians the test execution configuration.
## testNG_File
It contains the configuration for running test method parallelly with thread-count as 5. It also contains listeners implemented in the utilities package that are required to be executed during the run.

![image](https://user-images.githubusercontent.com/110168437/198850511-4196065c-15af-41ff-b617-a1936b27ec26.png)







