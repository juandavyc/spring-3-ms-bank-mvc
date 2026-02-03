# DevSu Bank - Documentación del Proyecto

##  Información General
Este proyecto consiste en una solución bancaria desarrollada con microservicios utilizando:

Este proyecto consiste en una solución bancaria desarrollada con microservicios utilizando:

- **Maven** como gestor de dependencias.
- **Java 21** como lenguaje principal.
- **Spring MVC** para la capa web y controladores.
- **Feign Client** para la comunicación entre microservicios.
- **Circuit Breaker** para resiliencia en llamadas remotas.
- **Cache** para optimizar consultas frecuentes.
- Arquitectura basada en **dos microservicios**:
  - `accounts`
  - `clients`
- Arquitectura **Hexagonal**, promoviendo separación de responsabilidades y testabilidad.
- Pruebas **unitarias** y **end-to-end (E2E)** con Karate.
- 
    - `accounts`
    - `clients`
---

##  Endpoints Principales

### Accounts
- Obtener cuentas:  
  `http://localhost:8081/api/accounts`
- Obtener movimientos:  
  `http://localhost:8081/api/transactions`

### Reports
- Generar reportes filtrados por fechas y cliente:  
  http://localhost:8081/api/reports?startDate=2024-01-01&endDate=2024-01-06&clientId=e3f1c9c3-4b2a-4f8b-9b6d-1a2e3f4b5c6d


---

## Pruebas con Karate

### Levantar el microservicio `clients`
```bash
mvn spring-boot:run -pl :clients

mvn test -Dtest=ClientsRunner -pl :clients
mvn clean test -Dtest=ClientsRunner -pl :clients
mvn clean test -Dtest=AccountsRunner -pl :accounts

```

Nota: Para pruebas end-to-end, el microservicio clients debe estar levantado previamente.

## Docker Compose


Docker Compose

Microservicio accounts → puerto 8081
Microservicio clients → puerto 8082

Levantar servicios con Docker

Desde la raíz del proyecto, ejecutar:
```bash
cd docker-compose
docker compose up -d
```
## Saludos