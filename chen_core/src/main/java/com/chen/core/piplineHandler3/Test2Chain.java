package com.chen.core.piplineHandler3;


import java.util.List;

public class Test2Chain {

    private List<TestComponent> testComponentList;

    Test2Chain(List<TestComponent> testComponentList) {
        this.testComponentList = testComponentList;
    }

    public void doExecute(Context context) {
        for(TestComponent testComponent : testComponentList){
            testComponent.execute(context);
        }
    }
}
