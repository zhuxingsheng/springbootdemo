package com.jack.event;

import lombok.Data;

/**
 * @description: event wrapper
 * @author: Jack
 * @create: 2019-08-04 19:37
 */
@Data
public class DemoEventWrapper {

    private Runnable runnable;

    public DemoEventWrapper(Runnable runnable){
        this.runnable = runnable;
    }
}
