package Stc.example.Stc.Assyment.repository;

import Stc.example.Stc.Assyment.entity.Permission;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
