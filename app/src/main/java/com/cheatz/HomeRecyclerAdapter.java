package com.cheatz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    public List<Homemodel> mainList;
    public Context context;

    public HomeRecyclerAdapter(List<Homemodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homelayout, parent, false);
        context = parent.getContext();
        return new HomeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerAdapter.ViewHolder holder, int position) {
        String subjectname=mainList.get(position).getSubjectname();
        String url=mainList.get(position).getBackgroundurl();


        Picasso.get().load(url).into(holder.background, new Callback() {
            @Override
            public void onSuccess() {
                holder.textview.setText(subjectname);
                holder.textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Mainpage.class);
                        Intent.putExtra("subjectname",subjectname);
                        context.startActivity(Intent);
                    }
                });
                holder.background.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Mainpage.class);
                        Intent.putExtra("subjectname",subjectname);
                        context.startActivity(Intent);
                    }
                });
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Mainpage.class);
                        Intent.putExtra("subjectname",subjectname);
                        context.startActivity(Intent);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                holder.textview.setText(e.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        View mView;
        ImageView background;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.textView3);
            background=mView.findViewById(R.id.background);
        }
    }
}
