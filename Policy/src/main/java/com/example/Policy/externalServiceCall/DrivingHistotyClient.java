package com.example.Policy.externalServiceCall;

import com.example.Policy.entity.dummyPolicy.DrivingHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DRIVINGHISTORY-SERVICE")
public interface DrivingHistotyClient {
    // calling DummyPolicies service set price using age in Service {DummyPolicies}

    @GetMapping("/driving-history/search/{licenseNo}")
    public DrivingHistoryDto searchByLicense(@PathVariable String licenseNo);
}
