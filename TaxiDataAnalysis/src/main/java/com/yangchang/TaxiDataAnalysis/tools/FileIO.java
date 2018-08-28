package com.yangchang.TaxiDataAnalysis.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.yangchang.TaxiDataAnalysis.bean.RoadEmptyTripBean;

public class FileIO {

    /**
     * 向文件中写入一个数字
     *
     * @param number    要写入的数字
     * @param writePath 文件路径
     * @param append    是否以追加的形式写入
     * @throws Exception
     */
    public static void writeOneStringToFile(String number, String writePath, boolean append)
            throws Exception {
        FileWriter fw = new FileWriter(writePath, append);
        fw.write(number);
        fw.flush();
        fw.close();
    }

    /**
     * 根据传入的二维int型数组写到path中去
     * @param array array
     * @param  writePath writePath
     * @throws Exception
     */
    public static void writeMultiArrayOfInteger(int[][] array, String writePath)
            throws Exception {
        FileWriter fw = new FileWriter(writePath);
        for (int i = 0; i < array.length; i++) // array.length 表示这个二维数组有多少行
        {
            for (int j = 0; j < array[i].length; j++) // array[i].length[i].length表示第i行有多少列
            {
                fw.write(array[i][j] + " ");
            }
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }

    /**
     * 根据传入的数组和要写到的path方法
     * @param array array
     * @param  writePath writePath
     * @throws Exception
     */
    public static void writeFileOfArrayOnlyForMatlab(float[][] array,
                                                     String writePath) throws Exception {
        FileWriter fw = new FileWriter(writePath);
        for (int i = array.length - 1; i >= 0; i--) // array.length 表示这个二维数组有多少行
        {
            for (int j = 0; j < array[i].length; j++) // array[i].length[i].length表示第i行有多少列
            {
                fw.write(MathUtil.getDecimalFormat((array[j][i]), 4) + " ");
            }
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }

    /**
     * 写String数组，追加，不换行-- 写一个String就换一行
     * @param writeArray 要写入的数组
     * @param writePath  文件路径
     * @param append     是否追加
     * @throws Exception
     */
    public static void writeArrayToFile(String[] writeArray,
                                        String writePath, boolean append) throws Exception {
        FileWriter fw = new FileWriter(writePath, append);
        for (int i = 0; i < writeArray.length; i++) {
            fw.write(writeArray[i]);
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }


    /**
     * 写一个集合到文件中，不追加，不换行-- 写一个对象就换一行
     * @param writeList
     * @param writePath
     * @throws Exception
     */
    public static void writeListToFile(ArrayList writeList,
                                       String writePath, boolean append) throws Exception {
        FileWriter fw = new FileWriter(writePath, append);
        for (Object s : writeList) {
            fw.write(s.toString());
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }

    /**
     * 写String数组，要根据指定参数换行，不追加
     * @param writeList writeList
     * @param writePath writePath
     * @param changeLine changeLine
     * @throws Exception
     */
    public static void writeArrayListOfStringLine(ArrayList<String> writeList,
                                                  String writePath, int changeLine) throws Exception {
        FileWriter fw = new FileWriter(writePath);
        int jishuqi = 0;
        for (String s : writeList) {
            if (jishuqi % changeLine == 0 && jishuqi != 0) {
                fw.write("\r\n");
            }
            jishuqi++;
            fw.write(s + " ");
            fw.flush();
        }
        fw.close();
    }

    /**
     * 根据传入的数组和要写到的path方法 SD:single-dimensional
     * @param array array
     * @param writePath writePath
     * @param changeline changeline
     * @throws Exception
     */
    public static void writeFileOfSDArray(int[] array, String writePath,
                                          int changeline) throws Exception {
        FileWriter fw = new FileWriter(writePath);
        for (int i = 0; i < array.length; i++) // array.length 表示这个二维数组有多少行
        {
            if (i % changeline == 0 && i != 0)
                fw.write("\r\n");
            fw.write(array[i] + " ");
            fw.flush();
        }
        fw.close();
    }

    /**
     * 读取一个文件,每行都是一个string数组，存到一个String数组的list集合中
     * @param readPath readPath
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> bufferedReaderAnArrayString(
            String readPath) throws IOException {
        ArrayList<String[]> listArrayList = new ArrayList<>();
        File file = new File(readPath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        while ((temp = br.readLine()) != null) {
            if ("".equals(temp))
                continue;
            String[] anArrayString = temp.split(" ");
            listArrayList.add(anArrayString);
        }
        br.close();
        return listArrayList;
    }

    /**
     * 读取一行int型文件
     * @param readPath
     * @return
     * @throws IOException
     */
    public static int[] bufferedReaderAnArrayInt(String readPath)
            throws IOException {
        File file = new File(readPath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        // FileReader fReader = new FileReader(file); //打开一个通道
        // char [] buffer = new char[10]; //定义一个容纳10个数的篮子
        // fReader.read(buffer); //每次从通道中拿10个数放到篮子中
        BufferedReader br = new BufferedReader(new FileReader(file)); // 有了bufferedRead,给我一个通道，每次给你读一行
        String temp = null;
        temp = br.readLine();
        String[] anArrayString = null;
        if (temp != null && !"".equals(temp)) {
            anArrayString = temp.split(" ");
        }
        br.close();
        if (anArrayString == null || anArrayString.length <= 0)
            return null;
        int[] returnArray = new int[anArrayString.length];
        for (int i = 0; i < anArrayString.length; i++) {
            if (anArrayString[i] != null)
                returnArray[i] = Integer.parseInt(anArrayString[i]);
        }
        return returnArray;
    }

    /**
     * 读取一行float型文件
     * @param readPath
     * @return
     * @throws IOException
     */
    public static float[] bufferedReaderAnArrayFloat(String readPath)
            throws IOException {
        File file = new File(readPath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        temp = br.readLine();
        String[] anArrayString = null;
        if (temp != null && !"".equals(temp)) {
            anArrayString = temp.split(" ");
        }
        br.close();
        if (anArrayString == null || anArrayString.length <= 0)
            return null;
        float[] returnArray = new float[anArrayString.length];
        for (int i = 0; i < anArrayString.length; i++) {
            if (anArrayString[i] != null)
                returnArray[i] = Float.parseFloat(anArrayString[i]);
        }
        return returnArray;
    }

    /**
     * 按行读取一个文件，一行不拆开，适用于行只有一个数据，或者一行多个数不拆开
     *
     * @param readPath
     * @return
     */
    public static ArrayList<String> readFileByLine(String readPath) {
        try {
            File file = new File(readPath);
            if (!file.exists() || file.isDirectory()) {
                throw new FileNotFoundException();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> anArrayString = new ArrayList<String>();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                if ("".equals(temp))
                    continue;
                anArrayString.add(temp);
            }
            br.close();
            return anArrayString;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * 按行读取一个文件，每行多个字符，多个字符要拆开
     *
     * @param readPath
     * @return
     * @throws IOException
     */
    public static ArrayList<String> readFileByLineSplit(String readPath)
            throws IOException {
        File file = new File(readPath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("没有该文件或者路径" + readPath);
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] anArrayString;
        ArrayList<String> returnString = new ArrayList<>();
        String temp;
        while ((temp = br.readLine()) != null) {
            if ("".equals(temp))
                continue;
            anArrayString = temp.split(" ");
            for (int i = 0; i < anArrayString.length; i++) {
                if (!"".equals(anArrayString[i])) {
                    returnString.add(anArrayString[i]);
                }
            }
        }
        br.close();
        return returnString;
    }

    /**
     * 写下车-上车的中间路段和时间的函数
     *
     * @param listWriteEmptyTripBeans
     */
    public static void writelistWriteEmptyTripBeans(
            ArrayList<RoadEmptyTripBean> listWriteEmptyTripBeans, String path)
            throws Exception {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        FileWriter fw = new FileWriter(path, true);
        for (RoadEmptyTripBean r : listWriteEmptyTripBeans) {
            int unit_id = r.getUnit_id();
            int bid = r.getBid();
            int offroad = r.getOffroad();
            Date offtime = r.getOfftime();
            // String offtimeString = sdf.format(offtime);
            String result = unit_id + "_" + bid + "_" + offroad + "_" + offtime;
            fw.write(result);
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }
}
