package com.chen.core.piplineHandler3;


import java.util.List;

public class Test2Chain {

    private List<Component> componentList;

    Test2Chain(List<Component> componentList) {
        this.componentList = componentList;
    }

    public void doExecute(Context context) {
        for(Component component:componentList){
            component.execute(context);
        }
    }
}
