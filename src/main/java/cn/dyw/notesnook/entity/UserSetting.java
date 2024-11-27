package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "user_settings")
public class UserSetting {
    
    @Id
    public String id;

    @Field(value = "LastSynced")
    public Long lastSynced;

    @Field(value = "Salt")
    public String salt;

    @Field(value = "UserId")
    public String userId;

    @Field(value = "VaultKey")
    public String vaultKey;

    @Field(value = "AttachmentsKey")
    public AttachmentsKey attachmentsKey;
    
}