package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.domain.enumeration.OrderStatus;
import com.dikolacici.DI_kolacici.domain.model.Customer;
import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.model.ProductOrder;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMail(String to, String subject, String htmlBody)  {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
//admin salje email korisniku
    public void sendOrderStatusEmail(Order order) {

        Context context = new Context();
        Customer customer = order.getCustomer();

        context.setVariable("firstName", customer.getFirstName());
        context.setVariable("status" , order.getStatus().name());

        List<ProductOrder> orderItems = order.getOrderItems();
        context.setVariable("orderItems", orderItems);

        BigDecimal totalPrice = BigDecimal.ZERO;
        for(ProductOrder orderitem : orderItems) {
            BigDecimal priceOrder = BigDecimal.valueOf(orderitem.getProduct().getPrice())
                    .multiply(BigDecimal.valueOf(orderitem.getQuantity()));
            totalPrice = totalPrice.add(priceOrder);
        }
        context.setVariable("totalPrice", totalPrice);

        String templateName;
        if(order.getStatus() == OrderStatus.ACCEPTED) {
            templateName = "order-status-accepted";
        } else if(order.getStatus() == OrderStatus.REJECTED) {
            templateName = "order-status-rejected";
        } else {
            throw new IllegalArgumentException("Unknown order status");
        }
        String htmlBody = templateEngine.process(templateName, context);
        sendMail(customer.getEmail(),"Your order status", htmlBody );
    }


    //korisnik salje email adminu
    public void sendOrderNotificationToAdmin(Order order) {
        Context context = new Context();
        Customer customer = order.getCustomer();

        context.setVariable("firstName", customer.getFirstName());
        context.setVariable("lastName", customer.getLastName());
        context.setVariable("productOrder", order.getOrderItems());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(ProductOrder orderitem : order.getOrderItems()) {
            BigDecimal priceOrder = BigDecimal.valueOf(orderitem.getProduct().getPrice())
                    .multiply(BigDecimal.valueOf(orderitem.getQuantity()));
            totalPrice = totalPrice.add(priceOrder);
        }
        context.setVariable("totalPrice", totalPrice);

        String html = templateEngine.process("admin-order" , context);
        sendMail("dorisbilic11@gmail.com","Your order notification", html);
    }
}