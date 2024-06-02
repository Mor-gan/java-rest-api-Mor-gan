package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cbfacademy.filehandler.InsufficientBalanceException;

@RestController
@RequestMapping("v3/api")
public class PaymentController {

    private final ListPaymentService listPaymentService;

    public PaymentController(ListPaymentService listPaymentService) {
        this.listPaymentService = listPaymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = listPaymentService.getAllPayments();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Paymentlist", "All payments");
        return new ResponseEntity<>(payments, headers, HttpStatus.OK);
    }

    @PostMapping("/processpayment")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payments) throws InsufficientBalanceException {
        Payment createdNewPayment = listPaymentService.processPayment(payments);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Processpayment", "Payment processing");
        return new ResponseEntity<>(createdNewPayment, headers, HttpStatus.CREATED);
    }

    @PutMapping("/updatepayment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") UUID id, @RequestBody Payment payments) {
        Payment updatedPayment = listPaymentService.updatePayment(id, payments);

        if (updatedPayment != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("PaymentUpdated", "Payment has been updated");
            return new ResponseEntity<>(updatedPayment, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Payment> cancelPayment(@PathVariable("id") UUID id) {
        boolean canceled = listPaymentService.cancelPayment(id);

        HttpHeaders headers = new HttpHeaders();

        if (canceled) {
            headers.add("Validpayment", "Payment removed");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } else {
            headers.add("Validpayment", "Payment not found");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

}
