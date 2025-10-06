package com.example.DrivingHistory.controller;

import com.example.DrivingHistory.entity.DrivingHistory;
import com.example.DrivingHistory.entity.UserHistoryResponse;
import com.example.DrivingHistory.service.DrivingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driving-history")
public class DrivingController {

    private final DrivingService service;

    public DrivingController(DrivingService service) {
        this.service = service;
    }

    @PostMapping("save")
    public DrivingHistory createHistory(@RequestBody DrivingHistory history) {
        return service.saveHistory(history);
    }

    // Driving ID "Auto-Generated"
    @GetMapping("/{id}")
    public ResponseEntity<DrivingHistory> getHistoryById(@PathVariable String id) {
        DrivingHistory history = service.getHistoryById(id);
        return ResponseEntity.ok(history);
    }

    // get user Details And history of person using licenseNo.
    @GetMapping("/user/{userId}")
    public List<DrivingHistory> getHistoryByUser(@PathVariable Long userId) {
        return service.getHistoryByUser(userId);
    }

    @GetMapping("/all")
    public List<DrivingHistory> getAll() {
        return service.getAll();
    }

    // delete using driving ID " Aur=to generate "
    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable String id) {
        service.deleteHistory(id);
        return "Driving history deleted with id: " + id;
    }

    @GetMapping("/license/{licenseNo}")
    public UserHistoryResponse getByLicenseNo(@PathVariable String licenseNo) {
        return service.getByLicenseNo(licenseNo);
    }

}