package se.sandevind.monty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.CombinableMatcher.both;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class MontyHallIntegrationTest {
    private static final Logger logger = Logger.getLogger(MontyHallIntegrationTest.class.getName());

    private RestTemplate restTemplate = new TestRestTemplate();
    private final int REQUESTS = 10000;

    @Test
    public void testRunWithSwap() {
        int success = doRestCalls("http://localhost:9090/run?lake=1&choice=swap");
        double percentage = getPercentage(success);

        assertThat(percentage, is(both(greaterThan(65d)).and(lessThan(70d))));
        String percentageStr = getPercentageStr(percentage);
        logger.log(Level.INFO, "Changing lakes - Successes:  " + success + " percentage: " + percentageStr);
    }

    @Test
    public void testRunWithStay() {
        int success = doRestCalls("http://localhost:9090/run?lake=1&choice=stay");
        double percentage = getPercentage(success);

        assertThat(percentage, is(both(greaterThan(30d)).and(lessThan(35d))));
        String percentageStr = getPercentageStr(percentage);
        logger.log(Level.INFO, "Staying with first lake - Successes:  " + success + " percentage: " + percentageStr);
    }

    private int doRestCalls(String url) {
        int success = 0;
        for (int i = 0; i < REQUESTS; i++) {
            success = restCall(success, url);
        }
        return success;
    }

    private int restCall(int success, String url) {
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        if (result) {
            success++;
        }
        return success;
    }

    private double getPercentage(double success) {
        return success / ((double)REQUESTS) * 100;
    }

    private String getPercentageStr(double percentage) {
        return String.format("%.2f", percentage) + "%";
    }
}
