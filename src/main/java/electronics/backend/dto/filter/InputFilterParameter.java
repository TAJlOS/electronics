package electronics.backend.dto.filter;

import lombok.Data;

@Data
public class InputFilterParameter extends FilterParameter {
    private Integer min;
    private Integer max;

    public InputFilterParameter(String name, String fieldName,Integer min, Integer max) {
        super(name, fieldName,FilterParameterType.input);
        this.min = min;
        this.max = max;
    }
}
