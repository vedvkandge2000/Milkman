package com.milkman.service;

import com.milkman.DTO.AddNewCustomerDTO;
import com.milkman.DTO.OrderDTO;
import com.milkman.model.Customer;
import com.milkman.model.Milkman;
import com.milkman.model.MilkmanCustomer;
import com.milkman.repository.MilkmanCustomerRepository;
import com.milkman.repository.MilkmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MilkmanService {
    
    @Autowired
    private MilkmanRepository milkmanRepository;

    @Autowired
    private MilkmanCustomerRepository milkmanCustomerRepository;
    
    public List<Milkman> getAllMilkman() {
        return milkmanRepository.findAll();
    }
    
    public Milkman getMilkmanById(UUID id) {
        return milkmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milkman not found"));
    }
    
    public Milkman createMilkman(Milkman milkman) {
        return milkmanRepository.save(milkman);
    }
    
    public Milkman updateMilkman(UUID id, Milkman userDetails) {
        Milkman user = milkmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milkman not found"));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());
        
        return milkmanRepository.save(user);
    }
    
    public void deleteMilkman(UUID id) {
        milkmanRepository.deleteById(id);
    }

    public MilkmanCustomer addCustomerForMilkman(Customer customer, AddNewCustomerDTO userDetails){
        MilkmanCustomer milkmanCustomer = new MilkmanCustomer();
        Milkman milkman = getMilkmanById(userDetails.getMilkManId());
        milkmanCustomer.setMilkman(milkman);
        milkmanCustomer.setCustomer(customer);
        milkmanCustomer.setCreatedAt(LocalDateTime.now());
        milkmanCustomer.setMilkRate(Double.parseDouble(userDetails.getRate()));
        milkmanCustomer.setDueAmount(0);
        System.out.println(milkmanCustomer.toString());
        return milkmanCustomerRepository.save(milkmanCustomer);
    }
} 