package com.bdm.inserciones;

import com.bmd.entities.Artista;
import com.bmd.entities.Integrante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class InsertArtistas {

    public List<Artista> crearArtistas() {
        List<Artista> artistas = new ArrayList<>();

        // Artista Selena Quintanilla
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Selena Quintanilla")
            .setImagen("selena.jpg")
            .setGenero("Cumbia")
            .build());

        // Artista Shakira
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Shakira")
            .setImagen("shakira.jpg")
            .setGenero("Pop")
            .build());

        // Artista Enrique Iglesias
        artistas.add(new Artista.Builder()
           .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Enrique Iglesias")
            .setImagen("enrique_iglesias.jpg")
            .setGenero("Pop")
            .build());

        // Artista Daddy Yankee
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Daddy Yankee")
            .setImagen("daddy_yankee.jpg")
            .setGenero("Reggaeton")
            .build());

         // Artista Bad Bunny
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Bad Bunny")
            .setImagen("bad_bunny.jpg")
            .setGenero("Reggaeton")
            .build());

        // Artista Ozuna
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Ozuna")
            .setImagen("ozuna.jpg")
            .setGenero("Reggaeton")
            .build());

        // Artista Camila
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Camila")
            .setImagen("camila.jpg")
            .setGenero("Pop")
            .build());

        // Artista Ricky Martin
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Ricky Martin")
            .setImagen("ricky_martin.jpg")
            .setGenero("Pop")
            .build());

        // Artista Juanes
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Juanes")
            .setImagen("juanes.jpg")
            .setGenero("Rock")
            .build());

        // Artista Alejandro Sanz
        artistas.add(new Artista.Builder()
           .setId(new ObjectId())
            .setNombre("Alejandro Sanz")
            .setImagen("alejandro_sanz.jpg")
            .setGenero("Pop")
            .build());

        // Artista Marc Anthony
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Marc Anthony")
            .setImagen("marc_anthony.jpg")
            .setGenero("Salsa")
            .build());

        // Artista Jennifer Lopez
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Jennifer Lopez")
            .setImagen("jennifer_lopez.jpg")
            .setGenero("Pop")
            .build());

        // Artista Luis Fonsi
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Luis Fonsi")
            .setImagen("luis_fonsi.jpg")
            .setGenero("Pop")
            .build());

        // Artista Carlos Vives
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Carlos Vives")
            .setImagen("carlos_vives.jpg")
            .setGenero("Vallenato")
            .build());

        // Artista Juan Gabriel
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Juan Gabriel")
            .setImagen("juan_gabriel.jpg")
            .setGenero("Pop")
            .build());

        // Artista Alejandro Fernández
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Alejandro Fernández")
            .setImagen("alejandro_fernandez.jpg")
            .setGenero("Ranchera")
            .build());

        // Artista Vicente Fernández
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Vicente Fernández")
            .setImagen("vicente_fernandez.jpg")
            .setGenero("Ranchera")
            .build());

        // Artista Pedro Infante
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Pedro Infante")
            .setImagen("pedro_infante.jpg")
            .setGenero("Ranchera")
            .build());

        // Artista La India
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("La India")
            .setImagen("la_india.jpg")
            .setGenero("Salsa")
            .build());

        // Artista Celia Cruz
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setNombre("Celia Cruz")
            .setImagen("celia_cruz.jpg")
            .setGenero("Salsa")
            .build());

        // Artista Romeo Santos
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Romeo Santos")
                .setImagen("romeo_santos.jpg")
                .setGenero("Bachata")
                .build());

        // Artista Prince Royce
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Prince Royce")
                .setImagen("prince_royce.jpg")
                .setGenero("Bachata")
                .build());

        // Artista Karol G
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Karol G")
                .setImagen("karol_g.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista J Balvin
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("J Balvin")
                .setImagen("j_balvin.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista Maluma
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Maluma")
                .setImagen("maluma.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista Nicky Jam
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Nicky Jam")
                .setImagen("nicky_jam.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista Zion & Lennox
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Zion & Lennox")
                .setImagen("zion_lennox.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista Anuel AA
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Anuel AA")
                .setImagen("anuel_aa.jpg")
                .setGenero("Reggaeton")
                .build());

        // Artista La Banda MS
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("La Banda MS")
                .setImagen("la_banda_ms.jpg")
                .setGenero("Banda")
                .build());

        // Artista Intocable
        artistas.add(new Artista.Builder()
                .setId(new ObjectId())
                .setNombre("Intocable")
                .setImagen("intocable.jpg")
                .setGenero("Banda")
                .build());

        
        
        //1 Banda The Beatles
        List<Integrante> integrantesBeatles = new ArrayList<>();
        integrantesBeatles.add(new Integrante.Builder()
            .setNombre("John Lennon")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1960, 1, 1))
            .setFechaSalida(LocalDate.of(1970, 4, 10))
            .setEstadoActividad(false)
            .build());
        integrantesBeatles.add(new Integrante.Builder()
            .setNombre("Paul McCartney")
            .setRol("Vocalista y bajista")
            .setFechaIngreso(LocalDate.of(1960, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesBeatles.add(new Integrante.Builder()
            .setNombre("George Harrison")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1960, 1, 1))
            .setFechaSalida(LocalDate.of(1970, 4, 10))
            .setEstadoActividad(false)
            .build());
        integrantesBeatles.add(new Integrante.Builder()
            .setNombre("Ringo Starr")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1962, 8, 18))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The Beatles")
            .setImagen("the_beatles.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesBeatles)
            .build());
        
                //2 Banda Queen
        List<Integrante> integrantesQueen = new ArrayList<>();
        integrantesQueen.add(new Integrante.Builder()
            .setNombre("Freddie Mercury")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setFechaSalida(LocalDate.of(1991, 11, 24))
            .setEstadoActividad(false)
            .build());
        integrantesQueen.add(new Integrante.Builder()
            .setNombre("Brian May")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesQueen.add(new Integrante.Builder()
            .setNombre("John Deacon")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesQueen.add(new Integrante.Builder()
            .setNombre("Roger Taylor")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Queen")
            .setImagen("queen.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesQueen)
            .build());

        //3 Banda Metallica
        List<Integrante> integrantesMetallica = new ArrayList<>();
        integrantesMetallica.add(new Integrante.Builder()
            .setNombre("James Hetfield")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1981, 10, 28))
            .setEstadoActividad(true)
            .build());
        integrantesMetallica.add(new Integrante.Builder()
            .setNombre("Lars Ulrich")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1981, 10, 28))
            .setEstadoActividad(true)
            .build());
        integrantesMetallica.add(new Integrante.Builder()
            .setNombre("Kirk Hammett")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1983, 4, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMetallica.add(new Integrante.Builder()
            .setNombre("Robert Trujillo")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2003, 2, 24))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Metallica")
            .setImagen("metallica.jpg")
            .setGenero("Metal")
            .setIntegrantes(integrantesMetallica)
            .build());

        //4 Banda U2
        List<Integrante> integrantesU2 = new ArrayList<>();
        integrantesU2.add(new Integrante.Builder()
            .setNombre("Bono")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1976, 9, 25))
            .setEstadoActividad(true)
            .build());
        integrantesU2.add(new Integrante.Builder()
            .setNombre("The Edge")
            .setRol("Guitarrista y tecladista")
            .setFechaIngreso(LocalDate.of(1976, 9, 25))
            .setEstadoActividad(true)
            .build());
        integrantesU2.add(new Integrante.Builder()
            .setNombre("Adam Clayton")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1976, 9, 25))
            .setEstadoActividad(true)
            .build());
        integrantesU2.add(new Integrante.Builder()
            .setNombre("Larry Mullen Jr.")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1976, 9, 25))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("U2")
            .setImagen("u2.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesU2)
            .build());

        //5 Banda Nirvana
        List<Integrante> integrantesNirvana = new ArrayList<>();
        integrantesNirvana.add(new Integrante.Builder()
            .setNombre("Kurt Cobain")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1987, 1, 1))
            .setFechaSalida(LocalDate.of(1994, 4, 5))
            .setEstadoActividad(false)
            .build());
        integrantesNirvana.add(new Integrante.Builder()
            .setNombre("Krist Novoselic")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1987, 1, 1))
            .setEstadoActividad(false)
            .build());
        integrantesNirvana.add(new Integrante.Builder()
            .setNombre("Dave Grohl")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1990, 10, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Nirvana")
            .setImagen("nirvana.jpg")
            .setGenero("Grunge")
            .setIntegrantes(integrantesNirvana)
            .build());

        //6 Banda AC/DC
        List<Integrante> integrantesACDC = new ArrayList<>();
        integrantesACDC.add(new Integrante.Builder()
            .setNombre("Angus Young")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1973, 11, 1))
            .setEstadoActividad(true)
            .build());
        integrantesACDC.add(new Integrante.Builder()
            .setNombre("Phil Rudd")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1975, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesACDC.add(new Integrante.Builder()
            .setNombre("Cliff Williams")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1977, 6, 1))
            .setEstadoActividad(true)
            .build());
        integrantesACDC.add(new Integrante.Builder()
            .setNombre("Brian Johnson")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1980, 4, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("AC/DC")
            .setImagen("acdc.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesACDC)
            .build());

        //7 Banda The Rolling Stones
        List<Integrante> integrantesRollingStones = new ArrayList<>();
        integrantesRollingStones.add(new Integrante.Builder()
            .setNombre("Mick Jagger")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1962, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRollingStones.add(new Integrante.Builder()
            .setNombre("Keith Richards")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1962, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRollingStones.add(new Integrante.Builder()
            .setNombre("Ronnie Wood")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1975, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRollingStones.add(new Integrante.Builder()
            .setNombre("Charlie Watts")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1963, 1, 1))
            .setFechaSalida(LocalDate.of(2021, 8, 24))
            .setEstadoActividad(false)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The Rolling Stones")
            .setImagen("the_rolling_stones.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesRollingStones)
            .build());

        
        //8 Banda Guns N' Roses
        List<Integrante> integrantesGNR = new ArrayList<>();
        integrantesGNR.add(new Integrante.Builder()
            .setNombre("Axl Rose")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1985, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesGNR.add(new Integrante.Builder()
            .setNombre("Slash")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1985, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesGNR.add(new Integrante.Builder()
            .setNombre("Duff McKagan")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1985, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesGNR.add(new Integrante.Builder()
            .setNombre("Steven Adler")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1985, 1, 1))
            .setFechaSalida(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(false)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Guns N' Roses")
            .setImagen("guns_n_roses.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesGNR)
            .build());

        //9 Banda Red Hot Chili Peppers
        List<Integrante> integrantesRHCP = new ArrayList<>();
        integrantesRHCP.add(new Integrante.Builder()
            .setNombre("Anthony Kiedis")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1983, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRHCP.add(new Integrante.Builder()
            .setNombre("Flea")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1983, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRHCP.add(new Integrante.Builder()
            .setNombre("John Frusciante")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1988, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesRHCP.add(new Integrante.Builder()
            .setNombre("Chad Smith")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1988, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Red Hot Chili Peppers")
            .setImagen("red_hot_chili_peppers.jpg")
            .setGenero("Funk Rock")
            .setIntegrantes(integrantesRHCP)
            .build());

        //10 Banda Pearl Jam
        List<Integrante> integrantesPearlJam = new ArrayList<>();
        integrantesPearlJam.add(new Integrante.Builder()
            .setNombre("Eddie Vedder")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesPearlJam.add(new Integrante.Builder()
            .setNombre("Mike McCready")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesPearlJam.add(new Integrante.Builder()
            .setNombre("Stone Gossard")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesPearlJam.add(new Integrante.Builder()
            .setNombre("Jeff Ament")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesPearlJam.add(new Integrante.Builder()
            .setNombre("Matt Cameron")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1998, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Pearl Jam")
            .setImagen("pearl_jam.jpg")
            .setGenero("Grunge")
            .setIntegrantes(integrantesPearlJam)
            .build());

        //11 Banda Aerosmith
        List<Integrante> integrantesAerosmith = new ArrayList<>();
        integrantesAerosmith.add(new Integrante.Builder()
            .setNombre("Steven Tyler")
            .setRol("Vocalista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesAerosmith.add(new Integrante.Builder()
            .setNombre("Joe Perry")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesAerosmith.add(new Integrante.Builder()
            .setNombre("Brad Whitford")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1971, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesAerosmith.add(new Integrante.Builder()
            .setNombre("Tom Hamilton")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesAerosmith.add(new Integrante.Builder()
            .setNombre("Joey Kramer")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1970, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Aerosmith")
            .setImagen("aerosmith.jpg")
            .setGenero("Rock")
            .setIntegrantes(integrantesAerosmith)
            .build());

        //12 Banda Foo Fighters
        List<Integrante> integrantesFooFighters = new ArrayList<>();
        integrantesFooFighters.add(new Integrante.Builder()
            .setNombre("Dave Grohl")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesFooFighters.add(new Integrante.Builder()
            .setNombre("Nate Mendel")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1995, 3, 1))
            .setEstadoActividad(true)
            .build());
        integrantesFooFighters.add(new Integrante.Builder()
            .setNombre("Taylor Hawkins")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1997, 3, 18))
            .setFechaSalida(LocalDate.of(2022, 3, 25))
            .setEstadoActividad(false)
            .build());
        integrantesFooFighters.add(new Integrante.Builder()
            .setNombre("Chris Shiflett")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1999, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Foo Fighters")
            .setImagen("foo_fighters.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesFooFighters)
            .build());

        //13 Banda Coldplay
        List<Integrante> integrantesColdplay = new ArrayList<>();
        integrantesColdplay.add(new Integrante.Builder()
            .setNombre("Chris Martin")
            .setRol("Vocalista principal y pianista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesColdplay.add(new Integrante.Builder()
            .setNombre("Jonny Buckland")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesColdplay.add(new Integrante.Builder()
            .setNombre("Guy Berryman")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesColdplay.add(new Integrante.Builder()
            .setNombre("Will Champion")
            .setRol("Baterista y vocalista secundario")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesColdplay.add(new Integrante.Builder()
            .setNombre("Phil Harvey")
            .setRol("Director creativo")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Coldplay")
            .setImagen("coldplay.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesColdplay)
            .build());

        //14 Banda Maroon 5
        List<Integrante> integrantesMaroon5 = new ArrayList<>();
        integrantesMaroon5.add(new Integrante.Builder()
            .setNombre("Adam Levine")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMaroon5.add(new Integrante.Builder()
            .setNombre("James Valentine")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2001, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMaroon5.add(new Integrante.Builder()
            .setNombre("Mickey Madden")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMaroon5.add(new Integrante.Builder()
            .setNombre("Matt Flynn")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2006, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMaroon5.add(new Integrante.Builder()
            .setNombre("PJ Morton")
            .setRol("Tecladista")
            .setFechaIngreso(LocalDate.of(2012, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Maroon 5")
            .setImagen("maroon_5.jpg")
            .setGenero("Pop Rock")
            .setIntegrantes(integrantesMaroon5)
            .build());

        //15 Banda Sleeping with Sirens
        List<Integrante> integrantesSWS = new ArrayList<>();
        integrantesSWS.add(new Integrante.Builder()
            .setNombre("Kellin Quinn")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesSWS.add(new Integrante.Builder()
            .setNombre("Jack Fowler")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2011, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesSWS.add(new Integrante.Builder()
            .setNombre("Nick Martin")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2013, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesSWS.add(new Integrante.Builder()
            .setNombre("Gabe Barham")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setFechaSalida(LocalDate.of(2019, 1, 1))
            .setEstadoActividad(false)
            .build());
        integrantesSWS.add(new Integrante.Builder()
            .setNombre("Justin Hills")
            .setRol("Tecladista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Sleeping with Sirens")
            .setImagen("sleeping_with_sirens.jpg")
            .setGenero("Post-Hardcore")
            .setIntegrantes(integrantesSWS)
            .build());

        //16 Banda Imagine Dragons
        List<Integrante> integrantesImagineDragons = new ArrayList<>();
        integrantesImagineDragons.add(new Integrante.Builder()
            .setNombre("Dan Reynolds")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(2008, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesImagineDragons.add(new Integrante.Builder()
            .setNombre("Wayne Sermon")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesImagineDragons.add(new Integrante.Builder()
            .setNombre("Ben McKee")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesImagineDragons.add(new Integrante.Builder()
            .setNombre("Daniel Platzman")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2011, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Imagine Dragons")
            .setImagen("imagine_dragons.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesImagineDragons)
            .build());

        //17 Banda Muse
        List<Integrante> integrantesMuse = new ArrayList<>();
        integrantesMuse.add(new Integrante.Builder()
            .setNombre("Matthew Bellamy")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMuse.add(new Integrante.Builder()
            .setNombre("Chris Wolstenholme")
            .setRol("Bajista y coros")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesMuse.add(new Integrante.Builder()
            .setNombre("Dominic Howard")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1994, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Muse")
            .setImagen("muse.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesMuse)
            .build());

        //18 Banda Arctic Monkeys
        List<Integrante> integrantesArcticMonkeys = new ArrayList<>();
        integrantesArcticMonkeys.add(new Integrante.Builder()
            .setNombre("Alex Turner")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesArcticMonkeys.add(new Integrante.Builder()
            .setNombre("Jamie Cook")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesArcticMonkeys.add(new Integrante.Builder()
            .setNombre("Nick O'Malley")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2006, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesArcticMonkeys.add(new Integrante.Builder()
            .setNombre("Matt Helders")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Arctic Monkeys")
            .setImagen("arctic_monkeys.jpg")
            .setGenero("Indie Rock")
            .setIntegrantes(integrantesArcticMonkeys)
            .build());

        //19 Banda Panic! At The Disco
        List<Integrante> integrantesPanicAtTheDisco = new ArrayList<>();
        integrantesPanicAtTheDisco.add(new Integrante.Builder()
            .setNombre("Brendon Urie")
            .setRol("Vocalista principal y multiinstrumentista")
            .setFechaIngreso(LocalDate.of(2004, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesPanicAtTheDisco.add(new Integrante.Builder()
            .setNombre("Dallon Weekes")
            .setRol("Bajista y tecladista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setFechaSalida(LocalDate.of(2017, 12, 27))
            .setEstadoActividad(false)
            .build());
        integrantesPanicAtTheDisco.add(new Integrante.Builder()
            .setNombre("Spencer Smith")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2004, 1, 1))
            .setFechaSalida(LocalDate.of(2015, 4, 2))
            .setEstadoActividad(false)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Panic! At The Disco")
            .setImagen("panic_at_the_disco.jpg")
            .setGenero("Pop Rock")
            .setIntegrantes(integrantesPanicAtTheDisco)
            .build());

        //20 Banda Linkin Park
        List<Integrante> integrantesLinkinPark = new ArrayList<>();
        integrantesLinkinPark.add(new Integrante.Builder()
            .setNombre("Chester Bennington")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(1999, 1, 1))
            .setFechaSalida(LocalDate.of(2017, 7, 20))
            .setEstadoActividad(false)
            .build());
        integrantesLinkinPark.add(new Integrante.Builder()
            .setNombre("Mike Shinoda")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesLinkinPark.add(new Integrante.Builder()
            .setNombre("Brad Delson")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesLinkinPark.add(new Integrante.Builder()
            .setNombre("Dave Farrell")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2000, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesLinkinPark.add(new Integrante.Builder()
            .setNombre("Rob Bourdon")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1996, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Linkin Park")
            .setImagen("linkin_park.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesLinkinPark)
            .build());

        //21 Banda Twenty One Pilots
        List<Integrante> integrantesTwentyOnePilots = new ArrayList<>();
        integrantesTwentyOnePilots.add(new Integrante.Builder()
            .setNombre("Tyler Joseph")
            .setRol("Vocalista principal y multiinstrumentista")
            .setFechaIngreso(LocalDate.of(2009, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesTwentyOnePilots.add(new Integrante.Builder()
            .setNombre("Josh Dun")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2011, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Twenty One Pilots")
            .setImagen("twenty_one_pilots.jpg")
            .setGenero("Alternative Hip-Hop")
            .setIntegrantes(integrantesTwentyOnePilots)
            .build());

        //22 Banda Green Day
        List<Integrante> integrantesGreenDay = new ArrayList<>();
        integrantesGreenDay.add(new Integrante.Builder()
            .setNombre("Billie Joe Armstrong")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(1987, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesGreenDay.add(new Integrante.Builder()
            .setNombre("Mike Dirnt")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(1987, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesGreenDay.add(new Integrante.Builder()
            .setNombre("Tré Cool")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1990, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Green Day")
            .setImagen("green_day.jpg")
            .setGenero("Punk Rock")
            .setIntegrantes(integrantesGreenDay)
            .build());

        
        //23 Banda Kings of Leon
        List<Integrante> integrantesKingsOfLeon = new ArrayList<>();
        integrantesKingsOfLeon.add(new Integrante.Builder()
            .setNombre("Caleb Followill")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(1999, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesKingsOfLeon.add(new Integrante.Builder()
            .setNombre("Jared Followill")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2000, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesKingsOfLeon.add(new Integrante.Builder()
            .setNombre("Nathan Followill")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(1999, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesKingsOfLeon.add(new Integrante.Builder()
            .setNombre("Matthew Followill")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2000, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Kings of Leon")
            .setImagen("kings_of_leon.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesKingsOfLeon)
            .build());

        //24 Banda The Killers
        
        
        
        
        
        List<Integrante> integrantesTheKillers = new ArrayList<>();
        integrantesTheKillers.add(new Integrante.Builder()
            .setNombre("Brandon Flowers")
            .setRol("Vocalista principal y tecladista")
            .setFechaIngreso(LocalDate.of(2001, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesTheKillers.add(new Integrante.Builder()
            .setNombre("Dave Keuning")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2001, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesTheKillers.add(new Integrante.Builder()
            .setNombre("Mark Stoermer")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesTheKillers.add(new Integrante.Builder()
            .setNombre("Ronnie Vannucci Jr.")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad (true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The Killers")
            .setImagen("the_killers.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesTheKillers)
            .build());

        //25 Banda Paramore
        List<Integrante> integrantesParamore = new ArrayList<>();
        integrantesParamore.add(new Integrante.Builder()
            .setNombre("Hayley Williams")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(2004, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesParamore.add(new Integrante.Builder()
            .setNombre("Taylor York")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2007, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesParamore.add(new Integrante.Builder()
            .setNombre("Zac Farro")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2004, 1, 1))
            .setFechaSalida(LocalDate.of(2010, 1, 1))
            .setEstadoActividad (false)
            .build());
        integrantesParamore.add(new Integrante.Builder()
            .setNombre("Jeremy Davis")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2004, 1, 1))
            .setFechaSalida(LocalDate.of(2015, 1, 1))
            .setEstadoActividad (false)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Paramore")
            .setImagen("paramore.jpg")
            .setGenero("Rock Alternativo")
            .setIntegrantes(integrantesParamore)
            .build());

        //26 Banda Florence and the Machine
        List<Integrante> integrantesFlorenceAndTheMachine = new ArrayList<>();
        integrantesFlorenceAndTheMachine.add(new Integrante.Builder()
            .setNombre("Florence Welch")
            .setRol("Vocalista principal")
            .setFechaIngreso(LocalDate.of(2007, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesFlorenceAndTheMachine.add(new Integrante.Builder()
            .setNombre("Isabella Summers")
            .setRol("Tecladista")
            .setFechaIngreso(LocalDate.of(2007, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesFlorenceAndTheMachine.add(new Integrante.Builder()
            .setNombre("Rob Ackroyd")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2007, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesFlorenceAndTheMachine.add(new Integrante.Builder()
            .setNombre("Tom Monger")
            .setRol("Arpista")
            .setFechaIngreso(LocalDate.of(2007, 1, 1))
            .setEstadoActividad (true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Florence and the Machine")
            .setImagen("florence_and_the_machine.jpg")
            .setGenero("Indie Rock")
            .setIntegrantes(integrantesFlorenceAndTheMachine)
            .build());

        //27 Banda The Black Keys
        List<Integrante> integrantesTheBlackKeys = new ArrayList<>();
        integrantesTheBlackKeys.add(new Integrante.Builder()
            .setNombre("Dan Auerbach")
            .setRol("Vocalista y guitarrista")
            .setFechaIngreso(LocalDate.of(2001, 1, 1))
            .setEstadoActividad (true)
            .build());
        integrantesTheBlackKeys.add(new Integrante.Builder()
            .setNombre("Patrick Carney")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2001, 1, 1))
            .setEstadoActividad (true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The Black Keys")
            .setImagen("the_black_keys.jpg")
            .setGenero("Blues Rock")
            .setIntegrantes(integrantesTheBlackKeys)
            .build());

        //28 Banda The 1975
        List<Integrante> integrantesThe1975 = new ArrayList<>();
        integrantesThe1975.add(new Integrante.Builder()
            .setNombre("Matty Healy")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesThe1975.add(new Integrante.Builder()
            .setNombre("Adam Hann")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesThe1975.add(new Integrante.Builder()
            .setNombre("Ross MacDonald")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesThe1975.add(new Integrante.Builder()
            .setNombre("George Daniel")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2002, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The 1975")
            .setImagen("the_1975.jpg")
            .setGenero("Indie Rock")
            .setIntegrantes(integrantesThe1975)
            .build());

        //29 Banda Of Monsters and Men
        List<Integrante> integrantesOfMonstersAndMen = new ArrayList<>();
        integrantesOfMonstersAndMen.add(new Integrante.Builder()
            .setNombre("Nanna Bryndís Hilmarsdóttir")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(2010, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesOfMonstersAndMen.add(new Integrante.Builder()
            .setNombre("Ragnar Þórhallsson")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(2010, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesOfMonstersAndMen.add(new Integrante.Builder()
            .setNombre("Brynjar Leifsson")
            .setRol("Guitarrista")
            .setFechaIngreso(LocalDate.of(2010, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesOfMonstersAndMen.add(new Integrante.Builder()
            .setNombre("Kristján Páll Kristjánsson")
            .setRol("Bajista")
            .setFechaIngreso(LocalDate.of(2010, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesOfMonstersAndMen.add(new Integrante.Builder()
            .setNombre("Arnar Rósenkranz Hilmarsson")
            .setRol("Baterista")
            .setFechaIngreso(LocalDate.of(2010, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("Of Monsters and Men")
            .setImagen("of_monsters_and_men.jpg")
            .setGenero("Indie Folk")
            .setIntegrantes(integrantesOfMonstersAndMen)
            .build());

         //30 Banda The Lumineers
        List<Integrante> integrantesTheLumineers = new ArrayList<>();
        integrantesTheLumineers.add(new Integrante.Builder()
            .setNombre("Wesley Schultz")
            .setRol("Vocalista principal y guitarrista")
            .setFechaIngreso(LocalDate.of(2005, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesTheLumineers.add(new Integrante.Builder()
            .setNombre("Jeremiah Fraites")
            .setRol("Baterista y percusionista")
            .setFechaIngreso(LocalDate.of(2005, 1, 1))
            .setEstadoActividad(true)
            .build());
        integrantesTheLumineers.add(new Integrante.Builder()
            .setNombre("Byron Isaacs")
            .setRol("Bajista y vocalista de apoyo")
            .setFechaIngreso(LocalDate.of(2018, 1, 1))
            .setEstadoActividad(true)
            .build());

        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Banda")
            .setNombre("The Lumineers")
            .setImagen("the_lumineers.jpg")
            .setGenero("Indie Folk")
            .setIntegrantes(integrantesTheLumineers)
            .build());
        
        //  Natanael Cano
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Natanael Cano")
            .setImagen("natanael_cano.jpg")
            .setGenero("Corridos Tumbados")
            .build());

        // Peso Pluma
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Peso Pluma")
            .setImagen("peso_pluma.jpg")
            .setGenero("Corridos Tumbados")
            .build());

        //  Tito Double P
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Tito Double P")
            .setImagen("tito_double_p.jpg")
            .setGenero("Corridos Belicos")
            .build());

        //  Luis R Conriquez
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Luis R Conriquez")
            .setImagen("luis_r_conriquez.jpg")
            .setGenero("Corridos Bélicos")
            .build());

        //  Junior H
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Junior H")
            .setImagen("junior_h.jpg")
            .setGenero("Corridos Tumbados")
            .build());
        
        // Dua Lipa
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Dua Lipa")
            .setImagen("dua_lipa.jpg")
            .setGenero("Pop")
            .build());

        // The Weeknd
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("The Weeknd")
            .setImagen("the_weeknd.jpg")
            .setGenero("R&B")
            .build());

        // Billie Eilish
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Billie Eilish")
            .setImagen("billie_eilish.jpg")
            .setGenero("Pop")
            .build());

        // Shawn Mendes
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Shawn Mendes")
            .setImagen("shawn_mendes.jpg")
            .setGenero("Pop")
            .build());

        // Post Malone
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Post Malone")
            .setImagen("post_malone.jpg")
            .setGenero("Hip-Hop")
            .build());
        
        // Harry Styles
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Harry Styles")
            .setImagen("harry_styles.jpg")
            .setGenero("Pop")
            .build());

        // Olivia Rodrigo
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Olivia Rodrigo")
            .setImagen("olivia_rodrigo.jpg")
            .setGenero("Pop")
            .build());

        // Khalid
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Khalid")
            .setImagen("khalid.jpg")
            .setGenero("R&B")
            .build());

        // Lizzo
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Lizzo")
            .setImagen("lizzo.jpg")
            .setGenero("Pop")
            .build());


         // Ed Sheeran
        artistas.add(new Artista.Builder()
            .setId(new ObjectId())
            .setTipoArtista("Solista")
            .setNombre("Ed Sheeran")
            .setImagen("ed_sheeran.jpg")
            .setGenero("Pop")
            .build());

        
        
        

        return artistas;
    }
}
