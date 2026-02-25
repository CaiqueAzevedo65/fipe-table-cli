package br.com.myproject.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo(@JsonAlias("codigo") int codigo,
                     @JsonAlias("nome") String nome) {
}
