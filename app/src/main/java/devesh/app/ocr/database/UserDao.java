package devesh.app.ocr.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM scanfile ORDER BY time desc")
    List<ScanFile> getAll();

    @Insert
    void insert(ScanFile scanFile);

    @Update
    void update(ScanFile scanFile);

    @Delete
    void delete(ScanFile scanFile);

    @Query("SELECT * FROM scanfile WHERE text = :searchText LIMIT 1")
    ScanFile findByText(String searchText);

    @Query("DELETE FROM scanfile")
    void nukeTable();
}