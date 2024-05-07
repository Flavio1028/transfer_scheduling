package br.com.codeup.transfer.service;

import br.com.codeup.transfer.controller.model.ExtractDTO;
import br.com.codeup.transfer.controller.model.ScheduleDTO;
import br.com.codeup.transfer.controller.model.SuccessDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchedulingService {

    SuccessDTO scheduleOperation(@Valid ScheduleDTO request);

    Page<ExtractDTO> retrieveExtract(String account, Pageable page);

}