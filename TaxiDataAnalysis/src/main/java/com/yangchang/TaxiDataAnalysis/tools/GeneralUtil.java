package com.yangchang.TaxiDataAnalysis.tools;

import com.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;

import java.util.List;

public class GeneralUtil {
    public static double baiduLon = 112.869256;
    public static double baiduLat = 28.056261;

    public static final int roadnum = 1050;
    public static final int totalDay = 366;

    /**
     * 所有時間
     */
    public static final int workAndWeek = 1; //所有時間

    public static final int workday = 2; //工作日
    public static final int weekend = 3; //休息日

    /**
     * 得到大变小的钥匙
     *
     * @param timeslot
     * @return
     * @throws Exception
     */
    public static int[] getBigToSmallKey(int timeslot) throws Exception {
        String readBigToSmallKey = "F:\\MDPGrid\\compress\\bigtosmall" + timeslot + ".txt";
        int[] bigToSmallKey = FileIO.bufferedReaderAnArrayInt(readBigToSmallKey);//得到大变小的钥匙
        return bigToSmallKey;
    }

    /**
     * 得到小变大的钥匙
     *
     * @param timeslot
     * @return
     * @throws Exception
     */
    public static int[] getSmallToBigKey(int timeslot) throws Exception {
        String readSmallToBigKey = "F:\\MDPGrid\\compress\\smalltobig" + timeslot + ".txt";
        int[] smallToBigArray = FileIO.bufferedReaderAnArrayInt(readSmallToBigKey);   //得到小变大的钥匙，但是是从1开始的
        return smallToBigArray;
    }

    /**
     * 得到平时或周末的字符串
     *
     * @param week
     * @return
     */
    public static String getWeekendOrWorkday(int week) {
        String weekendOrworkday = "";
        if (week == workAndWeek) // 全周
        {
            weekendOrworkday = "workAndWeek";
        } else if (week == workday) // 工作日
        {
            weekendOrworkday = "workday";
        } else if (week == weekend)// 休息日
        {
            weekendOrworkday = "weekend";
        }
        return weekendOrworkday;
    }

    /**
     * 根据平时还是周末，获得一年中有效的天数
     *
     * @param week
     * @return
     */
    public static int getTotalDayByWeek(int week) {
        int totalDay = 0;
        if (week == workAndWeek) // 全周
        {
            totalDay = 366;
        } else if (week == workday) // 工作日
        {
            totalDay = 262;
        } else if (week == weekend)// 休息日
        {
            totalDay = 104;
        }
        return totalDay;
    }

    /**
     * 成功获取数据
     * @param data
     * @return
     */
    public static ResultVO success(List data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMessage("获取数据成功");
        return resultVO;
    }

    /**
     * 成功获取数据
     * @param data
     * @param message
     * @return
     */
    public static ResultVO success(List data, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMessage(message);
        return resultVO;
    }
}
