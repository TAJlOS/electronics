package electronics.backend.service;

import electronics.backend.entity.CPU;
import electronics.backend.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CpuService {
    @Autowired
    private CPURepository cpuRepository;

    public Iterable<CPU> getCpuList(Integer page,
                                Integer order,
                                Integer min_price,
                                Integer max_price,
                                List<String> producer,
                                List<String> socket,
                                List<Boolean> overclocking,
                                List<Integer> n_core,
                                List<Integer> l3_cache,
                                Integer min_tdp,
                                Integer max_tdp,
                                List<Boolean> integrated_graphic_card) {
        page--; // client pages start with 1 but server pages start with 0
        Sort sort = null;
        switch (order) {
            case 0:
                sort = Sort.by("price").ascending();
                break;
            case 1:
                sort = Sort.by("price").descending();
                break;
            case 2:
                sort = Sort.by("name").ascending();
                break;

        }
        if (sort == null) {
            return new ArrayList<>();
        }
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return cpuRepository.findAll(min_price, max_price, producer, socket, overclocking,
                n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card, pageRequest);

    }
}
