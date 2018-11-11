package io.sergii.vitiv.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Main intention of this class is to test Hystrix command.
 */
@Service
public class TimeService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String getTime() {
        return restTemplate.getForEntity("http://time-service/time", String.class).getBody();
    }

    /**
     * Fall back service, used when real service is down
     *
     * @return some meaningfull message.
     */
    public String fallback() {
        return "This is end of time.";
    }
}

