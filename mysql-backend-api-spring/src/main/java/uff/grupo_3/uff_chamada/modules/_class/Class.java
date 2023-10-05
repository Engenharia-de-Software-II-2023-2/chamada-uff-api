package uff.grupo_3.uff_chamada.modules._class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
@SequenceGenerator(name = "class_id_sequence", sequenceName = "class_id_sequence")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_id_sequence")
    private int id;
    private String name;
    private int professorId;
    private int semesterId;
}
