package mao.android_room_frame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mao.android_room_frame.application.MainApplication;
import mao.android_room_frame.dao.StudentDao;
import mao.android_room_frame.entity.Student;

public class MainActivity extends AppCompatActivity
{

    private StudentDao studentDao;
    private TextView textView;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDao = MainApplication.getInstance().getStudentDatabase().getStudentDao();

        textView = findViewById(R.id.TextView1);

        editText1 = findViewById(R.id.EditText1);
        editText2 = findViewById(R.id.EditText2);
        editText3 = findViewById(R.id.EditText3);
        editText4 = findViewById(R.id.EditText4);


        findViewById(R.id.Button1).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insert();
            }
        });

        findViewById(R.id.Button2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                update();
            }
        });

        findViewById(R.id.Button3).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                delete();
            }
        });

        findViewById(R.id.Button4).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        findViewById(R.id.Button5).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    /**
     * 插入
     */
    private void insert()
    {
        try
        {
            Long id = Long.valueOf(editText1.getText().toString());
            String name = editText2.getText().toString();
            String sex = editText3.getText().toString();
            int age = Integer.parseInt(editText4.getText().toString());

            if (!sex.equals("男") && !sex.equals("女"))
            {
                throw new Exception("性别只能为男或者女");
            }
            Student student = new Student(id, name, sex, age);
            studentDao.insert(student);
            toastShow("已尝试插入");
        }
        catch (Exception e)
        {
            Log.e(TAG, "insert: ", e);
            toastShow("异常：" + e.getMessage());
        }
    }


    /**
     * 更新
     */
    private void update()
    {
        try
        {
            Long id = Long.valueOf(editText1.getText().toString());
            String name = editText2.getText().toString();
            String sex = editText3.getText().toString();
            int age = Integer.parseInt(editText4.getText().toString());

            Student student = studentDao.query(id);
            if (student == null)
            {
                throw new Exception("未查询到学号为" + id + "的信息");
            }
            student.setName(name);
            student.setSex(sex);
            student.setAge(age);
            int update = studentDao.update(student);
            if (update <= 0)
            {
                throw new Exception("更新失败");
            }
            toastShow("更新成功");
        }
        catch (Exception e)
        {
            Log.e(TAG, "update: ", e);
            toastShow("异常：" + e.getMessage());
        }
    }

    /**
     * 删除
     */
    private void delete()
    {
        try
        {
            if (editText1.getText().toString().equals(""))
            {
                toastShow("学号为空");
                return;
            }
            long id = Long.parseLong(editText1.getText().toString());

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("删除确认")
                    .setMessage("是否删除学号为" + id + "的信息？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            int delete = studentDao.delete(new Student(id, null, null, 0));
                            if (delete <= 0)
                            {
                                toastShow("删除失败");
                                return;
                            }
                            toastShow("删除成功");
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();

        }
        catch (Exception e)
        {
            Log.e(TAG, "delete: ", e);
            toastShow("异常：" + e.getMessage());
        }

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
}