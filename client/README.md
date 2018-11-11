#Client

Client application that combine all simple examples related to Spring Cloud.

 - `/time` - endpoint for testing how ribbon loadbalace request. This endpoint will use
 RestTemplate for getting time from one of two downstream time-services.
  
 - `/properties` - endpoint for testing resolving of properties from Spring Cloud Config Server.
 
 - `/header` - endpoint to observe how Zuul/Gateway filter all requests. During this transformation
 new header is added on Pre-Filter stage.