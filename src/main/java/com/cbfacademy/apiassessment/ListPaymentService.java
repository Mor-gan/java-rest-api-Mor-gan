package com.cbfacademy.apiassessment;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.cbfacademy.filehandler.InsufficientBalanceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListPaymentService extends PaymentService {

    private static final String outputFile = "classpath:paymentFile.json";
    private final List<Payment> payments;
    ObjectMapper objectMapper = new ObjectMapper();

    public ListPaymentService() {
        // Initialze all the instance variable inside a constructor
        this.payments = new ArrayList<>();
        Payment firstPayment = new Payment(new BigDecimal(2000), new BigDecimal(4000), "1141 2922 3338 4744", "Kwame",
                "178");
        Payment secondPayment = new Payment(new BigDecimal(3000), new BigDecimal(3500), "2233 4455 6677 8899", "Alice",
                "279");
        Payment thirdPayment = new Payment(new BigDecimal(3000), new BigDecimal(4500), "9876 5432 1098 7654", "Bob",
                "345");
        payments.add(firstPayment);
        payments.add(secondPayment);
        payments.add(thirdPayment);

        if (!payments.isEmpty()) {
            try {
                File outputFileObj = ResourceUtils.getFile(outputFile);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(outputFileObj, payments);
                System.out.println("File created at: " + outputFile);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("File Not created");
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment processPayment(Payment createPayment) throws InsufficientBalanceException {
        BigDecimal paymentAmount = createPayment.getAmount();
        BigDecimal cardBalance = createPayment.getBalance();
        if (cardBalance.compareTo(paymentAmount) >= 0) {
            payments.add(createPayment);
        } else {
            throw new InsufficientBalanceException("Insufficient Payment amount");
        }
        return createPayment; // Return the created Payment on success
    }

    @Override
    public Payment updatePayment(UUID id, Payment updatePayment) {
        for (int i = 0; i < payments.size(); i++) {
            Payment payment = payments.get(i);
            if (payment.getId().equals(id)) {
                payments.set(i, updatePayment);// setting the index
                return updatePayment;
            }
            System.out.println("Your Payment is successful ");
        }
        return updatePayment;
    }

    @Override
    public boolean cancelPayment(UUID id) {
        return payments.removeIf(payment -> payment.getId().equals(id));
    }
}