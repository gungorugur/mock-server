package com.gungor.mockserver;

import wiremock.org.apache.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ProductMockService implements MockService {

    @Override
    public void start() {
        products();
    }

    private void products() {
        stubFor(get(urlEqualTo("/product-service/products"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("product/get_products_response.json")));
    }
}
