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

    public Payment(String id, Order order, String method, Map<String,String> paymentData){
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if (order == null){
            throw new IllegalArgumentException();
        }

        if(paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }

        boolean isValid = false;

        switch (method){
            case "VOUCHER_CODE":
                String voucherCodeChecking = paymentData.get("voucherCode");

                if(voucherCodeChecking == null || voucherCodeChecking.isEmpty()){
                    throw new IllegalArgumentException("Payment data is invalid");
                }

                if(voucherCodeChecking.length() == 16 && voucherCodeChecking.startsWith("ESHOP")){
                    int i = 0;
                    for (char data : voucherCodeChecking.toCharArray()){
                        if (Character.isDigit(data)){
                            i++;
                        }
                    }
                    if (i == 8){
                        isValid = true;
                    }
                }
                break;


            case "CASH_ON_DELIVERY":
                String address = paymentData.get("address");
                String deliveryFee = paymentData.get("deliveryFee");

                isValid = address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty();

                break;
            default:
                throw new IllegalArgumentException();

        }

        if (isValid){
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status){
        this(id, order, method, paymentData);
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
