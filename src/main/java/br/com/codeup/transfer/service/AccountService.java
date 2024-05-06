package br.com.codeup.transfer.service;

import br.com.codeup.transfer.data.entity.Account;
import br.com.codeup.transfer.controller.model.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    void saveAccounts(List<Account> accounts);

    Page<AccountDTO> getAll(Pageable page);

}