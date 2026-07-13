package com.amit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
			Students student = context.getBean(Students.class);
			student.setName("Amit");
			student.setAge(30);

			System.out.println(student.getName());
			System.out.println(student.getAge());


		//no need to import the class if it is in a same package/folder
		//		Students student = new Students("amit" , 45);



	}

}
