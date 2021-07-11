package com.cheatz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        String notesurl=mainList.get(position).getNotesurl();
        String youtubeurl=mainList.get(position).getYoutubeurl();
        holder.textview.setText(subjectname);
        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(notesurl));
                context.startActivity(browserIntent);
            }
        });

        holder.youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeurl)));
            }
        });

        holder.questionbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(context, Questionbank.class);
                Intent.putExtra("subjectname",subjectname);
                context.startActivity(Intent);
            }
        });

        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(context, Mainpage.class);
                Intent.putExtra("subjectname",subjectname);
                context.startActivity(Intent);
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
        ImageButton youtube,notes,questionbank;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.textView6);
            youtube=mView.findViewById(R.id.imageButton3);
            notes=mView.findViewById(R.id.imageButton2);
            questionbank=mView.findViewById(R.id.imageButton);
        }
    }
}
