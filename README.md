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
- Admin dashboard met gebruikers, productvoorraad en alle reservaties
- Rate limiter: max. 60 requests/minuut per gebruiker (IP); statische bestanden tellen niet mee

## Security
- BCrypt password hashing
- CustomUserDetailsService voor authenticatie
- Alleen gebruikers met rol `ADMIN` kunnen naar `/h2-console` en beheerderspagina's
- CSRF alleen uitgeschakeld voor `/h2-console`, elders actief
- Rate limiting op applicatie-endpoints; statische bestanden worden uitgesloten

## Admin account
- Nieuwe gebruikers krijgen automatisch de rol `USER`.
- Een beheerder krijgt de rol `ADMIN` via de kolom `role` in de tabel `app_user`.
- Een bestaande gebruiker kan eenmalig beheerder worden gemaakt met:

```sql
UPDATE app_user SET role = 'ADMIN' WHERE username = 'admin';
```

### Implementatie
- Rollen worden opgeslagen in `User.java` en gebruikt in `CustomUserDetailsService.java`.
- Het admin dashboard staat in `AdminController.java` en `admin.html`.
- Security restricties in `SecurityConfig.java`.
- Rate limiting via `RateLimitConfig.java` (Bucket4j).
- Leningenbeheer: bij verwijderen van een lening wordt de voorraad automatisch verhoogd.

### Testen
- Maak een gebruiker aan via registratie.
- Zet voor een beheertest de kolom `role` van die gebruiker op `ADMIN` via de H2-console.
- Log opnieuw in en open `/admin` of `/h2-console`.

## Snelstart
1. Clone deze repo
2. `./mvnw spring-boot:run`
3. Open [http://localhost:8080](http://localhost:8080)

## Referenties
- Spring Boot documentation
- Baeldung tutorials
- Bucket4j docs
- GitHub Copilot