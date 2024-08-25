package com.netzwerk.ecommerce.controller;

import com.netzwerk.ecommerce.dto.OrderDto;
import com.netzwerk.ecommerce.service.OrderServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImplementation orderService;

    @Autowired
    public OrderController(OrderServiceImplementation orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/homeToOrders")
    public String homeToOrders(Model model) {
        List<String> makes = orderService.getDistinctMakes();
        log.info("Retrieved makes from service: {}", makes);
        model.addAttribute("makes", makes);
        model.addAttribute("order", new OrderDto());
        return "order";
    }

    @PostMapping("/saveOrderDto")
    public String saveOrders(@ModelAttribute("order") OrderDto order) {
        log.info("Received order for saving: {}", order.getModel());
        orderService.saveOrder(order);
        log.info("Order saved successfully.");
        return "redirect:/order/";
    }
}
