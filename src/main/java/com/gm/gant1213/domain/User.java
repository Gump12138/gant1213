package com.gm.gant1213.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "uid")
    private Long id;    // 用户名
    private String username;    // 密码
    private String password;    // 姓名
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
