package com.spring.test.chapter6.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document
@Data
public class MongoRole implements Serializable {
    private Long id;
    @Field("role_name")
    private String roleName = null;
    private String note = null;
}
