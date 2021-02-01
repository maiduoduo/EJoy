package com.ejoy.tool.app;



//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛


import android.util.Log;

import com.ejoy.tool.greendao.gen.DaoMaster;
import com.ejoy.tool.greendao.gen.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.imaidd.citypicker.style.citylist.utils.CityListLoader;
import com.kongzue.dialog.v2.DialogSettings;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.ejoy.tool.ui.data.resource.GlobalDataProvider.DB_NAME_V2;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_IOS;


/**
 * CN:      App
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/10/14
 * Des:    必须继承BaseApplication
 */
public class App extends BaseMApplication {

    private static App instance;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //初始化Fresco
        Fresco.initialize(this);
        DialogSettings.type = TYPE_IOS;

        /**
         * 预先加载一级列表所有城市的数据
         */
        CityListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);

        initLocalDb();


    }

    private void initLocalDb() {

        //复制assets目录下的数据库文件到应用数据库中
        try {
            copyDataBase(DB_NAME_V2);
        } catch (Exception e) {
            Log.e("Fragment", e.getMessage());
        }

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME_V2, null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }


    private void init() {
        instance = this;
    }


    /**
     * 是否打印日志
     *
     * @return
     */
    @Override
    protected boolean isDebug() {
        return false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDataBase(String dbname) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = this.getAssets().open(dbname);
        // Path to the just created empty db
        File outFileName = this.getDatabasePath(dbname);

        if (!outFileName.exists()) {
            outFileName.getParentFile().mkdirs();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }

}
