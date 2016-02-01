package com.maneater.base.util;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

public class TakePhotoUtil {

	private String FILE_DIR = null;
	private final static String TAKE_PRE = "photo_take";
	private final static String ZOOM_PRE = "photo_zoom";
	private final static String NAME_APEND = ".jpg";

	public final static int REQ_TAKE_PHOTO = 115;
	public final static int REQ_ZOOM_PHOTO = 114;
	public final static int REQ_CHOSE_PHOTO = 113;

	private int zoomW;
	private int zommH;
	private boolean zoom;

	private String fileKey;

	private Context context;

	/**
	 * 
	 * @param fileKey
	 *            唯一文件标识
	 * @param zoom
	 *            是否需要裁剪
	 * @param zoomW
	 *            裁剪w
	 * @param zommH
	 *            裁剪h
	 */
	public TakePhotoUtil(Context context, String fileKey, boolean zoom,
			int zoomW, int zommH) {
		this.fileKey = fileKey;
		this.zoomW = zoomW;
		this.zommH = zommH;
		this.zoom = zoom;
		this.context = context;

		if (StorageDir.getInstance(context).hasDir()) {
			FILE_DIR = StorageDir.getInstance(context).getAppRootDir()
					.getAppRootPath();
		}
	}

	public static boolean canHandle(int requestCode) {
		return requestCode == REQ_TAKE_PHOTO || requestCode == REQ_ZOOM_PHOTO
				|| requestCode == REQ_CHOSE_PHOTO;
	}

	private boolean checkSD(Context context) {
		if (StringUtils.isNotBlank(FILE_DIR)) {
			return true;
		} else {
			ToastUtil.showToast(context, "sd卡不可用");
			return false;
		}
	}

	private void reCreateTakeName() {
		takePath = FILE_DIR + File.separator + TAKE_PRE + fileKey
				+ System.currentTimeMillis() + NAME_APEND;
	}

	private String takePath = "";

	public void invokeTakePhoto(Activity activity) {
		if (checkSD(activity)) {
			prepare();
			deleteFile(takePath);
			reCreateTakeName();
			Uri outFileUri = Uri.fromFile(new File(takePath));
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// intent.putExtra("imageDir", TAKE_PRE + fileKey + NAME_APEND);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outFileUri);
			activity.startActivityForResult(intent, REQ_TAKE_PHOTO);
		}
	}

	public Uri handleTakeResult(Intent data) {
		return Uri.fromFile(new File(takePath));
	}

	public void invokeChoosePhoto(Activity activity) {
		if (checkSD(activity)) {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			Intent wrapperIntent = Intent.createChooser(intent, null);
			activity.startActivityForResult(wrapperIntent, REQ_CHOSE_PHOTO);
		}
	}

	public Uri handleChoosePhoto(Intent data) {
		String filePath = ChooseFileUtil.getPath(context, data.getData());
		return Uri.fromFile(new File(filePath));
	}

	public void invokeZoomPhoto(Activity activity, Uri srcFile) {
		if (checkSD(activity)) {
			try {
				prepare();
				String zoomFilePath = FILE_DIR + File.separator + ZOOM_PRE
						+ fileKey + NAME_APEND;
				deleteFile(zoomFilePath);
				new File(zoomFilePath).createNewFile();
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(srcFile, "image/*");
				intent.putExtra("crop", true);
				intent.putExtra("aspectX", zoomW);
				intent.putExtra("aspectY", zommH);
				intent.putExtra("outputX", zoomW > 0 ? zoomW : 300);
				intent.putExtra("outputY", zommH > 0 ? zommH : 300);
				intent.putExtra("return-data", false);
				intent.putExtra("scale", true);
				intent.putExtra("noFaceDetection", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(zoomFilePath)));
				intent.putExtra("outputFormat",
						Bitmap.CompressFormat.JPEG.toString());
				activity.startActivityForResult(intent, REQ_ZOOM_PHOTO);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(activity, "图片未找到", Toast.LENGTH_LONG).show();
			}
		}
	}

	public Uri handleZoomPhoto(Intent data) {
		String zoomFilePath = FILE_DIR + File.separator + ZOOM_PRE + fileKey
				+ NAME_APEND;
		if (new File(zoomFilePath).exists()) {
			return Uri.fromFile(new File(zoomFilePath));
		}
		return null;
	}

	private void prepare() {
		createDir(FILE_DIR);
		deleteFile(FILE_DIR + File.separator + ZOOM_PRE + File.separator
				+ fileKey + NAME_APEND);
	}

	/**
	 * 创建目录(不存在时创建)
	 */
	private static File createDir(String dir) {
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}

	private static boolean deleteFile(String filePath) {
		File fileDir = new File(filePath);
		return fileDir.delete();
	}

	/**
	 * 
	 * @param requestCode
	 * @param data
	 *            仅会在最后一步返回Uri
	 * @param activity
	 * @return
	 */
	public Uri defaultHandleResult(int requestCode, Intent data,
			Activity activity) {
		switch (requestCode) {
		case REQ_TAKE_PHOTO:
			Uri takeUri = handleTakeResult(data);
			if (zoom) {
				invokeZoomPhoto(activity, takeUri);
			} else {
				return takeUri;
			}
			break;
		case REQ_CHOSE_PHOTO:
			Uri chooseUri = handleChoosePhoto(data);
			if (zoom) {
				invokeZoomPhoto(activity, chooseUri);
			} else {
				return chooseUri;
			}
			break;
		case REQ_ZOOM_PHOTO:
			return handleZoomPhoto(data);
		}
		return null;
	}

	/** 清除临时拍摄的文件，截图的文件 ，仅在确定不使用相关文件的时候调用此方法 */
	public void clear() {
		String zoomFilePath = FILE_DIR + File.separator + ZOOM_PRE + fileKey
				+ NAME_APEND;
		deleteFile(zoomFilePath);
		deleteFile(takePath);
	}
}
