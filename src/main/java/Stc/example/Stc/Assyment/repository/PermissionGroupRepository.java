package Stc.example.Stc.Assyment.repository;

import Stc.example.Stc.Assyment.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository< PermissionGroup,Long> {

    boolean existsByName(String name);

}
