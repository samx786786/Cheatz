package com.cheatz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainpageRecyclerAdapter extends RecyclerView.Adapter<MainpageRecyclerAdapter.ViewHolder>  {

    public List<Mainpagemodel> mainList;
    public Context context;

    public MainpageRecyclerAdapter(List<Mainpagemodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public MainpageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainpagelayout, parent, false);
        context = parent.getContext();
        return new MainpageRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainpageRecyclerAdapter.ViewHolder holder, int position) {
        String imageurl=mainList.get(position).getImageurl();
        holder.loding.setVisibility(View.VISIBLE);
        Picasso.get().load(imageurl).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
               holder.loding.setVisibility(View.INVISIBLE);
                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Zoomimage.class);
                        Intent.putExtra("imageurl",imageurl);
                        context.startActivity(Intent);
                    }
                });
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Zoomimage.class);
                        Intent.putExtra("imageurl",imageurl);
                        context.startActivity(Intent);
                    }
                });
            }
            @Override
            public void onError(Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView image;
        TextView loding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            loding=mView.findViewById(R.id.textView6);
            image=mView.findViewById(R.id.image);
        }
    }
}
