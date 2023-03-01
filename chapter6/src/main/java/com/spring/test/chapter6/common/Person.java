package com.spring.test.chapter6.common;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long id;
    private String phone;

}
