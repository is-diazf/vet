package cl.mathias.doctores_service.service;

import cl.mathias.doctores_service.dto.DoctorResponseDTO;
import cl.mathias.doctores_service.model.Doctor;
import cl.mathias.doctores_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;


    // Obtener doctor por ID
    public DoctorResponseDTO obtenerPorId(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));
        return mapearADTO(doctor);
    }
    
    // Obtener todos los doctores
    public List<DoctorResponseDTO> obtenerTodos() {
        List<Doctor> doctores = doctorRepository.findAll();
        return doctores.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // Obtener doctores por especialidad
    public List<DoctorResponseDTO> obtenerPorEspecialidad(String especialidad) {
        List<Doctor> doctores = doctorRepository.findByEspecialidad(especialidad);
        return doctores.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    // Guardar doctor
    public DoctorResponseDTO guardar(Doctor doctor) {
        Doctor doctorGuardado = doctorRepository.save(doctor);
        return mapearADTO(doctorGuardado);
    }
    
    // Actualizar doctor
    public DoctorResponseDTO actualizar(Long id, Doctor detallesDoctor) {
        Doctor doctorExistente = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ese id: " + id));

        doctorExistente.setNombre(detallesDoctor.getNombre());
        doctorExistente.setEspecialidad(detallesDoctor.getEspecialidad());

        Doctor doctorActualizado = doctorRepository.save(doctorExistente);
        return mapearADTO(doctorActualizado);
    }

    // Eliminar doctor
    public void eliminar(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No hay doctor con ese id: " + id));
        doctorRepository.delete(doctor);
    }
    
    // Método privado para mapear de Entity a DTO
    private DoctorResponseDTO mapearADTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setNombre(doctor.getNombre());
        dto.setEspecialidad(doctor.getEspecialidad());
        return dto;
    }


}
