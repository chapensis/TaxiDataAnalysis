package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class RevenueEfficiencyBean {
    private int unit_id;

    private int operatetime;

    private int waitingtime;

    private int money;

    private int trip;
}
