# eindproject – Materiaal Reservatie Platform

## Beschrijving
Webapplicatie voor een kunstopleiding waar studenten materiaal kunnen reserveren.

## Technologieën
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- Thymeleaf

## Functionaliteiten
- Productcatalogus met filters
- Winkelmandje
- Checkout (bevestiging)
- Registratie en login met security

## Admin account
- **Gebruikersnaam:** admin
- **Wachtwoord:** Admin123
- De admin heeft toegang tot extra functies zoals het bekijken van de H2-console (`/h2-console`).
- Alleen de admin kan via de URL naar beheerderspagina's.
- De admin heeft de rol `ADMIN`, gewone gebruikers krijgen de rol `USER`.

### Implementatie
- De roltoekenning gebeurt in `CustomUserDetailsService.java`. Hier wordt gecontroleerd of de gebruikersnaam "admin" en het wachtwoord "Admin123" is, en dan wordt de rol `ADMIN` toegekend.
- In `SecurityConfig.java` zijn restricties ingesteld zodat alleen de admin toegang heeft tot admin-only routes.

### Testen
- Log in met bovenstaande gegevens om als admin te testen.
- Je kunt nu admin-functies en de H2-console gebruiken.

## Referenties
- Spring Boot documentation
- Baeldung tutorials
- GitHub Copilot (AI-assistent voor code, prompts en styling)




