package pers.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class RoadEmptyTripBean {

    private int unit_id;
    private int bid;
    private int offroad;
    private int offx;
    private int offy;
    private Date offtime;
    private int onroad;
    private int onx;
    private int ony;
    private Date ontime;
    private int dropoffminu;
    private int onminu;
    private int s_offroad;
    private int s_offx;
    private int s_offy;
    private int num;
    private int daytime;


    public RoadEmptyTripBean() {

    }

    public RoadEmptyTripBean(int unit_id, int bid, int offroad, Date offtime, int onroad, Date ontime) {
        this.unit_id = unit_id;
        this.bid = bid;
        this.offroad = offroad;
        this.offtime = offtime;
        this.onroad = onroad;
        this.ontime = ontime;
    }
}
