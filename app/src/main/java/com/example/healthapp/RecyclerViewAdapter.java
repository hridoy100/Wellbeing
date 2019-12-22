package com.example.healthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    static final String TAG = "RecyclerViewAdapter";
    ArrayList<String> mImagesNames = new ArrayList<>();
    ArrayList<String> mImages = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<String> mImagesNames, ArrayList<String> mImages, OnItemClickListener onItemClickListener) {
        this.mImagesNames = mImagesNames;
        this.mImages = mImages;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup, false);
        ViewHolder holder = new ViewHolder(view, onItemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int i) {
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(i))
                .into(viewHolder.circleImageView);
        viewHolder.imageName.setText(mImagesNames.get(i));

        /*viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImagesNames.get(i).equals("Bangladesh Power Division")){
                    Intent pdbIntent = new Intent(context, PDBJobList.class);
                    pdbIntent.putExtra("image_url", mImages.get(i));
                    pdbIntent.putExtra("image_name", mImagesNames.get(i));
                    context.startActivity(pdbIntent);
                }
                else if(mImagesNames.get(i).equals("Bank")){
                    Intent bankIntent = new Intent(context, BankJobList.class);
                    bankIntent.putExtra("image_url", mImages.get(i));
                    bankIntent.putExtra("image_name", mImagesNames.get(i));
                    context.startActivity(bankIntent);
                }
                else
                    Toast.makeText(context, mImagesNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mImagesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView circleImageView;
        TextView imageName;
        LinearLayout parentLayout;
        OnItemClickListener onItemClickListener;
        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.listImage);
            imageName = itemView.findViewById(R.id.categoryTitle);
            parentLayout = itemView.findViewById(R.id.linearRecycleView_category);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }
}
