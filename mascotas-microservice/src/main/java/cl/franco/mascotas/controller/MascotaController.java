package cl.franco.mascotas.controller;

import cl.franco.mascotas.dto.MascotaResponseDTO;
import cl.franco.mascotas.model.Mascota;
import cl.franco.mascotas.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

}
