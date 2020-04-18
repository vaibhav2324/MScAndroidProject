import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.R;

public class ReportViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView filename,description;

    public ReportViewHolder(@NonNull View itemView) {
        super(itemView);
        filename = itemView.findViewById(R.id.cardFileName);
        description = itemView.findViewById(R.id.cardDescription);
        imageView = itemView.findViewById(R.id.cardImage);
    }
}
