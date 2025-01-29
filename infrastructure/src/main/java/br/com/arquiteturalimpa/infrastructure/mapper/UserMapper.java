package br.com.arquiteturalimpa.infrastructure.mapper;

import br.com.arquiteturalimpa.core.domain.TaxNumber;
import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.infrastructure.dto.request.CreateUserRequest;
import br.com.arquiteturalimpa.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(User user){

        return new UserEntity(
          user.getId(),
          user.getEmail(),
          user.getPassword(),
          user.getTaxNumber().getValue(),
          user.getFullName(),
          user.getType(),
          user.getCreatedAt(),
          user.getUpdateAt()
        );
    }

    public User toUser(CreateUserRequest request) throws Exception {
        return new User(
            request.email(),
            request.password(),
            new TaxNumber(request.taxNumber()),
            request.fullname(),
            request.type()
        );
    }

    public User toUser(UserEntity userEntity) throws Exception {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                new TaxNumber(userEntity.getTaxNumber()),
                userEntity.getFullName(),
                userEntity.getType(),
                userEntity.getCreatedAt(),
                userEntity.getUpadateAt()
        );
    }
}
