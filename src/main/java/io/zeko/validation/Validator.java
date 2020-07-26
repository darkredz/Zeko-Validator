package io.zeko.validation;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Validator {
    private Notification note;
    private Map<String, List<?>> rules;

    public Validator(Map<String, List<?>> rules, Notification note) {
        this.setRules(rules);
        this.setNote(note);
    }

    public void setRules(Map<String, List<?>> rules) {
        this.rules = rules;
    }

    public void setNote(Notification note) {
        this.note = note;
    }

    public static Map<String, List<Object>> parseRules(String ruleStr) {
        //rules example = "required, isInteger, length;2;12, startWith;Hello, timeBefore;18:30, alphaNum, alphaNumDashSpace"
        return parseRules(ruleStr, ", ", ";");
    }

    public static Map<String, List<Object>> parseRules(String ruleStr, String ruleDelimiter, String argDelimiter) {
        final String[] rules = ruleStr.split(ruleDelimiter);
        final Map<String, List<Object>> ruleMap = new HashMap<>();

        for (int i = 0; i < rules.length; i++) {
            String r = rules[i];
            String[] parts = r.split(argDelimiter);
            String name = parts[0];

            List<Object> argsList = new ArrayList<>();
            for (int j = 1; j < parts.length; j++) {
                final String arg = parts[j];
                if (NumberUtils.isParsable(arg)) {
                    if (arg.contains(".")) {
                        final String[] argParts = arg.split("\\.");
                        final String afterDot = argParts[1];

                        if (afterDot.length() <= 7) {
                            argsList.add(Float.parseFloat(arg));
                        } else {
                            argsList.add(Double.parseDouble(arg));
                        }
                    } else {
                        if (arg.length() <= 10) {
                            argsList.add(Integer.parseInt(arg));
                        } else {
                            argsList.add(Long.parseLong(arg));
                        }
                    }
                } else {
                    argsList.add(arg);
                }
            }

            ruleMap.put(name, argsList);
        }
        return ruleMap;
    }

    public ValidationEngineString checkAll(Map<String, String> input, String fieldName) {
        PreConditionString preCond = NoteAllValidator.valid(input, fieldName, note);
        return invokeCheck(rules, preCond);
    }

    public ValidationEngineString check(Map<String, String> input, String fieldName) {
        PreConditionString preCond = NoteFirstValidator.valid(input, fieldName, note);
        return invokeCheck(rules, preCond);
    }

    public static ValidationEngineString invokeCheck(Map<String, List<?>> rules, PreConditionString field) {
        ValidationEngineString validate;
        if (rules.containsKey("required")) {
            validate = field.required();
        } else if (rules.containsKey("requiredLoose")) {
            validate = field.requiredLoose();
        } else {
            validate = field.optional();
        }
        rules.forEach((ruleName, args) -> {
            if (!ruleName.equals("required") && !ruleName.equals("requiredLoose") && !ruleName.equals("optional")) {
                if (ruleName.equals("inArray")) {
                    if (args.get(0) instanceof Integer) {
                        int[] items = ArrayUtils.toPrimitive(args.toArray(new Integer[args.size()]));
                        validate.inArray(items);
                    } else {
                        String[] items = new String[args.size()];
                        args.toArray(items);
                        validate.inArray(items);
                    }
                }
                else if (ruleName.equals("notInArray")) {
                    if (args.get(0) instanceof Integer) {
                        int[] items = ArrayUtils.toPrimitive(args.toArray(new Integer[args.size()]));
                        validate.notInArray(items);
                    } else {
                        String[] items = new String[args.size()];
                        args.toArray(items);
                        validate.notInArray(items);
                    }
                }
                else if (ruleName.equals("url") && args.size() > 0) {
                    if (args.get(0) instanceof String) {
                        String[] schemes = new String[args.size()];
                        args.toArray(schemes);
                        validate.url(schemes);
                    }
                }
                else if (ruleName.equals("separateByInArray") && args.size() > 0) {
                    if (args.get(0) instanceof String) {
                        String delimiter = (String) args.get(0);
                        String[] all = new String[args.size()];
                        args.toArray(all);
                        String[] items = Arrays.copyOfRange(all, 1, all.length - 1);
                        validate.separateByInArray(delimiter, items);
                    }
                }
                else {
                    try {
                        invoke(validate, ruleName, args);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }
        });
        return validate;
    }

    public static Method invoke(Object instance, String methodName, List<?> args) {
        Class<?> cls = instance.getClass();
        if (args == null || args.size() == 0) {
            try {
                Method m = cls.getDeclaredMethod(methodName);
                m.invoke(instance);
                return m;
            } catch (Exception err) {
            }
        } else {
            Class<?> params[] = new Class[args.size()];

            for (int i = 0; i < args.size(); i++) {
                Object arg = args.get(i);

                if (arg instanceof Integer) {
                    params[i] = Integer.TYPE;
                } else if (arg instanceof String) {
                    params[i] = String.class;
                } else if (arg instanceof Long) {
                    params[i] = Long.class;
                } else if (arg instanceof Float) {
                    params[i] = Float.class;
                } else if (arg instanceof Double) {
                    params[i] = Double.class;
                }
            }

            try {
                Method m = cls.getDeclaredMethod(methodName, params);
                m.invoke(instance, args.toArray());
                return m;
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return null;
    }

    public static Method invoke(Object instance, String methodName) {
        return invoke(instance, methodName, null);
    }

}
