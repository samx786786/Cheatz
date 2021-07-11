package com.cheatz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        String youtubeurl=mainList.get(position).getYoutubeurl();
        String imageurl=mainList.get(position).getImageurl();
        String name=mainList.get(position).getTopicname();
        Picasso.get().load(imageurl).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(context, Zoomimage.class);
                Intent.putExtra("imageurl",imageurl);
                context.startActivity(Intent);

            }
        });

        holder.youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeurl)));
            }
        });

        holder.textview.setText(name);
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeurl)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        ImageView youtube;
        View mView;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.textView25);
            image=mView.findViewById(R.id.image);
            youtube=mView.findViewById(R.id.imageButton2);
        }
    }
}
