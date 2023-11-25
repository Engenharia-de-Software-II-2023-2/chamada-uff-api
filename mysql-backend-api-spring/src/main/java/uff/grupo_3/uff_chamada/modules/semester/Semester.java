package uff.grupo_3.uff_chamada.modules.semester;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "semester_id_sequence", sequenceName = "semester_id_sequence")
@Table(name = "SEMESTER")
public class Semester {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semester_id_sequence")
    private int id;
    
    @Column(name = "year_num")
    private String year;
    private int semester;

    
}
