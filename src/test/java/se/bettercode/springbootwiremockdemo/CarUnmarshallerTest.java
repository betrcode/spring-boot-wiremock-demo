package se.bettercode.springbootwiremockdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;

public class CarUnmarshallerTest {

  @Test
  public void carUnmarshall() throws IOException {
    String json = "{ \"manufacturer\": \"Ford\", \"model\": \"Mustang\", \"year\": 1969 }";
    Car car = CarUnmarshaller.fromJson(json);
    assertEquals("Ford", car.getManufacturer());
    assertEquals("Mustang", car.getModel());
    assertEquals(1969, car.getYear());
  }
}
