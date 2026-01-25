package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.ProjectMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService implements GenericCrudService<Project, ProjectRequestDTO, ProjectResponseDTO> {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        Project entity = ProjectMapper.toEntity(dto);
        Project saved = repository.save(entity);
        return ProjectMapper.toDTO(saved);
    }

    @Override
    public List<ProjectResponseDTO> createAll(List<ProjectRequestDTO> dtoList) {
        List<Project> entities = dtoList.stream()
                .map(ProjectMapper::toEntity)
                .toList();
        List<Project> saved = repository.saveAll(entities);
        return saved.stream().map(ProjectMapper::toDTO).toList();
    }

    @Override
    public ProjectResponseDTO findById(Long id) {
        Optional<Project> projectEntity = repository.findById(id);
        return projectEntity.map(ProjectMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Override
    public List<ProjectResponseDTO> findAll() {
        List<Project> projects = repository.findAll();
        return projects.stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDTO update(Long id, ProjectRequestDTO dto) {
        Project entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        Project saved = repository.save(ProjectMapper.updateEntity(entity, dto));
        return ProjectMapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
