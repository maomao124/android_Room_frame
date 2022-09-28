package mao.android_room_frame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mao.android_room_frame.application.MainApplication;
import mao.android_room_frame.dao.StudentDao;

public class MainActivity extends AppCompatActivity
{

    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDao = MainApplication.getInstance().getStudentDatabase().getStudentDao();
    }


}