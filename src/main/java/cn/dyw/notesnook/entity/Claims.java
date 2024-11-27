package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Claims {

    @Field(value = "ClaimType")
    private String claimType;

    @Field(value = "ClaimValue")
    private String claimValue;

    @Field(value = "UserId")
    private String userId;

    @Field(value = "Value")
    private String value;
}