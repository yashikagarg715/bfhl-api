package com.chitkara.bfhl;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import com.chitkara.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests covering all 3 examples from the question paper
 * plus an edge-case empty-array test.
 */
class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ── Example A ──────────────────────────────────────────────────────────
    // Input:  ["a", "1", "334", "4", "R", "$"]
    // Expected:
    //   odd_numbers        → ["1"]
    //   even_numbers       → ["334", "4"]
    //   alphabets          → ["A", "R"]
    //   special_characters → ["$"]
    //   sum                → "339"
    //   concat_string      → "Ra"

    @Test
    void testExampleA() {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertEquals(List.of("1"),        res.getOddNumbers());
        assertEquals(List.of("334", "4"), res.getEvenNumbers());
        assertEquals(List.of("A", "R"),   res.getAlphabets());
        assertEquals(List.of("$"),        res.getSpecialCharacters());
        assertEquals("339",               res.getSum());
        assertEquals("Ra",                res.getConcatString());
    }

    // ── Example B ──────────────────────────────────────────────────────────
    // Input:  ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
    // Expected:
    //   odd_numbers        → ["5"]
    //   even_numbers       → ["2", "4", "92"]
    //   alphabets          → ["A", "Y", "B"]
    //   special_characters → ["&", "-", "*"]
    //   sum                → "103"
    //   concat_string      → "ByA"

    @Test
    void testExampleB() {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertEquals(List.of("5"),             res.getOddNumbers());
        assertEquals(List.of("2", "4", "92"),  res.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"),   res.getAlphabets());
        assertEquals(List.of("&", "-", "*"),   res.getSpecialCharacters());
        assertEquals("103",                    res.getSum());
        assertEquals("ByA",                    res.getConcatString());
    }

    // ── Example C ──────────────────────────────────────────────────────────
    // Input:  ["A", "ABCD", "DOE"]
    // Expected:
    //   odd_numbers        → []
    //   even_numbers       → []
    //   alphabets          → ["A", "ABCD", "DOE"]
    //   special_characters → []
    //   sum                → "0"
    //   concat_string      → "EoDdCbAa"

    @Test
    void testExampleC() {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList("A", "ABCD", "DOE"));

        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), res.getAlphabets());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0",         res.getSum());
        assertEquals("EoDdCbAa",  res.getConcatString());
    }

    // ── Edge Case: Empty array ──────────────────────────────────────────────
    @Test
    void testEmptyArray() {
        BfhlRequest req = new BfhlRequest();
        req.setData(List.of());

        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertTrue(res.getAlphabets().isEmpty());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("",  res.getConcatString());
    }

    // ── is_success should be true on valid request ─────────────────────────
    @Test
    void testIsSuccessTrue() {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList("1", "2", "a"));

        BfhlResponse res = service.processData(req);
        assertTrue(res.isSuccess());
    }
}
