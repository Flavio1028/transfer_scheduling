package br.com.codeup.transfer.controller;

import br.com.codeup.transfer.controller.model.AccountDTO;
import br.com.codeup.transfer.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> getAll(Pageable page) {
        return ResponseEntity.ok(this.accountService.getAll(page));
    }

}