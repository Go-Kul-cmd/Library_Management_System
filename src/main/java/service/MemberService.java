package com.example.library_management_system.service;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MemberService {
    private final MemberRepository repo;
    public MemberService(MemberRepository r){repo=r;}
    public List<Member> all(String q){return q==null||q.isBlank()?repo.findAll():repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(q,q);}
    public Member one(Long id){return repo.findById(id).orElseThrow(()->new java.util.NoSuchElementException("Member not found: "+id));}
    public Member create(Member m){return repo.save(m);}
    public Member update(Long id,Member x){Member m=one(id);m.setName(x.getName());m.setEmail(x.getEmail());m.setPhone(x.getPhone());return repo.save(m);}
    public void delete(Long id){repo.delete(one(id));}
}