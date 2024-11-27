package cn.dyw.notesnook.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(value = "users")
public class User {
    @Id
    private String id;

    @Field(value = "AccessFailedCount")
    private Integer accessFailedCount;

    @Field(value = "ConcurrencyStamp")
    private String concurrencyStamp;

    @Field(value = "Email")
    private String email;

    @Field(value = "LockoutEnabled")
    private Boolean lockoutEnabled;

    @Field(value = "NormalizedEmail")
    private String normalizedEmail;

    @Field(value = "NormalizedUserName")
    private String normalizedUserName;

    @Field(value = "UserName")
    private String userName;

    @Field(value = "EmailConfirmed")
    private Boolean emailConfirmed;

    @Field(value = "LockoutEnd")
    private String lockoutEnd;

    @Field(value = "PasswordHash")
    private String passwordHash;

    @Field(value = "PhoneNumber")
    private String phoneNumber;

    @Field(value = "PhoneNumberConfirmed")
    private Boolean phoneNumberConfirmed;

    @Field(value = "Roles")
    private List<String> roles;

    @Field(value = "SecurityStamp")
    private String securityStamp;

    @Field(value = "TwoFactorEnabled")
    private Boolean twoFactorEnabled;

    @Field(value = "Claims")
    private List<Claims> claims;

    @Field(value = "Tokens")
    private List<Tokens> tokens;

    @Field(value = "Logins")
    private List<Logins> logins;

}