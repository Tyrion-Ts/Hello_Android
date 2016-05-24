package com.example.hello_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by 健宇 on 2016/5/23.
 * 计算器Activity
 */
public class CalcActivity extends Activity {

    GridLayout gridLayout;

    String[] chars = new String[] {
            "√", "^", "C", "B",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };

    DecimalFormat decimalFormat = new DecimalFormat("###.000000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //使用grid.xml文件定义界面布局
        setContentView(R.layout.calc);

        gridLayout = (GridLayout) findViewById(R.id.root);
        for (int i = 0; i < chars.length; i++) {
            Button bn = new Button(this);
            bn.setText(chars[i]);
            bn.setTextSize(40);
            bn.setPadding(5, 35, 5, 35);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.FILL);


            //回退监听器
            if (i == 3) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().length() != 0) {
                            CharSequence back = tv.getText().subSequence(0, tv.getText().length() - 1);
                            tv.setText(back);
                        }
                    }
                });
            }

            // 计算监听器实现
            else if(i == 18) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        TextView rs = (TextView) findViewById(R.id.result);

                        // 不存在上一步的结果
                        if(rs.getText().equals("0")) {
                            // 开方计算
                            if(tv.getText().toString().contains("√")) {
                                String source = tv.getText().toString().replace("√", "");
                                double result = Math.sqrt(Double.parseDouble(source));
                                rs.setText(decimalFormat.format(result));
                            }

                            // 平方运算
                            else if(tv.getText().toString().contains("^")) {
                                String source = tv.getText().toString().replace("^", "");
                                double result = Math.pow(Double.parseDouble(source), 2);
                                rs.setText(decimalFormat.format(result));
                            }

                            // 除法运算
                            else if(tv.getText().toString().contains("/")) {
                                String[] digits1 = tv.getText().toString().split("/");
                                double result = Double.parseDouble(digits1[0]) / Double.parseDouble(digits1[1]);
                                rs.setText(decimalFormat.format(result));
                                Log.i("CalcActivity", "除法1:" + digits1[0]);
                            }

                            // 乘法运算
                            else if(tv.getText().toString().contains("*")) {
                                String[] digits2 = tv.getText().toString().split("\\*");
                                double result = Double.parseDouble(digits2[0]) * Double.parseDouble(digits2[1]);
                                rs.setText(decimalFormat.format(result));
                                Log.i("CalcActivity", "乘法1:" + digits2[0]);
                            }

                            // 加法运算
                            else if(tv.getText().toString().contains("+")) {
                                String[] digits3 = tv.getText().toString().split("\\+");
                                double result = Double.parseDouble(digits3[0]) + Double.parseDouble(digits3[1]);
                                rs.setText(decimalFormat.format(result));
                                Log.i("CalcActivity", "加法1:" + digits3[0]);
                            }

                            // 被减数负数减法运算
                            else if(tv.getText().toString().contains("-")
                                    && tv.getText().toString().indexOf("-") == 0
                                    && tv.getText().toString().lastIndexOf("-") > 0) {
                                String[] digits4 = tv.getText().toString().split("-");
                                Log.i("CalcActivity", "减法1:" + digits4[0]);

                                double result = Double.parseDouble("-" + digits4[1]) - Double.parseDouble(digits4[2]);
                                rs.setText(decimalFormat.format(result));

                            }

                            // 被减数正数减法运算
                            else if(tv.getText().toString().contains("-")
                                    && tv.getText().toString().indexOf("-") > 0) {
                                String[] digits5 = tv.getText().toString().split("-");
                                Log.i("CalcActivity", "减法2:" + digits5[0]);

                                double result = Double.parseDouble(digits5[0]) - Double.parseDouble(digits5[1]);
                                rs.setText(decimalFormat.format(result));
                            }

                        }

                        tv.setText("");
                    }
                });
            }


            // 加减乘除监听器
            else if(i == 7) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("/");
                    }
                });
            }

            else if(i == 11) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("*");
                    }
                });
            }

            else if(i == 15) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("-");
                    }
                });
            }

            else if(i == 19) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("+");
                    }
                });
            }

            // 开方监听器
            else if(i == 0) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("√");
                    }
                });
            }

            // 平方监听器
            else if(i == 1) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("^");
                    }
                });
            }

            // 清空监听器
            else if(i == 2) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        tv.setText("");
                        TextView rs = (TextView) findViewById(R.id.result);
                        rs.setText("0");
                    }
                });
            }

            // 数字及小数点监听器
            else if(i == 4) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("7");
                    }
                });
            }

            else if (i == 5) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("8");
                    }
                });
            }

            else if (i == 6) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("9");
                    }
                });
            }

            else if (i == 8) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("4");
                    }
                });
            }

            else if (i == 9) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("5");
                    }
                });
            }

            else if (i == 10) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("6");
                    }
                });
            }

            else if (i == 12) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("1");
                    }
                });
            }

            else if (i == 13) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("2");
                    }
                });
            }

            else if (i == 14) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("3");
                    }
                });
            }

            else if (i == 16) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append(".");
                    }
                });
            }

            else if (i == 17) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        if(tv.getText().equals("0"))
                            tv.setText("");
                        tv.append("0");
                    }
                });
            }

            gridLayout.addView(bn, params);
        }
    }
}
