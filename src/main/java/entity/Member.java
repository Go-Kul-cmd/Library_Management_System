package com.example.library_management_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="members")
public class Member {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @Email @NotBlank @Column(unique=true,nullable=false) private String email;
    private String phone;
    public Member() {}
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
}