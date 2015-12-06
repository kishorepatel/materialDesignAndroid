package in.blogspot.upsolving.pickpalettetut;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class PickerFragment extends DialogFragment {
    public static final int PHOTO_PICK = 100;
    public static final int PHOTO_TAKE = 101;

    @InjectView(R.id.pickImage)
    Button pickImage;
    @InjectView(R.id.takeImage)
    Button takeImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_picker, container, false);

        ButterKnife.inject(this, rootView);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
    @OnClick(R.id.pickImage)
    public void pickImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PHOTO_PICK);
    }

    @OnClick(R.id.takeImage)
    public void takeImage(View view){
        Intent photoTakerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(photoTakerIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(photoTakerIntent, PHOTO_TAKE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case PHOTO_PICK:{
                if(resultCode == Activity.RESULT_OK){
                    Log.d("ddd", "Picked a photo.");
                    Uri selectedImage = data.getData();
                    ((MainActivity)getActivity()).createPalette(selectedImage);
                    getDialog().dismiss();
                }
                break;
            }
            case PHOTO_TAKE:{
                if(resultCode == Activity.RESULT_OK){
                    Log.d("ddd", "Took a photo.");
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ((MainActivity)getActivity()).createPalette(imageBitmap);
                    getDialog().dismiss();
                }
            }
        }
    }
}
