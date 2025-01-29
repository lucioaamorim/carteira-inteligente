package br.com.arquiteturalimpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarteiraInteligenteAplication {

    public static void main(String[] args) {
        SpringApplication.run(CarteiraInteligenteAplication.class, args);
    }
}