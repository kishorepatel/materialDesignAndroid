package in.blogspot.upsolving.pickpalettetut;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Map;


public class SwatchAdapter extends ArrayAdapter {

    public SwatchAdapter(Context context, Object[] swatches){
        super(context, 0, swatches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map.Entry<String, Palette.Swatch> entry = (Map.Entry<String, Palette.Swatch>) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.swatch_view, parent,false);
        }

        View colorView = convertView.findViewById(R.id.swatch_color);
        int colorValue = entry.getValue().getRgb();
        colorView.setBackgroundColor(colorValue);

        TextView title = (TextView) convertView.findViewById(R.id.swatch_text);
        title.setText(entry.getKey() + " (#" + Integer.toHexString(entry.getValue().getRgb()).toUpperCase() +")");
        return convertView;
    }
}





























