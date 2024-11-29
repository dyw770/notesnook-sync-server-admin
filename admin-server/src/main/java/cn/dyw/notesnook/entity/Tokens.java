package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Tokens {

    @Field(value = "LoginProvider")
    private String loginProvider;

    @Field(value = "UserId")
    private String userId;

    @Field(value = "Name")
    private String name;

    @Field(value = "Value")
    private String value;
}