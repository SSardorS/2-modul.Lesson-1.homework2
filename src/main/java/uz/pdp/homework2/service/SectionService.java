package uz.pdp.homework2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework2.dto.SectionDto;
import uz.pdp.homework2.entity.Section;
import uz.pdp.homework2.repository.SectionRepository;
import uz.pdp.homework2.result.ResulBoleanAndText;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;

    public List<Section> getAll() {
        Optional<Section> java = sectionRepository.existsByName("java");
        if (java.isPresent()) {
            List<Section> byIdList = sectionRepository.findParentIdNative(java.get().getParent().getId());
            return byIdList;
        } else {
            return null;
        }
    }

    public Section getById(Long id) {
        Optional<Section> byId = sectionRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    public ResulBoleanAndText post(SectionDto sectionDto) {
        boolean b = sectionRepository.existsByParentId(sectionDto.getParentId());
        if (b) {
            return new ResulBoleanAndText(false, "This parentid is already exists");
        }

        //////////////////////////////////////

        List<Section> parentIdNative = sectionRepository.findParentIdNative(sectionDto.getParentId());
        for (Section section : parentIdNative) {
            Section sectionAdd = new Section(null, sectionDto.getName(), section);
            sectionRepository.save(sectionAdd);
        }
        return new ResulBoleanAndText(true, "ADDED");
    }


    public ResulBoleanAndText put(Long id, SectionDto sectionDto) {
        Optional<Section> byId = sectionRepository.findById(id);
        if (!byId.isPresent()) {
            return new ResulBoleanAndText(false, "This id is not foun");
        }

        /////////////////

        List<Section> parentIdNative = sectionRepository.findParentIdNative(sectionDto.getParentId());

        if (parentIdNative.isEmpty()) {
            return new ResulBoleanAndText(false, "This parent di is not found");
        }
        for (Section sectionAddParent : parentIdNative) {
            Section section = byId.get();
            section.setName(sectionDto.getName());
            section.setParent(sectionAddParent);
            sectionRepository.save(section);
        }
        return new ResulBoleanAndText(true, "edited");
    }

    public ResulBoleanAndText delete(Long id) {
        Optional<Section> byId = sectionRepository.findById(id);
        if (byId.isPresent()) {
            sectionRepository.deleteById(id);
            List<Section> parentIdNative = sectionRepository.findParentIdNative(id);
            for (Section section : parentIdNative) {
                sectionRepository.deleteById(section.getId());
            }
            return new ResulBoleanAndText(true, "Deleted");
        }
        return new ResulBoleanAndText(false, "This id si not found");
    }


}
