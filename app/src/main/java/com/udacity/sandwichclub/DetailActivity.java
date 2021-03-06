package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich swich) {
        TextView akaTextView = (TextView) findViewById(R.id.also_known_tv);
        TextView ingredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_tv);
        TextView originTextView = (TextView) findViewById(R.id.origin_tv);

//        if(swich.getAlsoKnownAs().size() == 0 || swich.getAlsoKnownAs().get(0).equals("")){
//            akaTextView.setVisibility(View.INVISIBLE);
//            findViewById(R.id.)
//        }

        for(int i=0; i< swich.getAlsoKnownAs().size(); i++){
            if(i!=0) akaTextView.append(", ");
            akaTextView.append(swich.getAlsoKnownAs().get(i));
        }



        //akaTextView.setText(swich.getAlsoKnownAs().toString());
        for(String item : swich.getIngredients()){
            ingredientsTextView.append(item + ", ");
        }
        //ingredientsTextView.setText(swich.getIngredients().toString());
        descriptionTextView.setText(swich.getDescription().toString());
        //if(swich.getPlaceOfOrigin().equals("")) originTextView.setVisibility(View.INVISIBLE);
        originTextView.setText(swich.getPlaceOfOrigin().toString());

    }
}
