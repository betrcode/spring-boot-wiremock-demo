package se.bettercode.springbootwiremockdemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class CarUnmarshaller {

  static Car fromJson(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Car car = mapper.readValue(json, Car.class);
    return car;
  }

}
