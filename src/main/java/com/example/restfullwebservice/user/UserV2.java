package com.example.restfullwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo2")
public class UserV2 extends User{

    private String grade;


}
