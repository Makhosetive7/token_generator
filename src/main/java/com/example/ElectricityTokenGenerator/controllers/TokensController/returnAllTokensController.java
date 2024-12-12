package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.ElectricityTokenGenerator.services.Tokens.returnAllTokensServices;
import com.example.ElectricityTokenGenerator.entity.TokensEntity;


@RestController
@RequestMapping("api/tokens/")
public class returnAllTokensController {

    public final returnAllTokensServices returnAllTokensServices;

  public returnAllTokensController(returnAllTokensServices returnAllTokensServices) {
    this.returnAllTokensServices = returnAllTokensServices;
  }

    // Retrieve all tokens available in the system
    @GetMapping("/")
    public List<TokensEntity> getAllTokens() {
        return returnAllTokensServices.getAllTokens();
    }
}