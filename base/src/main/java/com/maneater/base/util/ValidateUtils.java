package com.maneater.base.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import com.maneater.base.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUtils {


    public static final int INPUT_PASSWORD_MIN = 6;

    public static final int INPUT_PASSWORD_MAX = 16;

    public static final int INPUT_VERIFICATION_CODE_MIN = 4;

    public static final int INPUT_VERIFICATION_CODE_MAX = 4;

    /**
     * 验证手机号
     *
     * @param editText
     * @return
     * @author jiajing.mo
     * @date 2012-8-16
     */
    public static boolean validatePhone(EditText editText) {
        return validatePhone(editText, false);
    }

    public static boolean validatePhone(EditText editText, boolean isAllowedNull) {
        return validatePhone(editText, false, false);
    }

    public static boolean validatePhone(EditText editText, boolean isAllowedNull, boolean toast) {
        Context context = editText.getContext();
        String phone = editText.getText().toString().trim();
        String errorStr = null;
        boolean flag = toast;
        if (isAllowedNull && TextUtils.isEmpty(phone)) {
            return true;
        } else if (TextUtils.isEmpty(phone)) {
            errorStr = context.getString(R.string.input_mobile_null_error);
        } else if (!ValidateUtils.isPhone(phone)) {
            errorStr = context.getString(R.string.input_mobile_error);
        } else {
            flag = true;
        }
        if (!flag) {
            ToastUtil.showToast(context, errorStr);
        }
        return flag;
    }


    /**
     * 验证姓名
     *
     * @param editText
     * @return
     * @author jiajing.mo
     * @date 2012-8-16
     */
    public static boolean validateName(EditText editText) {
        return validateName(editText, false);
    }


    public static boolean validateName(EditText editText, boolean isAllowedNull) {
        Context context = editText.getContext();
        String name = editText.getText().toString().trim();
        String errorStr = null;
        boolean flag = false;
        if (isAllowedNull && TextUtils.isEmpty(name)) {
            return true;
        } else if (TextUtils.isEmpty(name)) {
            errorStr = context.getString(R.string.input_name_null_error);
        } else if (MathsUtils.getWordsCount(name) > 10) {
            errorStr = "请输入真实的姓名";
        } else {
            flag = true;
        }
        if (!flag) {
            ToastUtil.showToast(context, errorStr);
        }
        return flag;
    }


    /**
     * 验证验证码
     *
     * @param editText
     * @return
     * @author jiajing.mo
     * @date 2012-8-16
     */
    public static boolean validateVerificationCode(EditText editText) {
        Context context = editText.getContext();
        String str = editText.getText().toString();
        String errorStr = null;
        boolean flag = false;
        if (TextUtils.isEmpty(str)) {
            errorStr = context.getString(R.string.input_verification_code_null_error);
        } else if (!validateLength(str, INPUT_VERIFICATION_CODE_MIN, INPUT_VERIFICATION_CODE_MAX) || !TextUtils.isDigitsOnly(str)) {
            errorStr = context.getString(R.string.input_verification_code_format_error,
                    INPUT_VERIFICATION_CODE_MIN);
        } else {
            flag = true;
        }
        if (!flag) {
            ToastUtil.showToast(context, errorStr);
        }
        return flag;
    }

    public static boolean isVerificationCode(EditText editText) {
        String str = editText.getText().toString();
        boolean flag = false;
        if (TextUtils.isEmpty(str)) {
        } else if (!validateLength(str, INPUT_VERIFICATION_CODE_MIN, INPUT_VERIFICATION_CODE_MAX) || !TextUtils.isDigitsOnly(str)) {
        } else {
            flag = true;
        }
        return flag;
    }


    public static boolean validateLength(String str, int minLength, int maxLength) {
        int length = str.length();
        return length <= maxLength && length >= minLength;
    }

    public static boolean isPhone(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isPhone(EditText editText) {
        String string = editText.getText().toString();
        if (StringUtils.isBlank(string)) {
            return false;
        }
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }


    public static boolean validateStr(String str, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean validateBlank(EditText editText, String label) {
        if (editText != null && StringUtils.isBlank(editText.getText())) {
            ToastUtil.showToast(editText.getContext(), String.format("%1$s不能为空", label));
            return false;
        }
        return true;
    }

    /**
     * 验证输入内容是否为空
     *
     * @param editText
     * @return
     */
    public static boolean validateIsBlank(EditText editText) {
        return !(editText != null && editText.getText() != null && StringUtils.isNotBlank(editText.getText().toString()));

    }
}
