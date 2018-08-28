package com.yangchang.TaxiDataAnalysis.tools;

import com.yangchang.TaxiDataAnalysis.bean.CoordinateBean;
import com.yangchang.TaxiDataAnalysis.bean.Direction;

import java.text.DecimalFormat;
import java.util.*;

public class MathUtil {

    /**
     * 返回保留指定位数的double
     *
     * @param d    double型数字
     * @param last
     * @return
     */
    public static double getDecimalFormat(double d, int last) throws Exception {
        if (last < 0) {
            throw new Exception("传入的保留位数的参数不能为负数");
        } else if (d == 0) {
            return d;
        } else if (last == 0) {
            return Math.round(d);
        } else {
            DecimalFormat decimalFormat = getDecimalFormat(last);
            String p = decimalFormat.format(d);// format 返回的是字符串
            return Double.parseDouble(p);
        }
    }

    /**
     * 返回保留指定位数的float
     *
     * @param s    字符串数字
     * @param last 要保留的位数
     * @return float
     * @throws Exception
     */
    public static float getDecimalFormat(String s, int last) throws Exception {
        float f = Float.parseFloat(s);
        return getDecimalFormat(f, last);
    }

    /**
     * 返回保留指定位数的String
     *
     * @param f    float型数字
     * @param last 要保留的位数
     * @return float
     */
    public static float getDecimalFormat(float f, int last) throws Exception {
        if (last < 0) {
            throw new Exception("传入的保留位数的参数不能为负数");
        } else if (f == 0) {
            return f;
        } else if (last == 0) {
            return Math.round(f);
        } else {
            DecimalFormat decimalFormat = getDecimalFormat(last);
            String p = decimalFormat.format(f);// format 返回的是字符串
            return Float.parseFloat(p);
        }
    }

    /**
     * 根据保留的位数，返回DecimalFormat
     *
     * @param last 保留的位数
     * @return DecimalFormat
     */
    private static DecimalFormat getDecimalFormat(int last) {
        StringBuilder pattern = new StringBuilder(".");
        for (int i = 0; i < last; i++) {
            pattern.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        return decimalFormat;
    }

    /**
     * 获得ArrayListFloat的平均值
     *
     * @param listFloat
     * @return
     */
    public static float getAvgByListFloat(ArrayList<Float> listFloat)
            throws Exception {
        if (listFloat.size() == 0)
            return 0;
        float total = 0;
        for (float f : listFloat) {
            total = total + f;
        }
        return getDecimalFormat(total / listFloat.size(), 4);
    }

    /**
     * 返回一个String中的最大的值
     *
     * @param listArrayStrings
     * @return
     */
    public static int getMaxByString(String listStrings) {
        int max = 0;
        String[] listArrayStrings = listStrings.split(" ");
        for (String s : listArrayStrings) {
            if (!"".equals(s)) {
                int sInt = Integer.parseInt(s);
                if (sInt > max)
                    max = sInt;
            }
        }
        return max;
    }


    /**
     * 返回一个String中的最大的值
     *
     * @param listArrayStrings
     * @return
     */
    public static int getMaxNumOfStringArray(String[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("array should not be null");
        }
        int max = getIntByString(array[0]);
        for (int i = 1; i < array.length; i++) {
            int sInt = getIntByString(array[i]);
            if (sInt > max)
                max = sInt;
        }
        return max;
    }

    /**
     * 一个string字符转换成int
     *
     * @param s
     * @return
     */
    private static int getIntByString(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    /**
     * 返回一个ArrayList<String>中的最大的值
     *
     * @param list list
     * @return
     */
    public static int getMaxNumOfStringList(ArrayList<String> list) {
        if (list == null || list.size() == 0) {
            throw new RuntimeException("list should not be null");
        }
        int max = getIntByString(list.get(0));
        for (String s : list) {
            int sInt = getIntByString(s);
            if (sInt > max)
                max = sInt;
        }
        return max;
    }

    /**
     * 获得一个int数组中的最大值
     *
     * @param array
     * @return
     */
    public static int getMaxNumOfIntArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("array should not be null");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    /**
     * 对一个map进行排序
     *
     * @param path
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Map.Entry<Integer, CoordinateBean>> sortMapOfCoordinateBean(
            Map m) {
        List<Map.Entry<Integer, CoordinateBean>> list = // 得到一个排好序的list
                new ArrayList<Map.Entry<Integer, CoordinateBean>>(m.entrySet());
        // 升序
        Collections.sort(list, Comparator.comparing(Map.Entry::getKey));
        return list;
    }

    /**
     * 对一个Direction进行排序,给我一个没有排序的list.
     * 我返回一个排好序的reward给你,按照reward降序排列
     *
     * @param list
     * @throws Exception
     */
    public static ArrayList<Direction> sortDirections(ArrayList<Direction> list) {
        Collections.sort(list, (d1, d2) -> {
            Float float1 = new Float(d1.getReward());
            Float float2 = new Float(d2.getReward());
            // 降序
            return (float2).compareTo(float1);
        });
        return list;
    }

    /**
     * 自己写的排序方法，对于一个int型的list集合返回升序排列的结果
     *
     * @param list
     */
    public static void mySortListOfInteger(ArrayList<Integer> list) {
        Comparator<Integer> comparator = Integer::compareTo;
//        Comparator<Integer> comparator = (s1, s2) -> {
//            return s1.compareTo(s2);
//        };
        Collections.sort(list, comparator);
    }

    /**
     * 自己写的排序方法,
     * 对一个ArrayList<Integer>的arraylist升序排列
     *
     * @param list
     */
    public static void mySortListOfIntegerList(ArrayList<ArrayList<Integer>> list) {
        Comparator<ArrayList<Integer>> comparator = (s1, s2) -> {
            if (s1.get(0) < s2.get(0)) // 第一个数比大小，如果小于则不再比 >是降序 <是升序
                return -1;
            else if (s1.get(0).equals(s2.get(0))) // 如果等于，则再比较一下第二个数
            {
                if (s1.get(1) < s2.get(1)) // >是降序 <是升序
                    return -1;
                else {
                    return 1;
                }
            } else { // 如果大于也不再比较
                return 1;
            }
        };
        Collections.sort(list, comparator);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }
}
