#Functional description
This is a simple crawler application which takes as an input a json representing the array of urls 
which must be checked if their websites satisfy application criterias(exampe website's titles contains string 'news').
As a result the site will be saved in app with one of statuses 
- MARFEELIZABLE -  means that site satisifed one of existed criterias
- UNMARFEELIZABLE  -  means that site doesn't satisify any criterias
- FAILED -  checking website failed

Also in application will be saved the list of criterias which determine that site is MARFEELIZABLE.
Application offers to add site/get existed sites /get existed sites by status.

#Validation
- you can't add a site if it's already exist in system, it should be rechecked (or deleted/added) this functionality is not present yet
as a room for future improvements.
- if url is malformed the exception will be thrown back
- if one site check fails then all will be rollbacked
-

#Data model

- sites -  table which contains all added sites (url,status,createdDate,modifiedDate)
- marfeelizing_criterias -  defines a criteria which should be satisified to consider that site is MARFEELIZABLE, 
    One important thing is criteria type, which defines the logic to be done for checking and this must have its represntaion
    in backend code.
- criteria_properties - defines criteria properties

*To run application in full-functional environment at the start there is imported default criteria data:
 -  one checking that site's title contains news
 -  one checking that site's title contains nocians
This all is configurable and can be extended


#Used technologies   
- Spring-MVC
- Spring-Data-JPA
- HSQLD
- Spring Async -  each site url check will be executed in separate thread than main 
- hibernate-orm
- jUnit + Mockito
- bean-validation+hibernation validation
- jSoup - for site content parsing
- swagger


#Run application

To start application you don't need to have pre-installed any DB, it starts in memory db with pre-created schema and default data.
Simply deploy application to one of you servlet containers : Tomcat, Jboss etc, it was tested on Tomcat9.

* To run requests in Psotman please see attached postman collection.
