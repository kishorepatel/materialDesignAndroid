package in.blogspot.upsolving.simplepapertranstut;

import android.animation.Animator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String baconTitle = "Bacon";
    static String baconText = "Bacon ipsum dolor amet pork belly meatball kevin spare ribs. Frankfurter swine corned beef meatloaf, strip steak.";
    static String veggieTitle = "Veggie";
    static String veggieText = "Veggies es bonus vobis, proinde vos postulo essum magis kohlrabi welsh onion daikon amaranth tatsoi tomatillo melon azuki bean garlic.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar)).setTitle("Title");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recyclerview);
        rv.setLayoutManager(llm);
        rv.setAdapter( new RecyclerView.Adapter<ItemViewHolder>(){
            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(getLayoutInflater().inflate(R.layout.single_item, parent, false));
            }

            @Override
            public void onBindViewHolder(ItemViewHolder holder, int position) {
                holder.text1.setText(baconTitle);
                holder.text2.setText(baconText);
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text1;
        TextView text2;
        static int green;
        static int white;

        public ItemViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView)itemView.findViewById(R.id.text1);
            text2 = (TextView)itemView.findViewById(R.id.text2);
            itemView.setOnClickListener(this);

            if(green == 0){
                green = itemView.getContext().getResources().getColor(R.color.green);
            }
            if(white == 0){
                white = itemView.getContext().getResources().getColor(R.color.white);
            }
        }


        @Override
        public void onClick(View v) {
            boolean isVeggie = ((ColorDrawable)v.getBackground()) != null && ((ColorDrawable)v.getBackground()).getColor() == green;

            int radius = (int) Math.hypot(v.getWidth() / 2, v.getHeight()/2);

            if(isVeggie){
                text1.setText(baconTitle);
                text2.setText(baconText);
                v.setBackgroundColor(white);
            }
            else{
                Animator anim = ViewAnimationUtils.createCircularReveal(v, v.getWidth()/2, v.getHeight()/2, 0, radius);
                text1.setText(veggieTitle);
                text2.setText(veggieText);
                v.setBackgroundColor(green);
                anim.start();
            }
        }
    }
}
