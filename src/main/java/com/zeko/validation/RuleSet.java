package com.zeko.validation;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.CalendarValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.TimeValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class RuleSet {


    public static boolean isNotEmpty(String s) {
        return !isEmptyString(s);
    }

    public static boolean isNotTrimmedEmpty(String s) {
        return !isTrimmedEmptyString(s);
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean isWithinMax(String s, int max) {
        return isNull(s) || s.length() <= max;
    }

    public static boolean isWithinMin(String s, int min) {
        return nonNull(s) && s.length() >= min;
    }

    public static boolean length(String s, int min, int max) {
        return nonNull(s) && s.length() >= min && s.length() <= max;
    }

    public static boolean maxValue(Integer s, int max) {
        return s <= max;
    }

    public static boolean minValue(Integer s, int min) {
        return s >= min;
    }

    public static boolean isEmptyString(String v) {
        return nonNull(v) && v.isEmpty();
    }

    public static boolean isTrimmedEmptyString(String v) {
        return nonNull(v) && v.trim().isEmpty();
    }

    public static boolean isBlank(String v) {
        int strLen;
        if (v == null || (strLen = v.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(v.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean regex(String s, String regexExpr) {
        return s.matches(regexExpr);
    }

    public static boolean hasDigit(String s) {
        return s.matches(".*\\d.*");
    }

    public static boolean hasLetter(String s) {
        return s.matches(".*[a-zA-Z]+.*");
    }

    public static boolean digit(String s) {
        return s.matches("^\\d+$");
    }

    public static boolean letter(String s) {
        return s.matches("^[a-zA-Z]+$");
    }

    public static boolean alphaNum(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    public static boolean alphaNumSpace(String s) {
        return s.matches("^[a-zA-Z0-9\\s]+$");
    }

    public static boolean alphaNumLowerCase(String s) {
        return s.matches("^[a-z0-9]+$");
    }

    public static boolean alphaNumSpaceLowerCase(String s) {
        return s.matches("^[a-z0-9\\s]+$");
    }

    public static boolean alphaNumUpperCase(String s) {
        return s.matches("^[A-Z0-9]+$");
    }

    public static boolean alphaNumSpaceUpperCase(String s) {
        return s.matches("^[A-Z0-9\\s]+$");
    }

    public static boolean alphaNumDash(String s) {
        return s.matches("^[a-zA-Z0-9\\-]+$");
    }

    public static boolean alphaNumDashSpace(String s) {
        return s.matches("^[a-zA-Z0-9\\-\\s]+$");
    }

    public static boolean alphaNumUnderscore(String s) {
        return s.matches("^[a-zA-Z0-9\\_]+$");
    }

    public static boolean alphaNumUnderscoreSpace(String s) {
        return s.matches("^[a-zA-Z0-9\\_\\s]+$");
    }

    public static boolean allLowerCase(String s) {
        return s.equals(s.toLowerCase());
    }

    public static boolean allUpperCase(String s) {
        return s.equals(s.toUpperCase());
    }

    public static boolean passwordSimple(String s, int minLength, int maxLength) {
        return s.matches("^[a-zA-Z0-9\\_\\-\\.\\\\p{Punct}]{" + minLength + "," + maxLength + "}$");
    }

    public static boolean passwordSimple(String s) {
        return passwordSimple(s, 6, 32);
    }

    public static boolean creditCard(String s) {
        final List<String> listOfPattern = new ArrayList<String>();

        //Visa
        listOfPattern.add("^4[0-9]{12}(?:[0-9]{3})?$");

        //Master card
        listOfPattern.add("^5[1-5][0-9]{14}$");

        //American Express
        listOfPattern.add("^3[47][0-9]{13}$");

        //Diners Club
        listOfPattern.add("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");

        //Discover
        listOfPattern.add("^6(?:011|5[0-9]{2})[0-9]{3,}$");

        //JCB
        listOfPattern.add("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$");

        for (String pattern : listOfPattern) {
            if (s.matches(pattern)) return true;
        }
        return false;
    }

    public static boolean ccVisa(String s) {
        return s.matches("^4[0-9]{12}(?:[0-9]{3})?$");
    }

    public static boolean ccMaster(String s) {
        return s.matches("^5[1-5][0-9]{14}$");
    }

    public static boolean ccAmericanExpress(String s) {
        return s.matches("^3[47][0-9]{13}$");
    }

    public static boolean ccDinersClub(String s) {
        return s.matches("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
    }

    public static boolean ccDiscover(String s) {
        return s.matches("^6(?:011|5[0-9]{2})[0-9]{3,}$");
    }

    public static boolean ccJcb(String s) {
        return s.matches("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$");
    }

    public static boolean email(String s) {
        return s.indexOf("--") == -1 &&
                s.indexOf("-.") == -1 &&
                s.matches("^([\\w\\!\\#$\\%\\&'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+\\.)*[\\w\\!\\#$\\%\\&'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~\\_]+@((((([a-z0-9]{1}[a-z0-9\\-]{0,62}[a-z0-9]{1})|[a-z])\\.)+[a-z]{2,6})|(\\d{1,3}\\.){3}\\d{1,3}(\\:\\d{1,5})?)$");
    }

    public static boolean url(String s, String[] schemes) {
        final UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(s);
    }

    public static boolean url(String s) {
        return url(s, new String[]{"http", "https"});
    }

    public static boolean ipv4(String s) {
        return s.matches("^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    }

    /**
     * Validate a hostname as per RFC 952
     *
     * @return
     */
    public static boolean hostName(String s) {
        return s.matches("^(?=.{1,255}$)[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?)*\\.?$");
    }

    /**
     * Validate a server name that does not allow numbers at the start
     *
     * @return
     */
    public static boolean serverName(String s) {
        return s.matches("^[A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?)*$");
    }

    /**
     * Validate simple subdomain (alpha numeric dash) till the nested level defined
     *
     * @return
     */
    public static boolean subdomain(String s, int allowLevels) {
        final int levels = allowLevels + 2;
        return s.matches("^[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+){2," + levels + "}$");
    }

    public static boolean subdomain(String s) {
        return subdomain(s,1);
    }

    public static boolean domain(String s) {
        return s.matches("^[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,6}$");
    }

    /**
     * Validate Color hex #ff0000
     *
     * @return
     */
    public static boolean colorHex(String s) {
        return s.matches("^#([0-9a-fA-F]{1,2}){3}$");
    }

    public static boolean isNumber(String s) {
        return NumberUtils.isParsable(s);
    }

    public static boolean isLong(String s) {
        try {
            long d = Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isULong(String s) {
        try {
            long d = Long.parseUnsignedLong(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String s) {
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isUInteger(String s) {
        try {
            int d = Integer.parseUnsignedInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String s) {
        try {
            float d = Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Validate float value.
     *
     * @param decimal Number of Decimal points. max at 7
     * @return
     */
    public static boolean isFloat(String s, int decimal) {
        try {
            float d = Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return s.matches("^[-]?[0-9]*\\\\.?[0-9]{0," + decimal + "}$");
    }

    /**
     * Validate double value.
     *
     * @param decimal Number of Decimal points. max at 16
     * @return
     */
    public static boolean isDouble(String s, int decimal) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return s.matches("^[-]?[0-9]*\\\\.?[0-9]{0," + decimal + "}$");
    }

    public static boolean min(String s, long min) {
        long d;
        try {
            d = Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d >= min;
    }

    public static boolean max(String s, long max) {
        long d;
        try {
            d = Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d <= max;
    }

    public static boolean min(String s, int min) {
        int d;
        try {
            d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d >= min;
    }

    public static boolean max(String s, int max) {
        int d;
        try {
            d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d <= max;
    }

    public static boolean min(String s, double min) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d >= min;
    }

    public static boolean max(String s, double max) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d <= max;
    }

    public static boolean min(String s, float min) {
        float d;
        try {
            d = Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d >= min;
    }

    public static boolean max(String s, float max) {
        float d;
        try {
            d = Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return d <= max;
    }

    public static boolean inArray(String s, String[] arr) {
        boolean result = false;
        for (String i : arr) {
            if (i.equals(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean inArray(String s, int[] arr) {
        boolean result = false;
        int intVal;
        try {
            intVal = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }

        for (int i : arr) {
            if (i == intVal) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean notInArray(String s, String[] arr) {
        boolean result = true;

        for (String i : arr) {
            if (i.equals(s)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean notInArray(String s, int[] arr) {
        boolean result = true;
        int intVal;
        try {
            intVal = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }

        for (int i : arr) {
            if (i == intVal) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean separateBy(String s, String delimiter, int minSize, int maxSize) {
        String[] arr = s.split(delimiter);
        if (minSize == 0 && maxSize ==0) {
            return arr.length > 0;
        }
        return arr.length >= minSize && arr.length <= maxSize;
    }

    public static boolean separateBy(String s, String delimiter) {
        return separateBy(s, delimiter, 0, 0);
    }

    public static boolean separateByInArray(String s, String delimiter, String[] items, int minSize, int maxSize) {
        if (!separateBy(s, delimiter, minSize, maxSize)) {
            return false;
        }

        String[] input = s.split("delimiter");
        int totalMatched = 0;

        for (String itm : items) {
            for (String s2 : input) {
                if (s2.equals(itm)) {
                    totalMatched += 1;
                }
            }
        }
        return totalMatched == input.length;
    }

    public static boolean separateByInArray(String s, String delimiter, String[] items) {
        return separateByInArray(s, delimiter, items, 0, 0);
    }

    public static boolean isBoolean(String s) {
        return s.equals("false") || s.equals("true");
    }

    /**
     * Validate a 24 hour time format eg. 23:59
     *
     * @return
     */
    public static boolean time24Hour(String s) {
        return s.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    public static boolean dateFormat(String s, String format) {
        DateValidator v = DateValidator.getInstance();
        Date d =  v.validate(s, format);
        return d != null;
    }

    public static boolean dateFormat(String s) {
        return dateFormat("yyyy-MM-dd");
    }

    public static boolean dateBetween(String s, String start, String end, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date startDate = v.validate(start, format, timezone);
        Date endDate = v.validate(end, format, timezone);
        int resStart = v.compareDates(startDate, d, timezone);
        int resEnd = v.compareDates(endDate, d, timezone);

        return d != null && startDate != null && endDate != null &&
                (resStart == -1 || resStart == 0) && (resEnd == 1 || resEnd == 0);
    }

    public static boolean dateBetween(String s, String start, String end, String format) {
        return dateBetween(s, start, end, format, TimeZone.getDefault());
    }

    public static boolean dateBetween(String s, String start, String end) {
        return dateBetween(s, start, end, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateAfter(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareDates(d, compareDate, timezone) == 1;
    }

    public static boolean dateAfter(String s, String compareWith, String format) {
        return dateAfter(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateAfter(String s, String compareWith) {
        return dateAfter(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateBefore(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareDates(d, compareDate, timezone) == -1;
    }

    public static boolean dateBefore(String s, String compareWith, String format) {
        return dateBefore(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateBefore(String s, String compareWith) {
        return dateBefore(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateEq(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareDates(d, compareDate, timezone) == 0;
    }

    public static boolean dateEq(String s, String compareWith, String format) {
        return dateEq(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateEq(String s, String compareWith) {
        return dateEq(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateAfterYear(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareYears(d, compareDate, timezone) == 1;
    }

    public static boolean dateAfterYear(String s, String compareWith, String format) {
        return dateAfterYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateAfterYear(String s, String compareWith) {
        return dateAfterYear(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateBeforeYear(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareYears(d, compareDate, timezone) == -1;
    }

    public static boolean dateBeforeYear(String s, String compareWith, String format) {
        return dateBeforeYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateBeforeYear(String s, String compareWith) {
        return dateBeforeYear(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateEqYear(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareYears(d, compareDate, timezone) == 0;
    }

    public static boolean dateEqYear(String s, String compareWith, String format) {
        return dateEqYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateEqYear(String s, String compareWith) {
        return dateEqYear(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateAfterMonth(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareMonths(d, compareDate, timezone) == 1;
    }

    public static boolean dateAfterMonth(String s, String compareWith, String format) {
        return dateAfterMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateAfterMonth(String s, String compareWith) {
        return dateAfterMonth(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateBeforeMonth(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareMonths(d, compareDate, timezone) == -1;
    }

    public static boolean dateBeforeMonth(String s, String compareWith, String format) {
        return dateBeforeMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateBeforeMonth(String s, String compareWith) {
        return dateBeforeMonth(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean dateEqMonth(String s, String compareWith, String format, TimeZone timezone) {
        DateValidator v = DateValidator.getInstance();
        Date d = v.validate(s, format, timezone);
        Date compareDate = v.validate(compareWith, format, timezone);
        return d != null && v.compareMonths(d, compareDate, timezone) == 0;
    }

    public static boolean dateEqMonth(String s, String compareWith, String format) {
        return dateEqMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateEqMonth(String s, String compareWith) {
        return dateEqMonth(s, compareWith, "yyyy-MM-dd", TimeZone.getDefault());
    }

    public static boolean timeFormat(String s, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        return c != null;
    }

    public static boolean timeFormat(String s) {
        return timeFormat("H:mm:ss");
    }

    public static boolean timeBetween(String s, String start, String end, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar d = v.validate(s, format);
        Calendar startTime = v.validate(start, format);
        Calendar endTime = v.validate(end, format);
        int resStart = v.compareTime(startTime, d);
        int resEnd = v.compareTime(endTime, d);

        return d != null && startTime != null && endTime != null &&
                (resStart == -1 || resStart == 0) && (resEnd == 1 || resEnd == 0);
    }

    public static boolean timeBetween(String s, String start, String end) {
        return timeBetween(s, start, end, "H:mm:ss");
    }

    public static boolean timeAfter(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareTime(c, compareTime) == 1;
    }

    public static boolean timeAfter(String s, String compareWith) {
        return timeAfter(s, compareWith, "H:mm:ss");
    }

    public static boolean timeBefore(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareTime(c, compareTime) == -1;
    }

    public static boolean timeBefore(String s, String compareWith) {
        return timeBefore(s, compareWith, "H:mm:ss");
    }

    public static boolean timeEq(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareTime(c, compareTime) == 0;
    }

    public static boolean timeEq(String s, String compareWith) {
        return timeEq(s, compareWith, "H:mm:ss");
    }

    public static boolean timeAfterHour(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareHours(c, compareTime) == 1;
    }

    public static boolean timeAfterHour(String s, String compareWith) {
        return timeAfterHour(s, compareWith, "H:mm:ss");
    }

    public static boolean timeBeforeHour(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareHours(c, compareTime) == -1;
    }

    public static boolean timeBeforeHour(String s, String compareWith) {
        return timeBeforeHour(s, compareWith, "H:mm:ss");
    }

    public static boolean timeEqHour(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareHours(c, compareTime) == 0;
    }

    public static boolean timeEqHour(String s, String compareWith) {
        return timeEqHour(s, compareWith, "H:mm:ss");
    }

    public static boolean timeAfterMinute(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareMinutes(c, compareTime) == 1;
    }

    public static boolean timeAfterMinute(String s, String compareWith) {
        return timeAfterMinute(s, compareWith, "H:mm:ss");
    }

    public static boolean timeBeforeMinute(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareMinutes(c, compareTime) == -1;
    }

    public static boolean timeBeforeMinute(String s, String compareWith) {
        return timeBeforeMinute(s, compareWith, "H:mm:ss");
    }

    public static boolean timeEqMinute(String s, String compareWith, String format) {
        TimeValidator v = TimeValidator.getInstance();
        Calendar c = v.validate(s, format);
        Calendar compareTime = v.validate(compareWith, format);
        return c != null && v.compareMinutes(c, compareTime) == 0;
    }

    public static boolean timeEqMinute(String s, String compareWith) {
        return timeEqMinute(s, compareWith, "H:mm:ss");
    }


    public static boolean dateTimeFormat(String s, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        return c != null;
    }

    public static boolean dateTimeFormat(String s, String format) {
        return dateTimeFormat(s,  format, TimeZone.getDefault());
    }

    public static boolean dateTimeFormat(String s) {
        return dateTimeFormat(s,  "yyyy-MM-dd'T'H:mm:ss", TimeZone.getDefault());
    }

    public static boolean dateTimeBetween(String s, String start, String end, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar d = v.validate(s, format, timeZone);
        Calendar startTime = v.validate(start, format, timeZone);
        Calendar endTime = v.validate(end, format, timeZone);
        int resStart = v.compareDates(startTime, d);
        int resEnd = v.compareDates(endTime, d);

        return d != null && startTime != null && endTime != null &&
                (resStart == -1 || resStart == 0) && (resEnd == 1 || resEnd == 0);
    }

    public static boolean dateTimeBetween(String s, String start, String end, String format) {
        return dateTimeBetween(s, start, end, format, TimeZone.getDefault());
    }

    public static boolean dateTimeBetween(String s, String start, String end) {
        return dateTimeBetween(s, start, end, "yyyy-MM-dd'T'H:mm:ss", TimeZone.getDefault());
    }

    public static boolean dateTimeAfter(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareDates(c, compareTime) == 1;
    }

    public static boolean dateTimeAfter(String s, String compareWith, String  format) {
        return dateTimeAfter(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeAfter(String s, String compareWith) {
        return dateTimeAfter(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeBefore(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareDates(c, compareTime) == -1;
    }

    public static boolean dateTimeBefore(String s, String compareWith, String  format) {
        return dateTimeBefore(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeBefore(String s, String compareWith) {
        return dateTimeBefore(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeEq(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareDates(c, compareTime) == 0;
    }

    public static boolean dateTimeEq(String s, String compareWith, String  format) {
        return dateTimeEq(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeEq(String s, String compareWith) {
        return dateTimeEq(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }


    public static boolean dateTimeAfterWeek(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareWeeks(c, compareTime) == 1;
    }

    public static boolean dateTimeAfterWeek(String s, String compareWith, String  format) {
        return dateTimeAfterWeek(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeAfterWeek(String s, String compareWith) {
        return dateTimeAfterWeek(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeBeforeWeek(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareWeeks(c, compareTime) == -1;
    }

    public static boolean dateTimeBeforeWeek(String s, String compareWith, String  format) {
        return dateTimeBeforeWeek(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeBeforeWeek(String s, String compareWith) {
        return dateTimeBeforeWeek(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeEqWeek(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareWeeks(c, compareTime) == 0;
    }

    public static boolean dateTimeEqWeek(String s, String compareWith, String  format) {
        return dateTimeEqWeek(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeEqWeek(String s, String compareWith) {
        return dateTimeEqWeek(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }


    public static boolean dateTimeAfterMonth(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareMonths(c, compareTime) == 1;
    }

    public static boolean dateTimeAfterMonth(String s, String compareWith, String  format) {
        return dateTimeAfterMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeAfterMonth(String s, String compareWith) {
        return dateTimeAfterMonth(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeBeforeMonth(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareMonths(c, compareTime) == -1;
    }

    public static boolean dateTimeBeforeMonth(String s, String compareWith, String  format) {
        return dateTimeBeforeMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeBeforeMonth(String s, String compareWith) {
        return dateTimeBeforeMonth(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeEqMonth(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareMonths(c, compareTime) == 0;
    }

    public static boolean dateTimeEqMonth(String s, String compareWith, String  format) {
        return dateTimeEqMonth(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeEqMonth(String s, String compareWith) {
        return dateTimeEqMonth(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }


    public static boolean dateTimeAfterYear(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareYears(c, compareTime) == 1;
    }

    public static boolean dateTimeAfterYear(String s, String compareWith, String  format) {
        return dateTimeAfterYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeAfterYear(String s, String compareWith) {
        return dateTimeAfterYear(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeBeforeYear(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareYears(c, compareTime) == -1;
    }

    public static boolean dateTimeBeforeYear(String s, String compareWith, String  format) {
        return dateTimeBeforeYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeBeforeYear(String s, String compareWith) {
        return dateTimeBeforeYear(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

    public static boolean dateTimeEqYear(String s, String compareWith, String format, TimeZone timeZone) {
        CalendarValidator v = CalendarValidator.getInstance();
        Calendar c = v.validate(s, format, timeZone);
        Calendar compareTime = v.validate(compareWith, format, timeZone);
        return c != null && v.compareYears(c, compareTime) == 0;
    }

    public static boolean dateTimeEqYear(String s, String compareWith, String  format) {
        return dateTimeEqYear(s, compareWith, format, TimeZone.getDefault());
    }

    public static boolean dateTimeEqYear(String s, String compareWith) {
        return dateTimeEqYear(s, compareWith, "yyyy-MM-dd'T'H:mm:ss");
    }

}
