package com.co.hsg.innventa.backing.types;

/**
 *
 * @author hectsaga
 */
public enum SexType {
    MALE("M"),FEMALE("F");
    
    private String value;
    
    SexType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static SexType getType(String sexo) {
        for(SexType s :SexType.values()){
            if(s.getValue().equals(sexo)){
                return s;
            }
        }
        return null;
    }
}
