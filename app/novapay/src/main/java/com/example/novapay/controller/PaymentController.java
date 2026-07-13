package com.example.novapay.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @PostMapping("/process")
    public String payment() {
        return "Payment Successful";
    }
}