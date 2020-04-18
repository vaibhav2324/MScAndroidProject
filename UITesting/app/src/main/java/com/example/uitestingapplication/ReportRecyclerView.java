import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.R;
import com.example.uitestingapplication.ReportUIActivity;
import com.example.uitestingapplication.db.entity.Report;

import java.util.List;

public class ReportRecyclerView extends RecyclerView.Adapter {
    ReportViewHolder reportViewHolder;
    List<Report> reportList;
    public ReportRecyclerView(List<Report> reports){
        reportList = reports;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.report_items_layout,
                parent,
                false
        );
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Report report = reportList.get(position);
        reportViewHolder.imageView.setImageBitmap(ReportUIActivity.convertByteArrayToImage(report.getImage()));
        reportViewHolder.filename.setText(report.getFileName());
        reportViewHolder.description.setText(report.getDescription());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
