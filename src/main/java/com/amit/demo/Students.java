package com.amit.demo;

public class Students {
    private String name;
    private int age;

    public Students() { }


    public Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }


    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

}
