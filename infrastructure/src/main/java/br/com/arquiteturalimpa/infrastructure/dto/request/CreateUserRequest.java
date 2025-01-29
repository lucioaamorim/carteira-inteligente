package br.com.arquiteturalimpa.infrastructure.dto.request;

import br.com.arquiteturalimpa.core.domain.enums.UserTypeEnum;
import lombok.Builder;
import lombok.Data;


public record CreateUserRequest (String email, String password, String taxNumber, String fullname,
                                 UserTypeEnum type, String pin) {
}
