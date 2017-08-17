package lambda;

import domain.Car;
import domain.Insurance;
import domain.Person;

import java.util.Optional;

/**
 * Created by yanglikun on 2017/3/25.
 */
public class OptionalMain {

    public static void main(String[] args) {
        Insurance insurance = null;
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> s = optionalInsurance.map(Insurance::getName);
        System.out.println(s.isPresent());
    }

    public void test() {
        Optional<Person> opt = Optional.ofNullable(null);
        Optional<Optional<Car>> car = opt.map(Person::getCar);
    }

}


