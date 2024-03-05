package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private List<Product> products;

    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUp(){

        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c6328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.order = new Order(
                "a2c6328-4a37-4664-83c7-f32db8620155", this.products, 1708560000L, "Safira Sudrajat"
        );

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testAddPaymentWithVoucherCode(){
        this.paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266", this.order, "VOUCHER_CODE", this.paymentData);

        assertEquals("b3d7439-5b48-5775-94d8-g43ec9731266", payment.getId());
        assertEquals(this.order, payment.getOrder());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());

    }

    @Test
    void testAddPaymentWithCashOnDelivery(){
        this.paymentData = new HashMap<>();
        paymentData.put("address", "Jl. keren");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266", this.order, "CASH_ON_DELIVERY", this.paymentData);

        assertEquals("b3d7439-5b48-5775-94d8-g43ec9731266", payment.getId());
        assertEquals(this.order, payment.getOrder());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testAddPaymentWithEmptyMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                    this.order, "", this.paymentData);
        });
    }

    @Test
    void testAddPaymentWithInvalidMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                    this.order, "PINJAMAN_ONLINE", this.paymentData);
        });
    }

    @Test
    void testAddPaymentWithNullOrder(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                    null, "VOUCHER_CODE", this.paymentData);
        });
    }

    @Test
    void testAddPaymentWithEmptyPaymentData(){
        paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                    null, "VOUCHER_CODE", this.paymentData);
        });
    }

    @Test
    void testSetPaymentStatusToSuccessWhenUseVoucherCode(){
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "VOUCHER_CODE", this.paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusToRejectedWhenUseVoucherCode(){
        paymentData.put("voucherCode", "ESHOP1234ABC567891011");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "VOUCHER_CODE", this.paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusToInvalidStatusWhenUseVoucherCode(){
        paymentData.put("voucherCode", "ESHOP1234ABC567891011");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "VOUCHER_CODE", this.paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("HUFT");
        });
    }

    @Test
    void testSetPaymentStatusToSuccessWhenUseCashOnDelivery(){
        paymentData.put("address", "Jl. keren");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "CASH_ON_DELIVERY", this.paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusToRejectedWhenUseCashOnDelivery(){
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "CASH_ON_DELIVERY", this.paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusToInvalidStatusWhenUseCashOnDelivery(){
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266",
                this.order, "CASH_ON_DELIVERY", this.paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("HUFT");
        });
    }

}
