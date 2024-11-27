package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "settingsv2")
public class Settingsv2 {
    @Id
    public String id;

    @Field(value = "Algorithm")
    public String algorithm;

    @Field(value = "Cipher")
    public String cipher;

    @Field(value = "DateSynced")
    public Long dateSynced;

    @Field(value = "IV")
    public String iv;

    @Field(value = "ItemId")
    public String itemId;

    @Field(value = "Length")
    public Long length;

    @Field(value = "UserId")
    public String userId;

    @Field(value = "Version")
    public Double version;
}