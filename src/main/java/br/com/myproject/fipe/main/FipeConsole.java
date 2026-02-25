package br.com.myproject.fipe.main;

import br.com.myproject.fipe.models.*;
import br.com.myproject.fipe.services.FipeApiClient;
import br.com.myproject.fipe.services.JacksonDataConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FipeConsole {
    private final FipeApiClient fipeApiClient;
    private final JacksonDataConverter jacksonDataConverter;
    private final Scanner scanner = new Scanner(System.in);

    @Value("${api.fipe.address}")
    private String apiBaseUrl;

    public FipeConsole(FipeApiClient fipeApiClient, JacksonDataConverter jacksonDataConverter) {
        this.fipeApiClient = fipeApiClient;
        this.jacksonDataConverter = jacksonDataConverter;
    }

    public void menu() {
        System.out.println("""
                \n***** OPÇÕES *****
                Carros
                Motos
                Caminhões
                Digite uma das opções para consultar valores:
                """);
        var tipoVeiculo = scanner.nextLine().toLowerCase();

        List<Marca> marcas = fetchMarcas(tipoVeiculo);

        marcas.forEach(marca -> System.out.println(
                marca.codigo() + " " + marca.nome()
        ));

        System.out.println("Digite o código da marca desejada: ");
        var codigoMarca = scanner.nextInt();
        scanner.nextLine();

        ModeloListWrapper modelos = fetchModelos(tipoVeiculo, codigoMarca);

        System.out.println(modelos);

        modelos.modelosList().forEach(modelo -> System.out.println(
                modelo.codigo() + " " + modelo.nome()
        ));

        System.out.println("Digite o nome de um veículo para consulta: ");
        var nomeVeiculo = scanner.nextLine().toLowerCase();

        modelos.modelosList().stream()
                .filter(modelo -> modelo.nome().toLowerCase().contains(nomeVeiculo))
                .forEach(System.out::println);

        System.out.println("Digite o código do modelo desejado: ");
        var codigoModelo = scanner.nextInt();
        scanner.nextLine();

        List<Ano> anos = fetchAnos(tipoVeiculo, codigoMarca, codigoModelo);

        List<Veiculo> veiculos = new ArrayList<>();

        for (Ano ano : anos) {
            veiculos.add(fetchVeiculo(tipoVeiculo, codigoMarca, codigoModelo, ano.codigo()));
        }

        System.out.println("Todos os veículos com os valores por ano:");

        veiculos.forEach(System.out::println);
    }

    private List<Marca> fetchMarcas(String tipoVeiculo) {
        return jacksonDataConverter.deserializeList(
                fipeApiClient.fetchJson(
                        apiBaseUrl + "/" + tipoVeiculo + "/marcas"
                ),
                Marca.class);
    }

    private ModeloListWrapper fetchModelos(String tipoVeiculo, int codigoMarca) {
        return jacksonDataConverter.deserialize(
                fipeApiClient.fetchJson(
                        apiBaseUrl + "/" + tipoVeiculo + "/marcas/"
                                + codigoMarca + "/modelos"
                ),
                ModeloListWrapper.class);
    }

    private List<Ano> fetchAnos(String tipoVeiculo, int codigoMarca, int codigoModelo) {
        return jacksonDataConverter.deserializeList(
                fipeApiClient.fetchJson(
                        apiBaseUrl + "/" + tipoVeiculo + "/marcas/"
                                + codigoMarca + "/modelos/" + codigoModelo + "/anos"
                ),
                Ano.class);
    }

    private Veiculo fetchVeiculo(String tipoVeiculo, int codigoMarca, int codigoModelo, String codigoAno) {
        return jacksonDataConverter.deserialize(
                fipeApiClient.fetchJson(
                        apiBaseUrl + "/" + tipoVeiculo + "/marcas/"
                                + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno
                ),
                Veiculo.class);
    }
}
