package com.tao.model;

public class Role {
    private Integer id;

    private String roleName;

    private String roleCode;

    private String descip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getDescip() {
        return descip;
    }

    public void setDescip(String descip) {
        this.descip = descip == null ? null : descip.trim();
    }
}