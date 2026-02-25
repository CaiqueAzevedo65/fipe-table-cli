# fipe-table-cli

A Spring Boot CLI application for querying vehicle prices from Brazil's FIPE table API.

## About

This is a command-line tool that consumes the [FIPE API](https://deividfortuna.github.io/fipe/) to look up vehicle prices across all available model years. The user selects a vehicle type, brand, and model, and the application returns pricing data for every year available.

This project was built as a learning exercise focused on clean code practices, proper naming conventions, and Spring Boot fundamentals.

## Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Jackson** for JSON deserialization
- **Java HttpClient** for API consumption

## How It Works

1. Choose a vehicle type (cars, motorcycles, or trucks)
2. Select a brand from the listed options
3. Search for a model by name
4. Select a specific model by code
5. View prices for all available model years

## Running

```bash
./mvnw spring-boot:run
```

## Example

```
***** OPÇÕES *****
Carros
Motos
Caminhões
Digite uma das opções para consultar valores:
> carros

1 Acura
2 Agrale
3 Alfa Romeo
...

Digite o código da marca desejada:
> 59

Digite o nome de um veículo para consulta:
> civic

Digite o código do modelo desejado:
> 5765

Todos os veículos com os valores por ano:
Veiculo[valor=R$ 135.245,00, marca=Honda, modelo=Civic Sed. EXL 2.0, anoModelo=2024]
Veiculo[valor=R$ 120.930,00, marca=Honda, modelo=Civic Sed. EXL 2.0, anoModelo=2023]
...
```

## Project Structure

```
src/main/java/br/com/myproject/fipe/
├── FipeApplication.java            # Entry point (CommandLineRunner)
├── main/
│   └── FipeConsole.java            # CLI menu and user interaction
├── models/
│   ├── Ano.java                    # Year record
│   ├── Marca.java                  # Brand record
│   ├── Modelo.java                 # Model record
│   ├── ModeloListWrapper.java      # API response wrapper for models
│   └── Veiculo.java                # Vehicle pricing record
└── services/
    ├── FipeApiClient.java          # HTTP client for FIPE API
    ├── JsonDeserializer.java       # Deserialization contract (interface)
    └── JacksonDataConverter.java   # Jackson-based implementation
```

## What I Practiced

- Meaningful naming (Clean Code, Effective Java)
- Single Responsibility Principle
- Dependency Inversion via Spring IoC
- Interface/implementation separation
- Java Records for immutable DTOs
