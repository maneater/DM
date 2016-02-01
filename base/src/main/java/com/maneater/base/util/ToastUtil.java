package com.maneater.base.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast toast = null;

	public static void showToast(Context context, String msg) {
		showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
	}

	public static void showToast(Context context, int msg) {
		showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
	}

	private static synchronized void showToast(final Context act, final String msg, final int len) {
		try {
			if (toast != null) {
				toast.setText(msg);
			} else {
				toast = Toast.makeText(act, msg, len);
			}
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void showToast(final Context act, final int msg, final int len) {

		if (toast != null) {
			toast.setText(msg);
		} else {
			toast = Toast.makeText(act, msg, len);
		}
		toast.show();
	}
}
