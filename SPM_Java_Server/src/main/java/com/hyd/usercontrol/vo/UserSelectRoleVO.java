package com.hyd.usercontrol.vo;

import lombok.Data;

@Data
public class UserSelectRoleVO {
    public UserSelectRoleVO(String code,String name,String role)
    {
        roleCode=code;
        showName=name;
        roleCd=role;

    }

    public String roleCode;

    public String showName;

    public String roleCd;
}
