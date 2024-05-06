package br.com.codeup.transfer.service.impl;

import br.com.codeup.transfer.controller.model.ScheduleDTO;
import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.data.entity.Account;
import br.com.codeup.transfer.data.entity.Scheduling;
import br.com.codeup.transfer.data.repository.AccountRepository;
import br.com.codeup.transfer.data.repository.SchedulingRepository;
import br.com.codeup.transfer.service.SchedulingService;
import br.com.codeup.transfer.util.enums.ErrorMessageEnum;
import br.com.codeup.transfer.util.exception.ValidationErrorException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Service
@Validated
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SuccessDTO scheduleOperation(@Valid ScheduleDTO request) {

        // Valida se os usuários existem
        Account originAccount = this.getUserById(request.getOriginAccount(), ErrorMessageEnum.SOURCE_ACCOUNT_NOT_FOUND);
        Account destinationAccount = this.getUserById(request.getDestinationAccount(), ErrorMessageEnum.DESTINATION_COUNT_NOT_FOUND);

        Scheduling scheduling = this.modelMapper.map(request, Scheduling.class);
        scheduling.setOriginAccount(originAccount);
        scheduling.setDestinationAccount(destinationAccount);

        scheduling.setTax(BigDecimal.ZERO);

        this.schedulingRepository.save(scheduling);

        return SuccessDTO.builder()
                .message("Agendamento realizado com sucesso.")
                .build();
    }

    private Account getUserById(String accountNumber, ErrorMessageEnum errorMessage) {
        return this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ValidationErrorException(errorMessage));
    }

}