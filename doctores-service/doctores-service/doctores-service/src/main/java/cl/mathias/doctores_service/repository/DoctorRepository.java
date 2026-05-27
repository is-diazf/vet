package cl.mathias.doctores_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.mathias.doctores_service.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    List<Doctor> findByEspecialidad(String especialidad);

    Optional<Doctor> findByNombre(String nombre);

}
