package br.com.codeup.transfer.service.impl;

import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.controller.model.UserDTO;
import br.com.codeup.transfer.controller.model.UserSaveDTO;
import br.com.codeup.transfer.data.entity.User;
import br.com.codeup.transfer.data.repository.UserRepository;
import br.com.codeup.transfer.service.AccountService;
import br.com.codeup.transfer.service.UserService;
import br.com.codeup.transfer.util.enums.ErrorMessageEnum;
import br.com.codeup.transfer.util.exception.ValidationErrorException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AccountService accountService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(AccountService accountService, UserRepository userRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SuccessDTO save(@Valid UserSaveDTO request) {

        // Valida se o documento ja não esta cadastrado
        if (this.userRepository.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new ValidationErrorException(ErrorMessageEnum.DOCUMENT_ALREADY_REGISTERED);
        }

        User user = this.userRepository.saveAndFlush(this.modelMapper.map(request, User.class));

        user.getAccounts().forEach(account -> account.setUser(user));

        this.accountService.saveAccounts(this.modelMapper.map(user.getAccounts(), List.class));

        return SuccessDTO.builder()
                .message("Cliente cadastrado com sucesso.")
                .data(user)
                .build();
    }

    @Override
    public UserDTO getById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id)
                .orElseThrow(() -> new ValidationErrorException(ErrorMessageEnum.USER_NOT_FOUND)), UserDTO.class);
    }

    @Override
    public Page<UserDTO> getAll(Pageable page) {
        Page<User> userEntity = this.userRepository.findAll(page);
        return userEntity.map(value -> this.modelMapper.map(value, UserDTO.class));
    }

}