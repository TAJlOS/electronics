package electronics.backend.controller;

import electronics.backend.entity.CPU;
import electronics.backend.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CpuRestController {
    @Autowired
    private CpuService cpuService;

    @GetMapping(value = "/api/processors")
    public Iterable<CPU> getProcessors(@RequestParam(required = false, defaultValue = "1") Integer page,
                                       @RequestParam(required = false, defaultValue = "0") Integer order,
                                       @RequestParam(required = false) Integer min_price,
                                       @RequestParam(required = false) Integer max_price,
                                       @RequestParam(required = false) List<String> producer,
                                       @RequestParam(required = false) List<String> socket,
                                       @RequestParam(required = false) List<Boolean> overclocking,
                                       @RequestParam(required = false) List<Integer> n_core,
                                       @RequestParam(required = false) List<Integer> l3_cache,
                                       @RequestParam(required = false) Integer min_tdp,
                                       @RequestParam(required = false) Integer max_tdp,
                                       @RequestParam(required = false) List<Boolean> integrated_graphic_card
    ) {
        return cpuService.getCpuList(page, order, min_price, max_price, producer, socket, overclocking, n_core, l3_cache, min_tdp, max_tdp, integrated_graphic_card);
    }
}
