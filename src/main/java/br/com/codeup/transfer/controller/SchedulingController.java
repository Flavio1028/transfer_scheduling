package br.com.codeup.transfer.controller;

import br.com.codeup.transfer.controller.model.ScheduleDTO;
import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    @Autowired
    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public ResponseEntity<SuccessDTO> save(@RequestBody ScheduleDTO request) {
        return ResponseEntity.ok(this.schedulingService.scheduleOperation(request));
    }

}