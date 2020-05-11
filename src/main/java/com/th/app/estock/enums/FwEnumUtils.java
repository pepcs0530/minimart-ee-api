package com.th.app.estock.enums;

public enum FwEnumUtils {
	ONE("1"),  //calls constructor with value 3
    TWO("2"),  //calls constructor with value 2
    TREE("3"),   //calls constructor with value 1
    
	SUCCES_CODE_200("200"),
	SUCCES_DETAIL_200("SUCCESS"),
	
	SUCCES_CODE_300("300"),
	SUCCES_DETAIL_300("SUCCESS"),
	
	ERROR_CODE_500("500"),
	ERROR_DETAIL_500("ERROR"),
	
	; // semicolon needed when fields / methods follow

    private final String value;
    
    private FwEnumUtils(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
