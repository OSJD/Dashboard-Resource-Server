package com.ht.api.controller;

import com.ht.api.model.Customer;
import com.ht.api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PortalController {

    private final CustomerRepository customerRepository;


    @GetMapping("/test")
    public String index(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token --->"+accessToken);

		//...

        return "index";
    }

    @GetMapping("/portal-login")
    public String portalLogin(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
                return "****************SUCESSS ========>accountTransactions";
        } else {
            return null;
        }
    }
}
