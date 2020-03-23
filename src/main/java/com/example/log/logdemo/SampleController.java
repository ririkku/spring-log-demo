package com.example.log.logdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("api")
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    RestTemplate restTemplate;

    public SampleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String get() {
        logger.info("info!");
        return "get";
    }

    @GetMapping("to-other-app")
    public String toOtherApp() {

        String requestId = (String) RequestContextHolder
                .getRequestAttributes()
                .getAttribute("X-REQUEST-ID", RequestAttributes.SCOPE_REQUEST);

        logger.info("他のアプリケーションにリクエスト投げます！");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-REQUEST-ID", requestId);
        HttpEntity<String> entity = new HttpEntity<>("headers", headers);
        ResponseEntity<OtherAppResponse> response = restTemplate.exchange("http://localhost/api", HttpMethod.GET, entity, OtherAppResponse.class);

        return response.getBody().getValue();
    }
}
