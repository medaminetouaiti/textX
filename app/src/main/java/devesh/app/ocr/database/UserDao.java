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

    // ===== NOUVELLES MÉTHODES POUR SEARCH/FILTER =====

    @Query("SELECT * FROM scanfile WHERE LOWER(text) LIKE '%' || :query || '%' ORDER BY time desc")
    List<ScanFile> searchByText(String query);

    @Query("SELECT * FROM scanfile WHERE LOWER(keywords) LIKE '%' || :query || '%' ORDER BY time desc")
    List<ScanFile> searchByKeywords(String query);

    @Query("SELECT * FROM scanfile WHERE (LOWER(text) LIKE '%' || :query || '%' OR LOWER(keywords) LIKE '%' || :query || '%') ORDER BY time desc")
    List<ScanFile> searchByTextAndKeywords(String query);

    @Query("SELECT * FROM scanfile WHERE keywords IS NOT NULL AND keywords != '' ORDER BY time desc")
    List<ScanFile> getScansWithKeywords();

    @Query("SELECT * FROM scanfile WHERE time >= :startTime ORDER BY time desc")
    List<ScanFile> getScansAfterDate(long startTime);

    @Query("SELECT * FROM scanfile WHERE time >= :startTime AND time <= :endTime ORDER BY time desc")
    List<ScanFile> getScansBetweenDates(long startTime, long endTime);
}