package com.yangchang.TaxiDataAnalysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yangchang
 */
@RestController
@RequestMapping(value = "/taxidataanalysis/position")
public class PositionController {
    @GetMapping("hello")
    public String helllo() {
        return "hello";
    }
}
