package com.cheatz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImportantRecyclerAdapter  extends RecyclerView.Adapter<ImportantRecyclerAdapter.ViewHolder> {

    public List<Importantquestionmodel> mainList;
    public Context context;

    public ImportantRecyclerAdapter(List<Importantquestionmodel> notifList) {
        this.mainList=notifList;
    }

    @NonNull
    @Override
    public ImportantRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionbanklayout, parent, false);
        context = parent.getContext();
        return new ImportantRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImportantRecyclerAdapter.ViewHolder holder, int position) {
        String question=mainList.get(position).getQuetion();
        String answer=mainList.get(position).getAnswer();
        String imageurl=mainList.get(position).getImageurl();
        if (question!=null&&question=="")
        {
            holder.textview.setVisibility(View.VISIBLE);
            holder.textview.setText(question+"\n"+answer);
        }
        if (imageurl!=null&&imageurl=="")
        {
            holder.textview.setVisibility(View.VISIBLE);
            holder.textview.setText(question+"\n"+answer);
            holder.image.setVisibility(View.VISIBLE);
            holder.cardView.setVisibility(View.VISIBLE);
            Picasso.get().load(imageurl).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Intent = new Intent(context, Zoomimage.class);
                    Intent.putExtra("imageurl",imageurl);
                    context.startActivity(Intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textview;
        View mView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            image=mView.findViewById(R.id.imagevsd);
            textview=mView.findViewById(R.id.textView28);
            cardView=mView.findViewById(R.id.imageView14);
        }
    }
}
