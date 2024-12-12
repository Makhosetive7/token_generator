package com.example.ElectricityTokenGenerator.controllers.TokensController;

import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.services.Tokens.deleteTokensService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/tokens")
public class TokensDeleteController {

  private final deleteTokensService deleteTokensService;

  public TokensDeleteController(deleteTokensService deleteTokensService) {
    this.deleteTokensService = deleteTokensService;
  }
        // Delete created tokens
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTokens(@PathVariable Long id) {
        deleteTokensService.deleteTokens(id);
        return ResponseEntity.noContent().build();
    }



}
