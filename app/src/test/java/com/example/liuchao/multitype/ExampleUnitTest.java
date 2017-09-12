package com.example.liuchao.multitype;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    /**
     * 字符串时间转换
     *
     * @param timeStr
     * @return
     */
    public String formatTime(String timeStr) {
        StringBuffer formatStr = new StringBuffer();
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.US);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(timeStr));
            formatStr.append(calendar.get(Calendar.YEAR)).append("-")                               //年
                    .append(String.format("%02d", calendar.get(Calendar.MONTH) + 1)).append("-")     //月
                    .append(String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))).append(" ")  //日
                    .append(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))).append(":")   //时
                    .append(String.format("%02d", calendar.get(Calendar.MINUTE))).append(":")        //分
                    .append(String.format("%02d", calendar.get(Calendar.SECOND)));                   //秒
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr.toString();
    }

    /**
     * 字符串时间转换
     *
     * @param timeStr
     * @return
     */
    public String format(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.US);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format2.format(format.parse(timeStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    @Test
    public void test() {
        System.err.println(formatTime("Jun 28, 2017 4:28:20 PM"));
        System.err.println(format("Jun 28, 2017 4:28:20 PM"));
        System.err.println("x:" + inter());
    }

    public int inter() {
        int x = 0;
        try {
            x++;
            return x;
        } catch (Exception e) {

        } finally {
            ++x;
            System.err.println("x:" + x);
        }
        return x;
    }

    @Test
    public void testStatic() {
//        for (int i = 0; i < 5; i++) {
        final int common = 4;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("common:" + common);
                    Thread.currentThread().sleep(10);
                    print(common);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.err.println("=====:" + common);
                }
            }
        }).start();
        new Thread() {
            @Override
            public void run() {
                System.err.println("common:" + common);
                for (int i = 0; i < 100000; i++) {
                    System.err.print("print:" + i);
                }
                print(common);
            }
        }.start();
//        }
    }


    public void print(int num) {
        if ((2 | 1) == 5) {
        }
        System.err.println("threadId:" + Thread.currentThread().getId() + " num:" + (num + 1));
    }

    @Test
    public void month() {
        Calendar c = Calendar.getInstance();
        c.set(2017, 4, 0); //输入类型为int类型
        System.err.print("days:" + c.get(Calendar.DAY_OF_MONTH));
    }


    @Test
    public void weekTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        int week = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.err.print("共几周：" + week);
    }

    @Test
    public void main() {
        Base.Sub b = new Base.Sub();
        int a = 0;
        if (++a >= 1 && a++ >= 2) {
            a += 2;
        }
        System.err.print("result:" + a);
    }

}

class Base {
    private String a = "1";

    public Base() {
        call();
    }

    public void call() {
        System.out.println(a);
    }

    public static class Sub extends Base {
        public String a = "2";

        @Override
        public void call() {
            System.out.println(a);
        }
    }

}