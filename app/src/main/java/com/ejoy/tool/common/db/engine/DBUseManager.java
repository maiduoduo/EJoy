package com.ejoy.tool.common.db.engine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;


import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.fragment.citylist.model.City;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.ejoy.tool.ui.data.resource.GlobalDataProvider.DB_NAME_V2;


/**
 * 原生数据库操作管理类
 */
public class DBUseManager {
    private static final int BUFFER_SIZE = 1024;

    private String DB_PATH;
    private Context mContext;

    public DBUseManager(Context context) {
        this.mContext = context;
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + context.getPackageName() + File.separator + "databases" + File.separator;
        copyDBFile();
    }

    private void copyDBFile(){
        File dir = new File(DB_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        //如果旧版数据库存在，则删除
        File dbV1 = new File(DB_PATH + DB_NAME_V2);
        if (dbV1.exists()){
            dbV1.delete();
        }
        //创建新版本数据库
        File dbFile = new File(DB_PATH +  GlobalDataProvider.LATEST_DB_NAME);
        if (!dbFile.exists()){
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(GlobalDataProvider.LATEST_DB_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<City> getAllCities(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + GlobalDataProvider.LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + GlobalDataProvider.TABLE_NAME, null);
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_NAME));
            String province = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_PROVINCE));
            String pinyin = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_CODE));
            String tip = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_TIP));
            city = new City(name, province, pinyin, code,tip);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }

    public List<City> searchCity(final String keyword){
        String sql = "select * from " + GlobalDataProvider.TABLE_NAME + " where "
                + GlobalDataProvider.COLUMN_C_NAME + " like ? " + "or "
                +GlobalDataProvider.COLUMN_C_PINYIN + " like ? ";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + GlobalDataProvider.LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery(sql, new String[]{"%"+keyword+"%", keyword+"%"});

        List<City> result = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_NAME));
            String province = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_PROVINCE));
            String pinyin = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_CODE));
            String tip = cursor.getString(cursor.getColumnIndex(GlobalDataProvider.COLUMN_C_TIP));
            City city = new City(name, province, pinyin, code,tip);
            result.add(city);
        }
        cursor.close();
        db.close();
        CityComparator comparator = new CityComparator();
        Collections.sort(result, comparator);
        return result;
    }

    /**
     * sort by a-z
     */
    private class CityComparator implements Comparator<City>{
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }
}
