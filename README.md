# saleshandy

Local setup steps:
------------------

1. Make sure you have Java version 7 (JDK) or above installed in your machine.
2. Download eclipse [https://eclipse.org/downloads/].
3. Setup TestNG in eclipse using Market Place.
4. Clone the subtra test repo from here[https://github.com/navnkmr1032778/saleshandy.git].
5. Open the eclipse in either new workspace or existing one.
6. Import the file project by navigating File->import and select Existing Maven Projects
7. Once the project is imported, verify the java compiler path is set to jdk (not to JRE) by following steps below.
   i) Project -> properties
   ii) Click on Java compiler
   iii) Click on Installed JRE's link
   iv) Verify that the path set is for jdk 1.7 or above, otherwise change the path and save.
8. To download the dependencies
   i) Right click on the project module and select Run As -> Maven Install
  ii) Verify that build status is success. If not, restart eclipse and import the projects again.
 iii) Once it completed, right click on the project module and select Maven -> Update Project

 
 
 Execution:
 __________
 
 1. Execute the Maven project, run as -> Maven Install (Goal)

 	
 
 Result:
 _________
 
 1. If the total purchase value NOT equal of the total of all item's cost, test will be failed, if its equal then the test will be passed
 2. Report of the test execution can be found under the folder Reports/{current system millis}, if the test fails, it will have the snapshot of NDTV Weather page
 
 
 contact: lingamnaveen01@gmail.com
