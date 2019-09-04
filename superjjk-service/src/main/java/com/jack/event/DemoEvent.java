package com.jack.event;

import lombok.Data;

/**
 *
 * 事件
 */
@Data
public class DemoEvent  {


    public DemoEvent(String data){
        this.eventData = data;
    }

    private String eventData;
}
