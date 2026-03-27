package devesh.app.ocr.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ScanFile {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "time")
    public long time;

    @ColumnInfo(name = "summary")
    public String summary;

    @ColumnInfo(name = "keywords")
    public String keywords;
}