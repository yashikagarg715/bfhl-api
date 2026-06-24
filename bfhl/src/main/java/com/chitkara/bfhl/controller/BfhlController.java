package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes POST /bfhl endpoint.
 * Returns HTTP 200 on success, HTTP 500 on unexpected errors.
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")   // allows requests from any origin (needed for hosting)
public class BfhlController {

    private final BfhlService bfhlService;

    // Constructor injection (preferred over @Autowired on field)
    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Body: { "data": ["a", "1", "334", "$"] }
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);   // HTTP 200
    }
}
