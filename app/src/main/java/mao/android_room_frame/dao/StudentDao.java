package mao.android_room_frame.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mao.android_room_frame.entity.Student;

/**
 * Project name(项目名称)：android_Room_frame
 * Package(包名): mao.android_room_frame.dao
 * Interface(接口名): StudentDao
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/28
 * Time(创建时间)： 13:31
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Dao
public interface StudentDao
{
    /**
     * 查询所有
     *
     * @return {@link List}<{@link Student}>
     */
    @Query("select * from student")
    List<Student> queryAll();

    /**
     * 查询
     *
     * @param id id
     * @return {@link Student}
     */
    @Query("select * from student where id=:id")
    Student query(Long id);

    /**
     * 插入
     *
     * @param student 学生
     */
    @Insert()
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);

    /**
     * 插入列表
     *
     * @param list 列表
     */
    @Insert
    void insertList(List<Student> list);


    /**
     * 更新
     *
     * @param student 学生
     * @return int
     */
    @Update
    int update(Student student);


    /**
     * 删除
     *
     * @param student 学生
     * @return int
     */
    @Delete
    int delete(Student student);

    /**
     * 删除所有
     */
    @Query("delete from student where 1=1")
    void deleteAll();

}
