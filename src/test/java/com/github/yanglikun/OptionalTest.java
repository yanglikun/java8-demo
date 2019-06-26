package com.github.yanglikun;

import com.alibaba.fastjson.JSON;
import domain.Car;
import domain.Insurance;
import domain.Person;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {


    @Test
    public void map() {
        Insurance insurance = null;
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println(name);
    }

    @Test
    public void flatMap() {
        Person person = null;
        Optional<Person> optPerson = Optional.ofNullable(person);
        String name = optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        System.out.println(name);
    }

    @Test
    public void json() {
        Person person = new Person();
        person.setCar(Optional.of(new Car()));
        String json = JSON.toJSONString(person);
        System.out.println(json);
    }

    @Test
    public void filter() {
        Insurance insurance = new Insurance();
        insurance.setName("abc");
        Optional<Insurance> optionalInsurance = Optional.of(insurance);
//        optionalInsurance = Optional.empty();
        optionalInsurance.filter(x -> "abc".equals(x.getName()))
                .ifPresent(x -> System.out.println(x.getName()));
    }

}
