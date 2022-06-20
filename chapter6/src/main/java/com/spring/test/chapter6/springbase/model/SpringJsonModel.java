package com.spring.test.chapter6.springbase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author andgo
 * @date 2022/6/20
 *
 */
@Data
public class SpringJsonModel {
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date date;
     private LocalDateTime localDateTime;
     private LocalDate localDate;
}
