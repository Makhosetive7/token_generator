package com.example.ElectricityTokenGenerator.repository.Tokens;

import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalVendorRepository extends JpaRepository<LocalVendor, Long> {
    
    
}
