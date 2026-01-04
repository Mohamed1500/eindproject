# eindproject – Materiaal Reservatie Platform

## Beschrijving
Webapplicatie voor een kunstopleiding waar studenten materiaal kunnen reserveren, leningen beheren en hun eigen profiel kunnen bijhouden.

## Technologieën
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- Thymeleaf
- Bucket4j (rate limiting)

## Functionaliteiten
- Productcatalogus met filters en moderne dark mode UI
- Winkelmandje
- Checkout (bevestiging)
- Registratie en login met security
- Leningen-overzicht per gebruiker (met verwijderen en automatische voorraad-aanpassing)
- Rate limiter: max. 20 requests/minuut per gebruiker (IP)

## Security
- BCrypt password hashing
- CustomUserDetailsService voor authenticatie
- Alleen admin (rol `ADMIN`) kan naar `/h2-console` en beheerderspagina's
- CSRF alleen uitgeschakeld voor `/h2-console`, elders actief
- Rate limiting op alle endpoints

## Admin account
- **Gebruikersnaam:** admin
- **Wachtwoord:** Admin123
- De admin heeft toegang tot extra functies zoals het bekijken van de H2-console (`/h2-console`).
- Alleen de admin kan via de URL naar beheerderspagina's.
- De admin heeft de rol `ADMIN`, gewone gebruikers krijgen de rol `USER`.

### Implementatie
- Rollen worden toegekend in `CustomUserDetailsService.java`.
- Security restricties in `SecurityConfig.java`.
- Rate limiting via `RateLimitConfig.java` (Bucket4j).
- Leningenbeheer: bij verwijderen van een lening wordt de voorraad automatisch verhoogd.

### Testen
- Log in met bovenstaande gegevens om als admin te testen.
- Je kunt nu admin-functies en de H2-console gebruiken.

## Snelstart
1. Clone deze repo
2. `./mvnw spring-boot:run`
3. Open [http://localhost:8080](http://localhost:8080)

## Referenties
- Spring Boot documentation
- Baeldung tutorials
- Bucket4j docs
- GitHub Copilot




