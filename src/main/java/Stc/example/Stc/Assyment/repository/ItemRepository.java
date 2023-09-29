package Stc.example.Stc.Assyment.repository;

import Stc.example.Stc.Assyment.entity.Item;
import Stc.example.Stc.Assyment.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    boolean existsByNameAndTypeIn(String name, List<ItemType> types);
    Optional<Item> findByNameAndTypeIn(String name, List<ItemType> types);



    @Query(value = "SELECT * FROM item i WHERE i.parent_id = ?1", nativeQuery = true)
    List<Item> findByParentId(Long id);


}
