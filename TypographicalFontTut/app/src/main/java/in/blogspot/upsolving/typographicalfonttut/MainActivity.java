package in.blogspot.upsolving.typographicalfonttut;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.text_1)
    TextView helloText;

    Typeface courgette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        courgette = Typeface.createFromAsset(getAssets(), "Courgette-Regular.ttf");
        Log.d("ddd", "onCreate");
        helloText.setText("Good Morning");
       helloText.setTypeface(courgette);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ddd", "onStart");
       //
    }


}
