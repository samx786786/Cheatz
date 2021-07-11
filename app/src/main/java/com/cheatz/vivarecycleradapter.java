package com.cheatz;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class vivarecycleradapter extends RecyclerView.Adapter<vivarecycleradapter.ViewHolder> {

    public List<vivamodel> mainList;
    public Context context;

    public vivarecycleradapter(List<vivamodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public vivarecycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectorlayot, parent, false);
        context = parent.getContext();
        return new vivarecycleradapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vivarecycleradapter.ViewHolder holder, int position) {
        String question=mainList.get(position).getQuestion();
        String answer=mainList.get(position).getAnswer();
        holder.textview.setText("Q: "+question+"\n"+"A: "+answer);
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
            textview=mView.findViewById(R.id.textviewselctor);
        }
    }
}
