package br.com.codeup.transfer.service;

import br.com.codeup.transfer.controller.model.ScheduleDTO;
import br.com.codeup.transfer.controller.model.SuccessDTO;
import jakarta.validation.Valid;

public interface SchedulingService {

    SuccessDTO scheduleOperation(@Valid ScheduleDTO request);

}