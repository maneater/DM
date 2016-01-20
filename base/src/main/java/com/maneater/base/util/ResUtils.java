package com.maneater.base.util;

import android.content.Context;

import java.lang.reflect.Field;

public class ResUtils {
	public static int getStringIdentifier(Context context, String name) {
		return resIdentifier(context, "string", name);
	}

	public static String getStringByName(Context context, String name) {
		return context.getString(resIdentifier(context, "string", name));
	}

	public static String getStringByName(Context context, String name, Object... formatArgs) {
		return context.getString(resIdentifier(context, "string", name), formatArgs);
	}

	public static int getLayoutIdentifier(Context context, String name) {
		return resIdentifier(context, "layout", name);
	}

	public static int getStyleIdentifier(Context context, String name) {
		return resIdentifier(context, "style", name);
	}

	public static int getDrawableIdentifier(Context context, String name) {
		return resIdentifier(context, "drawable", name);
	}

	public static int getAnimIdentifier(Context context, String name) {
		return resIdentifier(context, "anim", name);
	}

	public static int getIdIdentifier(Context context, String name) {
		return resIdentifier(context, "id", name);
	}

	public static int getXmlIdentifier(Context context, String name) {
		return resIdentifier(context, "xml", name);
	}

	public static int getStyleableIdentifier(Context context, String name) {
		return resIdentifier(context, "styleable", name);
	}

	public static int getColorIdentifier(Context context, String name) {
		return resIdentifier(context, "color", name);
	}

	public static int getDimenIdentifier(Context context, String name) {
		return resIdentifier(context, "dimen", name);
	}

	/**
	 * 根据自定义属性名称，获取下属自定义属性集的标识集合
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static int[] getAttributesIdentifier(Context context, String name) {
		Field[] styleFields;
		try {
			styleFields = Class.forName(
					new StringBuilder().append(context.getPackageName()).append(".R$styleable")
							.toString()).getFields();
			if (styleFields != null && styleFields.length > 0) {
				int size = styleFields.length;
				for (int i = 0; i < size; i++) {
					if (styleFields[i].getName().equals(name)) {
						return (int[]) styleFields[i].get(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static int resIdentifier(Context context, String type, String name) {
		return context.getResources().getIdentifier(name, type, context.getPackageName());
	}
}
