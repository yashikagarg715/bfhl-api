package com.chitkara.bfhl.service;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BfhlServiceImpl implements BfhlService {


    private static final String FULL_NAME   = "yashika_garg";
    private static final String DOB         = "14022005";
    private static final String EMAIL       = "yashika1165.be23@chitkara.edu.in";
    private static final String ROLL_NUMBER = "2310991165";


    @Override
    public BfhlResponse processData(BfhlRequest request) {

        List<String> data = request.getData();

        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sumValue = 0;


        for (String item : data) {

            if (isNumber(item)) {
                // It's a numeric string
                long num = Long.parseLong(item);
                sumValue += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);   // keep original string as-is
                } else {
                    oddNumbers.add(item);
                }

            } else if (isAlphabet(item)) {
                // All characters in this token are letters → uppercase the whole token
                alphabets.add(item.toUpperCase());

            } else {
                // Anything else is a special character / mixed token
                specialCharacters.add(item);
            }
        }



        StringBuilder lettersCollected = new StringBuilder();
        for (String item : data) {
            for (char c : item.toCharArray()) {
                if (Character.isLetter(c)) {
                    lettersCollected.append(c);
                }
            }
        }

        String reversed = lettersCollected.reverse().toString();
        String concatString = applyAlternatingCaps(reversed);


        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(FULL_NAME + "_" + DOB)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sumValue))
                .concatString(concatString)
                .build();
    }



    /**
     * Returns true if the entire token represents an integer (positive or negative).
     */
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if every character in the token is a letter (a-z / A-Z).
     * Handles multi-character tokens like "ABCD" or "DOE".
     */
    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Alternating caps: index 0 → uppercase, 1 → lowercase, 2 → uppercase, …
     * Works on the already-reversed string.
     */
    private String applyAlternatingCaps(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return sb.toString();
    }
}
