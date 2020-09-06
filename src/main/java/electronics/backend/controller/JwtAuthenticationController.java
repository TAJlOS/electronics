package electronics.backend.controller;

import electronics.backend.security.JwtTokenUtil;
import electronics.backend.exception.UserAlreadyExistException;
import electronics.backend.dto.jwt.JwtRequest;
import electronics.backend.dto.jwt.JwtResponse;
import electronics.backend.entity.User;
import electronics.backend.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody JwtRequest registerRequest){
        if(jwtUserDetailsService.isUserExist(registerRequest.getUsername())){
           throw new UserAlreadyExistException(registerRequest.getUsername());
        }
        User user = jwtUserDetailsService.save(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(user.getUsername());
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<?> verify(){
        return ResponseEntity.ok("Token not expired");
    }

}
