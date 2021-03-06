package com.dzfp.dzfp.ui.custom;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dzfp.dzfp.R;


public class IPEditText extends LinearLayout {

    private EditText mFirstIP;
    private EditText mSecondIP;
    private EditText mThirdIP;
    private EditText mFourthIP;

    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;

    private SharedPreferences mPreferences;

    public IPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(
                R.layout.custom_my_edittext, this);
        mFirstIP = (EditText) findViewById(R.id.ip_first);
        mSecondIP = (EditText) findViewById(R.id.ip_second);
        mThirdIP = (EditText) findViewById(R.id.ip_third);
        mFourthIP = (EditText) findViewById(R.id.ip_fourth);

        mPreferences = context.getSharedPreferences("config_IP",
                Context.MODE_PRIVATE);

        OperatingEditText(context);
    }


    private void OperatingEditText(final Context context) {
        mFirstIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText1 = s.toString().substring(0, s.length() - 1);
                            mFirstIP.setText(mText1);
                        } else {
                            mText1 = s.toString().trim();
                        }

                        if (Integer.parseInt(mText1) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                       /* Editor editor = mPreferences.edit();
                        editor.putInt("IP_FIRST", mText1.length());
                        editor.commit();*/

                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSecondIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText2 = s.toString().substring(0, s.length() - 1);
                            mSecondIP.setText(mText2);
                        } else {
                            mText2 = s.toString().trim();
                        }

                        if (Integer.parseInt(mText2) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                       /* Editor editor = mPreferences.edit();
                        editor.putInt("IP_SECOND", mText2.length());
                        editor.commit();*/

                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();
                    }
                }


             /*   if (start == 0 && s.length() == 0) {
                    mFirstIP.setFocusable(true);
                    mFirstIP.requestFocus();
                    mFirstIP.setSelection(mPreferences.getInt("IP_FIRST", 0));
                }*/

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mThirdIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText3 = s.toString().substring(0, s.length() - 1);
                            Log.e("it3", mText3);
                            mThirdIP.setText(mText3);
                        } else {
                            mText3 = s.toString().trim();
                            Log.e("et3", mText3);
                        }

                        if (Integer.parseInt(mText3) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                      /*  Editor editor = mPreferences.edit();
                        editor.putInt("IP_THIRD", mText3.length());
                        editor.commit();*/

                        mFourthIP.setFocusable(true);
                        mFourthIP.requestFocus();
                    }
                }


                /*if (start == 0 && s.length() == 0) {
                    mSecondIP.setFocusable(true);
                    mSecondIP.requestFocus();
                    mSecondIP.setSelection(mPreferences.getInt("IP_SECOND", 0));
                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFourthIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s != null && s.length() > 0) {
                    mText4 = s.toString().trim();

                    if (Integer.parseInt(mText4) > 255) {
                        Toast.makeText(context, "请输入合法的ip地址", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }

                    /*Editor editor = mPreferences.edit();
                    editor.putInt("IP_FOURTH", mText4.length());
                    editor.commit();*/
                }


                /*if (start == 0 && s.length() == 0) {
                    mThirdIP.setFocusable(true);
                    mThirdIP.requestFocus();
                    mThirdIP.setSelection(mPreferences.getInt("IP_THIRD", 0));
                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public String getText(Context context) {
        if (TextUtils.isEmpty(mText1) || TextUtils.isEmpty(mText2)
                || TextUtils.isEmpty(mText3) || TextUtils.isEmpty(mText4)) {
            Toast.makeText(context, "请输入合法的ip地址", Toast.LENGTH_LONG).show();
        }
        return mFirstIP.getText().toString() + "." + mSecondIP.getText().toString() + "." + mThirdIP.getText().toString() + "." + mFourthIP.getText().toString();
    }

    public void setText(String ip1, String ip2, String ip3, String ip4) {
        mFirstIP.setText(ip1);
        mSecondIP.setText(ip2);
        mThirdIP.setText(ip3);
        mFourthIP.setText(ip4);
    }
}
