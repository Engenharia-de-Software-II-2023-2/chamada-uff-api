package uff.grupo_3.uff_chamada.justification;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "justification_id_sequence", sequenceName = "justification_id_sequence", allocationSize = 1)
public class Justification {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "justification_id_sequence")
    @Id
    private int id;
    private int studentId;
    private int attedanceId;
    private LocalDateTime createdAt;
    @Lob
    private byte[] document; // TODO: VER SE ISSO FUNCIONA / TESTAR NO POSTMAN
    private String comment;

}
