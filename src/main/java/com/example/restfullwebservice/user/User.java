package com.example.restfullwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"password" })
//@JsonFilter("UserInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of="id")
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Table(name = "TBL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(min=2, message = "Name 은 2글자 이상 입력해 주세요.")
    @Schema(description = "사용자 이름을 입력해 주세요.")
    private String name;



    //과거데이터만 올수 있는 제약 조건
    @Past
    @Schema(description = "사용자 등록일을 입력해 주세요.")
    private Date joinDate;

//  @JsonIgnore
    @Schema(description = "사용자 패스워드를  입력해 주세요.")
    private String password;

  //  @JsonIgnore
   @Schema(description = "사용자 주민번호를   입력해 주세요.")
    private String ssn;


   @OneToMany(mappedBy = "user")
   private List<Post> posts;



    @Builder
    public User(Integer id, String name, Date joinDate, String password, String ssn){
        this.id=id;
        this.name=name;
        this.joinDate=joinDate;
        this.password=password;
        this.ssn=ssn;
    }



}
