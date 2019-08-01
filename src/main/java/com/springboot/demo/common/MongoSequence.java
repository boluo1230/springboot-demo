package com.springboot.demo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class MongoSequence {
    @Id
    private String id;
    private int seq;
}