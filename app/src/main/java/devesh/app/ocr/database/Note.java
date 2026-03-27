package devesh.app.ocr.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String originalText;
    public String summary;
    public String tags;
    public long timestamp;
}
