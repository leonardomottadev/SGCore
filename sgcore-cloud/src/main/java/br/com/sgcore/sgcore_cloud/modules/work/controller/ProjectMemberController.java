package br.com.sgcore.sgcore_cloud.modules.work.controller;

import br.com.sgcore.sgcore_cloud.modules.work.domain.ProjectMember;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/project-member")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService service;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> create(@Valid @RequestBody ProjectMemberRequestDTO dto) {
        ProjectMember projectMember = service.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectMember.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ProjectMemberResponseDTO> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Collection<ProjectMemberResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody ProjectMemberRequestDTO dto) {

        service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
