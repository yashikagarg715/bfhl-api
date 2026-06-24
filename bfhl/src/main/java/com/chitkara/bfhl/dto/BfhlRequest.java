package com.chitkara.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Request DTO — the incoming JSON must have a "data" array.
 * Example: { "data": ["a", "1", "334", "$"] }
 */
@Data
public class BfhlRequest {

    @JsonProperty("data")
    private List<String> data;
}
