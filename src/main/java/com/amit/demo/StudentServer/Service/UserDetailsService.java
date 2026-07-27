// package com.amit.demo.StudentServer.Service;

// import com.amit.demo.StudentServer.Entity.User;
// import com.amit.demo.StudentServer.Repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     private UserRepository.java userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//         User.java user = userRepository.findByUserName(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

//         return org.springframework.security.core.userdetails.User.java.withUsername(user.getUserName())
//                 .password(user.getPassword())
//                 .roles(user.getRoles().toArray(new String[0]))
//                 .build();
//     }
// }