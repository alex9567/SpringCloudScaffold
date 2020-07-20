package com.chen.core.piplineHandler;

public interface Pipeline {

    Pipeline fireOne();

    Pipeline fireTwo();

    Pipeline fireThree();

    Pipeline fireAfterCompletion();
}
