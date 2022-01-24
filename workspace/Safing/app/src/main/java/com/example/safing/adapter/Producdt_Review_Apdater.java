package com.example.safing.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.Product_ReviewlDTO;
import com.example.safing.R;
import com.example.safing.activity.Review_Image_Activity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Producdt_Review_Apdater extends RecyclerView.Adapter<Producdt_Review_Apdater.ViewHolder> {

    boolean like_change= true;
    Context context;
    ArrayList<Product_ReviewlDTO> list;
    LayoutInflater inflater;

    public Producdt_Review_Apdater(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_review_rec, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding(holder, position);
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView item_product_review_profile;
        RatingBar item_product_review_rating;
        TextView item_product_review_tv1, item_product_review_tv2, item_product_review_tv3, item_product_review_tv4;
        ImageView item_product_review_img1, item_product_review_img2;
        Button item_product_review_btn;

        Drawable image1 = context.getResources().getDrawable(R.drawable.button_left_image_like1);
        Drawable image2 = context.getResources().getDrawable(R.drawable.button_left_image_like2);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_product_review_profile = itemView.findViewById(R.id.item_product_review_profile);
            item_product_review_rating = itemView.findViewById(R.id.item_product_review_rating);
            item_product_review_tv1 = itemView.findViewById(R.id.item_product_review_tv1);
            item_product_review_tv2 = itemView.findViewById(R.id.item_product_review_tv2);
            item_product_review_tv3 = itemView.findViewById(R.id.item_product_review_tv3);
            item_product_review_tv4 = itemView.findViewById(R.id.item_product_review_tv4);
            item_product_review_img1 = itemView.findViewById(R.id.item_product_review_img1);
            item_product_review_img2 = itemView.findViewById(R.id.item_product_review_img2);
            item_product_review_btn = itemView.findViewById(R.id.item_product_review_btn);


            item_product_review_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    item_product_review_rating.setRating(5);
                    item_product_review_tv1.setText(item_product_review_rating.getRating()+"");
                }
            });

            item_product_review_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(like_change== true) {
                        Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                        item_product_review_btn.setCompoundDrawables(image2, null,null,null);
                        like_change= false;
                    }else {
                        Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
                        item_product_review_btn.setCompoundDrawables(image1, null,null,null);

                        like_change= true;
                    }
                }
            });



        }
    }
    public void binding(ViewHolder holder, int position){
        holder.item_product_review_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Review_Image_Activity.class);
                context.startActivity(intent);
            }
        });
        holder.item_product_review_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Review_Image_Activity.class);
                context.startActivity(intent);
            }
        });
    }
}
