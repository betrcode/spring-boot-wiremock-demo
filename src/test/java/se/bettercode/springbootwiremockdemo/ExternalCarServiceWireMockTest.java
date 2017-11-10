package se.bettercode.springbootwiremockdemo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static junit.framework.TestCase.assertEquals;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class ExternalCarServiceWireMockTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080

  private ExternalCarService externalCarService;

  @Before
  public void setUp() {
    externalCarService = new ExternalCarService(new ExternalCarHttpService());
  }

  @Test
  public void getCar() throws IOException {
    Car expectedCar = Car.builder()
        .manufacturer("Ford")
        .model("Mustang")
        .year(1969)
        .build();

    stubFor(get(urlEqualTo("/car/1"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{ \"manufacturer\": \"Ford\", \"model\": \"Mustang\", \"year\": 1969 }")));

    final Car actualCar = externalCarService.getCarById(1);

    verify(getRequestedFor(urlMatching("/car/[a-z0-9]+")));
    assertEquals(expectedCar, actualCar);
  }
}
