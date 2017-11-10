package se.bettercode.springbootwiremockdemo;

import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
class ExternalCarService {

  private ExternalCarHttpService externalCarHttpService;

  Car getCarById(int id) throws IOException {
    String json = externalCarHttpService.getCarByIdOverHttp(id);
    Car car = CarUnmarshaller.fromJson(json);
    return car;
  }

}
