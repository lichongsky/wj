package com.lichong.wj.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
public class DateUtil {

    public enum DateType {
        YEAR, MONTH,WEEK, DAY, HH, MI, SS
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static final String STRING_DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String CN_DATETIME_FORMAT_TWO = "yyyy/MM/dd HH:mm:ss";

    public static final String CN_SPRIT_DATE_FORMAT_DAY = "yyyy/MM/dd";

    public static final String CN_SPRIT_DATE_FORMAT_DAY_TRANSPOSE  = "dd/MM/yyyy";

    public static final String CN_DATE_FORMAT_MONTH_TRANSPOSE  = "yyyy-MM";

    public static final List<String> MONTH_31DAY = Arrays.asList("1","3","5","7","8","10","12");

    public static final List<String> MONTH_30DAY = Arrays.asList("4","6","9","11");
    public static final int MAX_MONTH_DAY = 31;
    public static final int MEDIUM_MONTH_DAY = 30;
    public static final int LEAP_MONTH_DAY = 29;
    public static final int COMMON_MONTH_DAY = 28;

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm:ss"<br />
     *
     * @return
     */
    public static String getUTCTimeStr() {
        return ZonedDateTime.now(Clock.systemUTC()).toLocalDateTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT));

    }

    /**
     * 获取UTC日期，类型为字符串。格式为：yyyy-MM-dd
     *
     * @return
     */
    public static String getUTCDateStr() {
        return ZonedDateTime.now(Clock.systemUTC()).toLocalDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * 获取当前时间(UTC)
     * 
     * @return
     */
    public static Date getUtcTime() {
        String utcTimeStr = DateUtil.getUTCTimeStr();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        try {
            return sdf.parse(utcTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UTC时间(只包含年月日)
     * 
     * @return
     */
    public static LocalDate getUtcDate() {
        return LocalDateTime.now(Clock.systemUTC()).toLocalDate();
    }

    /**
     * UTC时间(时分秒)
     * 
     * @return
     */
    public static LocalDateTime getUtcDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    /**
     * 获取一周后的时间
     * 
     * @return
     */
    public static Long getWeekTimeMilli() {
        LocalDateTime nextWeek = LocalDateTime.now().plus(1, ChronoUnit.WEEKS);
        return nextWeek.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取一月后的时间
     * 
     * @return
     */
    public static Long getMonthTimeMilli() {
        LocalDateTime nextWeek = LocalDateTime.now().plus(1, ChronoUnit.MONTHS);
        return nextWeek.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将java date按照平台配置文件配置的日期字符格式化为字符串
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_FORMAT);
    }

    /**
     * 将java date按照平台配置文件配置的日期字符格式化为字符串
     * 
     * @param date
     * @return
     */
    public static String datetimeToString(Date date) {
        return dateToString(date, TIME_FORMAT);
    }

    /**
     * 将java date按照传入的日期字符格式化为字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        String target = null;
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        target = simpleDateFormat.format(date);
        return target;
    }


    /**
     * 时间戳转日期
     * 
     * @param ms
     * @return
     */
    public static Date transForDate(Long timeStamp) {
        Date date = new Date(Long.parseLong(String.valueOf(timeStamp)));
        // SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        // String sd = sdf.format(date); // 时间戳转换成时间
        return date;
    }

    /**
     * 获得某天最大时间
     * 
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间
     * 
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时区
     * 
     * @return
     */
    public static String getTimeZone() {
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.ZONE_OFFSET);
        cal.add(Calendar.MILLISECOND, -offset);
        Long timeStampUTC = cal.getTimeInMillis();
        Long timeStamp = System.currentTimeMillis();
        Long timeZone = (timeStamp - timeStampUTC) / (1000 * 3600);
        System.out.println(timeZone.intValue());
        return String.valueOf(timeZone);

    }

    /**
     * 获取当天过期秒数
     * 
     * @return
     */
    public static long getCurrentDayExpireSecond() {
        LocalDateTime currentTime = LocalDateTime.now();
        Long nowMilli = currentTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        LocalDateTime endTimOfDay = currentTime.with(LocalTime.MAX);
        Long endMilli = endTimOfDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        long expireSecond = (endMilli - nowMilli) / 1000;
        return expireSecond;
    }

    /**
     * 获取昨天的时间
     * 
     * @param timeFormat
     * @return
     */
    public static String getYesterdayByFormat(String timeFormat) {
        return LocalDateTime.now().plusDays(-1).format(DateTimeFormatter.ofPattern(timeFormat));
    }


    /**
     * 日期相减
     */
    public static Calendar dateCalculate(Date date, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal;
    }

    /**
     * 获取北京时间
     * 
     * @return
     */
    public static Date getBeiJingTime() {
        String dateStr = getDateByTimeZone(8);
        Date date = null;
        try {
            // 此处不实例对象,返回的值有问题
            SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdfNew.parse(dateStr);
        } catch (ParseException e) {
            log.error("时间转换异常,{}", e);
        }
        return date;
    }

    /**
     * 获取指定时区的时间
     * 
     * @param timeZoneOffset 时区序号
     * @return
     */
    public static String getDateByTimeZone(float timeZoneOffset) {
        // 时区序号
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        int newTime = (int) (timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }

    /**
     * 获取北京时间当天0点转UTC格式
     * 
     * @return
     */
    public static Date getBeijingCurrentDateToUtc() {
        // 获取北京时间
        Date beiJingDate = DateUtil.getBeiJingTime();
        // 获取日期部分
        Date day = DateUtil.getStartOfDay(beiJingDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // 减去8个小时
        calendar.add(Calendar.HOUR, -8);
        // 获取当前UTC时间及日期+16:00:00
        return calendar.getTime();
    }

    /**
     * 获取当前时间到当天最大时间的差(北京时间)--秒
     * 
     * @return
     */
    public static long getTimeDiff() {
        // 当前北京时间
        Date now = getBeiJingTime();
        // 当天的最大时间
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(now.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        Date end = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        // 时间差(秒)
        long millis = (end.getTime() - now.getTime()) / 1000;
        return millis;
    }

    /**
     * 去除时间后缀.0
     * 
     * @param dateStr 2018-11-16 11:25:54.0
     * @return
     */
    public static String getTimeFormat(String dateStr) {
        if (null == dateStr) {
            return dateStr;
        }
        return dateStr.substring(0, dateStr.lastIndexOf("."));
    }
    /**
     * 获取两个时间节点间的差值
     * @param startDate
     * @param endDate
     * @param dateType
     * @return
     */
    public static int getDvalue(Date startDate,Date endDate,DateType dateType){
        DateTime startDt = new DateTime(startDate);
        DateTime endDt = new DateTime(endDate);
        if (DateType.YEAR.equals(dateType)) {
            return Years.yearsBetween(startDt, endDt).getYears();
        }
        if (DateType.MONTH.equals(dateType)) {
            return Months.monthsBetween(startDt, endDt).getMonths();
        }
        if (DateType.WEEK.equals(dateType)) {
            return Weeks.weeksBetween(startDt, endDt).getWeeks();
        }
        if (DateType.DAY.equals(dateType)) {
            return Days.daysBetween(startDt, endDt).getDays();
        }
        if (DateType.HH.equals(dateType)) {
            return Hours.hoursBetween(startDt, endDt).getHours();
        }
        if (DateType.MI.equals(dateType)) {
            return Minutes.minutesBetween(startDt, endDt).getMinutes();
        }
        if (DateType.SS.equals(dateType)) {
            return Minutes.minutesBetween(startDt, endDt).getMinutes();
        }
        return Integer.MAX_VALUE;
    }
    /**
     * 目前只兼容年月日
     * @param dateStr
     * yyyy/MM/dd
     * dd/MM/yyyy
     * @return
     */
    public static Date str2Date(String dateStr){
        Date result = null;
        //替换中文
        dateStr = dateStr.replaceAll("[\\u4e00-\\u9fa5]","/").
                replaceAll("-","/");
        if(dateStr.matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")){
            //yyyy/MM/dd HH:mm:ss
            result = str2Date(dateStr, CN_DATETIME_FORMAT_TWO);
        }else if(dateStr.matches("(\\d{4}/\\d{2}/\\d{2}).*")) {
            //yyyy/MM/dd
            dateStr = dateStr.replaceAll("(\\d{4}/\\d{2}/\\d{2}).*","$1");
            result = str2Date(dateStr, CN_SPRIT_DATE_FORMAT_DAY);
        }else if(dateStr.matches("(\\d{2}/\\d{2}/\\d{4}).*")){
            //dd/MM/yyyy
            dateStr = dateStr.replaceAll("(\\d{2}/\\d{2}/\\d{4}).*","$1");
            result = str2Date(dateStr,CN_SPRIT_DATE_FORMAT_DAY_TRANSPOSE);
        }else if(dateStr.matches("(\\d{4}\\d{2}\\d{2}).*")){
            //yyyyMMdd
            dateStr = dateStr.replaceAll("(\\d{4}\\d{2}\\d{2}).*","$1");
            result = str2Date(dateStr,CN_SPRIT_DATE_FORMAT_DAY_TRANSPOSE);
        }
        return result;
    }
    /**
     * 字符串转换成时间类型
     * @param dateStr 需要转换的日期字符串
     * @param pattern 格式
     * @return
     */
    public static Date str2Date(String dateStr,String pattern){
        org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(dateStr, format);
        return dateTime.toDate();
    }

    /**
     * 日期加运算
     * @param date 需要加的日期
     * @param dateType
     *            需要加的类型<br>
     *            数值可以为年份，DateType.YEAR<br>
     *            数值可以为月份，DateType.MONTH<br>
     *            数值可以为周，DateType.WEEK<br>
     *            数值可以为天数，DateType.DAY<br>
     *            数值可以为小时，DateType.HH<br>
     *            数值可以为分钟，DateType.MI<br>
     *            数值可以为秒，DateType.SS
     * @param num 需要加的数值
     * @return 加完之后的日期
     */
    public static Date addDate(Date date, DateType dateType, int num){
        DateTime dt = new DateTime(date);
        if (DateType.YEAR.equals(dateType)) {
            return dt.plusYears(num).toDate();
        }
        if (DateType.MONTH.equals(dateType)) {
            return dt.plusMonths(num).toDate();
        }
        if (DateType.WEEK.equals(dateType)) {
            return dt.plusWeeks(num).toDate();
        }
        if (DateType.DAY.equals(dateType)) {
            return dt.plusDays(num).toDate();
        }
        if (DateType.HH.equals(dateType)) {
            return dt.plusHours(num).toDate();
        }
        if (DateType.MI.equals(dateType)) {
            return dt.plusMinutes(num).toDate();
        }
        if (DateType.SS.equals(dateType)) {
            return dt.plusSeconds(num).toDate();
        }
        return null;
    }


    public static void main(String[] args) throws ParseException {
        Date date1 = DateUtil.addDate(str2Date("2018-01","yyyy-MM"), DateType.MONTH, 1);
        String string = dateToString(date1,DATE_FORMAT);
        String string1 = dateToString(date1,CN_DATE_FORMAT_MONTH_TRANSPOSE);
        int dvalue = getDvalue(str2Date("2018-05-29"), str2Date("2018-07-01"), DateType.MONTH);
        System.out.println();

        getTimeDiff();
        System.out.println(".....");
        System.out.println(getDateByTimeZone(0));
        System.out.println(getBeiJingTime());
        System.out.println(getDateByTimeZone(8));

        long now = System.currentTimeMillis();
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        // long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
        // //当前毫秒数
        // System.out.println(now);
        // //当前时间 距离当天凌晨 秒数
        // System.out.println(overTime);
        // //当天凌晨毫秒数
        // System.out.println(sdfOne.parse(sdfOne.format(now)).getTime());
        // //当天凌晨日期
        // SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println(sdfTwo.format(sdfOne.parse(sdfOne.format(now)).getTime()));

        // 获取秒数
        // Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        // 获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);

        Date date = new Date();
        System.out.println("今天开始时间：" + getStartOfDay(date));
        System.out.println("今天结束时间：" + getEndOfDay(date));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(getStartOfDay(date)));
        System.out.println(sdf.format(getEndOfDay(date)));
        // 1530892799000
        // 1530892799999
        System.out.println(sdfOne.parse(sdf.format(getEndOfDay(date))).getTime());


        // 获取当天结束时间
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfDay = currentTime.with(LocalTime.MIN);
        LocalDateTime endOfDay = currentTime.with(LocalTime.MAX);
        Long startMilli = startOfDay.toInstant(ZoneOffset.of("+16")).toEpochMilli();
        Long endMilli = endOfDay.toInstant(ZoneOffset.of("+16")).toEpochMilli();
        System.out.println(startMilli);
        System.out.println(endMilli);
        System.out.println(transForDate(startMilli));
        System.out.println(transForDate(endMilli));

        long overTime = (endOfDay.toInstant(ZoneOffset.of("+8")).toEpochMilli() - milliSecond) / 1000;
        System.out.println(overTime);

        System.out.println(getCurrentDayExpireSecond());

        System.out.println(getYesterdayByFormat("yyyy-MM-dd"));

        Long endTime = DateUtil.getUtcTime().toInstant().toEpochMilli();

        System.out.println(DateUtil.getUtcTime());

        System.out.println();

        SimpleDateFormat sdf1 = new SimpleDateFormat(TIME_FORMAT);
        try {
            System.out.println(sdf.parse(ZonedDateTime.now(Clock.systemUTC()).with(LocalTime.MIN).toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern(TIME_FORMAT))).toInstant().toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
