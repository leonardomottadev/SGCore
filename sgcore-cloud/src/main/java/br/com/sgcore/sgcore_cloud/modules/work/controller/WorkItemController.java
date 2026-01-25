package br.com.sgcore.sgcore_cloud.modules.work.controller;

import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.service.WorkItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/work-item")
@RequiredArgsConstructor
public class WorkItemController {

    private final WorkItemService service;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public  ResponseEntity<WorkItemResponseDTO> create(@Valid @RequestBody WorkItemRequestDTO dto) {
        WorkItemResponseDTO workItem = service.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(workItem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(workItem);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<WorkItemResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Collection<WorkItemResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WorkItemResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody WorkItemRequestDTO dto) {
        WorkItemResponseDTO updatedWorkItem = service.update(id, dto);
        return ResponseEntity.ok().body(updatedWorkItem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
