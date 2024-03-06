package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    private Map<String, String> paymentData;


    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("b3d7439-5b48-5775-94d8-g43ec9731266", orders.getFirst(), "VOUCHER_CODE", this.paymentData, true);
        payments.add(payment1);

        paymentData = new HashMap<>();
        paymentData.put("address", "Jl. keren");
        paymentData.put("deliveryFee", "5000");
        Payment payment2 = new Payment("c4e8540-6c59-6886-05e9-h54fd0842377", orders.getFirst(), "CASH_ON_DELIVERY", this.paymentData, true);
        payments.add(payment2);

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment3 = new Payment("d5f9651-7d60-7997-16f0-i65g1953488", orders.getFirst(), "VOUCHER_CODE", this.paymentData, true);
        payments.add(payment3);
    }

    @Test
    void testAddPaymentByCashOnDelivery(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentByVoucher(){
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddCashOnDeliveryPaymentIfAlreadyExists(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddVoucherPaymentIfAlreadyExists(){
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testUSetStatusCashOnDeliveryPayment(){
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), payment.getPaymentData(),payment.isValid(), PaymentStatus.SUCCESS.getValue());

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment.getId(), PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusVoucherPayment(){
        Payment payment = payments.getFirst();
        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), payment.getPaymentData(),payment.isValid(), PaymentStatus.SUCCESS.getValue());

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment.getId(), PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalidStatus(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment.getId(), "HUFT"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalidOrderId(){
        doReturn(null).when(paymentRepository).findById("ZCZC");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus("ZCZC", PaymentStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testFindCashOnDeliveryByIdIfIdFound(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindVoucherPaymentByIdIfIdFound(){
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        doReturn(null).when(paymentRepository).findById("ZCZC");
        assertNull(paymentService.findById("ZCZC"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayment();
        List<Payment> result = paymentService.getAllPayment();
        assertEquals(payments, result);
    }
}
