package com.example.demoswagger.module;

import com.google.common.base.Strings;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

public class ResourceValid {

    public enum typeOBJECT {
        STRING, DATE, LONG, INTEGER, FLOAT, DOUBLE, BOOLEAN
    }

    public static boolean typeIsError(typeOBJECT typeObject, Object value) {
        try {
            return switch (typeObject) {
                case STRING -> !Objects.equals(value.getClass().getSimpleName(), String.class.getSimpleName());
                case DATE -> !Objects.equals(value.getClass().getSimpleName(), Date.class.getSimpleName());
                case LONG -> !Objects.equals(value.getClass().getSimpleName(), Long.class.getSimpleName());
                case INTEGER -> !Objects.equals(value.getClass().getSimpleName(), Integer.class.getSimpleName());
                case FLOAT -> !Objects.equals(value.getClass().getSimpleName(), Float.class.getSimpleName());
                case DOUBLE -> !Objects.equals(value.getClass().getSimpleName(), Double.class.getSimpleName());
                case BOOLEAN -> !Objects.equals(value.getClass().getSimpleName(), Boolean.class.getSimpleName());
                default -> !Objects.equals(value.getClass().getSimpleName(), Object.class.getSimpleName());
            };
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public static boolean StrIsError(String string) {
        try {
            return Strings.isNullOrEmpty(string) || string.equals("null") || string.trim().equals("NULL");
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    public static boolean ObjectIsError(typeOBJECT typeObject, Object Value) {
        try {
            return StrIsError(String.valueOf(Value)) || typeIsError(typeObject, Value);
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
                return switch (typeString) {
                    case REQUEST -> "Request body is empty";
                    case FIELD -> "Request body is error field";
                    case DIFFERENT -> "Request body is different field";
                    case NOTEXISTED -> "Data does not exist";
                    case EXISTED -> "Data have exist";
                    default -> "Error exception";
                };
            } else {
                return switch (typeString) {
                    case REQUEST -> String.format("Request body is empty %s", resourceName);
                    case FIELD -> String.format("Request body is error with field %s", resourceName);
                    case DIFFERENT -> String.format("Request body is different with field %s", resourceName);
                    case NOTEXISTED -> String.format("Data does not exist with field %s", resourceName);
                    case EXISTED -> String.format("Data have exist with field %s", resourceName);
                    default -> String.format("Error exception with %s", resourceName);
                };
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
