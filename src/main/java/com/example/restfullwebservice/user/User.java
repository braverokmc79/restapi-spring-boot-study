package com.example.restfullwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(value = {"password" })
//@JsonFilter("UserInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of="id")
public class User {

    private Integer id;

    @Size(min=2, message = "Name 은 2글자 이상 입력해 주세요.")
    private String name;

    //과거데이터만 올수 있는 제약 조건
    @Past
    private Date joinDate;

//  @JsonIgnore
    private String password;

  //  @JsonIgnore
    private String ssn;


    @Builder
    public User(Integer id, String name, Date joinDate, String password, String ssn){
        this.id=id;
        this.name=name;
        this.joinDate=joinDate;
        this.password=password;
        this.ssn=ssn;
    }



}
