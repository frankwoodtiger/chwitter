package com.chi.chwitter.config;

import org.apache.maven.shared.invoker.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
@Profile("dev")
public class DevSmtpServerStartupRunner implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(DevSmtpServerStartupRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("------------ DevSmtpServerStartupRunner start --------------");
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("./pom.xml"));
        request.setGoals(Arrays.asList("antrun:run@start-dev-fake-smtp-win"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            LOG.error("Cannot start fake smtp server.");
            e.printStackTrace();
        }
    }
}
