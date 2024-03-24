package org.example.cinemamanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cinemamanagement.payload.request.StatusRequestDTO;
import org.example.cinemamanagement.service.GetPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class GetPaymentStatusController {

    @Autowired
    private GetPaymentStatusService getPaymentStatusService;

    @GetMapping("/api/v1/vnpay/payment/status")
    public ResponseEntity<?> getStatus() {
        System.out.println("hello may cung");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/v1/vnpay/payment/status")
    public ResponseEntity<Map<String, Object>> getStatus(
            HttpServletRequest request,
            @RequestBody StatusRequestDTO statusRequestDTO
    ) throws IOException {
        Map<String, Object> result = this
                .getPaymentStatusService
                .getStatus(request, statusRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
