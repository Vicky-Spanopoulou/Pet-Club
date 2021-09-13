package cb13.project.enumeration;

import static cb13.project.constant.AuthorityConstant.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_DOCTOR(DOCTOR_AUTHORITIES),
    ROLE_ADVERTISED(ADVERTISED_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES );

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
    
    public static Role[] getAllRoles(){
        return Role.values();
    }
}
