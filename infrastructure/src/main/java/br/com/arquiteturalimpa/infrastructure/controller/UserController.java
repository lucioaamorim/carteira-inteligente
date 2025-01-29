package br.com.arquiteturalimpa.infrastructure.controller;

import br.com.arquiteturalimpa.core.exception.TransactionPinException;
import br.com.arquiteturalimpa.infrastructure.dto.response.BaseResponse;
import br.com.arquiteturalimpa.infrastructure.dto.request.CreateUserRequest;
import br.com.arquiteturalimpa.infrastructure.mapper.UserMapper;
import br.com.arquiteturalimpa.usecase.CreateUserUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.arquiteturalimpa.infrastructure.utils.Utilities.log;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserMapper userMapper;

    public UserController(CreateUserUseCase createUserUseCase, UserMapper userMapper) {
        this.createUserUseCase = createUserUseCase;
        this.userMapper = userMapper;
    }

    @PostMapping("/createUser")
    public BaseResponse<String> createUser (@RequestBody CreateUserRequest request) throws Exception, TransactionPinException {
        log.info("Start of user creation::UserController");
        createUserUseCase.create(userMapper.toUser(request),request.pin());
        log.info("User created successfully::UserController");
        return BaseResponse.<String>builder().success(true).message("User created successfully").build();
    }
}
