package com.maneater.base.util;

import android.content.Context;
import android.view.View;

import java.lang.reflect.*;
import java.util.Arrays;

public class InjectUtil {

    public interface InjectViewAble {
        public View findViewById(int viewId);
    }

    /**
     * 初始化所有 声明的 View 属性；约定 控件属性名和控件ID名相同
     */
    public static void injectViews(InjectViewAble target, Context mContext, View.OnClickListener onClickListener) {
        Field[] fields = target.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String name = field.getName();
                View view = target.findViewById(ResUtils.getIdIdentifier(mContext, name));
                if (view != null) {
                    Class<?> fieldClz = field.getType();
                    if (View.class.isAssignableFrom(fieldClz)) {
                        boolean canExecute = field.isAccessible();
                        field.setAccessible(true);
                        field.set(target, view);
                        if (field.getName().lastIndexOf("ACK") != -1 && onClickListener != null) {
                            view.setOnClickListener(onClickListener);
                        }
                        field.setAccessible(canExecute);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Type getParameterUpperBound(int index, ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (types.length <= index) {
            throw new IllegalArgumentException("Expected at least " + index + " type argument(s) but got: " + Arrays.toString(types));
        } else {
            Type paramType = types[index];
            return paramType instanceof WildcardType ? ((WildcardType) paramType).getUpperBounds()[0] : paramType;
        }
    }


    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType className2 = (ParameterizedType) type;
            Type rawType = className2.getRawType();
            if (!(rawType instanceof Class)) {
                throw new IllegalArgumentException();
            } else {
                return (Class) rawType;
            }
        } else if (type instanceof GenericArrayType) {
            Type className1 = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(className1), 0).getClass();
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
        }
    }
}
