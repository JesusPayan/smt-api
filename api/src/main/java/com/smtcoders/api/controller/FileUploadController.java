package com.smtcoders.api.controller;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*") // Asegúrate de permitir el origen de tu frontend
@Slf4j
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String encodeRecibe;
        System.out.println("Entro al controlador ");
        final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
                "image/jpeg", "image/png", "application/pdf"
        );
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío.");
        }

        String contentType = file.getContentType();
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Tipo de archivo no soportado: " + contentType);
        }

        try {
            // Aquí podrías guardar el archivo en disco o base de datos si deseas
            //byte[] fileBytes = file.getBytes();
            byte[] fileBytes = file.getInputStream().readAllBytes();
            System.out.println("Bytes original  del archivo recibido" + fileBytes);
            encodeRecibe = Base64.getEncoder().encode(fileBytes).toString();
            System.out.println("Bytes original  del archivo recibido" + encodeRecibe);
            String originalFilename = file.getOriginalFilename();
            Path path = Paths.get("/tmp/" + originalFilename);
            Files.write(path, fileBytes);

            // Por ejemplo: guardar en /tmp
            // Path path = Paths.get("/tmp/" + originalFilename);
            // Files.write(path, fileBytes);

            return ResponseEntity.ok("Archivo recibido correctamente: " + originalFilename);
        } catch (IOException e) {
            log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el archivo.");
        }
    }
}


/*
controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(@RequestParam("file") MultipartFile file) {
        try {
            archivoService.guardarArchivo(file);
            return ResponseEntity.ok("Archivo guardado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar el archivo.");
        }
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public Archivo guardarArchivo(MultipartFile file) throws Exception {
        Archivo archivo = new Archivo();
        archivo.setNombre(file.getOriginalFilename());
        archivo.setTipo(file.getContentType());
        archivo.setDatos(file.getBytes());
        return archivoRepository.save(archivo);
    }
}
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}

import jakarta.persistence.*;

@Entity
@Table(name = "archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;

    @Lob
    @Column(name = "datos", columnDefinition = "LONGBLOB")
    private byte[] datos;

    // Getters y Setters
}*/