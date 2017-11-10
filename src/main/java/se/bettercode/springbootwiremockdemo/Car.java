package se.bettercode.springbootwiremockdemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
  String manufacturer;
  String model;
  int year;
}
