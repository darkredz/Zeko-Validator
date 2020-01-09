package com.zeko.validation;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class PredicateUtil {

    public static Predicate<String> isNotEmpty() {
        return (v) -> !isEmptyString(v);
    }

    public static Predicate<String> isNotTrimmedEmpty() {
        return (v) -> !isTrimmedEmptyString(v);
    }

    public static Predicate<String> isNotBlank() {
        return (v) -> !isBlank(v);
    }

    public static Predicate<String> isWithinMax(int max) {
        return (v) -> isNull(v) || v.length() <= max;
    }

    public static Predicate<String> isWithinMin(int min) {
        return (v) -> nonNull(v) && v.length() >= min;
    }

    public static Predicate<Integer> maxValue(int max) {
        return (v) -> v <= max;
    }

    public static Predicate<Integer> minValue(int min) {
        return (v) -> v >= min;
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

    public static Predicate<String> hasDigit() {
        return (s) -> s.matches(".*\\d.*");
    }

    public static Predicate<String> hasLetter() {
        return (s) -> s.matches(".*[a-zA-Z]+.*");
    }

    public static Predicate<String> digit() {
        return (s) -> s.matches("^\\d+$");
    }

    public static Predicate<String> letter() {
        return (s) -> s.matches("^[a-zA-Z]+$");
    }

    public static Predicate<String> alphaNum() {
        return (s) -> s.matches("^[a-zA-Z0-9]+$");
    }

    public static Predicate<String> alphaNumSpace() {
        return (s) -> s.matches("^[a-zA-Z0-9\\s]+$");
    }

    public static Predicate<String> alphaNumLowerCase() {
        return (s) -> s.matches("^[a-z0-9]+$");
    }

    public static Predicate<String> alphaNumSpaceLowerCase() {
        return (s) -> s.matches("^[a-z0-9\\s]+$");
    }

    public static Predicate<String> alphaNumUpperCase() {
        return (s) -> s.matches("^[A-Z0-9]+$");
    }

    public static Predicate<String> alphaNumSpaceUpperCase() {
        return (s) -> s.matches("^[A-Z0-9\\s]+$");
    }

    public static Predicate<String> alphaNumDash() {
        return (s) -> s.matches("^[a-zA-Z0-9\\-]+$");
    }

    public static Predicate<String> alphaNumDashSpace() {
        return (s) -> s.matches("^[a-zA-Z0-9\\-\\s]+$");
    }

    public static Predicate<String> alphaNumUnderscore() {
        return (s) -> s.matches("^[a-zA-Z0-9\\_]+$");
    }

    public static Predicate<String> alphaNumUnderscoreSpace() {
        return (s) -> s.matches("^[a-zA-Z0-9\\_\\s]+$");
    }

    public static Predicate<String> allLowerCase() {
        return (s) -> s.equals(s.toLowerCase());
    }

    public static Predicate<String> allUpperCase() {
        return (s) -> s.equals(s.toUpperCase());
    }

    public static Predicate<String> passwordSimple(int minLength, int maxLength) {
        return (s) -> s.matches("^[a-zA-Z0-9\\_\\-\\.\\\\p{Punct}]{" + minLength + "," + maxLength + "}$");
    }

    public static Predicate<String> passwordSimple() {
        return passwordSimple(6, 32);
    }

    public static Predicate<String> creditCard() {
        final List<String> listOfPattern = new ArrayList<String>();
        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);

        String ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);

        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);

        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);

        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);

        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);

        return (s) -> {
            for (String pattern : listOfPattern) {
                if (s.matches(pattern)) return true;
            }
            return false;
        };
    }

    public static Predicate<String> ccVisa() {
        return (s) -> s.matches("^4[0-9]{6,}$");
    }

    public static Predicate<String> ccMaster() {
        return (s) -> s.matches("^5[1-5][0-9]{5,}$");
    }

    public static Predicate<String> ccAmericanExpress() {
        return (s) -> s.matches("^3[47][0-9]{5,}$");
    }

    public static Predicate<String> ccDinersClub() {
        return (s) -> s.matches("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$");
    }

    public static Predicate<String> ccDiscover() {
        return (s) -> s.matches("^6(?:011|5[0-9]{2})[0-9]{3,}$");
    }

    public static Predicate<String> ccJcb() {
        return (s) -> s.matches("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$");
    }

    public static Predicate<String> email() {
        return (s) -> s.indexOf("--") == -1 &&
                s.indexOf("-.") == -1 &&
                s.matches("^([\\w\\!\\#$\\%\\&'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+\\.)*[\\w\\!\\#$\\%\\&'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~\\_]+@((((([a-z0-9]{1}[a-z0-9\\-]{0,62}[a-z0-9]{1})|[a-z])\\.)+[a-z]{2,6})|(\\d{1,3}\\.){3}\\d{1,3}(\\:\\d{1,5})?)$");
    }

    public static Predicate<String> url(String[] schemes) {
        return (s) -> {
            final UrlValidator urlValidator = new UrlValidator(schemes);
            return urlValidator.isValid(s);
        };
    }

    public static Predicate<String> url() {
        return url(new String[]{"http", "https"});
    }

    public static Predicate<String> ipv4() {
        return (s) -> s.matches("^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    }

    /**
     * Validate a hostname as per RFC 952
     *
     * @return
     */
    public static Predicate<String> hostName() {
        return (s) -> s.matches("^(?=.{1,255}$)[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?)*\\.?$");
    }

    /**
     * Validate a server name that does not allow numbers at the start
     *
     * @return
     */
    public static Predicate<String> serverName() {
        return (s) -> s.matches("^[A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|\\b-){0,61}[0-9A-Za-z])?)*$");
    }

    /**
     * Validate simple subdomain (alpha numeric dash) till the nested level defined
     *
     * @return
     */
    public static Predicate<String> subdomain(int allowLevels) {
        final int levels = allowLevels + 2;
        return (s) -> s.matches("^[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+){2," + levels + "}$");
    }

    public static Predicate<String> subdomain() {
        return subdomain(1);
    }

    public static Predicate<String> domain() {
        return (s) -> s.matches("^[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,6}$");
    }

    /**
     * Validate Color hex #ff0000
     *
     * @return
     */
    public static Predicate<String> colorHex() {
        return (s) -> s.matches("^#([0-9a-fA-F]{1,2}){3}$");
    }

    public static Predicate<String> isNumber() {
        return (s) -> NumberUtils.isParsable(s);
    }

    public static Predicate<String> isInteger() {
        return (s) -> {
            try {
                int d = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        };
    }

    public static Predicate<String> isUInteger() {
        return (s) -> {
            try {
                int d = Integer.parseUnsignedInt(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        };
    }

    public static Predicate<String> isDouble() {
        return (s) -> {
            try {
                double d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        };
    }

    public static Predicate<String> isFloat() {
        return (s) -> {
            try {
                float d = Float.parseFloat(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        };
    }

    /**
     * Validate float value.
     *
     * @param decimal Number of Decimal points. max at 7
     * @return
     */
    public static Predicate<String> isFloat(int decimal) {
        return (s) -> {
            try {
                float d = Float.parseFloat(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return s.matches("^[-]?[0-9]*\\\\.?[0-9]{0," + decimal + "}$");
        };
    }

    /**
     * Validate double value.
     *
     * @param decimal Number of Decimal points. max at 16
     * @return
     */
    public static Predicate<String> isDouble(int decimal) {
        return (s) -> {
            try {
                double d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return s.matches("^[-]?[0-9]*\\\\.?[0-9]{0," + decimal + "}$");
        };
    }

    public static Predicate<String> minInt(int min) {
        return (s) -> {
            int d;
            try {
                d = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d >= min;
        };
    }

    public static Predicate<String> maxInt(int max) {
        return (s) -> {
            int d;
            try {
                d = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d <= max;
        };
    }

    public static Predicate<String> minDouble(double min) {
        return (s) -> {
            double d;
            try {
                d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d >= min;
        };
    }

    public static Predicate<String> maxDouble(double max) {
        return (s) -> {
            double d;
            try {
                d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d <= max;
        };
    }

    public static Predicate<String> minFloat(float min) {
        return (s) -> {
            float d;
            try {
                d = Float.parseFloat(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d >= min;
        };
    }

    public static Predicate<String> maxFloat(float max) {
        return (s) -> {
            float d;
            try {
                d = Float.parseFloat(s);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return d <= max;
        };
    }

    public static Predicate<String> inStrArray(String[] arr) {
        return (s) -> {
            boolean result = false;

            for (String i : arr) {
                if (i.equals(s)) {
                    result = true;
                    break;
                }
            }
            return result;
        };
    }

    public static Predicate<String> inIntArray(int[] arr) {
        return (s) -> {
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
        };
    }

    public static Predicate<String> notInStrArray(String[] arr) {
        return (s) -> {
            boolean result = true;

            for (String i : arr) {
                if (i.equals(s)) {
                    result = false;
                    break;
                }
            }
            return result;
        };
    }

    public static Predicate<String> notInIntArray(int[] arr) {
        return (s) -> {
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
        };
    }

    public static Predicate<String> separateBy(String delimiter, int minSize, int maxSize) {
        return (s) -> {
            String[] arr = s.split(delimiter);
            if (minSize == 0 && maxSize ==0) {
                return arr.length > 0;
            }
            return arr.length >= minSize && arr.length <= maxSize;
        };
    }

    public static Predicate<String> separateBy(String delimiter) {
        return separateBy(delimiter, 0, 0);
    }

    public static Predicate<String> separateByInArray(String delimiter, String[] items, int minSize, int maxSize) {
        return (s) -> {
            Predicate<String> testSep = separateBy(delimiter, minSize, maxSize);
            if (!testSep.test(s)) {
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
        };
    }

    public static Predicate<String> separateByInArray(String delimiter, String[] items) {
        return separateByInArray(delimiter, items, 0, 0);
    }

    public static Predicate<String> isBoolean() {
        return (s) -> s.equals("false") || s.equals("true");
    }

    /**
     * Validate a 24 hour time format eg. 23:59
     *
     * @return
     */
    public static Predicate<String> time24Hour() {
        return (s) -> s.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    /**
     * Validate Date format. Default yyyy/mm/dd.
     * Date valid from 1900-01-01 through 2099-12-31
     *
     * @param format Date format: yyyy-mm-dd, yyyy/mm/dd, yyyy.mm.dd
     * @return
     */
    public static Predicate<String> dateFormat(final String format, final boolean fullYearLength) {
        return (s) -> {
            //Date yyyy-mm-dd, yyyy/mm/dd, yyyy.mm.dd
            //1900-01-01 through 2099-12-31
            String yearFormat = "(19|20)?[0-9]{2}";
            if (fullYearLength == true) {
                if (format.indexOf("yyyy") > -1) {
                    yearFormat = "(19|20)[0-9]{2}";
                } else {
                    yearFormat = "[0-9]{2}";
                }
            }

            String fmt = null;

            switch (format) {
                case "dd/mm/yy":
                    fmt = "^\\b(0?[1-9]|[12][0-9]|3[01])[- \\/.](0?[1-9]|1[012])[- \\/.]" + yearFormat + "\\b$";
                    break;
                case "mm/dd/yy":
                    fmt = "^\\b(0?[1-9]|1[012])[- \\/.](0?[1-9]|[12][0-9]|3[01])[- \\/.]" + yearFormat + "\\b$";
                case "mm/dd/yyyy":
                    fmt = "^(0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])[- \\/.]" + yearFormat + "$";
                    break;
                case "dd/mm/yyyy":
                    fmt = "^(0[1-9]|[12][0-9]|3[01])[- \\/.](0[1-9]|1[012])[- \\/.]" + yearFormat + "$";
                    break;
                case "yy/mm/dd":
                    fmt = "^\\b" + yearFormat + "[- \\/.](0?[1-9]|1[012])[- \\/.](0?[1-9]|[12][0-9]|3[01])\\b$";
                    break;
                case "yyyy/mm/dd":
                default:
                    fmt = "^\\b" + yearFormat + "[- \\/.](0?[1-9]|1[012])[- \\/.](0?[1-9]|[12][0-9]|3[01])\\b$";
            }
            return s.matches(fmt);
        };
    }

    public static Predicate<String> dateFormat() {
        return dateFormat("yyyy/mm/dd", true);
    }

}
