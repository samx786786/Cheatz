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
import com.bumptech.glide.Glide;
import java.util.List;

public class branchselectrecycleradapter  extends RecyclerView.Adapter<branchselectrecycleradapter.ViewHolder> {

    public List<branchmodel> mainList;
    public Context context;

    public branchselectrecycleradapter(List<branchmodel> notifList) {
        this.mainList=notifList;
    }

    @NonNull
    @Override
    public branchselectrecycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlayout, parent, false);
        context = parent.getContext();
        return new branchselectrecycleradapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull branchselectrecycleradapter.ViewHolder holder, int position) {
        String backgroundpic=mainList.get(position).getBackgroundpic();
        String branchname=mainList.get(position).getBranchname();
        String moduels=mainList.get(position).getModuels();
        String year=mainList.get(position).getYear();
        String subbranch=mainList.get(position).getSubbranch();
        Glide.with(context).load(backgroundpic).thumbnail(0.25f).into(holder.background);
        holder.textview.setText(branchname+"\n"+subbranch+"\n"+year);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(context, ProfileActivity.class);
                Intent.putExtra("branchname",branchname);
                Intent.putExtra("subbranch",subbranch);
                Intent.putExtra("moduels",moduels);
                Intent.putExtra("year",year);
                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView background;
        TextView textview;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            background=mView.findViewById(R.id.background);
            textview=mView.findViewById(R.id.textView3);
        }
    }
}
