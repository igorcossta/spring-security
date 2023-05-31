package io.igorcossta.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("user get endpoint");
    }

    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("user post endpoint");
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("user delete endpoint");
    }
}
