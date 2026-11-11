package service;

import exception.DuplicateRecordException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.ItemRecord;

/**
 * Week 13 manager: keeps records in a typed in-memory list.
 * IDs are compared without regard to letter case.
 * @author BANGOYNA
 */
public class RecordManager {
    private final List<ItemRecord> records = new ArrayList<>();

    public void add(ItemRecord record) throws DuplicateRecordException {
        if (findById(record.getId()).isPresent()) {
            throw new DuplicateRecordException(
                    "Record ID " + record.getId() + " already exists.");
        }
        records.add(record);
    }

    public Optional<ItemRecord> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        for (ItemRecord record : records) {
            if (record.getId().equalsIgnoreCase(id.trim())) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    public List<ItemRecord> findAll() {
        return List.copyOf(records);
    }
}
