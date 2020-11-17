package com.mobo.xddemo.controller;

import com.mobo.xddemo.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mobo
 * @create 2020-11-16-14:37
 */
@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

    @GetMapping("save")
    public JsonData saveOrder(){
        return JsonData.buildSuccess("下单成功");
    }
}
