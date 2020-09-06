package electronics.backend.dto.filter;

import lombok.Data;

import java.util.List;

@Data
public class CheckboxFilterParameter extends FilterParameter {
    List<CpuFieldStatistic> values;

    public CheckboxFilterParameter(String name, String fieldName, List<CpuFieldStatistic> values) {
        super(name,fieldName, FilterParameterType.checkbox);
        this.values = values;
    }
}
