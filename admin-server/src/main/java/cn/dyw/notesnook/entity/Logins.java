package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Logins {

    @Field(value = "LoginProvider")
    private String loginProvider;

    @Field(value = "ProviderKey")
    private String providerKey;

    @Field(value = "ProviderDisplayName")
    private String providerDisplayName;

    @Field(value = "UserId")
    private String userId;
}