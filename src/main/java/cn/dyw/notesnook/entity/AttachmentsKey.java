package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class AttachmentsKey {
    
    @Field(value = "cipher")
    public String cipher;

    @Field(value = "iv")
    public String iv;

    @Field(value = "length")
    public Long length;

    @Field(value = "salt")
    public String salt;
}