package mao.android_room_frame.application;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import java.util.HashMap;
import java.util.Map;

import mao.android_room_frame.database.StudentDatabase;

/**
 * Project name(项目名称)：android_Room_frame
 * Package(包名): mao.android_room_frame.application
 * Class(类名): MainApplication
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/28
 * Time(创建时间)： 13:53
 * Version(版本): 1.0
 * Description(描述)： 无
 */


public class MainApplication extends Application
{
    /**
     * 标签
     */
    private static final String TAG = "MainApplication";

    /**
     * 实例，单例模式
     */
    private static volatile MainApplication mainApplication;

    public Map<String, Object> map = new HashMap<>();

    private StudentDatabase studentDatabase;

    /**
     * 得到StudentDatabase
     *
     * @return {@link StudentDatabase}
     */
    public StudentDatabase getStudentDatabase()
    {
        return studentDatabase;
    }

    /**
     * 获得实例
     *
     * @return {@link MainApplication}
     */
    public static MainApplication getInstance()
    {
        return mainApplication;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mainApplication = this;

        studentDatabase = Room.databaseBuilder(this, StudentDatabase.class, "student")
                //允许迁移数据库（发生数据库变更时，Room默认删除原数据库再创建新数据库。
                //如此一来原来的记录会丢失，故而要改为迁移方式以便保存原有记录）
                .addMigrations()
                //// 允许在主线程中操作数据库（Room默认不能在主线程中操作数据库）
                .allowMainThreadQueries()
                .build();


    }

    /**
     * This method is for use in emulated process environments.  It will
     * never be called on a production Android device, where processes are
     * removed by simply killing them; no user code (including this callback)
     * is executed when doing so.
     */
    @Override
    public void onTerminate()
    {
        super.onTerminate();
        Log.d(TAG, "onTerminate: ");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
    }
}
