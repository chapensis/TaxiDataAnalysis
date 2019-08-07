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
    public static final int workAndWeek = 1;

    /**
     * 工作日
     */
    public static final int workday = 2;

    /**
     * 休息日
     */
    public static final int weekend = 3;


    /**
     * 得到平时或周末的字符串
     *
     * @param week
     * @return
     */
    public static String getWeekendOrWorkday(int week) {
        String weekendOrworkday = "";
        // 全周
        if (week == workAndWeek)
        {
            weekendOrworkday = "workAndWeek";
        } else if (week == workday)
        {
            // 工作日
            weekendOrworkday = "workday";
        } else if (week == weekend)
        {
            // 休息日
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
        // 全周
        if (week == workAndWeek)
        {
            totalDay = 366;
        } else if (week == workday)
        {
            // 工作日
            totalDay = 262;
        } else if (week == weekend)
        {
            // 休息日
            totalDay = 104;
        }
        return totalDay;
    }

}
