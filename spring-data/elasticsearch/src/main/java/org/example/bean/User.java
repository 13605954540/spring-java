package org.example.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Accessors(chain = true)
@Document(indexName = "sys_user")
public class User extends BaseEntity<User> {

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text)
    private String hobby;
}
