package rawe.gordon.com.textswitchexample;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class MainActivity extends ActionBarActivity {

    private TextView stop,start;
    private TextSwitcher textSwitcher;

    String textToShow[]={"Main HeadLine","Your Message","New In Technology","New Articles","Business News","What IS New"};
    int messageCount=textToShow.length;
    // to keep current Index of text
    int currentIndex=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stop = (TextView)findViewById(R.id.stop);
        start = (TextView)findViewById(R.id.start);
        textSwitcher = (TextSwitcher)findViewById(R.id.switcher);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(MainActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                myText.setTextColor(Color.BLUE);
                return myText;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
        textSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),(String)v.getTag(),Toast.LENGTH_SHORT).show();
            }
        });

        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex++;
                // If index reaches maximum reset it
                if(currentIndex==messageCount)
                    currentIndex=0;
                textSwitcher.setText(textToShow[currentIndex]);
                textSwitcher.setTag(textToShow[currentIndex]);
                handler.postDelayed(this,1000);
            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable,1000);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });
    }
}
