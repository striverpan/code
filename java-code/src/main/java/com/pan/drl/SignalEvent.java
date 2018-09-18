package com.pan.drl;

import org.kie.api.definition.type.Role;

/**
 * Created by pan on 2017/12/1.
 */

@Role(Role.Type.EVENT)
public class SignalEvent {

    private int s1;

    public SignalEvent(int s1) {
        this.s1 = s1;
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }
}
