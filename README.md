# Automation_SmokeTest

Exercise:
We wants to make sure they have a stable application before they deploy the code in production.  They have hired you to automate smoke testing of their website. We want you to:

Identify 5 core functionality test cases that ensure application is stable and production ready.
Answer: 
Login Page
Search Page
Product Detail Page
Add to Cart
5. Payments

Write automation script for 3 of the scenarios identified above (exclude any authentication level test cases for automation)
Answer: I automated first 4 Scenarios from 5 mentioned above.

For automation script use any language of choice, we prefer JavaScript:
Answer: I automated the Smoke test using below tools and technology:
Selenium Web Driver- automation test tool
Java - programming language
TestNG - test tool for writing test cases
Maven - build management tool for dependencies
ExtentReport - api to generate reports


Project Details:

I had created Data Driven Framework using Selenium and Java with implementation of Page Object Model, this is a design pattern to create Object Repository for web UI elements. Under this model, for each web page in the application, there should be corresponding page class. This Page class will find the WebElements of that web page and also contains Page methods which perform operations on those WebElements. So under this model i divided my framework into 7 main components:
Page layer - com.qa.pages - each web page will have its own class carrying all objects and methods
Test layer- com.qa.testcases - each test cases written in testNG, so 3 smoke tests in my case
Base layer- com.qa.base - common class for properties file and web driver instance
Util - com.qa.util - class to carry api methods like - extent reports, httpUrlConnection for broken links
Config- com.qa.config - properties file for url, searchtext etc.
Reports - testNG.html or Extent.html
TestData - excel is used but in this case i haven’t used any testData externally as its a small test package so data is covered in config file.

Test Cases:
Testing_smoke.xml : this contains all 3 test scenarios:
Scenario-1: 
a) Validate login page title
b) Validate if there is no broken links on login page using HttpURLConnection API
c) Validate if there are 24 departments on login page and validate there names
d)  Validate if user is able to enter text in search bar and navigate to SearchPage

Scenario-2:
Validate search page title 
Validate search results by comparing text entered vs search results
Validate if first product with “add to cart” button is being successfully added to the car.

Scenario-3,4:
Validate checkout page title
Validate cart details by checking same product is being added from search page to checkout page.


Steps to execute Automation Smoke Test script:
src/main/resource > testng_smoke.xml — run this as a testNG 
after complete run, refresh the project
Open test-output> Extent.html - shows pass/fail coverage with pie charts.
