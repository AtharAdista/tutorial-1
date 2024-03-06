package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;

public interface PaymentService {

    public Payment addPayment(Payment payment);
    public Payment setStatus(String paymentId, String Status);
    public Payment findById(String orderId);
    public List<Payment> getAllPayment();
}