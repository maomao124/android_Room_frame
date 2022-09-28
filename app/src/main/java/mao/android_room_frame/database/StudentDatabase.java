package mao.android_room_frame.database;


import androidx.room.Database;

import androidx.room.RoomDatabase;


import mao.android_room_frame.dao.StudentDao;
import mao.android_room_frame.entity.Student;

/**
 * Project name(项目名称)：android_Room_frame
 * Package(包名): mao.android_room_frame.database
 * Class(类名): StudentDatabase
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/28
 * Time(创建时间)： 13:43
 * Version(版本): 1.0
 * Description(描述)： 无
 */

/*
entities表示该数据库有哪些表，version表示数据库的版本号
exportSchema表示是否导出数据库信息的json串，建议设为false，若设为true还需指定json文件的保
存路径
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase
{
    /**
     * 获取该数据库中某张表的持久化对象
     */
    public abstract StudentDao getStudentDao();
}
