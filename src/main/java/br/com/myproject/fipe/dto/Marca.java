package br.com.myproject.fipe.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marca(@JsonAlias("codigo") int codigo,
                    @JsonAlias("nome") String nome) {
}
