package com.skyrics.app.rest_controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyrics.app.dao.UserRepository;
import com.skyrics.app.entities.User;
import com.skyrics.app.jwt.service.JwtService;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.utility.AuthRequest;
import com.skyrics.app.utility.JwtResponse;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticatonController {
	

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	String generateToken =jwtService.generateToken(authRequest.getUsername());
        	Optional<User> user = userRepository.findByEmail(authRequest.getUsername());
        	UserDto userDto = modelMapper.map(user, UserDto.class);
            return new ResponseEntity<JwtResponse>(new JwtResponse(generateToken,true,userDto),HttpStatus.CREATED);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
	
}
