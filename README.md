# BookLibrary
Book Library use case - Spring Boot Microservices

##Components
1. ApiGatewayService - Using Netflix ZUUL proxy
2. BookLibraryEurekaServer - Using Netflix Eureka discovery client and discovery server.
3. BookService - Using Spring Boot Rest and Data JPA
4. SubscriptionService - Using Spring Boot Rest and Data JPA

##Running the project
1. First run BookLibraryEurekaServer module as this will help in identifying the other servics quickly and registers them in efficint way otherwise it will take lot of time to register / discover other services.
  cd BookLibraryEurekaServer
  mvn clean install
2. Second run BookService which keeps the records of books in the library.
  cd BookService
  mvn clean install
3. Third run SubscriptionService which keeps the records of subscription and subscribers of book available in the library.
  cd SubscriptionService
  mvn clean install
4. Third run ApiGatewayService which route the requests based on the uri parameters to different appropriate services.
  cd SubscriptionService
  mvn clean install
  
##Testing
1. Import BookLibraryAPIMicroService.postman_collection.json in Postman to test the service and end to end flow using Postman Rest Client.

