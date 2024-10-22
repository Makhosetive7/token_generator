package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ElectricityTokenGenerator.services.TokenServices;
import com.example.ElectricityTokenGenerator.entity.TokensEntity;


@RestController
@Controller
@RequestMapping("api/tokens/")
public class Tokens {

    private final TokenServices tokenServices;


    @Autowired
    private Tokens(TokenServices tokenServices){
        this.tokenServices = tokenServices;
    }

    //retrieve all tokens available in the system
    @GetMapping("/")
  public List<TokensEntity> getAllTokens(@PathVariable Long id){
    return tokenServices.getAllTokens();

  }
    //get tokens by user id
    @GetMapping("/{id}")
    public ResponseEntity<TokensEntity>  getTokensById(@PathVariable Long id) {
        Optional<TokensEntity> tokens = tokenServices.getTokensById(id);
        return tokens.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //create new tokens 
    @PostMapping
    public ResponseEntity<TokensEntity> createTokens(@PathVariable TokensEntity token){
        TokensEntity newToken = tokenServices.createTokens(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(newToken);
    }


    //delete created tokens 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TokensEntity> deleteTokens(@PathVariable Long id){
        tokenServices.deleteTokens(id);
        return ResponseEntity.noContent().build();
    }
}
