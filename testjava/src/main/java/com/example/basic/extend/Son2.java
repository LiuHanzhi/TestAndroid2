package com.example.basic.extend;

public class Son2 extends Son<ISon2> {

    public Son2(ISon2 iFather) {
        super(iFather);
    }

    public void iSon2Method() {
        t.iSon2Method();
    }

}
