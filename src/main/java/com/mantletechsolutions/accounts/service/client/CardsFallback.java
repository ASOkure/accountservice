package com.mantletechsolutions.accounts.service.client;

import com.mantletechsolutions.accounts.dto.CardsDto;
import com.mantletechsolutions.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }

}
