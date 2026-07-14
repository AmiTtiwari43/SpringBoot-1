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
                <ul type = "disc">
                    <li><h3>Languages:</h3>
                        JavaScript, Python, Java, C, C++, PHP</li>
                    <li> <h3>Frameworks & Libraries:</h3>  HTML, CSS, React.js, Next.js, Node.js, Express.js, Redux, Flask, ESLint</li>
                    <li><h3>Databases:</h3> MySQL, MongoDB, PostgreSQL</li>
                    <li><h3>Tools & Platforms:</h3>Git, GitHub, VS Code, Docker, Render, Vercel, Netlify, GCP, Figma</li>
                </ul>
                """;
    }

    @GetMapping("/education")
    public String Education() {
        return """
                <h1>Education</h1>
                <div><h3>Lovely Professional University</h3>
                        Phagwara , Punjab
                        <br>
                        CGPA : 8.0
                        <br>
                        Since 2023
                </div>
                 <div><h3>Kendriya Vidhyalaya No. 1 Patiala Cantt</h3>
                        Patiala , Punjab
                        <br>
                        Percentage : 80
                        <br>
                        2022-2023
                </div>
                 <div><h3>Kendriya Vidhyalaya No. 1 Patiala Cantt</h3>
                        Patiala , Punjab
                        <br>
                        Percentage : 88
                        <br>
                        2020-2021
                </div>
        """;
    }

}
