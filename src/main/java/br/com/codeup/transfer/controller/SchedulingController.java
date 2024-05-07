package br.com.codeup.transfer.controller;

import br.com.codeup.transfer.controller.model.ExtractDTO;
import br.com.codeup.transfer.controller.model.ScheduleDTO;
import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{account}")
    public ResponseEntity<Page<ExtractDTO>> getExtract(@PathVariable String account, Pageable page) {
        return ResponseEntity.ok(this.schedulingService.retrieveExtract(account, page));
    }

}