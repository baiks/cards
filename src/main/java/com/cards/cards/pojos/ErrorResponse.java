package com.cards.cards.pojos;

import lombok.*;


/**
 * This class holds a list of {@code ErrorModel} that describe the error raised on rejected validation
 */

@Value
public class ErrorResponse {
    String code;
    String message;

}
