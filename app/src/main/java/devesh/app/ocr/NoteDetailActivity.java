package devesh.app.ocr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import devesh.app.ocr.api.AIManager;
import devesh.app.ocr.database.DatabaseTool;
import devesh.app.ocr.database.ScanFile;

public class NoteDetailActivity extends AppCompatActivity {
    private static final String TAG = "NoteDetailActivity";

    private TextView tvOriginalText;
    private TextView tvResult;
    private Button btnSummarize;
    private ProgressBar progressBar;
    private AIManager aiManager;
    private DatabaseTool databaseTool;
    private String originalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        tvOriginalText = findViewById(R.id.tvOriginalText);
        tvResult = findViewById(R.id.tvResult);
        btnSummarize = findViewById(R.id.btnSummarize);
        progressBar = findViewById(R.id.progressBar);

        aiManager = new AIManager();
        databaseTool = new DatabaseTool(this);

        originalText = getIntent().getStringExtra("extra_text");
        if (originalText != null) {
            tvOriginalText.setText(originalText);
            
            // Check if we already have a summary in DB
            new Thread(() -> {
                ScanFile existing = databaseTool.findByText(originalText);
                if (existing != null && existing.summary != null) {
                    runOnUiThread(() -> tvResult.setText("Summary: " + existing.summary + "\n\nKeywords: " + existing.keywords));
                }
            }).start();
        }

        btnSummarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summarizeText(originalText);
            }
        });
    }

    private void summarizeText(String text) {
        if (text == null || text.isEmpty()) {
            Toast.makeText(this, "No text to summarize", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnSummarize.setEnabled(false);
        tvResult.setText("Analyzing...");

        aiManager.analyzeText(text, new AIManager.AIListener() {
            @Override
            public void onSuccess(String summary, String keywords) {
                progressBar.setVisibility(View.GONE);
                btnSummarize.setEnabled(true);
                tvResult.setText("Summary: " + summary + "\n\nKeywords: " + keywords);
                
                // Save to Database
                saveToHistory(text, summary, keywords);
            }

            @Override
            public void onFailure(String error) {
                progressBar.setVisibility(View.GONE);
                btnSummarize.setEnabled(true);
                tvResult.setText("Error: " + error);
                Toast.makeText(NoteDetailActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToHistory(String original, String summary, String keywords) {
        new Thread(() -> {
            try {
                ScanFile scanFile = databaseTool.findByText(original);
                if (scanFile == null) {
                    scanFile = new ScanFile();
                    scanFile.text = original;
                    scanFile.time = System.currentTimeMillis();
                    scanFile.summary = summary;
                    scanFile.keywords = keywords;
                    databaseTool.Add(scanFile);
                    Log.d(TAG, "New record saved with AI results");
                } else {
                    scanFile.summary = summary;
                    scanFile.keywords = keywords;
                    databaseTool.update(scanFile);
                    Log.d(TAG, "Existing record updated with AI results");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error saving to history: ", e);
            }
        }).start();
    }
}
