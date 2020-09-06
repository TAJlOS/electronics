package electronics.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name="cpu")
public class CPU implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String producer;
    @NotNull
    private String socket;
    @NotNull
    private Integer base_frequency;
    @NotNull
    private Integer boost_frequency;
    @NotNull
    private Integer n_core;
    @NotNull
    private Integer n_thread;
    @NotNull
    private Integer tdp;
    @NotNull
    private Integer l3_cache;
    @NotNull
    private Boolean overclocking;
    @NotNull
    private Boolean integrated_graphic_card;
    private String graphic_card;
    @NotNull
    private String img;
    @NotNull
    private Double price;

}
