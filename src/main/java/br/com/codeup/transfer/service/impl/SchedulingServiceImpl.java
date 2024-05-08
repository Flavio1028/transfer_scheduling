package br.com.codeup.transfer.service.impl;

import br.com.codeup.transfer.controller.model.ExtractDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@Validated
public class SchedulingServiceImpl implements SchedulingService {

    private final SchedulingRepository schedulingRepository;

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SchedulingServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, SchedulingRepository schedulingRepository) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public SuccessDTO scheduleOperation(@Valid ScheduleDTO request) {

        // Valida se os usuários existem
        Account originAccount = this.getUserById(request.getOriginAccount(), ErrorMessageEnum.SOURCE_ACCOUNT_NOT_FOUND);
        Account destinationAccount = this.getUserById(request.getDestinationAccount(), ErrorMessageEnum.DESTINATION_COUNT_NOT_FOUND);

        // Valida a data de trasferencia
        this.validateDataTransfer(request.getSchedulingDate());

        Scheduling scheduling = this.modelMapper.map(request, Scheduling.class);
        scheduling.setOriginAccount(originAccount);
        scheduling.setDestinationAccount(destinationAccount);
        scheduling.setTax(this.calculateRate(request.getSchedulingDate(), request.getTransferValue()));

        this.schedulingRepository.save(scheduling);

        return SuccessDTO.builder().message("Agendamento realizado com sucesso.").build();
    }

    @Override
    public Page<ExtractDTO> retrieveExtract(String account, Pageable page) {

        Page<Scheduling> accounts = this.schedulingRepository.findBYOriginAccount(account, page);

        return accounts.map(value -> ExtractDTO.builder()
                .customerName(value.getOriginAccount().getUser().getName())
                .originAccount(value.getOriginAccount().getAccountNumber())
                .transferValue(value.getTransferValue())
                .tax(value.getTax())
                .dataTransfer(value.getDataTransfer())
                .schedulingDate(value.getSchedulingDate())
                .beneficiaryName(value.getDestinationAccount().getUser().getName())
                .beneficiaryAccount(value.getOriginAccount().getAccountNumber())
                .build());

    }

    private Account getUserById(String accountNumber, ErrorMessageEnum errorMessage) {
        return this.accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ValidationErrorException(errorMessage));
    }

    private void validateDataTransfer(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        if (now.isAfter(date)) {
            throw new ValidationErrorException(ErrorMessageEnum.iNVALID_DATA_TRANSFER);
        }
    }

    private BigDecimal calculateRate(LocalDateTime dataTransfer, BigDecimal transferValue) {

        BigDecimal calculatedRate;

        // Calcula a data para do agendamento
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        long days = today.until(dataTransfer, ChronoUnit.DAYS);

        if (days == 0) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0.025));
        } else if (days <= 10) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0));
        } else if (days <= 20) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0.082));
        } else if (days <= 30) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0.069));
        } else if (days <= 40) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0.047));
        } else if (days <= 50) {
            calculatedRate = transferValue.multiply(BigDecimal.valueOf(0.017));
        } else {
            throw new ValidationErrorException(ErrorMessageEnum.INVALID_RATE);
        }

        return calculatedRate;
    }

}