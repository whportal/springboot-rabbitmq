package com.wwh.es.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author wangwenhao
 * @description Student
 * @date 2020-06-01 10:07
 */
@Data
@Accessors(chain = true)
@Document(indexName = "demo1",type = "_doc")
public class Student implements Serializable {

    private static final long serialVersionUID = -73912617768713135L;

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String nickName;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    @Field(type = FieldType.Integer)
    private Integer age;
}
