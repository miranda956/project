package com.practical.PROJECT.controller;

import com.practical.PROJECT.dto.CustomerDTO;
import com.practical.PROJECT.dto.LoginDTO;
import com.practical.PROJECT.model.Customer;
import com.practical.PROJECT.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer registeredCustomer = customerService.registerCustomer(customerDTO);
            return ResponseEntity.ok(registeredCustomer);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

//        @PostMapping("/login")
//        public ResponseEntity<String> login(@RequestParam String customerId, @RequestParam String pin) {
//            String token = authenticationService.authenticateAndGetToken(customerId, pin);
//            return ResponseEntity.ok(token);
//        }
    }
    @PostMapping("/login")
    @ApiOperation("Login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            String customerId = loginDTO.getCustomerId();
            String pin = loginDTO.getPin();

            String storedPin = customerService.getPinByCustomerId(customerId);
            if (storedPin.equals(pin)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid customer ID or pin");
            }
        } catch (NoSuchElementException e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid customer ID");
        } catch (Exception e) {
            // Handle other exceptions and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }







}
