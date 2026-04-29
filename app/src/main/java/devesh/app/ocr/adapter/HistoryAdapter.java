package devesh.app.ocr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devesh.app.ocr.HistoryActivity;
import devesh.app.ocr.R;
import devesh.app.ocr.database.ScanFile;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    Context mContext;
    private List<ScanFile> localDataSet;

    public HistoryAdapter(Context context, List<ScanFile> dataSet) {
        localDataSet = dataSet;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_history_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ScanFile scan = localDataSet.get(position);

        viewHolder.getTextView().setText(scan.text);

        if (scan.summary != null && !scan.summary.isEmpty()) {
            viewHolder.getTvSummary().setVisibility(View.VISIBLE);
            viewHolder.getTvSummary().setText("Summary: " + scan.summary);
        } else {
            viewHolder.getTvSummary().setVisibility(View.GONE);
        }

        if (scan.keywords != null && !scan.keywords.isEmpty()) {
            viewHolder.getTvKeywords().setVisibility(View.VISIBLE);
            viewHolder.getTvKeywords().setText("Keywords: " + scan.keywords);
        } else {
            viewHolder.getTvKeywords().setVisibility(View.GONE);
        }

        // Utilisation de la position directe pour éviter les décalages lors du filtrage
        viewHolder.getLLItem().setOnClickListener(view -> {
            if (mContext instanceof HistoryActivity) {
                ((HistoryActivity) mContext).OpenHistoryFile(viewHolder.getBindingAdapterPosition());
            }
        });

        viewHolder.getCopyButton().setOnClickListener(view -> {
            if (mContext instanceof HistoryActivity) {
                ((HistoryActivity) mContext).CopyText(viewHolder.getBindingAdapterPosition());
            }
        });

        viewHolder.getShareButton().setOnClickListener(view -> {
            if (mContext instanceof HistoryActivity) {
                ((HistoryActivity) mContext).ShareText(viewHolder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void updateList(List<ScanFile> newList) {
        localDataSet = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView tvSummary;
        private final TextView tvKeywords;
        private final LinearLayout LLItem;
        private final Button CopyButton;
        private final Button ShareButton;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            tvSummary = view.findViewById(R.id.tvSummary);
            tvKeywords = view.findViewById(R.id.tvKeywords);
            LLItem = view.findViewById(R.id.LLItem);
            CopyButton = view.findViewById(R.id.CopyButton);
            ShareButton = view.findViewById(R.id.ShareButton);
        }

        public TextView getTextView() { return textView; }
        public TextView getTvSummary() { return tvSummary; }
        public TextView getTvKeywords() { return tvKeywords; }
        public LinearLayout getLLItem() { return LLItem; }
        public Button getCopyButton() { return CopyButton; }
        public Button getShareButton() { return ShareButton; }
    }
}
