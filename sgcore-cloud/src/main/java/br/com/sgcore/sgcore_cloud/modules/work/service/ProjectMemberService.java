package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.ProjectMember;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.ProjectMemberMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.ProjectMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectMemberService implements GenericCrudService<ProjectMember, ProjectMemberRequestDTO, ProjectMemberResponseDTO> {

    private final ProjectMemberRepository repository;

    public ProjectMemberService(ProjectMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectMemberResponseDTO create(ProjectMemberRequestDTO dto) {
        ProjectMember entity = ProjectMemberMapper.toEntity(dto);
        ProjectMember saved = repository.save(entity);
        return ProjectMemberMapper.toDTO(saved);
    }

    @Override
    public List<ProjectMemberResponseDTO> createAll(List<ProjectMemberRequestDTO> dtoList) {
        List<ProjectMember> entities = dtoList.stream()
                .map(ProjectMemberMapper::toEntity)
                .toList();
        List<ProjectMember> saved = repository.saveAll(entities);
        return saved.stream().map(ProjectMemberMapper::toDTO).toList();
    }

    @Override
    public ProjectMemberResponseDTO findById(Long id) {
        Optional<ProjectMember> projectMemberEntity = repository.findById(id);
        return projectMemberEntity.map(ProjectMemberMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Project Member not found"));
    }

    @Override
    public List<ProjectMemberResponseDTO> findAll() {
        List<ProjectMember> projectMembers = repository.findAll();
        return projectMembers.stream().map(ProjectMemberMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProjectMemberResponseDTO update(Long id, ProjectMemberRequestDTO dto) {
        ProjectMember entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project Member not found"));
        ProjectMember saved = repository.save(ProjectMemberMapper.updateEntity(entity, dto));
        return ProjectMemberMapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
