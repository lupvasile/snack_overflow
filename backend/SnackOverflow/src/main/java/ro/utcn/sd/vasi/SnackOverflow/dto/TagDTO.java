package ro.utcn.sd.vasi.SnackOverflow.dto;

import lombok.Data;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;

@Data
public class TagDTO {
    private final String value;

    public static TagDTO ofEntity(Tag tag) {
        return new TagDTO(tag.getName());
    }
}
