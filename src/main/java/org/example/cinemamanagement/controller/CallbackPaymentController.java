package org.example.cinemamanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vnpay")
public class CallbackPaymentController {
    @GetMapping("/callback")
    public ResponseEntity<?> doCallBack(@RequestParam Map<String, Object> callBackInfo) {

        if (callBackInfo.get("vnp_ResponseCode").equals("00")) {
            // thanh toan thanh cong
            System.out.println("Thanh toan thanh cong");
            Map statusPayment = new HashMap();
            statusPayment.put("result", "success");
            statusPayment.put("message", "Thanh toan thanh cong");
            statusPayment.put("amount", callBackInfo.get("vnp_Amount"));
            statusPayment.put("orderInfo", callBackInfo.get("vnp_OrderInfo"));
            return new ResponseEntity<>(statusPayment, HttpStatus.OK);
        }
        // thanh toan that bai
        Map statusPayment = new HashMap();
        statusPayment.put("result", "fail");
        statusPayment.put("message", "Thanh toan that bai");
        statusPayment.put("amount", callBackInfo.get("vnp_Amount"));
        statusPayment.put("orderInfo", callBackInfo.get("vnp_OrderInfo"));
        System.out.println("Thanh toan that bai");
        return new ResponseEntity<>(statusPayment, HttpStatus.OK);
    }
}
