package br.com.arquiteturalimpa.infrastructure.controller;

import br.com.arquiteturalimpa.core.exception.TransferException;
import br.com.arquiteturalimpa.infrastructure.dto.request.TransferRequest;
import br.com.arquiteturalimpa.infrastructure.dto.response.BaseResponse;
import br.com.arquiteturalimpa.infrastructure.dto.response.ConsultBalanceResponse;
import br.com.arquiteturalimpa.usecase.ConsultBalanceUseCase;
import br.com.arquiteturalimpa.usecase.CreateTransactionUseCase;
import br.com.arquiteturalimpa.usecase.FindWalletByTaxNumberUserCase;
import br.com.arquiteturalimpa.usecase.TransferUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    private final ConsultBalanceUseCase consultBalanceUseCase;
    private final TransferUseCase transferUseCase;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase;

    public WalletController(ConsultBalanceUseCase consultBalanceUseCase, TransferUseCase transferUseCase,
                            CreateTransactionUseCase createTransactionUseCase, FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase) {
        this.consultBalanceUseCase = consultBalanceUseCase;
        this.transferUseCase = transferUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.findWalletByTaxNumberUserCase = findWalletByTaxNumberUserCase;
    }

    @GetMapping("/consultBalance/{taxNumber}")
    public BaseResponse<ConsultBalanceResponse> consultBalance(@PathVariable String taxNumber) throws Exception {
        var balance = consultBalanceUseCase.consult(taxNumber);

        return BaseResponse.<ConsultBalanceResponse>builder().success(true).result(new ConsultBalanceResponse(balance)).build();
    }

    @PostMapping("/transfer")
    public BaseResponse<String> transfer(@RequestBody TransferRequest transferRequest) throws TransferException, Exception {
        var from = findWalletByTaxNumberUserCase.findByTaxNumber(transferRequest.fromTaxNumber());
        var to = findWalletByTaxNumberUserCase.findByTaxNumber(transferRequest.toTaxNumber());

        var transaction = createTransactionUseCase.create(to, from, transferRequest.value());

        transferUseCase.transfer(transaction, transferRequest.pin());

        return BaseResponse.<String>builder().success(true).message("Transfer completed successfully").build();
    }
}
