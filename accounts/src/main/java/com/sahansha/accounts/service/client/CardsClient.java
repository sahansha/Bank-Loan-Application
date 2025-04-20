package com.sahansha.accounts.service.client;

import com.sahansha.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards" ,fallback = CardsClientFallback.class)
public interface CardsClient {

    @GetMapping("/api/v1/card/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
