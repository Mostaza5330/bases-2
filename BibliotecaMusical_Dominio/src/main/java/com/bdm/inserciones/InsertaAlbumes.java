package com.bdm.inserciones;

import com.bmd.entities.Album;
import com.bmd.entities.Artista;
import java.time.LocalDate;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import java.util.List;

public class InsertaAlbumes {
ObjectId id = new ObjectId();

    public List<Album> crearAlbums(List<Artista> artistas) {
        List<Album> albumes = new ArrayList<>();

        // Crea manualmente los álbumes para los artistas
        for (Artista artista : artistas) {
            if ("Selena Quintanilla".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Amor Prohibido")
                        .setImagenPortada("amor_prohibido.jpg")
                        .setFechaLanzamiento(LocalDate.of(1994, 3, 13))
                        .setGenero("Cumbia")
                        .setArtista(artista)
                        .setCanciones(List.of("Amor Prohibido", "Baila Esta Cumbia", "La Carcacha"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Entre a Mi Mundo")
                        .setImagenPortada("entre_a_mi_mundo.jpg")
                        .setFechaLanzamiento(LocalDate.of(1992, 5, 6))
                        .setGenero("Cumbia")
                        .setArtista(artista)
                        .setCanciones(List.of("Como La Flor", "La Carcacha", "¿Qué Creías?"))
                        .build());
            } else if ("Shakira".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Pies Descalzos")
                        .setImagenPortada("pies_descalzos.jpg")
                        .setFechaLanzamiento(LocalDate.of(1995, 2, 13))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Estoy Aquí", "Pies Descalzos", "Antología"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Dónde Están los Ladrones?")
                        .setImagenPortada("donde_estan_los_ladrones.jpg")
                        .setFechaLanzamiento(LocalDate.of(1998, 9, 29))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Ciega, Sordomuda", "Si Te Vas", "Moscas En La Casa"))
                        .build());
            } else if ("Enrique Iglesias".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Enrique Iglesias")
                        .setImagenPortada("enrique_iglesias.jpg")
                        .setFechaLanzamiento(LocalDate.of(1995, 11, 21))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Bailamos", "Experiencia Religiosa", "Si Tú Te Vas"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Escape")
                        .setImagenPortada("escape.jpg")
                        .setFechaLanzamiento(LocalDate.of(2001, 10, 30))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Escape", "Hero", "Don't Turn Off the Lights"))
                        .build());
            } else if ("Daddy Yankee".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Barrio Fino")
                        .setImagenPortada("barrio_fino.jpg")
                        .setFechaLanzamiento(LocalDate.of(2004, 5, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Gasolina", "Lo Que Pasó, Pasó", "Rompe"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("El Cartel: The Big Boss")
                        .setImagenPortada("el_cartel_the_big_boss.jpg")
                        .setFechaLanzamiento(LocalDate.of(2007, 6, 5))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Impacto", "Ella Me Levantó", "Fuera de Control"))
                        .build());
            } else if ("Bad Bunny".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("YHLQMDLG")
                        .setImagenPortada("yhlqmdlg.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 2, 29))
                        .setGenero("Reggaeton").setArtista(artista)
                        .setCanciones(List.of("Vete", "Safaera", "Yo Perreo Sola"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("El Último Tour Del Mundo")
                        .setImagenPortada("el_ultimo_tour_del_mundo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 11, 27))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Dákiti", "Booker T", "La Noche de Anoche"))
                        .build());
            } else if ("Ozuna".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Odisea")
                        .setImagenPortada("odisea.jpg")
                        .setFechaLanzamiento(LocalDate.of(2017, 8, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Dile Que Tú Me Quieres", "Se Preparó", "Tu Foto"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Aura")
                        .setImagenPortada("aura.jpg")
                        .setFechaLanzamiento(LocalDate.of(2018, 8, 24))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Vaina Loca", "Ibiza", "Hola"))
                        .build());
            } else if ("Camila".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Todo Cambió")
                        .setImagenPortada("todo_cambio.jpg")
                        .setFechaLanzamiento(LocalDate.of(2005, 11, 9))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Todo Cambió", "Mientes", "Alejate de Mí"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Dejarte de Amar")
                        .setImagenPortada("dejarte_de_amar.jpg")
                        .setFechaLanzamiento(LocalDate.of(2010, 2, 9))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Bésame", "Entre Tus Alas", "Maya"))
                        .build());
            } else if ("Ricky Martin".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Vuelve")
                        .setImagenPortada("vuelve.jpg")
                        .setFechaLanzamiento(LocalDate.of(1998, 5, 26))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("La Copa de la Vida", "Vuelve", "Volverás"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Livin' la Vida Loca")
                        .setImagenPortada("livin_la_vida_loca.jpg")
                        .setFechaLanzamiento(LocalDate.of(1999, 3, 23))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Livin' la Vida Loca", "She's All I Ever Had", "Shake Your Bon-Bon"))
                        .build());
            } else if ("Juanes".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Fíjate Bien")
                        .setImagenPortada("fijate_bien.jpg")
                        .setFechaLanzamiento(LocalDate.of(2000, 2, 29))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("A Dios le Pido", "Es Por Ti", "Para Tu Amor"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Un Día Normal")
                        .setImagenPortada("un_dia_normal.jpg")
                        .setFechaLanzamiento(LocalDate.of(2002, 5, 21))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("A Dios le Pido", "Un Día Normal", "La Paga"))
                        .build());
            } else if ("Alejandro Sanz".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Más")
                        .setImagenPortada("mas.jpg")
                        .setFechaLanzamiento(LocalDate.of(1997, 2, 3))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Corazón Partío", "Amiga Mía", "Y, ¿Si Fuera Ella?"))
                        .build());
                albumes.add(new Album.Builder()
                       .setId(new ObjectId())
                        .setNombre("El Alma al Aire")
                        .setImagenPortada("el_alma_al_aire.jpg")
                        .setFechaLanzamiento(LocalDate.of(2000, 9, 26))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Cuando Nadie Me Ve", "Quisiera Ser", "El Alma al Aire"))
                        .build());
            } else if ("Marc Anthony".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Otra Nota")
                        .setImagenPortada("otra_nota.jpg")
                        .setFechaLanzamiento(LocalDate.of(1993, 11, 9))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("Vivir lo Nuestro", "Te Conozco Bien", "Que Precio Tiene el Cielo"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Contra la Corriente")
                        .setImagenPortada("contra_la_corriente.jpg")
                        .setFechaLanzamiento(LocalDate.of(1997, 10, 21))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("Y Hubo Alguien", "Contra la Corriente", "Me Voy a Regalar"))
                        .build());
            } else if ("Jennifer Lopez".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("On the 6")
                        .setImagenPortada("on_the_6.jpg")
                        .setFechaLanzamiento(LocalDate.of(1999, 6, 1))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("If You Had My Love", "Waiting for Tonight", "No Me Ames"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("J.Lo")
                        .setImagenPortada("jlo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2001, 1, 23))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Love Don't Cost a Thing", "Play", "Ain't It Funny"))
                        .build());
            } else if ("Luis Fonsi".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Comenzaré")
                        .setImagenPortada("comenzare.jpg")
                        .setFechaLanzamiento(LocalDate.of(1998, 4, 13))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Échame la Culpa", "Dime Cómo Quieres", "No Te Vayas"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Palabras del Silencio")
                        .setImagenPortada("palabras_del_silencio.jpg")
                        .setFechaLanzamiento(LocalDate.of(2008, 8, 26))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Aquí Estoy Yo", "No Me Doy Por Vencido", "Llueve Por Dentro"))
                        .build());
            } else if ("Carlos Vives".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Clásicos de la Provincia")
                        .setImagenPortada("clasicos_de_la_provincia.jpg")
                        .setFechaLanzamiento(LocalDate.of(2001, 4, 10))
                        .setGenero("Vallenato")
                        .setArtista(artista)
                        .setCanciones(List.of("La Tierra del Olvido", "Fruta Fresca", "Como le Gusta a Tu Cuerpo"))
                        .build());
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Déjame Entrar")
                        .setImagenPortada("dejame_entrar.jpg")
                        .setFechaLanzamiento(LocalDate.of(2001, 10, 6))
                        .setGenero("Vallenato")
                        .setArtista(artista)
                        .setCanciones(List.of("Déjame Entrar", "Amor Sensible", "Papadio"))
                        .build());
            } else if ("Juan Gabriel".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Querida")
                        .setImagenPortada("querida.jpg")
                        .setFechaLanzamiento(LocalDate.of(1984, 11, 15))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Querida", "La Farsa", "No Tengo Dinero"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Recuerdos, Vol. II")
                        .setImagenPortada("recuerdos_vol_ii.jpg")
                        .setFechaLanzamiento(LocalDate.of(1986, 2, 15))
                        .setGenero("Pop")
                        .setArtista(artista)
                        .setCanciones(List.of("Te lo Pido por Favor", "No Me Vuelvo a Enamorar", "Caray"))
                        .build());
            } else if ("Alejandro Fernández".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Me Volvieron Loco Tus Besos")
                        .setImagenPortada("me_volvieron_loco.jpg")
                        .setFechaLanzamiento(LocalDate.of(1999, 8, 23))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("Me Volvieron Loco Tus Besos", "Que Manera de Quererte", "Te Voy a Perder"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Muy Dentro de Mi Corazón")
                        .setImagenPortada("muy_dentro_de_mi_corazon.jpg")
                        .setFechaLanzamiento(LocalDate.of(1996, 11, 19))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("Como Quien Pierde una Estrella", "Abrazame", "Nube Viajera"))
                        .build());
            } else if ("Vicente Fernández".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("La Voz Que Te Conquista")
                        .setImagenPortada("la_voz_que_te_conquista.jpg")
                        .setFechaLanzamiento(LocalDate.of(1992, 5, 1))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("El Son de la Negra", "Volver Volver", "La Ley del Monte"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Para Siempre")
                        .setImagenPortada("para_siempre.jpg")
                        .setFechaLanzamiento(LocalDate.of(2007, 9, 11))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("Estos Celos", "La Derrota", "El Último Beso"))
                        .build());
            } else if ("Pedro Infante".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("El Inmortal")
                        .setImagenPortada("el_inmortal.jpg")
                        .setFechaLanzamiento(LocalDate.of(1954, 3, 25))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("Amorcito Corazón", "Cien Años", "La Que Se Fue"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Las Mañanitas a Pedro Infante")
                        .setImagenPortada("las_mananitas_a_pedro_infante.jpg")
                        .setFechaLanzamiento(LocalDate.of(1957, 4, 15))
                        .setGenero("Ranchera")
                        .setArtista(artista)
                        .setCanciones(List.of("Las Mañanitas", "Serenata Huasteca", "La Calandria"))
                        .build());
            } else if ("La India".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Sobre el Fuego")
                        .setImagenPortada("sobre_el_fuego.jpg")
                        .setFechaLanzamiento(LocalDate.of(1994, 6, 21))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("Nunca Voy a Olvidarte", "Mi Mayor Venganza", "Me Voy"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Dicen Que Soy")
                        .setImagenPortada("dicen_que_soy.jpg")
                        .setFechaLanzamiento(LocalDate.of(1994, 10, 25))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("Dicen Que Soy", "Ese Hombre", "Vivir Lo Nuestro"))
                        .build());
            } else if ("Celia Cruz".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("La Negra Tiene Tumbao")
                        .setImagenPortada("la_negra_tiene_tumbao.jpg")
                        .setFechaLanzamiento(LocalDate.of(2001, 5, 1))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("La Negra Tiene Tumbao", "Ríe y Llora", "Quimbara"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Regalo del Alma")
                        .setImagenPortada("regalo_del_alma.jpg")
                        .setFechaLanzamiento(LocalDate.of(2003, 8, 5))
                        .setGenero("Salsa")
                        .setArtista(artista)
                        .setCanciones(List.of("Rie y Llora", "Ella Tiene Fuego", "Pa' Arriba no Va"))
                        .build());
            } else if ("Romeo Santos".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Fórmula, Vol. 1")
                        .setImagenPortada("formula_vol_1.jpg")
                        .setFechaLanzamiento(LocalDate.of(2011, 11, 8))
                        .setGenero("Bachata")
                        .setArtista(artista)
                        .setCanciones(List.of("You", "Promise", "El Amor Que Perdimos"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Fórmula, Vol. 2")
                        .setImagenPortada("formula_vol_2.jpg")
                        .setFechaLanzamiento(LocalDate.of(2014, 2, 25))
                        .setGenero("Bachata")
                        .setArtista(artista)
                        .setCanciones(List.of("Propuesta Indecente", "Odio", "Eres Mía"))
                        .build());
            } else if ("Prince Royce".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Prince Royce")
                        .setImagenPortada("prince_roye.jpg")
                        .setFechaLanzamiento(LocalDate.of(2010, 2, 1))
                        .setGenero("Bachata")
                        .setArtista(artista)
                        .setCanciones(List.of("Stand by Me", "Corazón Sin Cara", "El Amor Que Perdimos"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Phase II")
                        .setImagenPortada("phase_ii.jpg")
                        .setFechaLanzamiento(LocalDate.of(2012, 4, 10))
                        .setGenero("Bachata")
                        .setArtista(artista)
                        .setCanciones(List.of("Las Cosas Pequeñas", "Incondicional", "Te Me Vas"))
                        .build());
            } else if ("Karol G".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                       .setId(new ObjectId())
                        .setNombre("Ocean")
                        .setImagenPortada("ocean.jpg")
                        .setFechaLanzamiento(LocalDate.of(2019, 10, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Tusa", "Pineapple", "Bichota"))
                        .build());

                albumes.add(new Album.Builder()
                       .setId(new ObjectId())
                        .setNombre("KG0516")
                        .setImagenPortada("kg0516.jpg")
                        .setFechaLanzamiento(LocalDate.of(2021, 3, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("El Makinon", "200 Copas", "Location"))
                        .build());
            } else if ("J Balvin".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Vibras")
                        .setImagenPortada("vibras.jpg")
                        .setFechaLanzamiento(LocalDate.of(2018, 5, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Mi Gente", "Ahora Me Llama", "Bobo"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Colores")
                        .setImagenPortada("colores.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 3, 19))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Blanco", "Morado", "Rojo"))
                        .build());
            } else if ("Maluma".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("F.A.M.E.")
                        .setImagenPortada("fame.jpg")
                        .setFechaLanzamiento(LocalDate.of(2018, 5, 18))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Felices los 4", "Corazón", "El Préstamo"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("11:11")
                        .setImagenPortada("1111.jpg")
                        .setFechaLanzamiento(LocalDate.of(2019, 5, 17))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("HP", "11 PM", "Instinto Natural"))
                        .build());
            } else if ("Nicky Jam".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Fénix")
                        .setImagenPortada("fenix.jpg")
                        .setFechaLanzamiento(LocalDate.of(2017, 1, 20))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("El Perdón", "Hasta el Amanecer", "Travesuras"))
                        .build());

               
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Íntimo")
                        .setImagenPortada("intimo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2019, 11, 1))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Whine Up", "Te Robaré", "X"))
                        .build());
            } else if ("Zion & Lennox".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Motivan2")
                        .setImagenPortada("motivan2.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 11, 25))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Otra Vez", "Si Tu Me Dices", "Me Llamas"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Los Verdaderos")
                        .setImagenPortada("los_verdaderos.jpg")
                        .setFechaLanzamiento(LocalDate.of(2010, 11, 2))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Te Hago el Amor", "Hoy Lo Siento", "Dame tu Amor"))
                        .build());
            } else if ("Anuel AA".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Real Hasta la Muerte")
                        .setImagenPortada("real_hasta_la_muerte.jpg")
                        .setFechaLanzamiento(LocalDate.of(2018, 7, 17))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("Ella Quiere Beber", "Secreto", "Brindemos"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Emmanuel")
                        .setImagenPortada("emmanuel.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 5, 29))
                        .setGenero("Reggaeton")
                        .setArtista(artista)
                        .setCanciones(List.of("China", "Emmanuel", "Hasta Que Dios Diga"))
                        .build());
            } else if ("La Banda MS".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("La Mejor Banda del Mundo")
                        .setImagenPortada("la_mejor_banda.jpg")
                        .setFechaLanzamiento(LocalDate.of(2014, 10, 7))
                        .setGenero("Banda")
                        .setArtista(artista)
                        .setCanciones(List.of("No Me Pidas Perdón", "Hermosa Experiencia", "El Mechón"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("En Vivo")
                        .setImagenPortada("en_vivo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 4, 22))
                        .setGenero("Banda")
                        .setArtista(artista)
                        .setCanciones(List.of("Solo Con Verte", "Háblame de Ti", "Hermosa Experiencia"))
                        .build());
            } else if ("Intocable".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Intocable 20 Aniversario")
                        .setImagenPortada("intocable_20.jpg")
                        .setFechaLanzamiento(LocalDate.of(2014, 4, 29))
                        .setGenero("Banda")
                        .setArtista(artista)
                        .setCanciones(List.of("Aire", "Eres Mi Droga", "Te Amo Y Te Amo"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Highway")
                        .setImagenPortada("highway.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 7, 1))
                        .setGenero("Banda")
                        .setArtista(artista)
                        .setCanciones(List.of("Quédate Conmigo", "Quiéreme", "Dímelo"))
                        .build());
            }else if ("The Beatles".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Abbey Road")
                        .setImagenPortada("abbey_road.jpg")
                        .setFechaLanzamiento(LocalDate.of(1969, 9, 26))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Come Together", "Something", "Here Comes the Sun"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Sgt. Pepper's Lonely Hearts Club Band")
                        .setImagenPortada("sgt_pepper.jpg")
                        .setFechaLanzamiento(LocalDate.of(1967, 6, 1))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Lucy in the Sky with Diamonds", "With a Little Help from My Friends", "A Day in the Life"))
                        .build());

            } else if ("Queen".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("A Night at the Opera")
                        .setImagenPortada("a_night_at_the_opera.jpg")
                        .setFechaLanzamiento(LocalDate.of(1975, 11, 21))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Bohemian Rhapsody", "Love of My Life", "You're My Best Friend"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The Game")
                        .setImagenPortada("the_game.jpg")
                        .setFechaLanzamiento(LocalDate.of(1980, 6, 30))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Another One Bites the Dust", "Crazy Little Thing Called Love", "Save Me"))
                        .build());

            } else if ("Metallica".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Master of Puppets")
                        .setImagenPortada("master_of_puppets.jpg")
                        .setFechaLanzamiento(LocalDate.of(1986, 3, 3))
                        .setGenero("Metal")
                        .setArtista(artista)
                        .setCanciones(List.of("Battery", "Master of Puppets", "Welcome Home (Sanitarium)"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Metallica (The Black Album)")
                        .setImagenPortada("black_album.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 8, 12))
                        .setGenero("Metal")
                        .setArtista(artista)
                        .setCanciones(List.of("Enter Sandman", "The Unforgiven", "Nothing Else Matters"))
                        .build());

            } else if ("U2".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The Joshua Tree")
                        .setImagenPortada("the_joshua_tree.jpg")
                        .setFechaLanzamiento(LocalDate.of(1987, 3, 9))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("With or Without You", "I Still Haven't Found What I'm Looking For", "Where the Streets Have No Name"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Achtung Baby")
                        .setImagenPortada("achtung_baby.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 11, 18))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("One", "Mysterious Ways", "Until the End of the World"))
                        .build());

            } else if ("Nirvana".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Nevermind")
                        .setImagenPortada("nevermind.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 9, 24))
                        .setGenero("Grunge")
                        .setArtista(artista)
                        .setCanciones(List.of("Smells Like Teen Spirit", "Come as You Are", "Lithium"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("In Utero")
                        .setImagenPortada("in_utero.jpg")
                        .setFechaLanzamiento(LocalDate.of(1993, 9, 21))
                        .setGenero("Grunge")
                        .setArtista(artista)
                        .setCanciones(List.of("Heart-Shaped Box", "Rape Me", "All Apologies"))
                        .build());
            }else if ("AC/DC".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Back in Black")
                        .setImagenPortada("back_in_black.jpg")
                        .setFechaLanzamiento(LocalDate.of(1980, 7, 25))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Hells Bells", "Shoot to Thrill", "Back in Black"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Highway to Hell")
                        .setImagenPortada("highway_to_hell.jpg")
                        .setFechaLanzamiento(LocalDate.of(1979, 7, 27))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Highway to Hell", "Girls Got Rhythm", "If You Want Blood (You've Got It)"))
                        .build());

            } else if ("The Rolling Stones".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Exile on Main St.")
                        .setImagenPortada("exile_on_main_st.jpg")
                        .setFechaLanzamiento(LocalDate.of(1972, 5, 12))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Rocks Off", "Rip This Joint", "Tumbling Dice"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Sticky Fingers")
                        .setImagenPortada("sticky_fingers.jpg")
                        .setFechaLanzamiento(LocalDate.of(1971, 4, 23))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Brown Sugar", "Wild Horses", "Can't You Hear Me Knocking"))
                        .build());

            } else if ("Guns N' Roses".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Appetite for Destruction")
                        .setImagenPortada("appetite_for_destruction.jpg")
                        .setFechaLanzamiento(LocalDate.of(1987, 7, 21))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Welcome to the Jungle", "Sweet Child o' Mine", "Paradise City"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Use Your Illusion I")
                        .setImagenPortada("use_your_illusion_i.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 9, 17))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("November Rain", "Don't Cry", "Live and Let Die"))
                        .build());

            } else if ("Red Hot Chili Peppers".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Californication")
                        .setImagenPortada("californication.jpg")
                        .setFechaLanzamiento(LocalDate.of(1999, 6, 8))
                        .setGenero("Funk Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Scar Tissue", "Otherside", "Californication"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Blood Sugar Sex Magik")
                        .setImagenPortada("blood_sugar_sex_magik.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 9, 24))
                        .setGenero("Funk Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Give It Away", "Under the Bridge", "Suck My Kiss"))
                        .build());

            } else if ("Pearl Jam".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Ten")
                        .setImagenPortada("ten.jpg")
                        .setFechaLanzamiento(LocalDate.of(1991, 8, 27))
                        .setGenero("Grunge")
                        .setArtista(artista)
                        .setCanciones(List.of("Alive", "Even Flow", "Jeremy"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Vs.")
                        .setImagenPortada("vs.jpg")
                        .setFechaLanzamiento(LocalDate.of(1993, 10, 19))
                        .setGenero("Grunge")
                        .setArtista(artista)
                        .setCanciones(List.of("Go", "Daughter", "Elderly Woman Behind the Counter in a Small Town"))
                        .build());
            }else if ("Aerosmith".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Toys in the Attic")
                        .setImagenPortada("toys_in_the_attic.jpg")
                        .setFechaLanzamiento(LocalDate.of(1975, 4, 8))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Sweet Emotion", "Walk This Way", "Big Ten Inch Record"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Pump")
                        .setImagenPortada("pump.jpg")
                        .setFechaLanzamiento(LocalDate.of(1989, 9, 12))
                        .setGenero("Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Love in an Elevator", "Janie's Got a Gun", "What It Takes"))
                        .build());

            } else if ("Foo Fighters".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The Colour and the Shape")
                        .setImagenPortada("the_colour_and_the_shape.jpg")
                        .setFechaLanzamiento(LocalDate.of(1997, 5, 20))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Everlong", "My Hero", "Monkey Wrench"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Wasting Light")
                        .setImagenPortada("wasting_light.jpg")
                        .setFechaLanzamiento(LocalDate.of(2011, 4, 12))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Rope", "Walk", "These Days"))
                        .build());

            } else if ("Coldplay".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("A Rush of Blood to the Head")
                        .setImagenPortada("a_rush_of_blood_to_the_head.jpg")
                        .setFechaLanzamiento(LocalDate.of(2002, 8, 26))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Clocks", "The Scientist", "In My Place"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Viva la Vida or Death and All His Friends")
                        .setImagenPortada("viva_la_vida.jpg")
                        .setFechaLanzamiento(LocalDate.of(2008, 6, 12))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Viva la Vida", "Lovers in Japan", "Lost!"))
                        .build());

            } else if ("Maroon 5".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Songs About Jane")
                        .setImagenPortada("songs_about_jane.jpg")
                        .setFechaLanzamiento(LocalDate.of(2002, 6, 25))
                        .setGenero("Pop Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("This Love", "She Will Be Loved", "Harder to Breathe"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Overexposed")
                        .setImagenPortada("overexposed.jpg")
                        .setFechaLanzamiento(LocalDate.of(2012, 6, 26))
                        .setGenero("Pop Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Payphone", "One More Night", "Daylight"))
                        .build());

            } else if ("Sleeping with Sirens".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("If You Were a Movie, This Would Be Your Soundtrack")
                        .setImagenPortada("if_you_were_a_movie.jpg")
                        .setFechaLanzamiento(LocalDate.of(2012, 6, 26))
                        .setGenero("Post-Hardcore")
                        .setArtista(artista)
                        .setCanciones(List.of("Scene One - James Dean & Audrey Hepburn", "Scene Two - Roger Rabbit", "Scene Five - With Ears to See and Eyes to Hear"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Feel")
                        .setImagenPortada("feel.jpg")
                        .setFechaLanzamiento(LocalDate.of(2013, 6, 4))
                        .setGenero("Post-Hardcore")
                        .setArtista(artista)
                        .setCanciones(List.of("Alone", "Congratulations", "Feel"))
                        .build());
            }else if ("Imagine Dragons".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Night Visions")
                        .setImagenPortada("night_visions.jpg")
                        .setFechaLanzamiento(LocalDate.of(2012, 9, 4))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Radioactive", "Demons", "It's Time"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Evolve")
                        .setImagenPortada("evolve.jpg")
                        .setFechaLanzamiento(LocalDate.of(2017, 6, 23))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Believer", "Thunder", "Whatever It Takes"))
                        .build());

            } else if ("Muse".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Black Holes and Revelations")
                        .setImagenPortada("black_holes_and_revelations.jpg")
                        .setFechaLanzamiento(LocalDate.of(2006, 7, 3))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Supermassive Black Hole", "Starlight", "Knights of Cydonia"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The Resistance")
                        .setImagenPortada("the_resistance.jpg")
                        .setFechaLanzamiento(LocalDate.of(2009, 9, 14))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Uprising", "Resistance", "Undisclosed Desires"))
                        .build());

            } else if ("Arctic Monkeys".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("AM")
                        .setImagenPortada("am.jpg")
                        .setFechaLanzamiento(LocalDate.of(2013, 9, 9))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Do I Wanna Know?", "R U Mine?", "Why'd You Only Call Me When You're High?"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Favourite Worst Nightmare")
                        .setImagenPortada("favourite_worst_nightmare.jpg")
                        .setFechaLanzamiento(LocalDate.of(2007, 4, 23))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Brianstorm", "Teddy Picker", "Fluorescent Adolescent"))
                        .build());

            } else if ("Panic! At The Disco".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("A Fever You Can't Sweat Out")
                        .setImagenPortada("a_fever_you_cant_sweat_out.jpg")
                        .setFechaLanzamiento(LocalDate.of(2005, 9, 27))
                        .setGenero("Pop Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("I Write Sins Not Tragedies", "But It's Better If You Do", "Lying Is the Most Fun a Girl Can Have Without Taking Her Clothes Off"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Death of a Bachelor")
                        .setImagenPortada("death_of_a_bachelor.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 1, 15))
                        .setGenero("Pop Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Victorious", "Don't Threaten Me with a Good Time", "Emperor's New Clothes"))
                        .build());

            } else if ("Linkin Park".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Hybrid Theory")
                        .setImagenPortada("hybrid_theory.jpg")
                        .setFechaLanzamiento(LocalDate.of(2000, 10, 24))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("In the End", "Crawling", "One Step Closer"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Meteora")
                        .setImagenPortada("meteora.jpg")
                        .setFechaLanzamiento(LocalDate.of(2003, 3, 25))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Numb", "Somewhere I Belong", "Breaking the Habit"))
                        .build());
            }else if ("Twenty One Pilots".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Blurryface")
                        .setImagenPortada("blurryface.jpg")
                        .setFechaLanzamiento(LocalDate.of(2015, 5, 17))
                        .setGenero("Alternative Hip-Hop")
                        .setArtista(artista)
                        .setCanciones(List.of("Stressed Out", "Ride", "Tear in My Heart"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Trench")
                        .setImagenPortada("trench.jpg")
                        .setFechaLanzamiento(LocalDate.of(2018, 10, 5))
                        .setGenero("Alternative Hip-Hop")
                        .setArtista(artista)
                        .setCanciones(List.of("Jumpsuit", "Chlorine", "My Blood"))
                        .build());

            } else if ("Green Day".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Dookie")
                        .setImagenPortada("dookie.jpg")
                        .setFechaLanzamiento(LocalDate.of(1994, 2, 1))
                        .setGenero("Punk Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Basket Case", "When I Come Around", "Longview"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("American Idiot")
                        .setImagenPortada("american_idiot.jpg")
                        .setFechaLanzamiento(LocalDate.of(2004, 9, 21))
                        .setGenero("Punk Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("American Idiot", "Boulevard of Broken Dreams", "Wake Me Up When September Ends"))
                        .build());

            } else if ("Kings of Leon".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Only by the Night")
                        .setImagenPortada("only_by_the_night.jpg")
                        .setFechaLanzamiento(LocalDate.of(2008, 9, 19))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Sex on Fire", "Use Somebody", "Closer"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Mechanical Bull")
                        .setImagenPortada("mechanical_bull.jpg")
                        .setFechaLanzamiento(LocalDate.of(2013, 9, 24))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Supersoaker", "Wait for Me", "Temple"))
                        .build());

            } else if ("The Killers".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Hot Fuss")
                        .setImagenPortada("hot_fuss.jpg")
                        .setFechaLanzamiento(LocalDate.of(2004, 6, 7))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Mr. Brightside", "Somebody Told Me", "All These Things That I've Done"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Sam's Town")
                        .setImagenPortada("sams_town.jpg")
                        .setFechaLanzamiento(LocalDate.of(2006, 10, 2))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("When You Were Young", "Read My Mind", "For Reasons Unknown"))
                        .build());

            } else if ("Paramore".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Riot!")
                        .setImagenPortada("riot.jpg")
                        .setFechaLanzamiento(LocalDate.of(2007, 6, 12))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Misery Business", "That's What You Get", "Crushcrushcrush"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Paramore")
                        .setImagenPortada("paramore.jpg")
                        .setFechaLanzamiento(LocalDate.of(2013, 4, 5))
                        .setGenero("Rock Alternativo")
                        .setArtista(artista)
                        .setCanciones(List.of("Still Into You", "Ain't It Fun", "Now"))
                        .build());
            }else if ("Florence and the Machine".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Lungs")
                        .setImagenPortada("lungs.jpg")
                        .setFechaLanzamiento(LocalDate.of(2009, 7, 3))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Dog Days Are Over", "You've Got the Love", "Rabbit Heart (Raise It Up)"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Ceremonials")
                        .setImagenPortada("ceremonials.jpg")
                        .setFechaLanzamiento(LocalDate.of(2011, 10, 28))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Shake It Out", "What the Water Gave Me", "Spectrum"))
                        .build());

            } else if ("The Black Keys".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Brothers")
                        .setImagenPortada("brothers.jpg")
                        .setFechaLanzamiento(LocalDate.of(2010, 5, 18))
                        .setGenero("Blues Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Tighten Up", "Howlin' for You", "Next Girl"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("El Camino")
                        .setImagenPortada("el_camino.jpg")
                        .setFechaLanzamiento(LocalDate.of(2011, 12, 6))
                        .setGenero("Blues Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Lonely Boy", "Gold on the Ceiling", "Little Black Submarines"))
                        .build());

            } else if ("The 1975".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The 1975")
                        .setImagenPortada("the_1975.jpg")
                        .setFechaLanzamiento(LocalDate.of(2013, 9, 2))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Chocolate", "Sex", "Robbers"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("I Like It When You Sleep, for You Are So Beautiful Yet So Unaware of It")
                        .setImagenPortada("i_like_it_when_you_sleep.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 2, 26))
                        .setGenero("Indie Rock")
                        .setArtista(artista)
                        .setCanciones(List.of("Love Me", "The Sound", "Somebody Else"))
                        .build());

            } else if ("Of Monsters and Men".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("My Head Is an Animal")
                        .setImagenPortada("my_head_is_an_animal.jpg")
                        .setFechaLanzamiento(LocalDate.of(2011, 9, 20))
                        .setGenero("Indie Folk")
                        .setArtista(artista)
                        .setCanciones(List.of("Little Talks", "Mountain Sound", "King and Lionheart"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Beneath the Skin")
                        .setImagenPortada("beneath_the_skin.jpg")
                        .setFechaLanzamiento(LocalDate.of(2015, 6, 9))
                        .setGenero("Indie Folk")
                        .setArtista(artista)
                        .setCanciones(List.of("Crystals", "Empire", "Hunger"))
                        .build());

            } else if ("The Lumineers".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("The Lumineers")
                        .setImagenPortada("the_lumineers.jpg")
                        .setFechaLanzamiento(LocalDate.of(2012, 4, 3))
                        .setGenero("Indie Folk")
                        .setArtista(artista)
                        .setCanciones(List.of("Ho Hey", "Stubborn Love", "Submarines"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Cleopatra")
                        .setImagenPortada("cleopatra.jpg")
                        .setFechaLanzamiento(LocalDate.of(2016, 4, 8))
                        .setGenero("Indie Folk")
                        .setArtista(artista)
                        .setCanciones(List.of("Ophelia", "Cleopatra", "Angela"))
                        .build());
            } else if ("Natanael Cano".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Soy El Diablo")
                        .setImagenPortada("soy_el_diablo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2019, 6, 28))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("Soy El Diablo", "Amor Tumbado", "El Drip"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Corridos Tumbados")
                        .setImagenPortada("corridos_tumbados.jpg")
                        .setFechaLanzamiento(LocalDate.of(2019, 10, 18))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("El de la Codeína", "Porte Exuberante", "El F1"))
                        .build());

            } else if ("Peso Pluma".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("El Belicón")
                        .setImagenPortada("el_belicon.jpg")
                        .setFechaLanzamiento(LocalDate.of(2021, 7, 23))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("El Belicón", "Ando Enfocado", "Mil Historias"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Disco de Oro")
                        .setImagenPortada("disco_de_oro.jpg")
                        .setFechaLanzamiento(LocalDate.of(2022, 5, 20))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("AMG", "PRC", "El Azteca"))
                        .build());

            } else if ("Tito Double P".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Vida Bélica")
                        .setImagenPortada("vida_belica.jpg")
                        .setFechaLanzamiento(LocalDate.of(2021, 11, 15))
                        .setGenero("Corridos Bélicos")
                        .setArtista(artista)
                        .setCanciones(List.of("El Güero", "Las Plebes", "Entre Compas"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Destino Bélico")
                        .setImagenPortada("destino_belico.jpg")
                        .setFechaLanzamiento(LocalDate.of(2022, 8, 19))
                        .setGenero("Corridos Bélicos")
                        .setArtista(artista)
                        .setCanciones(List.of("El Comando", "Las Rutas", "Bélico Soy"))
                        .build());

            } else if ("Luis R Conriquez".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Mi Enemigo el Tiempo")
                        .setImagenPortada("mi_enemigo_el_tiempo.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 3, 20))
                        .setGenero("Corridos Bélicos")
                        .setArtista(artista)
                        .setCanciones(List.of("Mi Enemigo el Tiempo", "El Buho", "El Viejon"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Corridos Belicos")
                        .setImagenPortada("corridos_belicos.jpg")
                        .setFechaLanzamiento(LocalDate.of(2021, 10, 15))
                        .setGenero("Corridos Bélicos")
                        .setArtista(artista)
                        .setCanciones(List.of("El Amigo del Amigo", "El Doble RR", "El Peligroso"))
                        .build());

            } else if ("Junior H".equals(artista.getNombre())) {
                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Atrapado en un Sueño")
                        .setImagenPortada("atrapado_en_un_sueno.jpg")
                        .setFechaLanzamiento(LocalDate.of(2020, 4, 27))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("No He Cambiado", "Ella", "Días Nublados"))
                        .build());

                albumes.add(new Album.Builder()
                        .setId(new ObjectId())
                        .setNombre("Cruisin' with Junior")
                        .setImagenPortada("cruisin_with_junior.jpg")
                        .setFechaLanzamiento(LocalDate.of(2021, 2, 12))
                        .setGenero("Corridos Tumbados")
                        .setArtista(artista)
                        .setCanciones(List.of("La Bestia", "En La Rañosa", "Arriba las Manos"))
                        .build());
            }

            




            

        }

        return albumes;
    }
}
