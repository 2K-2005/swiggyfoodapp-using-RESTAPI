package com.example.demowithswiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demowithswiggy.model.*;
import com.example.demowithswiggy.dao.*;


@RestController
//@RequestMapping("/admin")
public class AdminController {
    @Autowired
	RestaurantRepo rr;
    @Autowired
	UserRepo ur;
    @Autowired
	FoodItemRepo fir;
    @Autowired
	FoodOrderRepo foor;
	
    
    @PostMapping("/restaurant")
    public Resuturant get1(@RequestBody Resuturant r) {
        return rr.save(r);
    }
    @PostMapping("/food")
    public FoodItem get2(@RequestBody FoodItem f) {
        return fir.save(f);
    }
    
    @PutMapping("/assign/{orderId}/{partnerId}")
    public FoodOrder assignDelivery(
            @PathVariable int orderId,
            @PathVariable int partnerId) {

        FoodOrder order = foor.findById(orderId).get();
        User partner = ur.findById(partnerId).get();

        order.setDeliveryPartner(partner);
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        return foor.save(order);
    }

    



}