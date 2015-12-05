package in.blogspot.upsolving.dynamicsurfacetut;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout)).setTitle("Pie Pie");

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(new RecyclerView.Adapter<ItemViewHolder>(){

            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(getLayoutInflater().inflate(R.layout.single_item, parent, false));
            }

            @Override
            public void onBindViewHolder(ItemViewHolder holder, int position) {
                holder.text1.setText("Name =>>>>>>");
                holder.text2.setText("Address====><><><<><");
            }

            @Override
            public int getItemCount() {
                return 15;
            }
        });
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        TextView text2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView)itemView.findViewById(R.id.text1);
            text2 = (TextView) itemView.findViewById(R.id.text2);
        }
    }
}
