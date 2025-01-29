package br.com.arquiteturalimpa.infrastructure.client;

import br.com.arquiteturalimpa.infrastructure.client.dto.response.ApiValidateResponse;
import org.springframework.stereotype.Service;

@Service
public class ApiValidateService {

    private final ApiValidationClient apiValidationClient;


    public ApiValidateService(ApiValidationClient apiValidationClient) {
        this.apiValidationClient = apiValidationClient;
    }

    public ApiValidateResponse validate(){
        try {
            return apiValidationClient.validate();
        } catch (Exception e) {
            return null;
        }

    }
}
