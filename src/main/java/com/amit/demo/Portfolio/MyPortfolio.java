package com.amit.demo.Portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPortfolio {

    @GetMapping("/myself")
    public String Myself() {
        return """
                <h1>Myself</h1>
                <p>My name is Amit Tiwari. I Study in LPU</p>
                <ul>
                    <li>GitHub: link </li>
                    <li>Leetcode: link</li>
                    <li>Linkedin: link</li>
                </ul>
                
                """;
    }

    @GetMapping("/skills")
    public String Skills() {
        return """
                <h1>Skills: </h1>
                """;
    }


}
