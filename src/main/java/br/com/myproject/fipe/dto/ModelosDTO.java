package br.com.myproject.fipe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloListWrapper(@JsonAlias("modelos") List<Modelo> modelosList) {
}
