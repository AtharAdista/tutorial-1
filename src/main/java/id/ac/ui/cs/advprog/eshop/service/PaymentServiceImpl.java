package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment){
        if (paymentRepository.findById(payment.getId()) == null){
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(String paymentId, String status){
        Payment payment = paymentRepository.findById(paymentId);
        if(payment != null){
            Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), payment.getPaymentData(), payment.isValid(), status);
            paymentRepository.save(newPayment);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment findById(String paymentId){
        return paymentRepository.findById(paymentId);
    }
    @Override
    public List<Payment> getAllPayment(){
        return paymentRepository.getAllPayment();
    }


}