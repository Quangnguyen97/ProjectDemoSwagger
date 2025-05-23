package com.example.demoswagger.module;

import com.google.common.base.Strings;

import java.text.DecimalFormat;
import java.util.Date;

public class ResourceValid {

    public enum typeOBJECT {
        STRING, DATE, LONG, INTEGER, FLOAT, DOUBLE, BOOLEAN
    }

    public static boolean TypeIsError(typeOBJECT typeObject, Object Value) {
        try {
            switch (typeObject) {
                case STRING:
                    return Value.getClass().getSimpleName() != String.class.getSimpleName();
                case DATE:
                    return Value.getClass().getSimpleName() != Date.class.getSimpleName();
                case LONG:
                    return Value.getClass().getSimpleName() != Long.class.getSimpleName();
                case INTEGER:
                    return Value.getClass().getSimpleName() != Integer.class.getSimpleName();
                case FLOAT:
                    return Value.getClass().getSimpleName() != Float.class.getSimpleName();
                case DOUBLE:
                    return Value.getClass().getSimpleName() != Double.class.getSimpleName();
                case BOOLEAN:
                    return Value.getClass().getSimpleName() != Boolean.class.getSimpleName();
                default:
                    return Value.getClass().getSimpleName() != Object.class.getSimpleName();
            }
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public static boolean StrIsError(String string) {
        try {
            return Strings.isNullOrEmpty(string) || string == "null" || string.trim() == "NULL";
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public static boolean ObjectIsError(typeOBJECT typeObject, Object Value) {
        try {
            return StrIsError(String.valueOf(Value)) || TypeIsError(typeObject, Value);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public enum typeERROR {
        REQUEST, FIELD, DIFFERENT, NOTEXISTED, EXISTED
    }

    public static String StringError(typeERROR typeString, String resourceName) {
        try {
            if (Strings.isNullOrEmpty(resourceName)) {
                switch (typeString) {
                    case REQUEST:
                        return "Request body is empty";
                    case FIELD:
                        return "Request body is error field";
                    case DIFFERENT:
                        return "Request body is different field";
                    case NOTEXISTED:
                        return "Data does not exist";
                    case EXISTED:
                        return "Data have exist";
                    default:
                        return "Error exception";
                }
            } else {
                switch (typeString) {
                    case REQUEST:
                        return String.format("Request body is empty %s", resourceName);
                    case FIELD:
                        return String.format("Request body is error with field %s", resourceName);
                    case DIFFERENT:
                        return String.format("Request body is different with field %s", resourceName);
                    case NOTEXISTED:
                        return String.format("Data does not exist with field %s", resourceName);
                    case EXISTED:
                        return String.format("Data have exist with field %s", resourceName);
                    default:
                        return String.format("Error exception with %s", resourceName);
                }
            }
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public static String FormatDouble(double value) {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            return String.valueOf(df.format(value));
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
