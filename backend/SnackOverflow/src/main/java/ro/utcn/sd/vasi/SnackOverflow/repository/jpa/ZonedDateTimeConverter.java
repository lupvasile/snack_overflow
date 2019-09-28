package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {
    @Override
    public String convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toString();
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String s) {
        return ZonedDateTime.parse(s);
    }
}
