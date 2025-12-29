package org.xu.admin.common;

public class UserContext {
    private static final ThreadLocal<Integer> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<Integer> currentUserRole = new ThreadLocal<>();

    public static void setUserId(Integer id) {
        currentUserId.set(id);
    }

    public static Integer getUserId() {
        return currentUserId.get();
    }

    public static void setUserRole(Integer admin) {
        currentUserRole.set(admin);
    }

    public static Integer getUserRole() {
        return currentUserRole.get();
    }

    public static void clear() {
        currentUserId.remove();
        currentUserRole.remove();
    }
}