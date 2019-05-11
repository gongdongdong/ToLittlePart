package com.gdd.tolittlepart.views.main.DI;

import javax.inject.Inject;

public class Student {
    private String name;
    private String age;

    @Inject
    public Student() {
        name = "default name";
        age = "default age";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
