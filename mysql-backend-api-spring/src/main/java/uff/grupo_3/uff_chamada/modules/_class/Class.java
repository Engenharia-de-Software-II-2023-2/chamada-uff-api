package uff.grupo_3.uff_chamada.modules._class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class")
@Data
@SequenceGenerator(name = "class_id_sequence", sequenceName = "class_id_sequence")
@AllArgsConstructor
@NoArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_id_sequence")
    private int id;
    private String name;
    private String subjectName;
    private int professorId;
    private int semesterId;
    private String schedule;
}
