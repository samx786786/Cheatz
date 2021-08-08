package com.cheatz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class labRecyclerAdapter extends RecyclerView.Adapter<labRecyclerAdapter.ViewHolder> {

    public List<labmodel> mainList;
    public Context context;

    public labRecyclerAdapter(List<labmodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public labRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vivalayout, parent, false);
        context = parent.getContext();
        return new labRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull labRecyclerAdapter.ViewHolder holder, int position) {
        String questionstring=mainList.get(position).getAnswer();
        String answerstring=mainList.get(position).getAnswer();
        holder.textview.setText(questionstring+"\n"+answerstring);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.vivatextview);
        }
    }
}
