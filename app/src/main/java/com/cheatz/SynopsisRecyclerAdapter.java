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

public class SynopsisRecyclerAdapter extends RecyclerView.Adapter<SynopsisRecyclerAdapter.ViewHolder> {

    public List<SynopsisModel> mainList;
    public Context context;

    public SynopsisRecyclerAdapter(List<SynopsisModel> notifList) {
        this.mainList=notifList;
    }

    @NonNull
    @Override
    public SynopsisRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.synopsislayout, parent, false);
        context = parent.getContext();
        return new SynopsisRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SynopsisRecyclerAdapter.ViewHolder holder, int position) {
        String url=mainList.get(position).getUrl();
        holder.loading.setVisibility(View.VISIBLE);
        Picasso.get().load(url).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.loading.setVisibility(View.VISIBLE);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(context, Zoomimage.class);
                        Intent.putExtra("imageurl",url);
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
        ImageView imageView;
        TextView loading;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            loading=mView.findViewById(R.id.textView6);
            imageView=mView.findViewById(R.id.image);
        }
    }
}
