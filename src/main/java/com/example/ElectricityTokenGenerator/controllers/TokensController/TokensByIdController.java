package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.services.Tokens.returnTokensByIdServices;

@RestController
@RequestMapping("api/tokens")
public class TokensByIdController {

  private final returnTokensByIdServices returnTokensByIdServices;

  public TokensByIdController(returnTokensByIdServices returnTokensByIdServices) {
    this.returnTokensByIdServices = returnTokensByIdServices;
  }

       // Get tokens by user id
    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokensById(@PathVariable Long id) {
        Optional<Token> tokens = returnTokensByIdServices.getTokensById(id);
        return tokens.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
}
