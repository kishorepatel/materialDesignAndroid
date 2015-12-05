
package in.blogspot.upsolving.scrollevent;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle("Recycler & Card");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(llm);

        rv.setAdapter(new RecyclerView.Adapter<MainActivity.CardHolder>() {
            @Override
            public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.single_item, parent, false);
                view.setMinimumWidth(parent.getWidth());

                return new CardHolder(view);
            }

            @Override
            public void onBindViewHolder(CardHolder holder, int position) {
                holder.name.setText("Kishroe");
                holder.address.setText("address");
                holder.city.setText("CITY");
            }

            @Override
            public int getItemCount() {
                return 30;
            }
        });


    }

    public class CardHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;
        TextView city;

        public CardHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_1_name);
            address = (TextView) itemView.findViewById(R.id.text_2_address);
            city = (TextView) itemView.findViewById(R.id.text_3_city);
        }
    }
}
