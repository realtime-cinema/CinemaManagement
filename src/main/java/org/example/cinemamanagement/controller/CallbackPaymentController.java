package org.example.cinemamanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/vnpay")
public class CallbackPaymentController {
    @GetMapping("/callback")
    public Flux<ResponseEntity<Map>> doCallBack(
            @RequestParam(name = "vnp_Amount") String amount,
            @RequestParam(name = "vnp_OrderInfo") String orderInfo,
            @RequestParam(name = "vnp_ResponseCode") String vnp_ResponseCode) {
//        localhost:8080/api/v1/vnpay/callback?vnp_Amount=10000000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=ahihih&vnp_PayDate=20240326144833&vnp_ResponseCode=24&vnp_TmnCode=E6T2KFD0&vnp_TransactionNo=0&vnp_TransactionStatus=02&vnp_TxnRef=38270779&vnp_SecureHash=731bdfda24d92061bbd9403b2d20d32bf1301de639de1a1c63416128cf90dfacf91d9131b1bdc90078678b68db93c5c2dbfde7c1275e903b1def08922bc51b3c
        System.out.println();
        if (vnp_ResponseCode.equals("00")) {
            // thanh toan thanh cong
            System.out.println("Thanh toan thanh cong");
            Map statusPayment = new HashMap();
            statusPayment.put("result", "success");
            statusPayment.put("message", "Thanh toan thanh cong");
            statusPayment.put("amount", amount);
            statusPayment.put("orderInfo", orderInfo);

            return Flux.just(new ResponseEntity<>(statusPayment, HttpStatus.OK));
        }
        Map statusPayment = new HashMap();
        statusPayment.put("result", "fail");
        statusPayment.put("message", "Thanh toan that bai");
        statusPayment.put("amount", amount);
        statusPayment.put("orderInfo", orderInfo);

        return Flux.just(new ResponseEntity<>(statusPayment, HttpStatus.OK));
    }

}
