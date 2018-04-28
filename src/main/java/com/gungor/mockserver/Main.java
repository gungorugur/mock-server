package com.gungor.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        final Integer port = 8080;
        final WireMockConfiguration wireMockConfiguration = options().port(port).withRootDirectory("src/main/resources");
        final WireMockServer wireMockServer = new WireMockServer(wireMockConfiguration);
        wireMockServer.start();
        configureFor("127.0.0.1", port);

        final Reflections reflections = new Reflections("com.gungor.mockserver");

        final Set<Class<? extends MockService>> serviceClasses = reflections.getSubTypesOf(MockService.class);

        serviceClasses.forEach(serviceClass -> {
            try {
                final MockService mockService = serviceClass.newInstance();
                mockService.start();
                logger.info("Service:" + serviceClass.getName() + " started..");
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Service starting has failed:" + serviceClass.getName(), e);
            }
        });
    }
}

