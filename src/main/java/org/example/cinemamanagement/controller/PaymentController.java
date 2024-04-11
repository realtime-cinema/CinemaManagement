package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddPaymentRequest;
import org.example.cinemamanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPayment(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody AddPaymentRequest req) {
        return ResponseEntity.ok(paymentService.addPayment(req));
    }
}