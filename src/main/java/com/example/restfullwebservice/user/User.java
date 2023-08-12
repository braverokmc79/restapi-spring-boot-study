package com.example.restfullwebservice.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min=2)
    private String name;

    //과거데이터만 올수 있는 제약 조건
    @Past
    private Date joinDate;





}
