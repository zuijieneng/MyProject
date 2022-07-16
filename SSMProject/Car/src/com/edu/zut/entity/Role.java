package com.edu.zut.entity;

import java.util.List;

public class Role {
    private String roleId;

    private String roleName;

    private String roleDescribe;

    private List<Operation> operation;

    public List<Operation> getOperation() {
        return operation;
    }

    public void setOperation(List<Operation> operation) {
        this.operation = operation;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe == null ? null : roleDescribe.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDescribe='" + roleDescribe + '\'' +
                ", operation=" + operation +
                '}';
    }
}