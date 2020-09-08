package com.ejoy.tool.scaffold.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author DCL
 * @date 2018/8/22 14:48
 * @des 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class TimeTools {
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMAT2DAY = "yyyy-MM-dd";
    public static final String FORMAT2DAY_XG = "yyyy/MM/dd";
    public static final String FORMAT_GLWZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMAT_MDHM = "MM月dd日 HH:mm";
    public static final String FORMAT_HM = "HH:mm";
    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat datetimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //xx月xx日 HH:mm
    public static final SimpleDateFormat MDHM = new SimpleDateFormat("MM月dd日 HH:mm");
    private static final SimpleDateFormat datetimeFormat_spe = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final SimpleDateFormat GMTSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat HMFormat = new SimpleDateFormat("HH:mm");

    //设置时间格式
    public static final SimpleDateFormat sdf1 = new SimpleDateFormat("y-M-d h:m:s a E");
    public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd hh:mm:ss a E");
    public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MMM-ddd hhh:mmm:sss a E");
    public static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyy-MMMM-dddd hhhh:mmmm:ssss a E");
    public static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss a E");
    public static final SimpleDateFormat sdf6 = new SimpleDateFormat("MM月dd日 HH:mm a E");
    public static final SimpleDateFormat sdf7 = new SimpleDateFormat("MM月dd日 HH:mm");
    public static final SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取星期
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * 字符串转时间
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 字符串转时间
     * @param str
     * @param format
     * @return
     */
    public static Date str2DateFormat(String str, SimpleDateFormat format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = format.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }


    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }


    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     *用当天的日期的long型字符数串减去昨天日期long型字符数串
     * 其中：
     *  86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
     * @return
     */
    public static String getYesterdayByDate(String format){
        //实例化当天的日期
        Date today = new Date();
        //用当天的日期减去昨天的日期
        Date yesterdayDate = new Date(today.getTime()-86400000L);
        String yesterday = new SimpleDateFormat(format).format(yesterdayDate);
        return yesterday;
    }


    /**
     * 获取当前时间前一周日期
     * @param format
     * @return
     */
    public static String getLatestWeekDate(SimpleDateFormat format){
        Calendar c = Calendar.getInstance();

        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取当前时间前一月日期
     * @param format
     * @return
     */
    public static String getLatestMonthDate(SimpleDateFormat format){
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }


    /**
     * 获取当前时间前三个月日期
     * @param format
     * @return
     */
    public static String getLatestThreeMonthDate(SimpleDateFormat format){
        Calendar c = Calendar.getInstance();
        //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        return mon3;
    }


    /**
     * 获取当前时间前一年日期
     * @param format
     * @return
     */
    public static String getLatestYearDate(SimpleDateFormat format){
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        return year;
    }




    /**
     * 格式到秒
     *
     * @param time
     * @return
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }


    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    /**
     * 格式到分
     *
     * @param time
     * @return
     */
    public static String getDayTime(long time,String format) {
        return new SimpleDateFormat(format).format(time);

    }

    /**
     * 格式化类似格林威治时间未标准格式
     *      到天 yyyy-MM-dd
     *
     * @param time
     * @return
     */
    private static String dayTime = "";
    public static String getGMT2Day(String time,SimpleDateFormat dateFormat) {
        Date date = null;
        try {
            date = GMTSimpleDateFormat.parse(time);
            //输出格式：2019-12-02 09:28:33
            dayTime = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayTime;
    }

    /**
     * 格式到毫秒
     *
     * @param time
     * @return
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr
     *            需要转换的字符串
     * @param formatStr
     *            需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException
     *             转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转化时间输入时间与当前时间的间隔
     *
     * @param timestamp
     * @return
     */
    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 把字符串转化为时间格式
     *
     * @param timestamp
     * @return
     */
    public static String getStandardTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        Date date = new Date(timestamp * 1000);
        sdf.format(date);
        return sdf.format(date);
    }

    /**
     * 获得当前日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm
     *
     * @return
     */
    public static String formatDateNoSecond(Date date) {
        return datetimeFormat1.format(date);
    }

    /**
     * 格式化日期时间 日期时间格式HH:mm
     *
     * @return
     */
    public static String formatHourMinuter(Date date) {
        return HMFormat.format(date);
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     *long类型
     * @return
     */
    public static long currentLongTime() {
        String fromDate = timeFormat.format(now());
        long time = 0;
        try {
            time = timeFormat.parse(fromDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     * string转换成long类型
     * @return
     */
    public static long String2LongTime(String timeStr,SimpleDateFormat format) {
        long time = 0;
        try {
//            time = timeFormat.parse(timeStr).getTime();
            time = format.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return datetimeFormat_spe.format(now());
    }
    public static String getCurrentDateStr1() {
        return datetimeFormat.format(now());
    }

//        /**
//         * 格式化时间 时间格式HH:mm:ss
//         *
//         * @return
//         */
//        public static String formatTime(Date date) {
//            return timeFormat.format(date);
//        }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     *
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     *
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate
     *            日期范围开始
     * @param endDate
     *            日期范围结束
     * @param src
     *            需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     *
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     *
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     *
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     *
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 把秒格式化为分种小时
     *
     * @param second
     * @return
     */
    public static String parseSecond(int second) {
        if (second >= 60) {
            return second / 60 + "分";
        } else if (second >= 60 * 60) {
            return second / 60 * 60 + "时";
        } else if (second >= 60 * 60 * 24) {
            return second / 60 * 60 + "天";
        } else {
            return second + "秒";
        }
    }


    /**
     * 比较时间大小
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            if (beginDate.getTime() < endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() > endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 比较时间大小
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end,SimpleDateFormat format) {
        try {
            Date beginDate = format.parse(begin);
            Date endDate = format.parse(end);
            if (beginDate.getTime() < endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() > endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得年份
     * @param date
     * @return
     */
    public int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月份
     * @param date
     * @return
     */
    public int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得星期几
     * @param date
     * @return
     */
    public int getWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得日期
     * @param date
     * @return
     */
    public int getDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 获得天数差
     * @param begin
     * @param end
     * @return
     */
    public long getDayDiff(Date begin, Date end){
        long day = 1;
        if(end.getTime() < begin.getTime()){
            day = -1;
        }else if(end.getTime() == begin.getTime()){
            day = 1;
        }else {
            day += (end.getTime() - begin.getTime())/(24 * 60 * 60 * 1000) ;
        }
        return day;
    }



    //Todo:-----------------------------

    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;

    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getLongDateToString(long date_data) {
        //时间戳转化为Sting或Date
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time= new Long(date_data);
        String d = format.format(time);

        return d;
    }

    /**
     * 时间戳转换成字符窜(年月日)
     */
    public static String getLongDateToString1(long date_data,SimpleDateFormat format) {
        //时间戳转化为Sting或Date
        Long time= new Long(date_data);
        String d = format.format(time);
        return d;
    }


    /**
     * 时间戳转换成期日期格式
     */
    public static Date getLongDateToDate(long date_data) throws ParseException {
        //时间戳转化为Sting或Date
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time= new Long(date_data);
        String d = format.format(time);
        Date date_1 = format.parse(d);

        return  date_1;
    }


    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 时间格式HH:mm:ss'T' JJ
     *
     * @return
     */
    public static String formatDateWithT(String date) {
        return datetimeFormat_spe.format(date);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 两个时间相差距离多少秒
     * @param str1 时间参数 1 格式：12:00:00
     * @param str2 时间参数 2 格式：12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static long[] getDistanceTimeSeconds(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
//        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 时间转换
     *      String标准时间转换成Date格式时间
     *      提交时格林威治时间
     */
    private static StringBuffer stringBuffer;
    public static Date StringToDateGLWZ(String dateTimeStr){
        if (dateTimeStr.contains("/")) {
            //endTimeStr.replaceAll("/","-");
            String[] split = dateTimeStr.split("\\/");
            stringBuffer = new StringBuffer();
            stringBuffer.append(split[0]+"-");
            if (split[1].length() < 2){
                stringBuffer.append("0"+split[1]+"-");
            }else {
                stringBuffer.append(split[1]+"-");
            }
            if (split[2].length() < 11){
                stringBuffer.append("0"+split[2]);
            }else {
                stringBuffer.append(split[2]);
            }
            String s = stringBuffer.toString();
            return parseStrDate(s);
        }else {
            return parseStrDate(dateTimeStr);
        }
    }

    private static Date parseStrDate(String s) {
        Date date = null;
        if (!TextUtils.isEmpty(s)) {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            try {
                date = sdf.parse(s);
            } catch (Exception e) {
                Log.e("tag", "日期解析出错！", e);
            }
            return date;
        }else {
            throw new NullPointerException("dateTimeStr不能为空");
        }
    }

    /**
     * 解析标准时间格式的String类型时间
     * @param s
     * @param format
     * @return
     */
    public static String parseMultiDate(String s, SimpleDateFormat format) {
        Date date2 = str2Date(s);
        String date = null;
        if (!TextUtils.isEmpty(s)) {
            try {
                date = format.format(date2.getTime());
            } catch (Exception e) {
                Log.e("tag", "日期解析出错！", e);
            }
            return date;
        }else {
            throw new NullPointerException("dateTimeStr不能为空");
        }
    }

    public static boolean compareBetweenLongDate(String dateFrom, String dateTo, SimpleDateFormat format, int isForward){
        long l = String2LongTime(dateFrom, format);
        long l1 = TimeTools.String2LongTime(dateTo, format);
        if (isForward == 0){
            if (l1 >= l){
                return true;
            }else {
                return false;
            }
        }else if (isForward == 1){
            if (l1 > l){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
//        String trim = etArriveStartTime.getText().toString().trim();
//        if (!TextUtils.isEmpty(trim)){
//            long l = TimeTools.String2LongTime(trim, TimeTools.HMFormat);
//            etArriveEndllTime.setText(TimeTools.formatHourMinuter(date));
//            String trim1 = etArriveEndllTime.getText().toString().trim();
//            long l1 = TimeTools.String2LongTime(trim1, TimeTools.HMFormat);
//            ILog.e(TAG,"到达时间-开始:"+l);
//            ILog.e(TAG,"到达时间-结束:"+l1);
//            if (l1 > l) {
//                etArriveEndllTime.setText(TimeTools.formatHourMinuter(date));
//            }else {
//                etArriveEndllTime.setText(null);
//                SnackbarUtil.LongSnackbar(rlFilterRoot,"结束时间不能早于/等于开始时间", R.color.white,R.color.red);
//            }
//        }
    }
}