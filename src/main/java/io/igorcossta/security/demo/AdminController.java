package io.igorcossta.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("admin get endpoint");
    }

    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("admin post endpoint");
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("admin delete endpoint");
    }
}
