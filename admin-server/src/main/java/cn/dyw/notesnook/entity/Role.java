package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(value = "roles")
public class Role {
    @Id
    private String id;

    @Field(value = "ConcurrencyStamp")
    private String concurrencyStamp;

    @Field(value = "Name")
    private String name;

    @Field(value = "NormalizedName")
    private String normalizedName;

    @Field(value = "Claims")
    private List<Claims> Claims;
}