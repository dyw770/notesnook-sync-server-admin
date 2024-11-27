package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@Data
@Document(value = "notes")
public class Notes {

    @Id
    private String id;

    @Field("Cipher")
    private String cipher;

    @Field("DateSynced")
    private String dateSynced;

    @Field("IV")
    private String iv;

    @Field("ItemId")
    private String itemId;

    @Field("Length")
    private String length;

    @Field("UserId")
    private String userId;

    @Field("Version")
    private String version;

    @Field("Algorithm")
    private String algorithm;
}
