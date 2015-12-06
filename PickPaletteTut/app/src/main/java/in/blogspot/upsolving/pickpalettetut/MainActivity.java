package in.blogspot.upsolving.pickpalettetut;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {



    @InjectView(R.id.fab)
    FloatingActionButton fab;
    SwatchAdapter swatchAdapter;
    @InjectView(R.id.grid_view)
    GridView gridView;
    @InjectView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar.setTitle("Pick Palette");
        ButterKnife.inject(this);
    }

    @OnClick(R.id.fab)
    public void click(View view){
        Log.d("ddd", "fab click");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PickerFragment pickerFragment = new PickerFragment();
        pickerFragment.show(getFragmentManager(), "dialog");
        ft.commit();
    }



    public void createPalette(Object obj){
        Bitmap bitmap = null;
        try{
            if(obj instanceof Uri){
                Uri imageUri = (Uri) obj;
                Picasso.with(this).load(imageUri).into(imageView);
                InputStream is = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(is);
            }
            else{
                bitmap = (Bitmap) obj;
                imageView.setImageBitmap(bitmap);
            }
        }
        catch(Exception e){

        }

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                HashMap<String, Palette.Swatch> palSwatch = processPalette(palette);
                swatchAdapter = new SwatchAdapter(getApplicationContext(), palSwatch.entrySet().toArray());
                gridView.setAdapter(swatchAdapter);
            }
        });
    }


    HashMap<String, Palette.Swatch> processPalette(Palette palette){
        HashMap<String, Palette.Swatch> ret = new HashMap<>();
        if(palette.getVibrantSwatch() != null){
            ret.put("Vibrant", palette.getVibrantSwatch());
        }
        if(palette.getDarkVibrantSwatch() != null){
            ret.put("Dark vibrant", palette.getDarkVibrantSwatch());
        }
        if(palette.getLightVibrantSwatch() != null){
            ret.put("Light Vibrant", palette.getLightVibrantSwatch());
        }
        if(palette.getMutedSwatch() != null){
            ret.put("Muted", palette.getMutedSwatch());
        }
        if (palette.getDarkMutedSwatch() != null)
            ret.put("DarkMuted", palette.getDarkMutedSwatch());
        if (palette.getLightMutedSwatch() != null)
            ret.put("LightMuted", palette.getLightMutedSwatch());

        return ret;
    }

    @OnItemClick(R.id.grid_view)
    void onItemClick(int position){
        Palette.Swatch swatch = ((Map.Entry<String, Palette.Swatch>) gridView.getItemAtPosition(position)).getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("Title Text Color: ").append("#" + Integer.toHexString(swatch.getBodyTextColor()).toUpperCase()).append("\n");
        sb.append("Population: ").append(swatch.getPopulation());

        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}






















