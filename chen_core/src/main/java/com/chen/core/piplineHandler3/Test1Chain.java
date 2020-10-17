package com.chen.core.piplineHandler3;


import java.util.List;

public class Test1Chain {

    private List<TestComponent> testComponentList;

    Test1Chain(List<TestComponent> testComponentList) {
        this.testComponentList = testComponentList;
    }

    public void doExecute(Context context) {
        for(TestComponent testComponent : testComponentList){
            testComponent.execute(context);
        }
    }
}
