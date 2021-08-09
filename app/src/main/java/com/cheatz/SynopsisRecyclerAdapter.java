package com.cheatz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        String question=mainList.get(position).getQuestion();
        String answers=mainList.get(position).getAnswers();
        holder.textview.setText(question+"\n"+answers);
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
            textview=mView.findViewById(R.id.synopsistextview);
        }
    }
}
