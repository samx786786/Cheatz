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

public class Selectorrecycleradapter extends RecyclerView.Adapter<Selectorrecycleradapter.ViewHolder>  {

    public List<Selectormodel> mainList;
    public Context context;
    String branchnamex;
    String subbranchx;
    String yearx;

    public Selectorrecycleradapter(List<Selectormodel> notifList, String branchname, String year, String subbranch) {
        this.mainList=notifList;
        this.branchnamex=branchname;
        this.subbranchx=subbranch;
        this.yearx=year;
    }

    @NonNull
    @Override
    public Selectorrecycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlayout, parent, false);
        context = parent.getContext();
        return new Selectorrecycleradapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Selectorrecycleradapter.ViewHolder holder, int position) {
        String sem =mainList.get(position).getSem();
        String url=mainList.get(position).getImageurl();
        holder.mView.setVisibility(View.GONE);
        Picasso.get().load(url).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.mView.setVisibility(View.VISIBLE);
                holder.textview.setText(sem);
                holder.textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, ProfileSavedataActivity.class);
                        Intent.putExtra("branchname",branchnamex);
                        Intent.putExtra("subbranch",subbranchx);
                        Intent.putExtra("sem",sem);
                        Intent.putExtra("year",yearx);
                        context.startActivity(Intent);
                    }
                });
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, ProfileSavedataActivity.class);
                        Intent.putExtra("branchname",branchnamex);
                        Intent.putExtra("subbranch",subbranchx);
                        Intent.putExtra("sem",sem);
                        Intent.putExtra("year",yearx);
                        context.startActivity(Intent);
                    }
                });
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, ProfileSavedataActivity.class);
                        Intent.putExtra("branchname",branchnamex);
                        Intent.putExtra("subbranch",subbranchx);
                        Intent.putExtra("sem",sem);
                        Intent.putExtra("year",yearx);
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
        ImageView imageView;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.textView3);
            imageView=mView.findViewById(R.id.background);
        }
    }
}
