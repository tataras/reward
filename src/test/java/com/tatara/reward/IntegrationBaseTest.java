package com.tatara.reward;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("integration-tests")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract public class IntegrationBaseTest {

    @LocalServerPort
    protected int randomServerPort;
    protected final TestRestTemplate restTemplate = new TestRestTemplate();
}
