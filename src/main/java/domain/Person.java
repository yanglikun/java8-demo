package domain;

import java.util.Optional;

/**
 * Created by yanglikun on 2017/3/25.
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
