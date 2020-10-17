package com.chen.core.piplineHandler3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwoComponent implements Component{
    @Override
    public void execute(Context context) {
        log.info("two");
    }
}
