package com.example.restfullwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Integer id;


    private String description;


    // User : Post  -> 1 : (0 :N), Main : Sub -> Parent -> Child
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;







}
