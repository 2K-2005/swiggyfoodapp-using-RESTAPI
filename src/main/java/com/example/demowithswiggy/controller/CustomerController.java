package com.example.demowithswiggy.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demowithswiggy.dao.*;
import com.example.demowithswiggy.model.*;


@RestController
//@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
	UserRepo ur;
    @Autowired
	FoodItemRepo fir;
    @Autowired
	FoodOrderRepo foor;
    @GetMapping("/foods")
    public List<FoodItem> viewFoods() {
        return fir.findAll();
    }
    @PostMapping("/order/{customerId}")
    public FoodOrder placeOrder(
            @PathVariable int customerId,
            @RequestBody List<Integer> foodIds) {

        FoodOrder order = new FoodOrder();
        order.setCustomer(ur.findById(customerId).get());
        order.setItems(fir.findAllById(foodIds));
        order.setStatus(OrderStatus.PLACED);

        return foor.save(order);
    }

    
	
}