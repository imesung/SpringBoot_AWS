package com.mesung.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloReponseDto {

    private final String name;
    private final int amount;
}
