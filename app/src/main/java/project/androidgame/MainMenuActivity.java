package project.androidgame;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
    private TextView textView;
    private TextView textView2;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        AssetManager mgr=getAssets();

        Typeface tf= Typeface.createFromAsset(mgr, "fonts/Boutiques.ttf");


        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);


        final RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        textView=new TextView(this);

        textView.setText("start play");

        lp1.topMargin=500;

        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setTextSize(80);

        textView.setTypeface(tf);

        textView.setTextColor(Color.BLACK);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Toast.makeText(MainMenuActivity.this,"开始",Toast.LENGTH_SHORT).show();
                    textView.setTextColor(Color.RED);
                }else {
                    textView.setTextColor(Color.BLACK);
                    Intent intent=new Intent(MainMenuActivity.this,PlayActivity.class);
                    MainMenuActivity.this.startActivity(intent);
                }
                return true;
            };
        });

        textView.setSelected(true);

        textView.setLayoutParams(lp1);

        relativeLayout.addView(textView);

        textView2=(TextView)findViewById(R.id.textView2);

        textView.setTypeface(tf);

        textView2.setTypeface(tf);

        Listener();
    }
    public void Listener(){
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainMenuActivity.this,PlayActivity.class);
//                MainMenuActivity.this.startActivity(intent);
//            }
//        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this,"再见",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
