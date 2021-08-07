package com.cheatz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class shoprecycleradapter extends RecyclerView.Adapter<shoprecycleradapter.ViewHolder> {

    public List<Shopmodel> mainList;
    public Context context;

    public shoprecycleradapter(List<Shopmodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public shoprecycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoplayout, parent, false);
        context = parent.getContext();
        return new shoprecycleradapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shoprecycleradapter.ViewHolder holder, int position) {
       String url=mainList.get(position).getBackgroundurl();
       Picasso.get().load(url).into(holder.imageView);
       String bookname=mainList.get(position).getBookname();
       int bookprice=mainList.get(position).getPrice();
       holder.textview.setText(bookname+"\n"+" ₹ "+bookprice);
       holder.mView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // detail activity

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
