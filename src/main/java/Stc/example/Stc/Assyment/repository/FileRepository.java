package Stc.example.Stc.Assyment.repository;

import Stc.example.Stc.Assyment.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData,Long> {





}
