package com.manik.employee_management.controller;

import com.manik.employee_management.dto.LoginRequest;
import com.manik.employee_management.dto.LoginResponse;
import com.manik.employee_management.entity.Employee;
import com.manik.employee_management.repository.EmployeeRepository;
import com.manik.employee_management.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        String token = jwtUtil.generateToken(employee.getEmail());

        return ResponseEntity.ok(new LoginResponse(token, employee.getEmail(), employee.getRole()));
    }
}