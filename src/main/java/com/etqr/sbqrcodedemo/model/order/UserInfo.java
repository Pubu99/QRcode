package com.etqr.sbqrcodedemo.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private String userId;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String nic;
    private String phone;
    private String country;
    private String city;
    private String password;
    private String username;
    private String role;
}
