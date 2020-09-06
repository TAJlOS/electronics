package electronics.backend.dto.filter;

import lombok.Data;

@Data
public abstract class FilterParameter {
    private String name;
    private String fieldName;
    private FilterParameterType type;

    public FilterParameter(String name, String fieldName,FilterParameterType type) {
        this.name = name;
        this.fieldName = fieldName;
        this.type = type;
    }
}
