# Documentatie – Materiaalreservatieplatform

## Inleiding
Deze webapplicatie is ontwikkeld als proof of concept voor een kunstopleiding. Studenten kunnen materiaal (zoals lampen, podiumelementen, kabels, etc.) reserveren en huren voor hun projecten. De applicatie is gebouwd met Java 17, Spring Boot, Spring Security, Spring Data JPA en Thymeleaf.

## Architectuur en Opbouw
- **Backend:** Java (Spring Boot), met controllers voor catalogus, winkelmandje, registratie, login en checkout.
- **Frontend:** Thymeleaf-templates met moderne, donkere styling (zie `style.css`).
- **Dataopslag:** In-memory repository (`InMemoryProductRepository`) voor producten, H2 database voor gebruikers.
- **Security:** Spring Security met BCrypt hashing voor wachtwoorden. Adminrol wordt toegekend op basis van gebruikersnaam en wachtwoord.


## Belangrijkste Functionaliteiten
- **Catalogus:** Overzicht van alle producten, met zoek- en filtermogelijkheden op categorie.
- **Winkelmandje:** Gebruikers kunnen producten toevoegen aan hun winkelmandje (sessie-gebaseerd), aantallen aanpassen en verwijderen.
- **Checkout:** Bevestigingspagina met keuze van afhaaldatum en optionele opmerkingen.
- **Registratie & Login:** Veilige registratie en login met wachtwoordhashing. Unieke gebruikersnaam en e-mail verplicht.
- **Leningen:** Gebruikers kunnen hun eigen leningen bekijken en verwijderen. Bij verwijderen van een lening wordt de voorraad van het product automatisch verhoogd.
- **Rate Limiting:** Er geldt een limiet van 20 requests per minuut per gebruiker (IP) via Bucket4j. Bij overschrijding krijgt de gebruiker een HTTP 429 Too Many Requests.
- **Beveiliging:** Alleen geregistreerde gebruikers kunnen reserveren. Admin heeft extra rechten. CSRF is alleen uitgeschakeld voor de H2-console.

## Belangrijkste Klassen en Bestanden
- `Product`, `User`, `WinkelmandItem`: Domeinmodellen voor producten, gebruikers en winkelmanditems.
- `ProductRepository`, `InMemoryProductRepository`, `UserRepository`: Dataopslag en -opvraging.
- `CatalogusController`, `WinkelmandController`, `CheckoutController`, `RegistrationController`, `HomeController`: Verwerken van webverzoeken.
- `CustomUserDetailsService`, `UserService`: Authenticatie, registratie en gebruikersbeheer.
- `SecurityConfig`: Configuratie van Spring Security, wachtwoordhashing en toegangsrestricties.
- `templates/`: Thymeleaf-HTML-templates voor alle pagina's.
- `static/style.css`: Moderne, consistente styling voor alle pagina's.


## Security & Designkeuzes
- **Wachtwoorden:** Worden gehasht met BCrypt.
- **Adminrol:** Wordt toegekend als gebruikersnaam "admin" en wachtwoord "Admin123" overeenkomen.
- **Toegangscontrole:** Alleen ingelogde gebruikers kunnen producten reserveren. Admin-only routes zijn afgeschermd.
- **CSRF:** Alleen uitgeschakeld voor `/h2-console`, elders actief.
- **Rate limiting:** Bucket4j rate limiter op alle endpoints (20 requests/minuut/IP).
- **Sessie:** Winkelmandje wordt per gebruiker in de sessie bijgehouden.
- **Validatie:** Unieke gebruikersnaam en e-mail bij registratie. Foutmeldingen worden getoond bij mislukte login/registratie.


## Uitleg per core functionaliteit
- **Data weergeven & filteren:** Catalogus toont alle producten, met zoekbalk en categorie-filter. Zie `CatalogusController` en `catalogus.html`.
- **Login & registratie:** Veilige registratie en login, met foutafhandeling. Zie `RegistrationController`, `UserService`, `login.html`, `register.html`.
- **Sessie & checkout:** Winkelmandje wordt in de sessie bijgehouden. Checkoutpagina toont inhoud en laat bevestigen toe. Zie `WinkelmandController`, `CheckoutController`, `winkelmand.html`, `checkout.html`.
- **Leningenbeheer:** Gebruikers kunnen hun leningen bekijken en verwijderen. Bij verwijderen van een lening wordt de voorraad van het product automatisch verhoogd. Zie `LeningController`, `LeningService`, `leningen.html`.
- **Rate limiting:** Zie `config/RateLimitConfig.java` voor de implementatie van de rate limiter.

## Extra's
- **Moderne UI:** Donkere, toegankelijke en responsieve layout.
- **Gebruiksvriendelijkheid:** Direct zoeken/filteren, duidelijke foutmeldingen, bevestigingspagina.

## Aanvullende informatie
- Zie README.md voor referenties en adminaccount.
- Zie HELP.md voor technische links.

