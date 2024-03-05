package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;
    boolean valid;

    public Payment(String id, Order order, String method, Map<String,String> paymentData, boolean valid){
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if (valid){
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }

        if (order == null){
            throw new IllegalArgumentException();
        }

        if(paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, boolean valid, String status){
        this(id, order, method, paymentData, valid);
        this.setStatus(status);
    }

    public void setStatus(String status){
        if (PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
