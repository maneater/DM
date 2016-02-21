/**
 * @COPYRIGHT Shanghai RaxTone-Tech Co.,Ltd.
 * @Title: MathsUtils.java  
 * @Description: 基本数学工具类
 * @author lei.qu 
 * @date 2012-2-7
 * @version V1.0 
 * 
 * Modification History: 
 * 2012-2-7  |  lei.qu   |  Created 
 */
package com.maneater.base.util;

import android.text.Editable;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class MathsUtils {

	/**
	 * 欧式千位制计数方式显示（比如：300,100,1237），有小数位则显示小数位
	 * 
	 * @param money
	 *            不再使用
	 * @return
	 */
	public static String formatMoney(double money) {

		return formatMoneyWithoutUnit(money) + "元";
	}

	/**
	 * 格式化金额
	 * 
	 * @param money
	 *
	 * @return
	 */
	public static String formatMoneyWithoutUnit(double money) {
		DecimalFormat df = new DecimalFormat("#,##0.##");
		return df.format(money);
	}

	/**
	 * 米转换公里
	 * 
	 * @param metre
	 *            米
	 * @return 千米
	 * @author lei.qu
	 */
	public static double meterConvertKM(double metre) {
		/* 千米进位 */
		double METRE_CARRY = 1000;
		return metre / METRE_CARRY;
	}

	/**
	 * 欧式千位制计数方式显示公里数，保留一位小数
	 * 
	 * @param meter
	 * @return
	 */
	public static String convertDistanceToStr(double meter) {

		return convertDistanceToStrWithoutUnit(meter) + "公里";
	}

	/**
	 * 欧式千位制计数方式显示公里数，保留一位小数
	 * 
	 * @param meter
	 * @return
	 */
	public static String convertDistanceToStrWithoutUnit(double meter) {

		String result;
		if (meter >= 100) {
			double doubleValue = new BigDecimal(meter).divide(new BigDecimal(1000)).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
			if (doubleValue > 1) {
				String format = "#,##0.0";
				DecimalFormat df = new DecimalFormat(format);
				result = df.format(doubleValue);
			} else {
				result = String.valueOf(doubleValue);
			}
		} else {
			result = "0.0";
		}
		return result;
	}

	public static float convertToSpeed(int distance, int time) {
		// 每小时XX公里
		if (time == 0 || distance == 0) {
			return 0.5f;
		}
		return (float) retainDecimalNonUp(distance * 3.6f / time, 1);
	}

	/**
	 * 秒转换小时（小数）
	 * 
	 * @param minute
	 *            分钟
	 * @return 小时
	 * @author lei.qu
	 */
	public static double minutesConvertHour(double minute) {
		/* 小时进位 */
		double MIUTE_CARRY = 60;
		return minute / MIUTE_CARRY;
	}

	/**
	 * double 类型比较是否相等
	 * 
	 * @param a
	 * @param b
	 * @return true：相等，false：不相等
	 * @author lei.qu
	 */
	public static boolean equalsDouble(double a, double b) {
		int decimal = 8;
		BigDecimal aDecimal = new BigDecimal(a);
		BigDecimal bDecimal = new BigDecimal(b);
		aDecimal = aDecimal.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		bDecimal = bDecimal.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		if (aDecimal.equals(bDecimal)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保留几位小数
	 * 
	 * @param result
	 *            需转换的数
	 * @param decimal
	 *            需要保留几位
	 * @return <p>
	 * @author lei.qu
	 */
	public static double retainDecimal(double result, int decimal) {
		BigDecimal bigDecimal = new BigDecimal(result);
		return bigDecimal.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 保留几位小数，不进位
	 * 
	 * @param result
	 * @param decimal
	 * @return
	 * @author jiajing.mo
	 * @date 2012-8-22
	 */
	public static double retainDecimalNonUp(double result, int decimal) {
		BigDecimal bigDecimal = new BigDecimal(result);
		return bigDecimal.setScale(decimal, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 取整数，进位
	 * 
	 * @param result
	 * @return
	 * @author jiajing.mo
	 * @date 2012-8-22
	 */
	public static int retainDecimalUp(double result) {
		return (int) Math.ceil(result);
	}

	/**
	 * 把整数保留几位
	 * <p>
	 * 
	 * @param result
	 *            需转换的整数
	 * @param digit
	 *            需要保留几位
	 * 
	 * 
	 * @return String 转换后的值
	 *         <p>
	 * @author Liangxianlin
	 * @date 2012-1-2
	 */
	public static String retainInteger(long result, int digit) {

		String str = String.valueOf(result);
		if (str.length() < digit) {
			for (int i = 0; i < digit - str.length(); i++) {
				str = "0" + str;
			}
		}

		return str;

	}

	/**
	 * 计算 x-->y 的单位向量
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double[] unitVector(float x1, float y1, float x2, float y2) {
		double x = x2 - x1;
		double y = y2 - y1;
		if (x == 0 && y == 0) {
			return new double[] { 0, 0 };
		}
		double hypot = Math.hypot(x, y);
		return new double[] { x / hypot, y / hypot };
	}

	// /** 计算点绕另一个点旋转后的坐标 */
	// public static void rotatePoint(Point srcPoint, Point centerPoint, int
	// angle) {
	// float angleR = (float) Math.toRadians(angle);
	// int x1 = srcPoint.x;
	// int y1 = srcPoint.y;
	// srcPoint.x = (int) ((x1 - centerPoint.x) * Math.cos(angleR)
	// + (y1 - centerPoint.y) * Math.sin(angleR) + centerPoint.x);
	// srcPoint.y = (int) (-(x1 - centerPoint.x) * Math.sin(angleR)
	// + (y1 - centerPoint.y) * Math.cos(angleR) + centerPoint.y);
	// }

	/**
	 * 获取4位随机数
	 * 
	 * @return
	 */
	public static String getRandomStr() {
		int max = 9999;
		int min = 1000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return String.valueOf(s);
	}

	/**
	 * 格式化计费里程（向下取整）
	 * 
	 * @param mileageOfBilling
	 * @return 0公里，45公里，7，456公里。。。
	 */
	public static String formatMileageOfBilling(int mileageOfBilling) {
		return formatMileageOfBillingWithoutUnit(mileageOfBilling) + "公里";
	}

	/**
	 * 格式化计费里程
	 * 
	 * @param mileageOfBilling
	 * @return
	 */
	public static String formatMileageOfBillingWithoutUnit(int mileageOfBilling) {
		int kilo = mileageOfBilling / 1000;
		String result;
		if (kilo == 0) {
			result = "0";
		} else {
			String format = "#,###";
			DecimalFormat df = new DecimalFormat(format);
			result = df.format(kilo);
		}
		return result;
	}

	/**
	 * 获取字符个数(汉字算1个,两个英文字符算一个,向上取整)
	 * 
	 * @param s
	 * @return
	 */
	public static int getWordsCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;

		}

		if (length % 2 != 0) {
			length = length / 2 + 1;
		} else {
			length = length / 2;
		}
		return length;
	}

	/**
	 * 获取字符个数(汉字算1个,两个英文字符算一个,向上取整)
	 * 
	 * @param s
	 * @return
	 */
	public static int getWordsCount(EditText editText) {
		Editable editable = editText.getText();
		if (editable != null) {
			return getWordsCount(editable.toString());
		} else {
			return 0;
		}
	}

	public static String formatCo2(long co2) {
		double _co2 = co2 / 1000.0;
		DecimalFormat df = new DecimalFormat("#,##0.###");
		return df.format(_co2) + "kg";
	}
	
	public static Number doubleToInt(double d){
		if(d % 1.0 == 0){
		    return (int)d;
		}
		return d;
	}
}
