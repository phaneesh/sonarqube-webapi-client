package io.dyuti.sonarqube;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;
import io.dyuti.sonarqube.client.SonarClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    static WireMockServer wireMockServer;
    static SonarClient sonarClient;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(Options.DYNAMIC_PORT);
        wireMockServer.start();
        sonarClient = SonarClientFactory.createSonarClient(wireMockServer.baseUrl(), "test");
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @BeforeEach
    void beforeEach() {
        wireMockServer.resetAll();
    }

}
