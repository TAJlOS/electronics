package electronics.backend.repository;


import electronics.backend.entity.CPU;
import electronics.backend.dto.filter.CpuFieldStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CPURepository extends PagingAndSortingRepository<CPU, Long> {
    String qMinPrice = " (:minPrice is null or price >= :minPrice) ";
    String qMaxPrice = " (:maxPrice is null or price <= :maxPrice) ";
    String qProducers = " (COALESCE(:producer) is null or producer IN(:producer)) ";
    String qSocket = " (COALESCE(:socket) is null or socket IN(:socket)) ";
    String qOverclocking = " (COALESCE(:overclocking) is null or overclocking IN(:overclocking)) ";
    String qNCore = " (COALESCE(:n_core) is null or n_core IN(:n_core)) ";
    String qL3Cache = " (COALESCE(:l3_cache) is null or l3_cache IN(:l3_cache)) ";
    String qMinTDP = " (:minTDP is null or tdp >= :minTDP) ";
    String qMaxTDP = " (:maxTDP is null or tdp <= :maxTDP) ";
    String qIntegratedGraphicCard = " (COALESCE(:integrated_graphic_card) is null or integrated_graphic_card IN(:integrated_graphic_card)) ";
    String and = " AND ";

    @Query(nativeQuery = true, value = "SELECT min(price) FROM cpu WHERE "+ qMinPrice+ and + qMaxPrice + and + qProducers + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard)
    Integer findMinPrice(@Param(value = "minPrice") Integer minPrice,
                         @Param(value = "maxPrice") Integer maxPrice,
                         @Param(value = "producer") List<String> producer,
                         @Param(value = "socket") List<String> socket,
                         @Param(value = "overclocking") List<Boolean> overclocking,
                         @Param(value = "n_core") List<Integer> n_core,
                         @Param(value = "l3_cache") List<Integer> l3_cache,
                         @Param(value = "minTDP") Integer minTDP,
                         @Param(value = "maxTDP") Integer maxTDP,
                         @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT max(price) FROM cpu WHERE "+qMinPrice+ and + qMaxPrice + and +
            qProducers + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard)
    Integer findMaxPrice(@Param(value = "minPrice") Integer minPrice,
                         @Param(value = "maxPrice") Integer maxPrice,
                         @Param(value = "producer") List<String> producer,
                         @Param(value = "socket") List<String> socket,
                         @Param(value = "overclocking") List<Boolean> overclocking,
                         @Param(value = "n_core") List<Integer> n_core,
                         @Param(value = "l3_cache") List<Integer> l3_cache,
                         @Param(value = "minTDP") Integer minTDP,
                         @Param(value = "maxTDP") Integer maxTDP,
                         @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(producer) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT  producer as name, count(producer) as cnt FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard+" GROUP BY producer) AS table2 "+
            "ON table1.producer=table2.name")
    List<CpuFieldStatistic> findDistinctProducers(@Param(value = "minPrice") Integer minPrice,
                                                  @Param(value = "maxPrice") Integer maxPrice,
                                                  @Param(value = "socket") List<String> socket,
                                                  @Param(value = "overclocking") List<Boolean> overclocking,
                                                  @Param(value = "n_core") List<Integer> n_core,
                                                  @Param(value = "l3_cache") List<Integer> l3_cache,
                                                  @Param(value = "minTDP") Integer minTDP,
                                                  @Param(value = "maxTDP") Integer maxTDP,
                                                  @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(socket) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT  socket as name, count(socket) as cnt  FROM cpu  WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and +
            qOverclocking + and + qNCore + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard+" GROUP BY socket)  AS table2 "+
            "ON table1.socket=table2.name")
    List<CpuFieldStatistic> findDistinctSockets(@Param(value = "minPrice") Integer minPrice,
                                                @Param(value = "maxPrice") Integer maxPrice,
                                                @Param(value = "producer") List<String> producer,
                                                @Param(value = "overclocking") List<Boolean> overclocking,
                                                @Param(value = "n_core") List<Integer> n_core,
                                                @Param(value = "l3_cache") List<Integer> l3_cache,
                                                @Param(value = "minTDP") Integer minTDP,
                                                @Param(value = "maxTDP") Integer maxTDP,
                                                @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(overclocking) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT  overclocking as name, count(overclocking) as cnt  FROM cpu  WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and +
            qSocket  + and + qNCore + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard+" GROUP BY overclocking)  AS table2 "+
            "ON table1.overclocking=table2.name")
    List<CpuFieldStatistic> findDistinctOverclocking(@Param(value = "minPrice") Integer minPrice,
                                                     @Param(value = "maxPrice") Integer maxPrice,
                                                     @Param(value = "producer") List<String> producer,
                                                     @Param(value = "socket") List<String> socket,
                                                     @Param(value = "n_core") List<Integer> n_core,
                                                     @Param(value = "l3_cache") List<Integer> l3_cache,
                                                     @Param(value = "minTDP") Integer minTDP,
                                                     @Param(value = "maxTDP") Integer maxTDP,
                                                     @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(n_core) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT  n_core as name, count(n_core) as cnt  FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and +
            qSocket + and + qOverclocking + and + qL3Cache + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard+" GROUP BY n_core)  AS table2  "+
            "ON table1.n_core=table2.name")
    List<CpuFieldStatistic> findDistinctNCores(@Param(value = "minPrice") Integer minPrice,
                                               @Param(value = "maxPrice") Integer maxPrice,
                                               @Param(value = "producer") List<String> producer,
                                               @Param(value = "socket") List<String> socket,
                                               @Param(value = "overclocking") List<Boolean> overclocking,
                                               @Param(value = "l3_cache") List<Integer> l3_cache,
                                               @Param(value = "minTDP") Integer minTDP,
                                               @Param(value = "maxTDP") Integer maxTDP,
                                               @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(l3_cache) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT  l3_cache as name, count(l3_cache) as cnt  FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and +
            qSocket + and + qOverclocking + and + qNCore + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard+" GROUP BY l3_cache)  AS table2 "+
            "ON table1.l3_cache=table2.name")
    List<CpuFieldStatistic> findDistinctL3Cache(@Param(value = "minPrice") Integer minPrice,
                                                @Param(value = "maxPrice") Integer maxPrice,
                                                @Param(value = "producer") List<String> producer,
                                                @Param(value = "socket") List<String> socket,
                                                @Param(value = "overclocking") List<Boolean> overclocking,
                                                @Param(value = "n_core") List<Integer> n_core,
                                                @Param(value = "minTDP") Integer minTDP,
                                                @Param(value = "maxTDP") Integer maxTDP,
                                                @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT min(tdp) FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and + qMinTDP + and + qMaxTDP + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache + and + qIntegratedGraphicCard)
    Integer findMinTDP(@Param(value = "minPrice") Integer minPrice,
                       @Param(value = "maxPrice") Integer maxPrice,
                       @Param(value = "producer") List<String> producer,
                       @Param(value = "socket") List<String> socket,
                       @Param(value = "overclocking") List<Boolean> overclocking,
                       @Param(value = "n_core") List<Integer> n_core,
                       @Param(value = "l3_cache") List<Integer> l3_cache,
                       @Param(value = "minTDP") Integer minTDP,
                       @Param(value = "maxTDP") Integer maxTDP,
                       @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT max(tdp) FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and + qMinTDP + and + qMaxTDP + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache  + and + qIntegratedGraphicCard)
    Integer findMaxTDP(@Param(value = "minPrice") Integer minPrice,
                       @Param(value = "maxPrice") Integer maxPrice,
                       @Param(value = "producer") List<String> producer,
                       @Param(value = "socket") List<String> socket,
                       @Param(value = "overclocking") List<Boolean> overclocking,
                       @Param(value = "n_core") List<Integer> n_core,
                       @Param(value = "l3_cache") List<Integer> l3_cache,
                       @Param(value = "minTDP") Integer minTDP,
                       @Param(value = "maxTDP") Integer maxTDP,
                       @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card);


    @Query(nativeQuery = true, value = "SELECT DISTINCT(integrated_graphic_card) AS name, table2.cnt AS cnt FROM cpu  AS table1 "+
            "LEFT JOIN (SELECT integrated_graphic_card as name, count(integrated_graphic_card) as cnt FROM cpu WHERE "+
            qMinPrice + and + qMaxPrice + and + qProducers + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache  + and +
            qMinTDP + and + qMaxTDP +" GROUP BY integrated_graphic_card)  AS table2 "+
            "ON table1.integrated_graphic_card=table2.name")
    List<CpuFieldStatistic> findDistinctIntegratedGraphic(@Param(value = "minPrice") Integer minPrice,
                                                          @Param(value = "maxPrice") Integer maxPrice,
                                                          @Param(value = "producer") List<String> producer,
                                                          @Param(value = "socket") List<String> socket,
                                                          @Param(value = "overclocking") List<Boolean> overclocking,
                                                          @Param(value = "n_core") List<Integer> n_core,
                                                          @Param(value = "l3_cache") List<Integer> l3_cache,
                                                          @Param(value = "minTDP") Integer minTDP,
                                                          @Param(value = "maxTDP") Integer maxTDP);


    @Query(nativeQuery = true, value = "SELECT * FROM cpu WHERE " + qMinPrice + and + qMaxPrice + and + qProducers + and +
            qSocket + and + qOverclocking + and + qNCore + and + qL3Cache  + and +
            qMinTDP + and + qMaxTDP + and + qIntegratedGraphicCard)
    Page<CPU> findAll(@Param(value = "minPrice") Integer minPrice,
                      @Param(value = "maxPrice") Integer maxPrice,
                      @Param(value = "producer") List<String> producer,
                      @Param(value = "socket") List<String> socket,
                      @Param(value = "overclocking") List<Boolean> overclocking,
                      @Param(value = "n_core") List<Integer> n_core,
                      @Param(value = "l3_cache") List<Integer> l3_cache,
                      @Param(value = "minTDP") Integer minTDP,
                      @Param(value = "maxTDP") Integer maxTDP,
                      @Param(value = "integrated_graphic_card") List<Boolean> integrated_graphic_card,
                      Pageable page);
}
