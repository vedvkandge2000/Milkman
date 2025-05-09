package com.milkman.controller;

import com.milkman.DTO.MilkmanInfoDTO;
import com.milkman.DTO.MyOrdersResDTO;
import com.milkman.model.Customer;
import com.milkman.service.CustomerService;
import com.milkman.service.MilkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    private MilkmanService milkmanService;

    @GetMapping("/")
    ResponseEntity<?> getAllCustomer(){
        try{
            List<Customer> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable UUID id){
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/myMilkmans/{id}")
    ResponseEntity<?> getAllMilkmanForCustomer(@PathVariable UUID id){
        try{
            List<MilkmanInfoDTO> res = milkmanService.getAllMilkmanForCustomer(id);
            return ResponseEntity.ok(res);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        } 
    }

    @GetMapping("/{id}/myOrders/")
    ResponseEntity<?> getMyOrders(@PathVariable UUID id){
        try {
            List<MyOrdersResDTO> myOrders = customerService.getAllMyOrders(id);
            System.out.println( "MyOrders: " + myOrders.size());
            return ResponseEntity.ok(myOrders);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);
        }
    }

}
