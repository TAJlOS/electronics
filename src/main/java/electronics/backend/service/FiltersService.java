package electronics.backend.service;

import electronics.backend.dto.filter.CheckboxFilterParameter;
import electronics.backend.dto.filter.FilterParameter;
import electronics.backend.dto.filter.InputFilterParameter;
import electronics.backend.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersService {
    @Autowired
    private CPURepository cpuRepository;

    public List<FilterParameter> getCpuFilters(Integer min_price,
                                               Integer max_price,
                                               List<String> producer,
                                               List<String> socket,
                                               List<Boolean> overclocking,
                                               List<Integer> n_core,
                                               List<Integer> l3_cache,
                                               Integer min_tdp,
                                               Integer max_tdp,
                                               List<Boolean> integrated_graphic_card) {
        List<FilterParameter> cpuFilterParameters = new ArrayList<>();
        cpuFilterParameters.add(new InputFilterParameter("Цена",
                "price",
                cpuRepository.findMinPrice(min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card),
                cpuRepository.findMaxPrice(min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Производитель",
                "producer",
                cpuRepository.findDistinctProducers(min_price, max_price, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Сокет",
                "socket",
                cpuRepository.findDistinctSockets(min_price, max_price, producer, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Свободный множитель",
                "overclocking",
                cpuRepository.findDistinctOverclocking(min_price, max_price, producer, socket, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Количество ядер",
                "n_core",
                cpuRepository.findDistinctNCores(min_price, max_price, producer, socket, overclocking, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Объём кэша L3",
                "l3_cache",
                cpuRepository.findDistinctL3Cache(min_price, max_price, producer, socket, overclocking, n_core, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new InputFilterParameter("Тепловыделение (TDP)",
                "tdp",
                cpuRepository.findMinTDP(min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card),
                cpuRepository.findMaxTDP(min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card)));
        cpuFilterParameters.add(new CheckboxFilterParameter("Интегрированное графическое ядро",
                "integrated_graphic_card",
                cpuRepository.findDistinctIntegratedGraphic(min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp)));
        return cpuFilterParameters;
    }
}
