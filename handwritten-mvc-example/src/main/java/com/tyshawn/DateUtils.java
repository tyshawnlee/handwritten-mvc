package com.tyshawn;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author litianxiang
 */
public class DateUtils {

    private static Log logs = LogFactory.getLog(DateUtils.class);

    //private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    /**
     * 格式： yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式： yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_S = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 格式： yyyyMMddHHmmss
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    /**
     * 格式： yyyyMMddHHmmssSSS
     */
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    /**
     * 格式：yyyy-MM-dd
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 格式：yyyyMM
     */
    public static final String YM = "yyyyMM";

    public static final String MD = "MM-dd";

    /**
     * 格式：yyyyMMdd
     */
    public static final String YMD = "yyyyMMdd";
    /**
     * 格式： yyyyMMddHH
     */
    private static final String YMDH = "yyyyMMddHH";
    /**
     * 格式： yyyy-MM-dd HH:mm
     */
    public static final String yyyMMddHHmm= "yyyy-MM-dd HH:mm";
    /**
     * 格式： yyyy-MM-ddTHH:mm
     */
    public static final String YMDTHM = "yyyy-MM-dd'T'HH:mm";
    /**
     * 格式： yyyy-MM-ddTHH:mm:ss
     */
    public static final String YMDTHMS = "yyyy-MM-dd'T'HH:mm:ss";
    /**
     * 格式： yyyy-MM-dd HH:mm:ss
     */
    public static final String yyyyMMdd= "yyyy-MM-dd";

    public static final String yyyyMMddHH= "yyyy-MM-dd HH";

    public static final String yyyyMMddHHmm= "yyyy-MM-dd HH:mm";

    public static final String HH = "HH";

    public static final String HHmm = "HH:mm";

    public static final String HHmmss = "HHmmss";


    /**
     * 格式： yyyy-MM-dd
     */
    public static final SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat(yyyyMMdd);
    public static final SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat(YMD);
    public static final SimpleDateFormat DATE_FORMAT_YM = new SimpleDateFormat(YM);
    public static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat(DATETIME);


    public static final long MINUTE_MILLISECONDS =  60 * 1000;

    public static final long FIVE_MINUTE_MILLISECONDS =  5 * 60 * 1000;

    public static final long TEN_MINUTE_MILLISECONDS = 10 * 60 * 1000;


    public static final long HOUR_MILLISECONDS =  60 * 60 * 1000;
    public static final long DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    public static final long THREEDAY_MILLISECONDS =  3 * DAY_MILLISECONDS;
    public static final long WEEK_MILLISECONDS = 7 * DAY_MILLISECONDS;
    public static final long MONTH_MILLISECONDS = 31 * DAY_MILLISECONDS;
    public static final long YEAR_MILLISECONDS = 365 * DAY_MILLISECONDS;
    public static final long DAY_SECONDS = 24 * 60 * 60;
    public static final long WEEK_SECONDS = 7 * DAY_SECONDS;

    /**
     * 获取两个日期之间相差的天数(计算的是两个时间点的时间差（以天为单位，舍弃尾数）)
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDayRange(Date startDate, Date endDate){
        return (int) (endDate.getTime()/DAY_MILLISECONDS-startDate.getTime()/DAY_MILLISECONDS);
    }
    /**
     * 获取两个日期之间日期 (时分秒以开始时间为准)
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getDays(Date startDate, Date endDate){
        List<Date> days = Lists.newArrayListWithExpectedSize(DateUtils.getDayRange(startDate, endDate)+1);
        endDate = DateUtils.getEndTime(endDate);
        for(Date day=startDate; !day.after(endDate); day=DateUtils.addDay(day, 1)){
            days.add(day);
        }
        return days;
    }

    public static List<String> getDaysByDayWithFormat(Date start,Date end,String format){
        List<Date> days = getDays(start,end);
        final DateFormat formater = new SimpleDateFormat(format);
        return Lists.transform(days, new Function<Date, String>() {
            @Override
            public String apply(Date input) {
                return formater.format(input);
            }
        });
    }

    public static List<String> getDaysByDayWithFormat(List<Date> days,String format){
        final DateFormat formater = new SimpleDateFormat(format);
        return Lists.transform(days, new Function<Date, String>() {
            @Override
            public String apply(Date input) {
                return formater.format(input);
            }
        });
    }

    /**
     * 获取日期中最大的日期
     * @param dateArgs
     * @return
     */
    public static Date getMaxDate(Date ... dateArgs){
        Long dateTime = Long.MIN_VALUE;
        for(Date date : dateArgs){
            if(date.getTime() > dateTime){
                dateTime = date.getTime();
            }
        }
        return new Date(dateTime);
    }

    /**
     * 获取日期中最小日期
     * @param dateArgs
     * @return
     */
    public static Date getMinDate(Date ... dateArgs){
        Long dateTime = Long.MAX_VALUE;
        for(Date date: dateArgs){
            if(date.getTime() < dateTime){
                dateTime = date.getTime();
            }
        }
        return new Date(dateTime);
    }

    /*	*//**
     * 获取该时间段的0分0秒
     * @param
     * @return
     *//*
	public static Date getDateTimeZero(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}*/

    public static List<Date> getMonths(Date startDate, Date endDate){
        if(startDate==null || endDate==null || startDate.after(endDate)){
            throw new RuntimeException("参数错误，statDate=="+startDate+" endDate=="+endDate);
        }
        List<Date> monthDateList = Lists.newArrayList();
        Date month = DateUtils.getMonthStartDate(startDate);
        while(month.before(endDate) || month.equals(endDate)){
            monthDateList.add(month);
            month = org.apache.commons.lang3.time.DateUtils.addMonths(month, 1);
        }
        return monthDateList;
    }



    public static Map<String,Object> putDateInListByMinute(Date start,Date end){

        Map<String,Object> map = Maps.newLinkedHashMap();
        while (start.getTime() <= end.getTime()){
            String date = DateUtils.formatDate(start,yyyyMMddHHmm);
            map.put(date,null);
            start = new Date(start.getTime() + MINUTE_MILLISECONDS);
        }
        map.put(DateUtils.formatDate(end,yyyyMMddHHmm),null);
        return map;
    }


    public static Map<String,Object> putDateInListByHour(Date start,Date end){
        Map<String,Object> map = Maps.newLinkedHashMap();
        while (start.getTime() <= end.getTime()){
            map.put(DateUtils.formatDate(start,yyyyMMddHH),null);
            start = new Date(start.getTime() + HOUR_MILLISECONDS);
            System.out.println(DateUtils.formatDate(start,yyyMMddHHmm));
        }
        map.put(DateUtils.formatDate(end,yyyyMMddHH),null);
        return map;
    }

    public static Map<String,Object> putDateInListByDay(Date start,Date end){
        Map<String,Object> map = Maps.newLinkedHashMap();
        while (start.getTime() <= end.getTime()){
            map.put(DateUtils.formatDate(start,yyyyMMdd),null);
            start = new Date(start.getTime() + DAY_MILLISECONDS);
        }
        map.put(DateUtils.formatDate(end,yyyyMMdd),null);
        return map;
    }


    /**
     * 两个时间相差多少天
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    public static boolean isSameDay(Date date1,Date date2){
        return formatDate(date1).equals(formatDate(date2));
    }

    public static Date getDate(Date date, int diffDay) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, diffDay);
        return c.getTime();
    }

    public static Date getDate(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取昨天0点的时间
     * @return
     */
    public static Date getYesterStartTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定天数前0点的时间
     * @return
     */
    public static Date getStartTimeAgo(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定天数前23点59分59秒的时间
     * @return
     */
    public static Date getEndTimeAgo(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取昨天23点59分59秒的时间
     * @return
     */
    public static Date getYesterdayEndTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    public static Date getYesterdayTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static int getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getOnlyMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取指定时间+/-day天的开始时间
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    public static Date getDayEndTime(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定时间七天前的开始时间
     * @param date
     * @return
     */
    public static Date getBefore7DayStartTime(Date date){
        return getDayStartTime(date, -7);
    }

    public static Date getBefore7DayEndTime(Date date){
        return getDayEndTime(date, -7);
    }

    /**
     * 获取指定时间前days天的开始时间(2016-08-04 12:30:00 days=-1 则表示 2016-08-03 00:00:00)
     * @param date
     * @param days
     * @return
     */
    public static Date getBeforeDayStartTime(Date date,int days){
        return getDayStartTime(date, days);
    }

    public static Date getBeforeDayEndTime(Date date,int days){
        return getDayEndTime(date, days);
    }
    /**
     * 获取指定时间30天前的开始时间
     * @param date
     * @return
     */
    public static Date getBefore30DayStartTime(Date date){
        return getDayStartTime(date, -30);
    }

    /**
     * 获取指定时间180天前的开始时间
     * @param date
     * @return
     */
    public static Date getBefore180DayStartTime(Date date){
        return getDayStartTime(date, -180);
    }

    /**
     * 获取格林尼治时间的开始时间
     * @return
     */
    public static Date getUTCStartTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 判断日期是否是月份开头
     * @param date
     * @return
     */
    public static boolean isMonthStartDate(Date date){
        return getMonthStartDate(date).equals(date);
    }

    /**
     * 获取指定日期所在月份的开始时间
     * @param date
     * @return
     */
    public static Date getMonthStartDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    public static Date getMonthStartDate(Date date,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        setDayStartTime(calendar);
        return calendar.getTime();
    }


    /**
     * 获取指定日期所在月份的结束时间
     * @param date
     * @return
     */
    public static Date getMonthEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在周的开始时间
     * @param date
     * @return
     */
    public static Date getWeekStartDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在周的结束时间
     * @param date
     * @return
     */
    public static Date getWeekEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    /**
     * 根据时间获取当前 星期
     * @param time
     * @return
     */
    public static int getTodayWeek(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(weekIndex == 0 ){
            weekIndex = 7;
        }
        return weekIndex;
    }


    /**
     * 设置为一天的最后结束时间，23:59:59:999
     * @param calendar
     */
    public static void setDayEndTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    /**
     * 将时间置为当天0点
     * @param calendar
     */
    public static void setDayStartTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获取指定日期的开始时间(0点)
     * @param day
     * @return
     */
    public static Date getStartTime(Date day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        setDayStartTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取小时的开始时间
     * @param day
     * @return
     */
    public static Date getStartHourTime(Date day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取今天的开始时间
     * @return
     */
    public static Date getStartTime(){
        return getStartTime(new Date());
    }

    /**
     * 获取指定日期的结束时间，23:59:59:999
     * @param day
     * @return
     */
    public static Date getEndTime(Date day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        setDayEndTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取今天的结束时间
     * @return
     */
    public static Date getEndTime(){
        return getEndTime(new Date());
    }

    /**
     * 按指定格式将字符串转为日期
     * @param dateStr
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateStr,String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(dateStr);
    }


    public static long getUTCDate(Date dt){
        return dt.getTime() + Calendar.getInstance().get(Calendar.ZONE_OFFSET);
    }

    public static long getTime(Date dt){
        if(dt != null){
            return dt.getTime() ;
        }
        return 0L;
    }

    /**
     * 按指定格式将字符串转为日期
     * @param date
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date,String formatStr) {
        if(date == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }


    /**
     * 转换yyyy-MM-dd格式的日期
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr) throws ParseException {
        return new SimpleDateFormat(DATE).parse(dateStr);
    }

    public static Date getDateUnsafe(String dateStr) {
        try {
            return new SimpleDateFormat(DATE).parse(dateStr);
        } catch (ParseException e) {
            logs.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 转换yyyy-MM-dd格式的日期
     * @param dateStr
     * @return
     */
    public static Date getDateQuietly(String dateStr){
        try {
            return new SimpleDateFormat(DATE).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 按yyyy-MM-dd 返回字符串
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE).format(date);
    }

    /**
     * 按yyyy-MM-dd HH:mm:ss返回字符串
     * @param date
     * @return
     */
    public static String formatDateAndTime(Date date) {
        if(null != date) {
            return new SimpleDateFormat(DATETIME).format(date);
        }
        return "";
    }

    /**
     * 按yyyyMM格式返回  如：201407
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String result = new SimpleDateFormat(YM).format(cal.getTime());
        return Integer.parseInt(result);
    }

    public static String getLastMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
        String time = format.format(c.getTime());
        return time;
    }

    /**
     * 返回每个月的第一天   如：20140701
     * @param date
     * @return
     */
    public static int getFristDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, days);
        return getIntDate(cal.getTime());
    }

    /**
     * 返回每个月的第一天   如：20140701
     * @param date
     * @return
     */
    public static Calendar getFristDateOfMonthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, days);
        return cal;
    }

    /**
     * 返回上个月的第一天 如：20140601
     * @param date
     * @return
     */
    public static int getFristDateOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.MONTH, -1);
        return getIntDate(cal.getTime());
    }

    /**
     * 返回第一个星期的第一天
     */
    public static int getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return getIntDate(c.getTime());

    }

    /**
     * 返回上一个星期的第一天
     */
    public static int getFirstDayOfPreWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
        return getFirstDayOfWeek(c.getTime());
    }

    /**
     * 返回最后一星期的第一天
     */
    public static int getFirstDayOfWeek_lastWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.add(Calendar.DAY_OF_MONTH, -7);
        return getIntDate(c.getTime());
    }

    /**
     * 返回昨天
     */
    public static int getYesterday() {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -1);
        return getIntDate(c.getTime());
    }

    /**
     * @return  今天
     */
    public static int getToday() {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        return getIntDate(c.getTime());
    }

    public static int getIntDate(Date date) {
        return Integer.parseInt(new SimpleDateFormat(YMD).format(date));
    }

    public static int getIntDate(Date date,String format) {
        return Integer.parseInt(new SimpleDateFormat(format).format(date));
    }

    public static Date getDateBySeconed(int seconed){
        return new Date((long)seconed * 1000l);
    }

    public static Date getDateByInt(int date) {
        try {
            return DATE_FORMAT_YMD.parse(date + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取整数日期的最后时刻23.59.59
     */
    public static Date getDateEndTimeByInt(int date) {
        Date newDate = getDateByInt(date);
        if (newDate != null) {
            return getEndTime(newDate);
        }
        return null;
    }

    /**
     * 获取所有的日期列表YYMMDD 包括首尾日期
     * @param startDate
     * @return
     */
    public static List<Integer> getIntDateList(int startDate, int endDate) {
        try {
            Date startDay = DATE_FORMAT_YMD.parse(startDate + "");
            Date endDay = DATE_FORMAT_YMD.parse(endDate + "");
            List<Date> result = getDays(startDay, endDay);
            List<Integer> intDays = Lists.transform(result, new Function<Date, Integer>() {
                @Override
                public Integer apply(Date input) {
                    return getIntDate(input);
                }
            });
            return intDays;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    /**
     * 格式如 yyyyMMddHH
     * @return
     */
    public static String getCurrentHour() {
        Calendar c = new GregorianCalendar();
        return new SimpleDateFormat(YMDH).format(c.getTime());
    }

    /**
     * 返回 yyyyMMdd格式的今日日期
     * @return
     */
    public static String getTodayStr() {
        Calendar c = new GregorianCalendar();
        return new SimpleDateFormat(YMD).format(c.getTime());
    }

    /**
     * 返回特定时间格式的日期
     * @return
     */
    public static String getDayStr(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * 返回 当前时间到今天结束剩余的秒数
     * @return
     */
    public static int getRemainingSecondsOfToday(Date currentDate) {
        Date endOfToday = DateUtils.getEndTime(currentDate);
        long diffSeconds = (endOfToday.getTime() - currentDate.getTime()) / 1000;
        return new Long(diffSeconds).intValue();
    }

    public static int getDiffHour(Date start, Date end) {
        long diffSeconds = (end.getTime() - start.getTime()) / 1000;
        return (int)Math.ceil(diffSeconds/3600);
    }

    public static int getDiffMillis(Date start, Date end) {
        long diffSeconds = (end.getTime() - start.getTime());
        return (int)Math.ceil(diffSeconds);
    }

    /**
     * @param args
     * @throws ParseException
     */
//	public static void main(String[] args) throws ParseException {
//		System.out.println(getDateByInt(20170726));
//		try {
//
//			Calendar calendar = Calendar.getInstance(Locale.CHINA);
//			calendar.add(Calendar.DAY_OF_WEEK, 3);
//			System.out.println(DateUtils.formatDate(calendar.getTime(), DateUtils.yyyyMMddHHmmssSSS));
//
//			System.out.println(DateUtils.formatDate(DateUtils.getBefore7DayStartTime(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//
//			System.out.println(DateUtils.formatDate(DateUtils.getMonthStartDate(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//			System.out.println(DateUtils.formatDate(DateUtils.getMonthEndDate(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//
//			System.out.println(DateUtils.formatDate(DateUtils.getWeekStartDate(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//			System.out.println(DateUtils.formatDate(DateUtils.getWeekEndDate(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//
//			System.out.println(DateUtils.formatDate(DateUtils.getStartTime(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//			System.out.println(DateUtils.formatDate(DateUtils.getEndTime(calendar.getTime()), DateUtils.yyyyMMddHHmmssSSS));
//
//			Date localDate = DateUtils.getDate("20160218", "yyyyMMdd");
//			System.out.println(localDate);
//			System.out.println(localDate.getTime());
//			System.out.println(localDate.getTime() + 1000*60*60*24);
//			System.out.println(new Date(localDate.getTime() + 1000*60*60*24));
//
//			System.out.println("getFirstDayOfWeek = " + DateUtils.getFirstDayOfWeek(localDate));
//			System.out.println("getFirstDayOfWeek_lastWeek = " + DateUtils.getFirstDayOfWeek_lastWeek(localDate));
//			System.out.println("getFirstDayOfPreWeek=" + DateUtils.getFirstDayOfPreWeek(new Date()));
//			System.out.println("getFristDateOfMonth = " + DateUtils.getFristDateOfMonth(localDate));
//			System.out.println("getFristDateOfLastMonth = " + DateUtils.getFristDateOfLastMonth(localDate));
//			System.out.println("getYesterday = " + DateUtils.getYesterday());
//			System.out.println("getMonth = " + DateUtils.getMonth(localDate));
//
//			Calendar c = Calendar.getInstance();
//			System.out.println(c.get(Calendar.DAY_OF_YEAR));
//			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
//			int startDate = DateUtils.getFirstDayOfWeek(c.getTime());
//			System.out.println(startDate);
//
//
//
//			Calendar cc = Calendar.getInstance();
//			cc.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
//			int startDatec = DateUtils.getFristDateOfMonth(c.getTime());
//			int endDate = DateUtils.getToday();
//			System.out.println(startDatec);
//			System.out.println(endDate);
//		} catch (ParseException e) {
//			logs.error("系统异常", e);
//		}

//		//getDayRange
//		Date now = new Date();
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(now);
//		cal.add(Calendar.DATE, -1);
//		Date yesterday = cal.getTime();
//		System.out.println("now: " + DateFormatUtils.format(now, "yyyyMMdd"));
//		System.out.println("yesterday: " + DateFormatUtils.format(yesterday, "yyyyMMdd"));
//		System.out.println(getDayRange(now, yesterday));
//		System.out.println(getBefore7DayStartTime(new Date()));
//		System.out.println(getBeforeDayStartTime(new Date(),-6));
//
//		List<Date> monthList = getMonths(new Date(), DateUtils.addDay(new Date(), 6));
//		for(Date month : monthList){
//			System.out.println(DateUtils.formatDateAndTime(month));
//		}
//	}

    /**
     * @param date
     * @param hour
     * @return
     */
    public static Date getDateByDiffHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    public static int compare(Date date1, Date date2, boolean isAsc) {
        if(null == date1 && null == date2) {
            return 0;
        }
        else {
            if(null == date1) {
                return isAsc ? -1 : 1;
            }
            if(null == date2) {
                return isAsc ? 1 : -1;
            }
            return isAsc ? date1.compareTo(date2) : date2.compareTo(date1);
        }
    }

    /**
     * 得到某年的第一天的开始时间
     * yxl
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,  cal.getMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return DateUtils.getStartTime(cal.getTime());
    }

    /**
     * 获取diff年前的今日
     * @param date
     * @param diff
     * @return
     */
    public static Date getDiffYear(Date date, int diff){
        Calendar cc = Calendar.getInstance();
        cc.setTime(date);
        cc.set(Calendar.YEAR, cc.get(Calendar.YEAR) + diff);
        return cc.getTime();
    }

    /**
     * 得到某年的最后一天的结束时间
     * yxl
     * @param year
     * @return
     */
    public static Date getLastDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,  cal.getMaximum(Calendar.MONTH));
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return DateUtils.getEndTime(cal.getTime());
    }

    /**
     * 判断两个时间段是否存在交集
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !start2.after(end1);
    }

    /**
     * 得到某年某月的第一天的开始时间
     * yxl
     * @param year
     * @param month 从0开始
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return DateUtils.getStartTime(cal.getTime());
    }

    /**
     * 得到某年某月的最后一天的结束时间
     * yxl
     * @param year
     * @param month 从0开始
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return DateUtils.getEndTime(cal.getTime());
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
    }

    // add hour
    public static Date addHour(Date date, int hour) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.HOUR_OF_DAY, hour);
        return startDT.getTime();
    }

    // add month
    public static Date addMonth(Date date, int month) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.MONTH, month);
        return startDT.getTime();
    }

    // add day
    public static Date addDay(Date date, int day) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, day);
        return startDT.getTime();
    }

    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static Date setTime(Integer year,Integer month,Integer date,Integer hour,Integer minute,Integer second){
        Calendar cal = Calendar.getInstance();

        if(year != null){
            cal.set(Calendar.YEAR, year);
        }
        if(month != null){
            cal.set(Calendar.MONTH, month);
        }
        if(date != null){
            cal.set(Calendar.DAY_OF_MONTH, date);
        }
        if(hour != null){
            cal.set(Calendar.HOUR_OF_DAY,hour);
        }
        if(minute != null){
            cal.set(Calendar.MINUTE,minute);
        }
        if(second != null){
            cal.set(Calendar.SECOND,second);
        }

        return cal.getTime();
    }

    /**
     * 根据生日获取年龄
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) {
            return -1;
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthDay);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            }else{
                age--;
            }
        }
        return age;
    }

    public static class RequestParamDateRange {
        private String paramStartDate;
        private String paramEndDate;
        private Date startDate;
        private Date endDateInclude;
        private Date endDateExclude;
        private Date now;
        private static final int DEFAULT_DATE_RANGE = 7;
        private static final int DEFAULT_MAX_DATE_RANGE = 365;

        public RequestParamDateRange(String paramStartDate, String paramEndDate, int dateRange, int maxDateRange, boolean includeToday) {
            this.paramStartDate = paramStartDate;
            this.paramEndDate = paramEndDate;

            if(dateRange<1){
                dateRange = DEFAULT_DATE_RANGE;
            }

            if(maxDateRange<1){
                maxDateRange = DEFAULT_MAX_DATE_RANGE;
            }

            now = new Date();

            if(null == paramStartDate || paramEndDate == null) {
                startDate = DateUtils.getStartTime(DateUtils.getDate(now, -dateRange));
                endDateExclude = DateUtils.getStartTime(now);
                endDateInclude = DateUtils.getDate(endDateExclude, -1);
            }else{
                try {
                    Date startTimeDate = org.apache.commons.lang3.time.DateUtils.parseDate(paramStartDate, "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd");
                    Date endTimeDate = org.apache.commons.lang3.time.DateUtils.parseDate(paramEndDate, "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd");

                    startDate = startTimeDate.before(endTimeDate) ? startTimeDate : endTimeDate;
                    endDateInclude = endTimeDate.before(startTimeDate) ? startTimeDate : endTimeDate;

                    Date currentDateStartTime = DateUtils.getStartTime(now);
                    if(endDateInclude.after(currentDateStartTime) || (!includeToday && endDateInclude.getTime()==currentDateStartTime.getTime())){
                        endDateExclude = currentDateStartTime;
                        endDateInclude = DateUtils.getDate(endDateExclude, -1);
                        //startDate = DateUtils.getStartTime(org.apache.commons.lang3.time.DateUtils.addDays(endDateInclude, -dateRange));
                    }else{
                        endDateExclude = DateUtils.getDate(endDateInclude, 1);
                    }

                    int days = DateUtils.getDayRange(startDate, endDateInclude);
                    if(days>maxDateRange){
                        startDate = org.apache.commons.lang3.time.DateUtils.addDays(endDateInclude, -maxDateRange);
                    }
                } catch (Exception e) {
                    logs.error("系统异常", e);
                    startDate = DateUtils.getStartTime(DateUtils.getDate(now, -7));
                    endDateInclude = DateUtils.getStartTime(now);
                }
            }
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getNow() {
            return now;
        }

        public Date getEndDateExclude() {
            return endDateExclude;
        }

        public Date getEndDateInclude() {
            return endDateInclude;
        }

        public String getParamStartDate() {
            return paramStartDate;
        }

        public String getParamEndDate() {
            return paramEndDate;
        }

    }
}

