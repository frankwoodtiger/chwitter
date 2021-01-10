package com.chi.chwitter;

import com.chi.chwitter.config.DevSmtpServerStartupRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ChwitterApplicationTests {
    /*
        To prevent running the dev server when test triggered.
        See: https://stackoverflow.com/questions/41843861/spring-componentscan-excludefilters-annotation-not-working-in-spring-boot-test-c/41884101
    */
    @MockBean
    DevSmtpServerStartupRunner devSmtpServerStartupRunner;

    @Test
    void contextLoads() {
    }

}
