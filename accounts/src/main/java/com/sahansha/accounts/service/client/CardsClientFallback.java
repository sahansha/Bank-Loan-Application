package com.sahansha.accounts.service.client;

import com.sahansha.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsClientFallback implements CardsClient {
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        return null;
    }
}
