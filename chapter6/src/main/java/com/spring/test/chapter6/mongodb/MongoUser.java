package com.spring.test.chapter6.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document
@Data
public class MongoUser implements Serializable {

    @Id
    private Long id;

    @Field("user_name")
    private String userName = null;

    private String note = null;

    private List<MongoRole> roleList = null;
}
