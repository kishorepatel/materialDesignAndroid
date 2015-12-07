package in.blogspot.upsolving.transitionimageslideuptut;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.TOP);

                ViewGroup vg = (ViewGroup)findViewById(android.R.id.content);
                TransitionManager.beginDelayedTransition(vg, slide);

                image.setVisibility(View.INVISIBLE);
            }
        });

    }
}
