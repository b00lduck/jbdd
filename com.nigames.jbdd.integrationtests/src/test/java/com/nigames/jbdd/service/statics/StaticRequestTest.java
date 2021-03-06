package com.nigames.jbdd.service.statics;

import com.jayway.restassured.RestAssured;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port:8888")
public class StaticRequestTest {

    @Test
    public void canGet() {
        RestAssured.when().get("http://localhost:8888/app/index.html")
                .then().statusCode(200);
    }

}
