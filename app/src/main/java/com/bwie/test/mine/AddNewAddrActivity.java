package com.bwie.test.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.test.myelectronicmall.R;

public class AddNewAddrActivity extends AppCompatActivity {
    private ImageView mDetailImageBack;
    /**
     * 保存
     */
    private TextView mTextSave;
    private RelativeLayout mDetaiRelative;
    /**
     * 收货人
     */
    private TextView mTextPerson;
    private EditText mEditPerson;
    /**
     * 联系电话
     */
    private TextView mTextPhone;
    private EditText mEditPhone;
    /**
     * 所在地区
     */
    private TextView mTextArea;
    /**
     * 请选择 >
     */
    private TextView mEditArea;
    private LinearLayout mLinearArea;
    /**
     * 街道
     */
    private TextView mTextRoad;
    private EditText mEditRoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_addr);
        //隐藏原有标题
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        mDetaiRelative = (RelativeLayout) findViewById(R.id.detai_relative);
        mDetailImageBack = (ImageView) findViewById(R.id.detail_image_back);
        mTextSave = (TextView) findViewById(R.id.text_save);
        mTextPerson = (TextView) findViewById(R.id.text_person);
        mEditPerson = (EditText) findViewById(R.id.edit_person);
        mTextPhone = (TextView) findViewById(R.id.text_phone);
        mEditPhone = (EditText) findViewById(R.id.edit_phone);
        mLinearArea = (LinearLayout) findViewById(R.id.linear_area);
        mTextArea = (TextView) findViewById(R.id.text_area);
        mEditArea = (TextView) findViewById(R.id.edit_area);
        mTextRoad = (TextView) findViewById(R.id.text_road);
        mEditRoad = (EditText) findViewById(R.id.edit_road);
    }
}
