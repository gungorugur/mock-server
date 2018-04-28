package com.gungor.mockserver;

import wiremock.org.apache.http.HttpStatus;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserMockService implements MockService {

    @Override
    public void start() {
        createUserMock();
        getUserMock();
    }

    private void createUserMock() {

        stubFor(post(urlEqualTo("/user-service/users"))
                .withHeader(HttpHeader.CONTENT_TYPE.asString(), equalTo("application/json"))
                .withRequestBody(equalToJson("{\"name\":\"ugur\",\"surname\":\"gungor\"}"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("user/create_user_response.json")));
    }

    private void getUserMock() {

        stubFor(get(urlEqualTo("/user-service/users/aaf45f4d0e6c422c80d55417c1ac2725"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("user/get_user_response.json")));
    }
}
