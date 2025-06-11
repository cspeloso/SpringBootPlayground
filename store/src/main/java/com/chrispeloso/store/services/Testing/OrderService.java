package com.chrispeloso.store.services.Testing;

import org.springframework.stereotype.Component;

@Component  //  @Component - Manages objects of type OrderService via Spring. @Service is an alias for @Component. We typically use @Component for utility classes. @Service is typically used for classes that contain business logic.
public class OrderService {

    private PaymentService paymentService;

//    @Autowired  //  @Autowired - used to be used to tell Spring to autowire this class with it's dependencies. No longer necessary if class has a single constructor.
    public OrderService(PaymentService ps)
    {
        this.paymentService = ps;
    }

    public void placeOrder()
    {
        paymentService.processPayment(10);
    }


    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
