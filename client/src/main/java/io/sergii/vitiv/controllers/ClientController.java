package io.sergii.vitiv.controllers;

import io.sergii.vitiv.ConfigProperties;
import io.sergii.vitiv.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${some.other.property}")
    private String someOtherProperty;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private TimeService timeService;

    /**
     * Intention of this method is to get time from one of service.
     * This is show how @LoadBalanced annotation works.
     * Time should be returned from each instance, by default round robin rule applied.
     *
     * @return response.
     */
    @GetMapping("/time")
    public String getTime() {
        return "Current time is: " + timeService.getTime();
    }

    /**
     * Intention of this method is to test how property was fetched from config server.
     * Properties are populated through @ConfigurationProperties and @Value.
     *
     * @return response.
     */
    @GetMapping("/properties")
    public String getProperties() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(configProperties.getProperty());
        stringBuilder.append(" || ");
        stringBuilder.append(someOtherProperty);

        return stringBuilder.toString();
    }

    /**
     * Intention of this method is to test how header was created and passed from Zuul service.
     * @param header custom created header from Gateway.
     * @return response
     */
    @GetMapping("/header")
    public String helloHeader(@RequestHeader("test-header") String header) {
        return "Hello from header: " + header;
    }
}
