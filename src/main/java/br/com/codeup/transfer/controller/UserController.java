package br.com.codeup.transfer.controller;

import br.com.codeup.transfer.controller.model.SuccessDTO;
import br.com.codeup.transfer.controller.model.UserDTO;
import br.com.codeup.transfer.controller.model.UserSaveDTO;
import br.com.codeup.transfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<SuccessDTO> save(@RequestBody UserSaveDTO request) {
        return ResponseEntity.status(201).body(this.userService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAll(Pageable page) {
        return ResponseEntity.ok(this.userService.getAll(page));
    }

}