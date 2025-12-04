enum UserRole {
    ADMIN("管理员", "拥有所有操作权限：增删改查"),
    USER("普通用户", "拥有查询和修改个人信息权限"),
    GUEST("游客", "仅拥有查询公开信息权限");

    private final String roleName;
    private final String permission;

    UserRole(String roleName, String permission) {
        this.roleName = roleName;
        this.permission = permission;
    }

    public void showPermission() {
        System.out.println("角色：" + roleName + "，权限：" + permission);
    }
}

public class EnumPermission {
    public static void main(String[] args) {
        UserRole[] roles = UserRole.values();
        for (UserRole role : roles) {
            role.showPermission();
        }
    }
}
