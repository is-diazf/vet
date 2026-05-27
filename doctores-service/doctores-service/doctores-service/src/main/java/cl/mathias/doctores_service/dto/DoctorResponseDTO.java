package cl.mathias.doctores_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DoctorResponseDTO {

    private Long id;
    private String nombre;
    private String especialidad;

}
