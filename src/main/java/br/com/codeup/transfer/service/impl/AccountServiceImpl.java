package br.com.codeup.transfer.service.impl;

import br.com.codeup.transfer.data.entity.Account;
import br.com.codeup.transfer.controller.model.AccountDTO;
import br.com.codeup.transfer.data.repository.AccountRepository;
import br.com.codeup.transfer.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveAccounts(List<Account> accounts) {
        this.accountRepository.saveAll(accounts);
    }

    public Page<AccountDTO> getAll(Pageable page) {
        return this.accountRepository.findAll(page).map(account -> {
            AccountDTO accountDTO = this.modelMapper.map(account, AccountDTO.class);
            accountDTO.setUserId(account.getUser().getId());
            return accountDTO;
        });
    }

}