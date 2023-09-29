package Stc.example.Stc.Assyment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file_data")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Lob
    @Column(name = "file_binary")
    private byte [] binary ;

    @OneToOne(mappedBy = "fileData")
    private Item file;



}
