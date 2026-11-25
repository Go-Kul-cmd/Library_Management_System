package com.example.library_management_system.repository;
import com.example.library_management_system.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
    List<BorrowRecord> findAllByOrderByIssueDateDesc();
    boolean existsByBookIdAndStatus(Long bookId,String status);
}