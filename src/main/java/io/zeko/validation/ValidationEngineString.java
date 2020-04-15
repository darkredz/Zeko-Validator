package io.zeko.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.TimeZone;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

/**
 * Validation engine for String type value. Provides convenience methods for String validation predicate.
 */
public class ValidationEngineString extends ValidationEngine<String, ValidationEngineString> {

    public ValidationEngineString(String value) {
        super(value);
    }

    public ValidationEngineString(String fieldName, String value, Note note) {
        super(fieldName, value, note);
    }

    public ValidationEngineString(String value, List<String> note) {
        super(value, note);
    }

    @Override
    public ValidationEngineString must(Predicate<String> predicate, String message) {
        return (ValidationEngineString) super.must(predicate, message);
    }

    public ValidationEngineString must(Predicate<String> predicate, String message, String validationName) {
        return (ValidationEngineString) super.must(predicate, message, validationName);
    }

    /**
     * Set a value with length greater than the max to be considered as invalid.
     * valid; v.length() less or equal than max
     *
     * @param max     maximum valid value
     * @param message validation error
     * @return validation engines
     */
    public ValidationEngineString maxLength(int max, String message) {
        return must((s) -> RuleSet.isWithinMax(s, max), message, String.format("maxLength;%d", max));
    }

    public ValidationEngineString maxLength(int max) {
        return maxLength(max, null);
    }

    /**
     * Set a value with length less than the min to be considered as invalid.
     * valid; v.length() greater or equal than min
     *
     * @param min     minimum valid value
     * @param message validation error
     * @return validation engines
     */
    public ValidationEngineString minLength(int min, String message) {
        return must((s) -> RuleSet.isWithinMin(s, min), message, String.format("minLength;%d", min));
    }

    public ValidationEngineString minLength(int min) {
        return minLength(min, null);
    }

    public ValidationEngineString regex(String regexExpr, String message) {
        return must((s) -> RuleSet.regex(s, regexExpr), message, String.format("regex;%s", regexExpr));
    }

    public ValidationEngineString regex(String regexExpr) {
        return regex(regexExpr);
    }

    /**
     * Convenience method for defining max and min lengths
     * <p>
     * length(1, 3) is same as; minLength(1) AND maxLength(3)
     *
     * @param min     minimum value
     * @param max     maximum value
     * @param message validation error
     * @return validation engine
     */
    public ValidationEngineString length(int min, int max, String message) {
        return must((s) -> RuleSet.length(s, min, max), message, String.format("length;%d;%d", min, max));
    }

    public ValidationEngineString length(int min, int max) {
        return length(min, max, null);
    }

    /**
     * Set a value that does not contain {@link String#contains} the provided sequence of char values to be considered as invalid.
     *
     * @param value   the sequence to search for
     * @param message validation error
     * @return validation engine
     */
    public ValidationEngineString hasChar(CharSequence value, String message) {
        return must(s -> s.contains(value), message, String.format("hasChar;%s", value));
    }

    public ValidationEngineString hasChar(CharSequence value) {
        return hasChar(value, null);
    }

    /**
     * Set value that does not start with {@link String#startsWith} the provided string to be considered invalid.
     *
     * @param value   the string to search for
     * @param message validation error
     * @return validation engine
     */
    public ValidationEngineString startWith(String value, String message) {
        return must(s -> nonNull(s) && s.startsWith(value), message, String.format("startWith;%s", value));
    }

    public ValidationEngineString startWith(String value) {
        return startWith(value, null);
    }

    public ValidationEngineString endWith(String value, String message) {
        return must(s -> nonNull(s) && s.endsWith(value), message, String.format("endWith;%s", value));
    }

    public ValidationEngineString endWith(String value) {
        return endWith(value, null);
    }

    public ValidationEngineString digit(String message) {
        return must((s) -> RuleSet.digit(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString digit() {
        return digit(null);
    }

    public ValidationEngineString letter(String message) {
        return must((s) -> RuleSet.letter(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString letter() {
        return letter(null);
    }

    public ValidationEngineString hasDigit(String message) {
        return must((s) -> RuleSet.hasDigit(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString hasDigit() {
        return hasDigit(null);
    }

    public ValidationEngineString hasLetter(String message) {
        return must((s) -> RuleSet.hasLetter(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString hasLetter() {
        return hasLetter(null);
    }

    public ValidationEngineString alphaNum(String message) {
        return must((s) -> RuleSet.alphaNum(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNum() {
        return alphaNum(null);
    }

    public ValidationEngineString alphaNumSpace(String message) {
        return must((s) -> RuleSet.alphaNumSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumSpace() {
        return alphaNumDashSpace(null);
    }

    public ValidationEngineString alphaNumLowerCase(String message) {
        return must((s) -> RuleSet.alphaNumLowerCase(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumLowerCase() {
        return alphaNumLowerCase(null);
    }

    public ValidationEngineString alphaNumSpaceLowerCase(String message) {
        return must((s) -> RuleSet.alphaNumSpaceLowerCase(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }


    public ValidationEngineString alphaNumSpaceLowerCase() {
        return alphaNumSpaceLowerCase(null);
    }

    public ValidationEngineString alphaNumUpperCase(String message) {
        return must((s) -> RuleSet.alphaNumUpperCase(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumUpperCase() {
        return alphaNumUpperCase(null);
    }

    public ValidationEngineString alphaNumDash(String message) {
        return must((s) -> RuleSet.alphaNumDash(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumDash() {
        return alphaNumDash(null);
    }

    public ValidationEngineString alphaNumDashSpace(String message) {
        return must((s) -> RuleSet.alphaNumDashSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumDashSpace() {
        return alphaNumDashSpace(null);
    }

    public ValidationEngineString alphaNumUnderscore(String message) {
        return must((s) -> RuleSet.alphaNumUnderscore(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumUnderscore() {
        return alphaNumUnderscore(null);
    }

    public ValidationEngineString alphaNumUnderscoreSpace(String message) {
        return must((s) -> RuleSet.alphaNumUnderscoreSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumUnderscoreSpace() {
        return alphaNumUnderscoreSpace(null);
    }

    public ValidationEngineString alphaNumQuoteSpace(String message) {
        return must((s) -> RuleSet.alphaNumQuoteSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumQuoteSpace() {
        return alphaNumQuoteSpace(null);
    }

    public ValidationEngineString alphaNumQuoteDashSpace(String message) {
        return must((s) -> RuleSet.alphaNumQuoteDashSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaNumQuoteDashSpace() {
        return alphaNumQuoteDashSpace(null);
    }

    public ValidationEngineString alphaQuoteSpace(String message) {
        return must((s) -> RuleSet.alphaQuoteSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaQuoteSpace() {
        return alphaQuoteSpace(null);
    }

    public ValidationEngineString alphaQuoteDashSpace(String message) {
        return must((s) -> RuleSet.alphaQuoteDashSpace(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString alphaQuoteDashSpace() {
        return alphaQuoteDashSpace(null);
    }

    public ValidationEngineString allLowerCase(String message) {
        return must((s) -> RuleSet.allLowerCase(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString allLowerCase() {
        return allLowerCase(null);
    }

    public ValidationEngineString allUpperCase(String message) {
        return must((s) -> RuleSet.allUpperCase(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString allUpperCase() {
        return allUpperCase(null);
    }

    public ValidationEngineString passwordSimple(int minLength, int maxLength, String message) {
        return must((s) -> RuleSet.passwordSimple(s, minLength, maxLength), message, String.format("passwordSimple;%d;%d", minLength, maxLength));
    }

    public ValidationEngineString passwordSimple(int minLength, int maxLength) {
        return passwordSimple(minLength, maxLength, null);
    }

    public ValidationEngineString passwordSimple(String message) {
        return must((s) -> RuleSet.passwordSimple(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString passwordSimple() {
        return passwordSimple(null);
    }

    public ValidationEngineString creditCard(String message) {
        return must((s) -> RuleSet.creditCard(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString creditCard() {
        return creditCard(null);
    }

    public ValidationEngineString ccVisa(String message) {
        return must((s) -> RuleSet.ccVisa(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccVisa() {
        return ccVisa(null);
    }

    public ValidationEngineString ccMaster(String message) {
        return must((s) -> RuleSet.ccMaster(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccMaster() {
        return ccMaster(null);
    }

    public ValidationEngineString ccAmericanExpress(String message) {
        return must((s) -> RuleSet.ccAmericanExpress(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccAmericanExpress() {
        return ccAmericanExpress(null);
    }

    public ValidationEngineString ccDinersClub(String message) {
        return must((s) -> RuleSet.ccDinersClub(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccDinersClub() {
        return ccDinersClub(null);
    }

    public ValidationEngineString ccDiscover(String message) {
        return must((s) -> RuleSet.ccDiscover(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccDiscover() {
        return ccDiscover(null);
    }

    public ValidationEngineString ccJcb(String message) {
        return must((s) -> RuleSet.ccJcb(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ccJcb() {
        return ccJcb(null);
    }

    public ValidationEngineString email(String message) {
        return must((s) -> RuleSet.email(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString email() {
        return email(null);
    }

    public ValidationEngineString url(String[] schemes, String message) {
        return must((s) -> RuleSet.url(s, schemes), message, String.format("passwordSimple;%s", String.join(";", schemes)));
    }

    public ValidationEngineString url(String[] schemes) {
        return url(schemes, null);
    }

    public ValidationEngineString url(String message) {
        return must((s) -> RuleSet.url(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString url() {
        return url(new String[]{"http", "https"}, null);
    }

    public ValidationEngineString ipv4(String message) {
        return must((s) -> RuleSet.ipv4(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString ipv4() {
        return ipv4(null);
    }

    public ValidationEngineString hostName(String message) {
        return must((s) -> RuleSet.hostName(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString hostName() {
        return hostName(null);
    }

    public ValidationEngineString serverName(String message) {
        return must((s) -> RuleSet.serverName(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString serverName() {
        return serverName(null);
    }

    public ValidationEngineString subdomain(int allowLevels, String message) {
        return must((s) -> RuleSet.subdomain(s, allowLevels), message, String.format("subdomain;%d", allowLevels));
    }

    public ValidationEngineString subdomain(int allowLevels) {
        return subdomain(allowLevels, null);
    }

    public ValidationEngineString subdomain(String message) {
        return must((s) -> RuleSet.subdomain(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString subdomain() {
        return subdomain(null);
    }

    public ValidationEngineString colorHex(String message) {
        return must((s) -> RuleSet.colorHex(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString colorHex() {
        return colorHex(null);
    }

    public ValidationEngineString isNumber(String message) {
        return must((s) -> RuleSet.isNumber(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isNumber() {
        return isNumber(null);
    }

    public ValidationEngineString isInteger(String message) {
        return must((s) -> RuleSet.isInteger(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isInteger() {
        return isInteger(null);
    }

    public ValidationEngineString isUInteger(String message) {
        return must((s) -> RuleSet.isUInteger(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isUInteger() {
        return isUInteger(null);
    }

    public ValidationEngineString isDouble(String message) {
        return must((s) -> RuleSet.isDouble(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isDouble() {
        return isDouble(null);
    }

    public ValidationEngineString isLong(String message) {
        return must((s) -> RuleSet.isLong(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isLong() {
        return isDouble(null);
    }

    public ValidationEngineString isULong(String message) {
        return must((s) -> RuleSet.isULong(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isULong() {
        return isULong(null);
    }

    public ValidationEngineString isFloat(String message) {
        return must((s) -> RuleSet.isFloat(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isFloat() {
        return isFloat(null);
    }

    public ValidationEngineString isFloat(int decimalPlaces, String message) {
        return must((s) -> RuleSet.isFloat(s, decimalPlaces), message, String.format("isFloat;%d", decimalPlaces));
    }

    public ValidationEngineString isFloat(int decimalPlaces) {
        return isFloat(null);
    }

    public ValidationEngineString isDouble(int decimalPlaces, String message) {
        return must((s) -> RuleSet.isDouble(s, decimalPlaces), message, String.format("isDouble;%d", decimalPlaces));
    }

    public ValidationEngineString isDouble(int decimalPlaces) {
        return isDouble(null);
    }

    public ValidationEngineString isBoolean(String message) {
        return must((s) -> RuleSet.isBoolean(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString isBoolean() {
        return isBoolean(null);
    }

    public ValidationEngineString min(long min, String message) {
        return must((s) -> RuleSet.min(s, min), message, String.format("min;%d", min));
    }

    public ValidationEngineString min(long min) {
        return min(min, null);
    }

    public ValidationEngineString max(long max, String message) {
        return must((s) -> RuleSet.max(s, max), message, String.format("max;%d", max));
    }

    public ValidationEngineString max(long max) {
        return max(max, null);
    }

    public ValidationEngineString min(int min, String message) {
        return must((s) -> RuleSet.min(s, min), message, String.format("min;%d", min));
    }

    public ValidationEngineString min(int min) {
        return min(min, null);
    }

    public ValidationEngineString max(int max, String message) {
        return must((s) -> RuleSet.max(s, max), message, String.format("max;%d", max));
    }

    public ValidationEngineString max(int max) {
        return max(max, null);
    }

    public ValidationEngineString min(float min, String message) {
        return must((s) -> RuleSet.min(s, min), message, String.format("min;%d", min));
    }

    public ValidationEngineString min(float min) {
        return min(min, null);
    }

    public ValidationEngineString max(float max, String message) {
        return must((s) -> RuleSet.max(s, max), message, String.format("max;%d", max));
    }

    public ValidationEngineString max(float max) {
        return max(max, null);
    }

    public ValidationEngineString min(double min, String message) {
        return must((s) -> RuleSet.min(s, min), message, String.format("min;%d", min));
    }

    public ValidationEngineString min(double min) {
        return min(min, null);
    }

    public ValidationEngineString max(double max, String message) {
        return must((s) -> RuleSet.max(s, max), message, String.format("max;%d", max));
    }

    public ValidationEngineString max(double max) {
        return max(max, null);
    }

    public ValidationEngineString inArray(int[] arr, String message) {
        return must((s) -> RuleSet.inArray(s, arr), message, String.format("inArray;%s", StringUtils.join(arr, ",")));
    }

    public ValidationEngineString inArray(int[] arr) {
        return inArray(arr, null);
    }

    public ValidationEngineString inArray(String[] arr, String message) {
        return must((s) -> RuleSet.inArray(s, arr), message, String.format("inArray;%s", String.join(",", arr)));
    }

    public ValidationEngineString inArray(String[] arr) {
        return inArray(arr, null);
    }

    public ValidationEngineString notInArray(int[] arr, String message) {
        return must((s) -> RuleSet.notInArray(s, arr), message, String.format("notInArray;%s", StringUtils.join(arr, ",")));
    }

    public ValidationEngineString notInArray(int[] arr) {
        return notInArray(arr, null);
    }

    public ValidationEngineString notInArray(String[] arr, String message) {
        return must((s) -> RuleSet.notInArray(s, arr), message, String.format("notInArray;%s", String.join(",", arr)));
    }

    public ValidationEngineString notInArray(String[] arr) {
        return notInArray(arr, null);
    }

    public ValidationEngineString separateBy(String delimiter, int minSize, int maxSize, String message) {
        return must((s) -> RuleSet.separateBy(s, delimiter, minSize, maxSize), message, String.format("separateBy;%s;%d;%d", delimiter, minSize, maxSize));
    }

    public ValidationEngineString separateBy(String delimiter, int minSize, int maxSize) {
        return separateBy(delimiter, minSize, maxSize, null);
    }

    public ValidationEngineString separateBy(String delimiter, String message) {
        return must((s) -> RuleSet.separateBy(s, delimiter), message, String.format("separateBy;%s", delimiter));
    }

    public ValidationEngineString separateBy(String delimiter) {
        return separateBy(delimiter, null);
    }

    public ValidationEngineString separateByInArray(String delimiter, String[] items, int minSize, int maxSize, String message) {
        return must((s) -> RuleSet.separateByInArray(s, delimiter, items, minSize, maxSize), message,
                String.format("separateByInArray;%s;%s;%d;%d", delimiter, String.join(",", items), minSize, maxSize));
    }

    public ValidationEngineString separateByInArray(String delimiter, String[] items, int minSize, int maxSize) {
        return separateByInArray(delimiter, items, minSize, maxSize, null);
    }

    public ValidationEngineString separateByInArray(String delimiter, String[] items, String message) {
        return must((s) -> RuleSet.separateByInArray(s, delimiter, items), message, String.format("separateByInArray;%s;%s"));
    }

    public ValidationEngineString separateByInArray(String delimiter, String[] items) {
        return separateByInArray(delimiter, items, null);
    }

    public ValidationEngineString time24Hour(String message) {
        return must((s) -> RuleSet.time24Hour(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString time24Hour() {
        return time24Hour(null);
    }

    public ValidationEngineString dateFormat(String format, String message) {
        return must((s) -> RuleSet.dateFormat(s, format), message, String.format("dateFormat;%s", format));
    }

    public ValidationEngineString dateFormat(String message) {
        return must((s) -> RuleSet.dateFormat(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString dateFormat() {
        return dateFormat(null);
    }

    public ValidationEngineString dateBetween(String start, String end, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateBetween(s, start, end, format, timeZone), message,
                String.format("dateBetween;%s;%s;%s;%s", start, end, format, timeZone));
    }

    public ValidationEngineString dateBetween(String start, String end, String format, TimeZone timeZone) {
        return dateBetween(start, end, format, timeZone, null);
    }

    public ValidationEngineString dateBetween(String start, String end, String format, String message) {
        return must((s) -> RuleSet.dateBetween(s, start, end, format), message,
                String.format("dateBetween;%s;%s;%s", start, end, format));
    }

    public ValidationEngineString dateBetween(String start, String end, String message) {
        return must((s) -> RuleSet.dateBetween(s, start, end), message,
                String.format("dateBetween;%s;%s", start, end));
    }

    public ValidationEngineString dateBetween(String start, String end) {
        return dateBetween(start, end, null);
    }

    public ValidationEngineString dateAfter(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateAfter(s, compareWith, format, timeZone), message,
                String.format("dateAfter;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateAfter(String compareWith, String format, TimeZone timeZone) {
        return dateAfter(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateAfter(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateAfter(s, compareWith, format), message,
                String.format("dateAfter;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateAfter(String compareWith, String message) {
        return must((s) -> RuleSet.dateAfter(s, compareWith), message,
                String.format("dateAfter;%s", compareWith));
    }

    public ValidationEngineString dateAfter(String compareWith) {
        return dateAfter(compareWith, null);
    }

    public ValidationEngineString dateBefore(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateBefore(s, compareWith, format, timeZone), message,
                String.format("dateBefore;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateBefore(String compareWith, String format, TimeZone timeZone) {
        return dateBefore(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateBefore(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateBefore(s, compareWith, format), message,
                String.format("dateBefore;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateBefore(String compareWith, String message) {
        return must((s) -> RuleSet.dateBefore(s, compareWith), message,
                String.format("dateBefore;%s", compareWith));
    }

    public ValidationEngineString dateBefore(String compareWith) {
        return dateBefore(compareWith, null);
    }

    public ValidationEngineString dateEq(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateEq(s, compareWith, format, timeZone), message,
                String.format("dateEq;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateEq(String compareWith, String format, TimeZone timeZone) {
        return dateEq(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateEq(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateEq(s, compareWith, format), message,
                String.format("dateEq;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateEq(String compareWith, String message) {
        return must((s) -> RuleSet.dateEq(s, compareWith), message,
                String.format("dateEq;%s", compareWith));
    }

    public ValidationEngineString dateEq(String compareWith) {
        return dateEq(compareWith, null);
    }

    public ValidationEngineString dateAfterYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateAfterYear(s, compareWith, format, timeZone), message,
                String.format("dateAfterYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateAfterYear(String compareWith, String format, TimeZone timeZone) {
        return dateAfterYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateAfterYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateAfterYear(s, compareWith, format), message,
                String.format("dateAfterYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateAfterYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateAfterYear(s, compareWith), message,
                String.format("dateAfterYear;%s", compareWith));
    }

    public ValidationEngineString dateAfterYear(String compareWith) {
        return dateAfterYear(compareWith, null);
    }

    public ValidationEngineString dateBeforeYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateBeforeYear(s, compareWith, format, timeZone), message,
                String.format("dateBeforeYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateBeforeYear(String compareWith, String format, TimeZone timeZone) {
        return dateBeforeYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateBeforeYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateBeforeYear(s, compareWith, format), message,
                String.format("dateBeforeYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateBeforeYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateBeforeYear(s, compareWith), message,
                String.format("dateBeforeYear;%s", compareWith));
    }

    public ValidationEngineString dateBeforeYear(String compareWith) {
        return dateBeforeYear(compareWith, null);
    }

    public ValidationEngineString dateEqYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateEqYear(s, compareWith, format, timeZone), message,
                String.format("dateEqYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateEqYear(String compareWith, String format, TimeZone timeZone) {
        return dateEqYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateEqYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateEqYear(s, compareWith, format), message,
                String.format("dateEqYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateEqYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateEqYear(s, compareWith), message,
                String.format("dateEqYear;%s", compareWith));
    }

    public ValidationEngineString dateEqYear(String compareWith) {
        return dateEqYear(compareWith, null);
    }

    public ValidationEngineString dateAfterMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateAfterMonth(s, compareWith, format, timeZone), message,
                String.format("dateAfterMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateAfterMonth(String compareWith, String format, TimeZone timeZone) {
        return dateAfterMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateAfterMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateAfterMonth(s, compareWith, format), message,
                String.format("dateAfterMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateAfterMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateAfterMonth(s, compareWith), message,
                String.format("dateAfterMonth;%s", compareWith));
    }

    public ValidationEngineString dateAfterMonth(String compareWith) {
        return dateAfterMonth(compareWith, null);
    }

    public ValidationEngineString dateBeforeMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateBeforeMonth(s, compareWith, format, timeZone), message,
                String.format("dateBeforeMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateBeforeMonth(String compareWith, String format, TimeZone timeZone) {
        return dateBeforeMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateBeforeMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateBeforeMonth(s, compareWith, format), message,
                String.format("dateBeforeMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateBeforeMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateBeforeMonth(s, compareWith), message,
                String.format("dateBeforeMonth;%s", compareWith));
    }

    public ValidationEngineString dateBeforeMonth(String compareWith) {
        return dateBeforeMonth(compareWith, null);
    }

    public ValidationEngineString dateEqMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateEqMonth(s, compareWith, format, timeZone), message,
                String.format("dateEqMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateEqMonth(String compareWith, String format, TimeZone timeZone) {
        return dateEqMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateEqMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateEqMonth(s, compareWith, format), message,
                String.format("dateEqMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateEqMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateEqMonth(s, compareWith), message,
                String.format("dateEqMonth;%s", compareWith));
    }

    public ValidationEngineString dateEqMonth(String compareWith) {
        return dateEqMonth(compareWith, null);
    }

    public ValidationEngineString timeFormat(String format, String message) {
        return must((s) -> RuleSet.timeFormat(s, format), message, String.format("timeFormat;%s;", format));
    }

    public ValidationEngineString timeFormat(String message) {
        return must((s) -> RuleSet.timeFormat(s), message, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public ValidationEngineString timeFormat() {
        return timeFormat(null);
    }

    public ValidationEngineString timeBetween(String start, String end, String format, String message) {
        return must((s) -> RuleSet.timeBetween(s, start, end, format), message, String.format("timeBetween;%s;%s;%s;", start, end, format));
    }

    public ValidationEngineString timeBetween(String start, String end, String message) {
        return must((s) -> RuleSet.timeBetween(s, start, end), message, String.format("timeBetween;%s;%s;", start, end));
    }

    public ValidationEngineString timeBetween(String start, String end) {
        return timeBetween(start, end, null);
    }

    public ValidationEngineString timeAfter(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeAfter(s, compareWith, format), message, String.format("timeBetween;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeAfter(String compareWith, String message) {
        return must((s) -> RuleSet.timeAfter(s, compareWith), message, String.format("timeBetween;%s;%s", compareWith));
    }

    public ValidationEngineString timeAfter(String compareWith) {
        return timeAfter(compareWith, null);
    }

    public ValidationEngineString timeBefore(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeBefore(s, compareWith, format), message, String.format("timeBefore;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeBefore(String compareWith, String message) {
        return must((s) -> RuleSet.timeBefore(s, compareWith), message, String.format("timeBefore;%s", compareWith));
    }

    public ValidationEngineString timeBefore(String compareWith) {
        return timeBefore(compareWith, null);
    }

    public ValidationEngineString timeEq(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeEq(s, compareWith, format), message, String.format("timeEq;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeEq(String compareWith, String message) {
        return must((s) -> RuleSet.timeEq(s, compareWith), message, String.format("timeEq;%s", compareWith));
    }

    public ValidationEngineString timeEq(String compareWith) {
        return timeEq(compareWith, null);
    }

    public ValidationEngineString timeAfterHour(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeAfterHour(s, compareWith, format), message, String.format("timeAfterHour;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeAfterHour(String compareWith, String message) {
        return must((s) -> RuleSet.timeAfterHour(s, compareWith), message, String.format("timeAfterHour;%s", compareWith));
    }

    public ValidationEngineString timeAfterHour(String compareWith) {
        return timeAfterHour(compareWith, null);
    }

    public ValidationEngineString timeBeforeHour(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeBeforeHour(s, compareWith, format), message, String.format("timeBeforeHour;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeBeforeHour(String compareWith, String message) {
        return must((s) -> RuleSet.timeBeforeHour(s, compareWith), message, String.format("timeBeforeHour;%s", compareWith));
    }

    public ValidationEngineString timeBeforeHour(String compareWith) {
        return timeBeforeHour(compareWith, null);
    }

    public ValidationEngineString timeEqHour(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeEqHour(s, compareWith, format), message, String.format("timeEqHour;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeEqHour(String compareWith, String message) {
        return must((s) -> RuleSet.timeEqHour(s, compareWith), message, String.format("timeEqHour;%s", compareWith));
    }

    public ValidationEngineString timeEqHour(String compareWith) {
        return timeEqHour(compareWith, null);
    }

    public ValidationEngineString timeAfterMinute(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeAfterMinute(s, compareWith, format), message, String.format("timeAfterMinute;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeAfterMinute(String compareWith, String message) {
        return must((s) -> RuleSet.timeAfterMinute(s, compareWith), message, String.format("timeAfterMinute;%s", compareWith));
    }

    public ValidationEngineString timeAfterMinute(String compareWith) {
        return timeAfterMinute(compareWith, null);
    }

    public ValidationEngineString timeBeforeMinute(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeBeforeMinute(s, compareWith, format), message, String.format("timeBeforeMinute;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeBeforeMinute(String compareWith, String message) {
        return must((s) -> RuleSet.timeBeforeMinute(s, compareWith), message, String.format("timeBeforeMinute;%s", compareWith));
    }

    public ValidationEngineString timeBeforeMinute(String compareWith) {
        return timeBeforeMinute(compareWith, null);
    }

    public ValidationEngineString timeEqMinute(String compareWith, String format, String message) {
        return must((s) -> RuleSet.timeEqMinute(s, compareWith, format), message, String.format("timeEqMinute;%s;%s", compareWith, format));
    }

    public ValidationEngineString timeEqMinute(String compareWith, String message) {
        return must((s) -> RuleSet.timeEqMinute(s, compareWith), message, String.format("timeEqMinute;%s", compareWith));
    }

    public ValidationEngineString timeEqMinute(String compareWith) {
        return timeEqMinute(compareWith, null);
    }

    public ValidationEngineString dateTimeFormat(String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeFormat(s, format, timeZone), message, String.format("dateTimeFormat;%s;%s", format, timeZone));
    }

    public ValidationEngineString dateTimeFormat(String format, TimeZone timeZone) {
        return dateTimeFormat(format, timeZone, null);
    }

    public ValidationEngineString dateTimeFormat(String format, String message) {
        return must((s) -> RuleSet.dateTimeFormat(s, format), message, String.format("dateTimeFormat;%s;%s", format));
    }

    public ValidationEngineString dateTimeFormat(String message) {
        return must((s) -> RuleSet.dateTimeFormat(s), message, "dateTimeFormat");
    }

    public ValidationEngineString dateTimeFormat() {
        return dateTimeFormat(null);
    }

    public ValidationEngineString dateTimeBetween(String start, String end, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeBetween(s, start, end, format, timeZone), message, String.format("dateTimeBetween;%s;%s;%s;%s", start, end, format, timeZone));
    }

    public ValidationEngineString dateTimeBetween(String start, String end, String format, TimeZone timeZone) {
        return dateTimeBetween(start, end, format, timeZone, null);
    }

    public ValidationEngineString dateTimeBetween(String start, String end, String format, String message) {
        return must((s) -> RuleSet.dateTimeBetween(s, start, end, format), message, String.format("dateTimeBetween;%s;%s;%s", start, end, format));
    }

    public ValidationEngineString dateTimeBetween(String start, String end, String message) {
        return must((s) -> RuleSet.dateTimeBetween(s, start, end), message, String.format("dateTimeBetween;%s;%s", start, end));
    }

    public ValidationEngineString dateTimeBetween(String start, String end) {
        return dateTimeBetween(start, end, null);
    }

    public ValidationEngineString dateTimeAfter(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeAfter(s, compareWith, format, timeZone), message, String.format("dateTimeAfter;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeAfter(String compareWith, String format, TimeZone timeZone) {
        return dateTimeAfter(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeAfter(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeAfter(s, compareWith, format), message, String.format("dateTimeAfter;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeAfter(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeAfter(s, compareWith), message, String.format("dateTimeAfter;%s", compareWith));
    }

    public ValidationEngineString dateTimeAfter(String compareWith) {
        return dateTimeAfter(compareWith, null);
    }

    public ValidationEngineString dateTimeBefore(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeBefore(s, compareWith, format, timeZone), message, String.format("dateTimeBefore;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeBefore(String compareWith, String format, TimeZone timeZone) {
        return dateTimeBefore(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeBefore(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeBefore(s, compareWith, format), message, String.format("dateTimeBefore;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeBefore(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeBefore(s, compareWith), message, String.format("dateTimeBefore;%s", compareWith));
    }

    public ValidationEngineString dateTimeBefore(String compareWith) {
        return dateTimeBefore(compareWith, null);
    }

    public ValidationEngineString dateTimeEq(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeEq(s, compareWith, format, timeZone), message, String.format("dateTimeEq;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeEq(String compareWith, String format, TimeZone timeZone) {
        return dateTimeEq(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeEq(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeEq(s, compareWith, format), message, String.format("dateTimeEq;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeEq(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeEq(s, compareWith), message, String.format("dateTimeEq;%s", compareWith));
    }

    public ValidationEngineString dateTimeEq(String compareWith) {
        return dateTimeEq(compareWith, null);
    }

    public ValidationEngineString dateTimeAfterWeek(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeAfterWeek(s, compareWith, format, timeZone), message, String.format("dateTimeAfterWeek;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeAfterWeek(String compareWith, String format, TimeZone timeZone) {
        return dateTimeAfterWeek(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeAfterWeek(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeAfterWeek(s, compareWith, format), message, String.format("dateTimeAfterWeek;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeAfterWeek(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeAfterWeek(s, compareWith), message, String.format("dateTimeAfterWeek;%s", compareWith));
    }

    public ValidationEngineString dateTimeAfterWeek(String compareWith) {
        return dateTimeAfterWeek(compareWith, null);
    }

    public ValidationEngineString dateTimeBeforeWeek(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeBeforeWeek(s, compareWith, format, timeZone), message, String.format("dateTimeBeforeWeek;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeBeforeWeek(String compareWith, String format, TimeZone timeZone) {
        return dateTimeBeforeWeek(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeBeforeWeek(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeBeforeWeek(s, compareWith, format), message, String.format("dateTimeBeforeWeek;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeBeforeWeek(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeBeforeWeek(s, compareWith), String.format("dateTimeBeforeWeek;%s", compareWith));
    }

    public ValidationEngineString dateTimeBeforeWeek(String compareWith) {
        return dateTimeBeforeWeek(compareWith, null);
    }

    public ValidationEngineString dateTimeEqWeek(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeEqWeek(s, compareWith, format, timeZone), message, String.format("dateTimeEqWeek;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeEqWeek(String compareWith, String format, TimeZone timeZone) {
        return dateTimeEqWeek(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeEqWeek(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeEqWeek(s, compareWith, format), message, String.format("dateTimeEqWeek;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeEqWeek(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeEqWeek(s, compareWith), message, String.format("dateTimeEqWeek;%s", compareWith));
    }

    public ValidationEngineString dateTimeEqWeek(String compareWith) {
        return dateTimeEqWeek(compareWith, null);
    }

    public ValidationEngineString dateTimeAfterMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeAfterMonth(s, compareWith, format, timeZone), message, String.format("dateTimeAfterMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeAfterMonth(String compareWith, String format, TimeZone timeZone) {
        return dateTimeAfterMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeAfterMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeAfterMonth(s, compareWith, format), message, String.format("dateTimeAfterMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeAfterMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeAfterMonth(s, compareWith), message, String.format("dateTimeAfterMonth;%s", compareWith));
    }

    public ValidationEngineString dateTimeAfterMonth(String compareWith) {
        return dateTimeAfterMonth(compareWith, null);
    }

    public ValidationEngineString dateTimeBeforeMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeBeforeMonth(s, compareWith, format, timeZone), message, String.format("dateTimeBeforeMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeBeforeMonth(String compareWith, String format, TimeZone timeZone) {
        return dateTimeBeforeMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeBeforeMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeBeforeMonth(s, compareWith, format), message, String.format("dateTimeBeforeMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeBeforeMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeBeforeMonth(s, compareWith), message, String.format("dateTimeBeforeMonth;%s", compareWith));
    }

    public ValidationEngineString dateTimeBeforeMonth(String compareWith) {
        return dateTimeBeforeMonth(compareWith, null);
    }

    public ValidationEngineString dateTimeEqMonth(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeEqMonth(s, compareWith, format, timeZone), message, String.format("dateTimeEqMonth;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeEqMonth(String compareWith, String format, TimeZone timeZone) {
        return dateTimeEqMonth(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeEqMonth(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeEqMonth(s, compareWith, format), message, String.format("dateTimeEqMonth;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeEqMonth(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeEqMonth(s, compareWith), message, String.format("dateTimeEqMonth;%s", compareWith));
    }

    public ValidationEngineString dateTimeEqMonth(String compareWith) {
        return dateTimeEqMonth(compareWith, null);
    }

    public ValidationEngineString dateTimeAfterYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeAfterYear(s, compareWith, format, timeZone), message, String.format("dateTimeAfterYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeAfterYear(String compareWith, String format, TimeZone timeZone) {
        return dateTimeAfterYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeAfterYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeAfterYear(s, compareWith, format), message, String.format("dateTimeAfterYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeAfterYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeAfterYear(s, compareWith), message, String.format("dateTimeAfterYear;%s", compareWith));
    }

    public ValidationEngineString dateTimeAfterYear(String compareWith) {
        return dateTimeAfterYear(compareWith, null);
    }

    public ValidationEngineString dateTimeBeforeYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeBeforeYear(s, compareWith, format, timeZone), message, String.format("dateTimeBeforeYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeBeforeYear(String compareWith, String format, TimeZone timeZone) {
        return dateTimeBeforeYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeBeforeYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeBeforeYear(s, compareWith, format), message, String.format("dateTimeBeforeYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeBeforeYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeBeforeYear(s, compareWith), message, String.format("dateTimeBeforeYear;%s", compareWith));
    }

    public ValidationEngineString dateTimeBeforeYear(String compareWith) {
        return dateTimeBeforeYear(compareWith, null);
    }

    public ValidationEngineString dateTimeEqYear(String compareWith, String format, TimeZone timeZone, String message) {
        return must((s) -> RuleSet.dateTimeEqYear(s, compareWith, format, timeZone), message, String.format("dateTimeEqYear;%s;%s;%s", compareWith, format, timeZone));
    }

    public ValidationEngineString dateTimeEqYear(String compareWith, String format, TimeZone timeZone) {
        return dateTimeEqYear(compareWith, format, timeZone, null);
    }

    public ValidationEngineString dateTimeEqYear(String compareWith, String format, String message) {
        return must((s) -> RuleSet.dateTimeEqYear(s, compareWith, format), message, String.format("dateTimeEqYear;%s;%s", compareWith, format));
    }

    public ValidationEngineString dateTimeEqYear(String compareWith, String message) {
        return must((s) -> RuleSet.dateTimeEqYear(s, compareWith), message, String.format("dateTimeEqYear;%s", compareWith));
    }

    public ValidationEngineString dateTimeEqYear(String compareWith) {
        return dateTimeEqYear(compareWith, null);
    }
}
