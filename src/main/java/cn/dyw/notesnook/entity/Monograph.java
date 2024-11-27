package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "monographs")
public class Monograph {
    @Id
    public String id;

    @Field(value = "CompressedContent")
    public Object compressedContent;

    @Field(value = "EncryptedContent")
    public String encryptedContent;

    @Field(value = "SelfDestruct")
    public Boolean selfDestruct;

    @Field(value = "UserId")
    public String userId;

    @Field(value = "DatePublished")
    public Long datePublished;

    @Field(value = "Title")
    public String title;

}