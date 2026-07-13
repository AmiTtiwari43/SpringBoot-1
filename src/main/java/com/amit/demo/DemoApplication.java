package com.amit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		//no need to import the class if it is in a same package/folder
		Students student = new Students("amit" , 45);

		System.out.println(student.getAge());
		System.out.println(student.getName());
	}

}
