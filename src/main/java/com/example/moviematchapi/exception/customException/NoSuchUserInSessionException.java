package com.example.moviematchapi.exception.customException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoSuchUserInSessionException extends RuntimeException{
    private String message;
}
