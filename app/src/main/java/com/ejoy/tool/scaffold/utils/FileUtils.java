package com.ejoy.tool.scaffold.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;


import com.ejoy.tool.app.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

public class FileUtils {
	public static final String CACHE = "cache";
	public static final String ICON = "icon";
	public static final String ROOT = "GooglePlay";
	public static final String FILE_DIRECTOR_NAME = "Ejoy/cache";
	private static final int EOF = -1;
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	private static final double ONE_KB = 1024;
	private static final String FILE_DIRECTOR_HEAD_NAME = "Ejoy/cache";
	/**
	 * 获取图片的缓存的路径
	 * @return
	 */
	public static File getIconDir(){
		return getDir(ICON);
		
	}
	/**
	 * 获取缓存路径
	 * @return
	 */
	public static File getCacheDir() {
		return getDir(CACHE);
	}
	public static File getDir(String cache) {
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(cache);// /mnt/sdcard/GooglePlay/cache
			
		}else{
			File filesDir = Utils.getContext().getCacheDir();    //  cache  getFileDir file
			path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
			path.append(File.separator);///data/data/com.itheima.googleplay/cache/
			path.append(cache);///data/data/com.itheima.googleplay/cache/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// 创建文件夹
		}
		return file;

	}

	private static boolean isSDAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


	public static File resultImageFile() {
		return new File(outFileDirectory().getAbsolutePath(), "ejoy" + System.currentTimeMillis() + ".jpg");
	}

	public static File outFileDirectory() {
		String storageState = Environment.getExternalStorageState();
		File rootFile = storageState.equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory() : App.getAppContext().getCacheDir();
		rootFile = new File(rootFile.getAbsolutePath(), FILE_DIRECTOR_NAME);
		if (!rootFile.exists() || !rootFile.isDirectory()) {
			rootFile.mkdirs();
		}
		return rootFile;
	}

	public static File outFileDirectory(String directorName) {
		String storageState = Environment.getExternalStorageState();
		File rootFile = storageState.equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory() : App.getAppContext().getCacheDir();
		rootFile = new File(rootFile.getAbsolutePath(), directorName);
		if (!rootFile.exists() || !rootFile.isDirectory()) {
			rootFile.mkdirs();
		}
		return rootFile;
	}


	public static Uri fileToUri(@NonNull Context context, @NonNull File file, @NonNull Intent intent) {
		Uri uri;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			String authority = context.getPackageName() + ".provider";
			uri = FileProvider.getUriForFile(context, authority, file);
			List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
			if (resolveInfos != null && resolveInfos.size() > 0)
				for (ResolveInfo resolveInfo : resolveInfos) {
					String packageName = resolveInfo.activityInfo.packageName;
					context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
				}
		} else {
			uri = Uri.fromFile(file);
		}
		return uri;
	}


	/**
	 * Uri和File相互转
	 * 			兼容各个版本
	 * @param context
	 * @param uri
	 * @return
	 */
    public static File uriToFile(Context context,Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

	public static File from(Context context, Uri uri) throws IOException {
		InputStream inputStream = context.getContentResolver().openInputStream(uri);
		String fileName = getFileName(context, uri);
		String[] splitName = splitFileName(fileName);
		File tempFile = File.createTempFile(splitName[0], splitName[1]);
		tempFile = rename(tempFile, fileName);
		tempFile.deleteOnExit();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(tempFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (inputStream != null) {
			copy(inputStream, out);
			inputStream.close();
		}

		if (out != null) {
			out.close();
		}
		return tempFile;
	}

	private static String getFileName(Context context, Uri uri) {
		String result = null;
		if (uri.getScheme().equals("content")) {
			Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
			try {
				if (cursor != null && cursor.moveToFirst()) {
					result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		if (result == null) {
			result = uri.getPath();
			int cut = result.lastIndexOf(File.separator);
			if (cut != -1) {
				result = result.substring(cut + 1);
			}
		}
		return result;
	}

	private static long copy(InputStream input, OutputStream output) throws IOException {
		long count = 0;
		int n;
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		while (EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	private static String[] splitFileName(String fileName) {
		String name = fileName;
		String extension = "";
		int i = fileName.lastIndexOf(".");
		if (i != -1) {
			name = fileName.substring(0, i);
			extension = fileName.substring(i);
		}

		return new String[]{name, extension};
	}

	private static File rename(File file, String newName) {
		File newFile = new File(file.getParent(), newName);
		if (!newFile.equals(file)) {
			if (newFile.exists() && newFile.delete()) {
				Log.e("FileUtils", "Delete old " + newName + " file");
			}
			if (file.renameTo(newFile)) {
				Log.e("FileUtils", "Rename file to " + newName);
			}
		}
		return newFile;
	}


	public static boolean isImageFile(File file) {
		if (file != null && file.exists() && file.isFile()) {
			String imagePath = file.getAbsolutePath();
			if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg") || imagePath.endsWith(".png") ||
					imagePath.endsWith(".bmp") || imagePath.endsWith(".webp") || imagePath.endsWith(".JPG")) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 计算图片大小
	 * @param fileSize 文件长度
	 * @return
	 */
	public static String imageSize(long fileSize) {
		DecimalFormat df = new DecimalFormat(".##");
		if (fileSize * 1.0d / FileUtils.ONE_KB < 1) {
			return df.format(fileSize) + "B";
		} else if (fileSize * 1.0d / FileUtils.ONE_KB >= 1 && fileSize * 1.0d / FileUtils.ONE_KB < FileUtils.ONE_KB) {
			return df.format(fileSize * 1.0d / FileUtils.ONE_KB) + "KB";
		} else {
			return df.format(fileSize * 1.0d / FileUtils.ONE_KB / FileUtils.ONE_KB) + "M";
		}
	}


	public static void scanImage(@NonNull Context context, @NonNull File file) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			MediaScanner ms = new MediaScanner(context, file);
			ms.refresh();
		} else {
			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			intent.setData(Uri.fromFile(file));
			context.sendBroadcast(intent);
		}
	}

	public static File getFileDirectorHead(Context context) {
		String storageState = Environment.getExternalStorageState();
		File rootFile = storageState.equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory() : context.getCacheDir();
		return new File(rootFile, FILE_DIRECTOR_HEAD_NAME);
	}


	/**
	 * 删除文件夹所有文件或者删除指定文件
	 *
	 * @param file 文件或者文件夹
	 */
	public static void deleteAllFile(final @NonNull File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files != null && files.length > 0) {
					for (File childFile : files) {
						deleteAllFile(childFile);
					}
				}
			}
		}

	}


	public void deletePhotoWithPath(String path) {
		if (path != null && path.length() > 0) {
			File file = new File(path);
			deleteFile(file);
		}
	}

	public void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		}
	}

	public static String getReadableFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	/**
	 * 不带单位
	 *   格式化结果为：kb
	 * @param size
	 * @return
	 */
	public static String getReadableFileSizeWithoutB(long size) {
		if (size <= 0) {
			return "0";
		}
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " ";
	}

	/**
	 * 从asset路径下读取对应文件转String输出
	 *
	 * @param mContext
	 * @return
	 */
	public static String getJson(Context mContext, String fileName) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		AssetManager am = mContext.getAssets();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					am.open(fileName)));
			String next = "";
			while (null != (next = br.readLine())) {
				sb.append(next);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.delete(0, sb.length());
		}
		return sb.toString().trim();
	}

	/**
	 * 按行读取文本文件
	 *
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String readTextFromFile(InputStream is) throws Exception {
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer buffer = new StringBuffer("");
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
			buffer.append("\n");
		}
		return buffer.toString().trim();
	}





}
