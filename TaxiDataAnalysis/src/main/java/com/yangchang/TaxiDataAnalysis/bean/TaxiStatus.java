package com.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

import java.util.LinkedList;

/**
 * 出租车状态信息
 */
@Data
public class TaxiStatus {
    private int bid;
    private int daytime;
    private int unit_id;
    private int status; //״̬��1���ؿͣ�0:�ճ�
    private int destination; //Ŀ�ĵص�·�α��
    private int x; //Ŀ�ĵص�·�α��x
    private int y; //Ŀ�ĵص�·�α��y
    private int time;  //ʣ�������ʱ�䣬1��ʣ��ʱ�䵽��Ŀ�ĵأ�2��ʣ��ʱ�������Ҳ����˿͵�·
    private int vertex; //���㣬Ѱ�͵�ʱ��ר��
    private int lastseekingtime; //�ҵ��˿ͺ���ʵ����һ��ʣ��Ĳ���ʱ����Ҫ����
    private int lastseekingroad; //�ҵ��˿ͺ���ʵ����һ��ʣ��Ĳ���·����Ҫ����
    private int isOccupied; //���⳵�ǿճ������ؿ�״̬ 1���ؿ� 2���ճ�
    private float tempSeekingtime; //��ʱ��seekingtime��ֻ�е�driving time��Чʱ���Ż��tempSeekingTime��ӽ�ȥ
    private float seekingtime; //һ�����⳵�ܵĲ���ʱ��
    private float drivingtime; //һ�����⳵�ܵ���Ӫʱ��
    private float drivingmoney;//һ�����⳵�ܵ�׬��Ǯ
    private LinkedList<Integer> recMNPRoadList; //ר�Ÿ�MNP�õ�һ��list

    public TaxiStatus() {
        this.recMNPRoadList = new LinkedList<Integer>();
    }

    public TaxiStatus(int status, int destination, int time, int vertex) {
        this.status = status;
        this.destination = destination;
        this.time = time;
        this.vertex = vertex;
        this.seekingtime = 0;
        this.drivingtime = 0;
        this.drivingmoney = 0;
        this.recMNPRoadList = new LinkedList<Integer>();
    }
}
