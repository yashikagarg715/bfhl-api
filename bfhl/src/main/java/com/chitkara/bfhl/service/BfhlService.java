package com.chitkara.bfhl.service;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;

/**
 * Service interface for BFHL processing logic.
 * Always use interface + implementation — required by the problem statement.
 */
public interface BfhlService {

    BfhlResponse processData(BfhlRequest request);
}
