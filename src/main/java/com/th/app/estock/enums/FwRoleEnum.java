package com.th.app.estock.enums;

import org.springframework.security.core.GrantedAuthority;

public enum FwRoleEnum implements GrantedAuthority {
	ROLE_ADMIN, ROLE_CLIENT;

	  public String getAuthority() {
	    return name();
	  }
}
