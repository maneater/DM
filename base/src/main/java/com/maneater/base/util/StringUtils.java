package com.maneater.base.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	public static String getMD5(String content) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(content.getBytes("utf-8"));
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	// 将字符串转化成时间
	public static Date strToDate(String dateStr) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, e
	 * 
	 * mpty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to
	 *        isBlank(CharSequence)
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and
	 *         not whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isNotBlank(String) to
	 *        isNotBlank(CharSequence)
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}

	public static String splitDoubleToString(double doubleNum, int scale) {
		String sDouble = String.valueOf(doubleNum);
		int dotIndex = sDouble.indexOf(".");
		if (dotIndex + scale > sDouble.length() - 1) {
			return sDouble;
		} else {
			return sDouble.substring(0, dotIndex + scale + 1);
		}
	}

	/**
	 * 四舍五入后保留小数点后 pointScale 位，不足用0补齐
	 * 
	 * @param num
	 * @param pointScale
	 *            精确到小数点后 几位
	 * @return
	 */
	public static String roundDoubleToString(double num, int pointScale) {
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(pointScale, 4);
		String result = (bd.doubleValue() + "");
		int doIndex = result.indexOf(".");

		if (result.length() - (doIndex + 1) < pointScale) {
			result = appendZero(result, pointScale - (result.length() - (doIndex + 1)));
		}

		return result;
	}

	/**
	 * @param src
	 * @param i
	 * @return
	 */
	private static String appendZero(String src, int i) {
		StringBuilder result = new StringBuilder(src);
		while (i > 0) {
			result.append("0");
			i--;
		}
		return result.toString();
	}

	/**
	 * 获取字符串的字节数，一个中文为2个字节，一个字母或数字为1个字节
	 * 
	 * @param s
	 * @return
	 */
	public static int getByteCountsOfString(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;

		}
		return length;

	}

	/**
	 * @param string
	 * @param defValue
	 * @return
	 */
	public static int forInt(String string, int defValue) {
		try {
			return Integer.valueOf(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defValue;
	}

	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return all blank or all not blank && equals
	 */
	public static boolean isSame(String str1, String str2) {
		// all blank
		if (StringUtils.isBlank(str1) && StringUtils.isBlank(str2)) {
			return true;
		}
		// ALL not blank && equals
		if (!StringUtils.isBlank(str1) && !StringUtils.isBlank(str2) && str1.equals(str2)) {
			return true;
		}
		return false;
	}

	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static String hiddenMobile(String mobile) {
		if (mobile != null && mobile.length() == 11) {
			String starStr = mobile.substring(0, 3);
			String endStr = mobile.substring(7);
			mobile = starStr + "****" + endStr;
		}
		return mobile;
	}

	public static String GetInetAddress(String host) {
		String IPAddress = "";
		InetAddress ReturnStr1 = null;
		try {
			ReturnStr1 = InetAddress.getByName(host);
			IPAddress = ReturnStr1.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return IPAddress;
		}
		return IPAddress;
	}
}
