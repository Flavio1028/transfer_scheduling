package br.com.codeup.transfer.service;

import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.controller.model.UserDTO;
import br.com.codeup.transfer.controller.model.UserSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface UserService {

    SuccessDTO save(@Valid UserSaveDTO request);

    UserDTO getById(Long id);

    Page<UserDTO> getAll(Pageable page);

}