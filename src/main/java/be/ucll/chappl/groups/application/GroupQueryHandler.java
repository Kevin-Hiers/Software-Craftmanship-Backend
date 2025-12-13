package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.infrastructure.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GroupQueryHandler {
    private final GroupRepository groupRepository;

    public GroupQueryHandler(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupInformation getById(UUID groupId) {
        var group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("group not found"));
        return GroupInformation.from(group);
    }
}
