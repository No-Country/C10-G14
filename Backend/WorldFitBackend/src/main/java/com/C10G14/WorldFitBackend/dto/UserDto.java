package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String clientSince;
    private List<Role> roles;
    private String profilePicture;
    private String weight;
    private String height;
    private String sex;
    private String age;
}
