package mao.android_room_frame;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mao.android_room_frame.application.MainApplication;
import mao.android_room_frame.dao.StudentDao;
import mao.android_room_frame.entity.Student;

public class MainActivity2 extends AppCompatActivity
{

    private EditText editText1;
    private EditText editText2;

    private static final String TAG = "MainActivity2";
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        studentDao = MainApplication.getInstance().getStudentDatabase().getStudentDao();

        editText1 = findViewById(R.id.EditText2_1);
        editText2 = findViewById(R.id.EditText2_2);

        findViewById(R.id.Button_2_1).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insertList();
            }
        });

        findViewById(R.id.Button_2_2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                deleteAll();
            }
        });


    }


    /**
     * 批量插入
     */
    private void insertList()
    {
        try
        {
            int count = Integer.parseInt(editText1.getText().toString());
            long startId = Long.parseLong(editText2.getText().toString());
            if (count < 1)
            {
                toastShow("数量小于1");
                return;
            }
            if (startId <= 0)
            {
                toastShow("起始学号小于0");
                return;
            }

            List<Student> list = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                long id = startId + i;
                String name = UUID.randomUUID().toString().substring(0, 6);
                String sex = getIntRandom(1, 10) > 5 ? "男" : "女";
                int age = getIntRandom(15, 30);

                Student student = new Student(id, name, sex, age);
                list.add(student);
            }
            studentDao.insertList(list);
            toastShow("已尝试批量插入");
        }
        catch (Exception e)
        {
            Log.e(TAG, "InsertList: ", e);
            toastShow("异常：" + e.getMessage());
        }
    }

    /**
     * 删除所有
     */
    private void deleteAll()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("清空全部数据！")
                .setMessage("这将会清空所有的数据，是否继续？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            studentDao.deleteAll();
                            toastShow("清空成功");
                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, "onClick: ", e);
                            toastShow("异常：" + e.getMessage());
                        }
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toastShow("点击取消");
                    }
                })
                .create()
                .show();
    }

    /**
     * 显示消息
     *
     * @param message 消息
     */
    private void toastShow(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到int随机
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     */
    public static int getIntRandom(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }
}