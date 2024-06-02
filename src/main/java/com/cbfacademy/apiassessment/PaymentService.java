package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.UUID;

import com.cbfacademy.filehandler.InsufficientBalanceException;

public class PaymentService {

   List<Payment> getAllPayments() {
      return null;
   }

   Payment processPayment(Payment createPayment) throws InsufficientBalanceException {
      return createPayment;
   }

   Payment updatePayment(UUID id, Payment updatePayment) {
      return updatePayment;
   }

   boolean cancelPayment(UUID id) {
      return true;

   }
}