package cl.mathias.doctores_service.controller;

import cl.mathias.doctores_service.dto.DoctorResponseDTO;
import cl.mathias.doctores_service.model.Doctor;
import cl.mathias.doctores_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Obtener todos los doctores
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> listarDoctores() {
        List<DoctorResponseDTO> doctores = doctorService.obtenerTodos();
        return ResponseEntity.ok(doctores);
    }

    // Obtener doctor por ID (usado por Feign Client)
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> obtenerDoctor(@PathVariable Long id) {
        DoctorResponseDTO doctor = doctorService.obtenerPorId(id);
        return ResponseEntity.ok(doctor);
    }

    // Obtener doctores por especialidad
    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<DoctorResponseDTO>> listarDoctoresPorEspecialidad(@PathVariable String especialidad) {
        List<DoctorResponseDTO> doctores = doctorService.obtenerPorEspecialidad(especialidad);
        return ResponseEntity.ok(doctores);
    }

    // Crear un nuevo doctor
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> guardarDoctor(@RequestBody Doctor doctor) {
        DoctorResponseDTO nuevoDoctor = doctorService.guardar(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDoctor);
    }

    // Actualizar un doctor existente
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> actualizarDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {
        DoctorResponseDTO doctorActualizado = doctorService.actualizar(id, doctor);
        return ResponseEntity.ok(doctorActualizado);
    }

    // Eliminar un doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        doctorService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}
