package com.lilac.soaback.global.util;
import org.springframework.validation.BindingResult;

public class ValidationUtils {

    public static void validateBindingResult(BindingResult bindingResult, RuntimeException exception) {
        if (bindingResult.hasErrors()) {
            throw exception;
        }
    }
}
