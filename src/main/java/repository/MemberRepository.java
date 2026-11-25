package com.example.library_management_system.repository;
import com.example.library_management_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String n,String e);
}