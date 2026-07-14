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

    @GetMapping("/projects")
    public String Projects() {
        return """
                <h1>Projects</h1>
                <div>
                    <h3>DocVerse- Doctor Appointment Management System</h3>
                         <pre>
                            • Built a role-based healthcare platform aligned with OWASP Top-10 security standards.
                            • Streamlined booking with MongoDB aggregations and indexing, achieving <50ms queries and zero double bookings.
                            • Integrated AI health triage and analytics, improving specialist accuracy ~40% and admin efficiency ~60%.
                             Tech: React, Node.js, Express.js, MongoDB, JWT, OAuth, Tailwind CSS, Google Gemini API, SendGrid, UPI Payment</pre>
                </div>
                
                <div>
                    <h3>EventEase Lite- Event Management Platform </h3>
                         <pre>
                            • Architected a scalable MERN event platform with 9-state booking flow, UPI payments, refunds, and schema validation.
                            • Strengthened multi-layer RBAC security with JWT and middleware hardening, reducing authorization defects by ~30%.
                            • Optimized React performance using memoization, lint-safe hooks, and optimistic updates, cutting re-renders ~35%.
                            Tech: React, Node.js, Express.js, MongoDB, Mongoose, Tailwind CSS, JWT, bcrypt, ESLint, RESTful APIs</pre>
                </div>
                
                <div>
                    <h3>AI Virtual Therapy Assistant </h3>
                         <pre>
                            • Created an AI-assisted CBT chatbot with structured dialogue flows, improving user understanding and clarity by 25%.
                            • Implemented intent recovery and fallback routing, reducing conversation failures and interruptions by 30%.
                            • Refined short-term context memory handling, improving response relevance and reducing repeated queries by 20%.
                            Tech: HTML, CSS, JavaScript, Node.js, Express.js, MongoDB, Google Gemini AP</pre>
                </div>
        """;
    }
    @GetMapping("/contacts")
    public String Contacts() {
        return """
                <h1>Contacts</h1>
                Mob : 9115410470
                <br>
                Email : tiwari.amit4356@gmail.com"
                """;
    }

}
