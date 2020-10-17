package com.chen.core.piplineHandler3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThreeTestComponent implements TestComponent {
    @Override
    public void execute(Context context) {
        log.info("three");
    }
}
