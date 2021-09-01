package com.example.basic.extend;

public class Son<T extends ISon> extends Father<T>  {

    public Son(T iFather) {
        super(iFather);
    }

    public void iSonMethod() {
        t.iSonMethod();
    }


}
