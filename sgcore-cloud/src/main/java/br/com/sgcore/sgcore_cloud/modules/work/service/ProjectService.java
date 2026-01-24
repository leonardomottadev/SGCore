package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.ProjectMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.ProjectRepository;
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
    public Project create(ProjectRequestDTO dto) {
        Project entity = ProjectMapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public List<Project> createAll(List<ProjectRequestDTO> dtoList) {
        List<Project> entities = dtoList.stream()
                .map(ProjectMapper::toEntity)
                .toList();
        return repository.saveAll(entities);
    }

    @Override
    public ProjectResponseDTO findById(Long id) {
        Optional<Project> projectEntity = repository.findById(id);
        return projectEntity.map(ProjectMapper::toDTO).orElse(null);
    }

    @Override
    public List<ProjectResponseDTO> findAll() {
        List<Project> projects = repository.findAll();
        return projects.stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, ProjectRequestDTO dto) {
        Optional<Project> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return;
        }
        repository.save(ProjectMapper.toEntity(dto));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
