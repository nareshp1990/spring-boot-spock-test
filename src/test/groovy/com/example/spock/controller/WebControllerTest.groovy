package com.example.spock.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * def setup() {}          // run before every feature method
 * def cleanup() {}        // run after every feature method
 * def setupSpec() {}     // run before the first feature method
 * def cleanupSpec() {}   // run after the last feature method
 */

@Slf4j
@WebMvcTest
class WebControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc;

    void setup() {
        log.info("In setup()")
    }

    void cleanup() {
        log.info("In cleanup()")
    }

    def "when get is performed then the response has status 200 and content is 'Hello world!'"() {

        expect: "Status is 200 and the response is 'Hello world!'"
        log.info("### performing hello request ###")
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "Hello world!"
    }

}
