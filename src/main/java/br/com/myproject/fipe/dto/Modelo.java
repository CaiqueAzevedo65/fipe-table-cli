package br.com.myproject.fipe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo(@JsonAlias("codigo") int codigo,
                     @JsonAlias("nome") String nome) {
}
