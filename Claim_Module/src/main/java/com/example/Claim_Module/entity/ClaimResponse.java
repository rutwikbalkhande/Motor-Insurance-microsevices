package com.example.Claim_Module.entity;

import jakarta.ws.rs.core.Link;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimResponse {

        private Claim claim;
        private PolicyDto policy;

}
