package com.ldy.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * @Description: 时间工具类
 * @author: rbkevin.xu 
 * @version: 2018年11月22日 下午4:32:58
 */
public class DateUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private static final SimpleDateFormat formatYMDHMSL = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat formatYMDX = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat formatYMDSTR = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat formatHMSSTR = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat formatHMSSSSSTR = new SimpleDateFormat("HHmmss-SSS");


    private static final SimpleDateFormat formatDMY = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat formatYMDXHMS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat formatYMDTHMSS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat formatYMDTHMSSSSS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS");
    private static final SimpleDateFormat formatYMDTHMSZZZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");

    private static final SimpleDateFormat formatYMDTHMSZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final SimpleDateFormat formatMDYTHMS = new SimpleDateFormat("MM-dd-yyyy'T'HH:mm:ss");
    private static final SimpleDateFormat formatMDY = new SimpleDateFormat("MM-dd-yyyy");
    private static final TimeZone timeZone = TimeZone.getTimeZone("ISO 8601");

    public static String formatYMDTHMSS(Date date){
        synchronized(formatYMDTHMSS){
            return formatYMDTHMSS.format(date);
        }
    }

    public static String formatYMDStr(Date date){
        synchronized(formatYMDSTR){
            return formatYMDSTR.format(date);
        }
    }

    public static String formatYMDHMSLDate(Date date){
        synchronized(formatYMDHMSL){
            return formatYMDHMSL.format(date);
        }
    }

    public static Date parseYMDHMSL(String strDate) throws ParseException{
        synchronized(formatYMDHMSL){
            return formatYMDHMSL.parse(strDate);
        }
    }

	public static String formatYMDDate(Date date){
        synchronized(formatYMD){
            return formatYMD.format(date);
        }  
    }
    
    public static Date parseYMD(String strDate) throws ParseException{
        synchronized(formatYMD){
            return formatYMD.parse(strDate);
        }
    }
    
    public static String formatYMDHMSDate(Date date){
        synchronized(formatYMDHMS){
            return formatYMDHMS.format(date);
        }  
    }
    
    public static Date parseYMDHMS(String strDate) throws ParseException{
        synchronized(formatYMDHMS){
            return formatYMDHMS.parse(strDate);
        }
    }

    public static String formatYMDXDate(Date date){
        synchronized(formatYMDX){
            return formatYMDX.format(date);
        }  
    }
    
    public static Date parseYMDX(String strDate) throws ParseException{
        synchronized(formatYMDX){
            return formatYMDX.parse(strDate);
        }
    }
    
    public static String formatYMDXHMSDate(Date date){
        synchronized(formatYMDXHMS){
            return formatYMDXHMS.format(date);
        }  
    }
    
    public static Date parseYMDXHMS(String strDate) throws ParseException{
        synchronized(formatYMDXHMS){
            return formatYMDXHMS.parse(strDate);
        }
    }
    
    public static String formatDMYDate(Date date){
	synchronized(formatDMY){
	    return formatDMY.format(date);
	}  
    }
    
    public static Date parseDMY(String strDate) throws ParseException{
	synchronized(formatDMY){
	    return formatDMY.parse(strDate);
	}
    }

    public static Date parseDMY(Date data) throws ParseException{
        synchronized(formatYMD){
            return formatYMD.parse(formatYMD.format(data));
        }
    }

    public static String formatHMSDate(Date date){
        synchronized(formatHMSSTR){
            return formatHMSSTR.format(date);
        }
    }

    public static Date parseHMS(String strDate) throws ParseException{
        synchronized(formatHMSSTR){
            return formatHMSSTR.parse(strDate);
        }
    }

    public static String formatHMSSSSDate(Date date){
        synchronized(formatHMSSSSSTR){
            return formatHMSSSSSTR.format(date);
        }
    }

    public static Date parseHMSSSS(String strDate) throws ParseException{
        synchronized(formatHMSSSSSTR){
            return formatHMSSSSSTR.parse(strDate);
        }
    }
	
	public static synchronized String subDateStr10Char(String src){
		if(null!=src && src.length()>10){
			if(!src.startsWith("20")){
				return formatYMDHMSDate(new Date());
			}
			return src.substring(0, 10);
		}return "null";
	}
	
	public static synchronized String subDateStr4Char(String src){
		if(null!=src && src.length()>4){
			if(!src.startsWith("20")){
				return formatYMDHMSDate(new Date());
			}
			return src.substring(0, 4);
		}return "null";
	}

         /**
           * Java将Unix时间戳转换日期
           * @param timestamp 时间戳 如："1473048265";
           * @return 返回结果 如："2016-09-05 16:06:42";
           */
    public static Date timeStamp2Date(Long timestamp) {
        timestamp = timestamp;
        Date date = new Date(timestamp);
        return date;
    }

    /**
     * date1 大于date2返回true
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public static boolean compareTimeStamp(Long timestamp1, Long timestamp2) {
        Date date1 =timeStamp2Date(timestamp1);
        Date date2 =timeStamp2Date(timestamp2);

        return formatYMDStr(date1).compareTo(formatYMDStr(date2)) > 0;
    }

    /**
     * XMLGregorianCalendar 转Date
     * @param cal
     * @return
     */
    public static Date convertToDate(XMLGregorianCalendar cal){
        GregorianCalendar ca = null;
        if(cal!=null && cal.toGregorianCalendar()!=null){
            ca = cal.toGregorianCalendar();
            return ca.getTime();
        }
        return null;
    }

    public static Date strToDate(String strDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         ParsePosition pos = new ParsePosition(0);
         Date strtodate = formatter.parse(strDate, pos);
         return strtodate;
    }

    public static Date strUtcToDate(String strDate) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse(strDate);
        return date;
    }

    /**
     * local时间转换成UTC时间
     * @param localTime
     * @return
     */
    public static Date localToUTC(String localTime) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = sdf.parse(localTime);
        long localTimeInMillis = localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return utcDate;
    }

    /**
     * 函数功能描述:UTC时间转本地时间格式
     * @param utcTime UTC时间
     * @param utcTimePatten UTC时间格式
     * @return 本地时间格式的时间
     * eg:utc2Local("2017-06-14 09:37:50.788+08:00", "yyyy-MM-dd'T'HH:mm:ss",)
     */
    public static String utc2Local(String utcTime, String utcTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));//时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTime;
        }
        synchronized (formatYMDHMS){
            formatYMDHMS.setTimeZone(TimeZone.getDefault());
            String localTime = formatYMDHMS.format(gpsUTCDate.getTime());
            return localTime;
        }
    }
    
    /**
     * @Description: 往前推num秒
     * @author: rbkevin.xu
     * @param date
     * @param num
     * @return
     * @version: 2019年5月24日 上午11:55:13
     */
    public static Date addSecond(Date date,int num) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	calendar.add(Calendar.SECOND, num);
	return calendar.getTime();
    }
    
    /**
     * @Description 获取ISO8601格式时间 yyyy-MM-dd'T'HH:mm:ss.SSSS
     * @Author wuxin
     * @Param 
     * @return 
     * @Version 2019/10/9 20:56
     */
    public static String getISO8601(Date date){
        synchronized (formatYMDTHMSSSSS){
            formatYMDTHMSSSSS.setTimeZone(timeZone);
            return formatYMDTHMSSSSS.format(date);
        }
    }
    /**
     * @Description 根据指定日期, 增加或者减少分钟
     * @Author wuxin
     * @Param
     * @return
     * @Version 2019/10/17 18:07
     */
    public static Date addMinute(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }

    /**
     * @Description 根据指定日期, 增加或者减少天数
     * @Author wuxin
     * @Param
     * @return
     * @Version 2019/10/17 18:07
     */
    public static Date addDay(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
    /**
     * @Description 根据指定日期, 增加或者减少月数
     * @Author wuxin
     * @Param
     * @return
     * @Version 2019/10/17 18:07
     */
    public static Date addMonth(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    /**
     * @Description 根据指定日期, 增加或者减少年数
     * @Author wuxin
     * @Param
     * @return
     * @Version 2019/10/17 18:07
     */
    public static Date addYear(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * @Description 根据指定日期, 增加或者减少小时
     * @Author wuxin
     * @Param
     * @return
     * @Version 2019/10/17 18:07
     */
    public static Date addHour(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, amount);
        return calendar.getTime();
    }

    /**
     * 获取 某天 的零点时间
     * @param days 与当前时间相差的天数
     * @return 某天 的零点时间
     * @author tangfudong
     */
    public static Date getZeroTime(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getZeroTime(Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     *
     * @param begin 开始时间 向下取整
     * @param end 结束时间 向上取整
     * @return
     */
    public static List<Date> getZeroTimes(Date begin, Date end) {
        List<Date> dates = new ArrayList<Date>();
        Date beginZero = getZeroTime(begin, 0);
        Date endZero = getZeroTime(end, 1);
        if(beginZero.getTime() > endZero.getTime()){
            return Collections.emptyList();
        }
        for(int i=0;;i++){
            Date zeroTime = getZeroTime(begin, i);
            if(zeroTime.getTime() > endZero.getTime()){
                break;
            }
            dates.add(zeroTime);
        }
        return dates;
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static void main(String[] args) throws Exception{
        /*Date zeroTime = getZeroTime(-7);
        Date zeroTime1 = getZeroTime(0);
        List<Date> zeroTimes = getZeroTimes(zeroTime, zeroTime1);
        System.out.println(zeroTimes);*/
        //Date utcDate = DateUtil.localToUTC("2020-01-03 14:16:30");
        //System.out.println(DateUtil.formatYMDTHMSS(utcDate));
        System.out.print(getISO8601_2(new Date()));
    }

    public static String getCurrentTimestamp() {
        String format = null;
        synchronized(formatYMDTHMSZZZ){
            format = formatYMDTHMSZZZ.format(new Date());
        }
        String part1 = format.substring(0, 22);
        String part2 = format.substring(22, 24);
        return part1 + ":" + part2;
    }
    public static String getISO8601_2(Date date){
        try {
            synchronized (formatYMDTHMSZ){
                return formatYMDTHMSZ.format(date);
            }
        } catch (Exception  e) {
            LOGGER.error("format error :",e);
            return null;
        }
    }

    public static String convertFormatMDYTHMS(String date){
        try {
            Date parse = formatMDYTHMS.parse(date);
            return formatYMDTHMSS.format(parse);
        } catch (ParseException e) {
            LOGGER.error("convertFormatMDYTHMS error", e);
        }
       return null;
    }

    public static String convertFormatMDY(String date){

        try {
            Date parse = formatMDY.parse(date);
            return formatYMD.format(parse);
        } catch (ParseException e) {
            LOGGER.error("convertFormatMDY error", e);
        }
        return null;
    }
    /**
     * @description 获取ISO8601格式时间 yyyy-MM-dd'T'HH:mm:ss
     * @author wuxin
     * @param date
     * @return java.lang.String
     * @version 2020/3/14 14:36
     */
    public static String getISO8601YMDTHMS(Date date){
        SimpleDateFormat formatYMDTHM = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatYMDTHM.setTimeZone(timeZone);
        return formatYMDTHM.format(date);
    }

    /**
     * @description 获取ISO8601格式时间 yyyy-MM-dd'T'HH:mm:ss
     * @author wuxin
     * @param date
     * @return java.lang.String
     * @version 2020/3/14 14:36
     */
    public static Date parseISO8601YMDTHMS(String date) throws ParseException {
        SimpleDateFormat formatYMDTHM = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatYMDTHM.setTimeZone(timeZone);
        return formatYMDTHM.parse(date);
    }
}
