/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Graphics2D;
import com.bmd.entities.Usuario;
import com.bmn.dto.AlbumVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerAlbumesFavoritosBO;
import com.bmn.negocio.ObtenerAlbumesFiltradosBO;
import com.bmn.singletonUsuario.UsuarioST;
import controlador.RenderCeldas;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Collectors;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Sebastian Murrieta Verduzco -233463
 */
public class AlbumFavorito extends javax.swing.JFrame {

    private boolean isMenuVisible = true;
    private Usuario usuarioActual;
    private List<AlbumVistaDTO> albumes;
    private ObtenerAlbumesFavoritosBO obtenerAlbumes;

    public AlbumFavorito() {
        try {
            initComponents();
            cargarComboBox(); // Configurar tabla
            configurarTabla();

            // Obtener usuario actual
            this.usuarioActual = UsuarioST.getInstance();
            if (usuarioActual != null) {
                System.out.println("Usuario actual: " + usuarioActual.getNombre());
            } else {
                System.out.println("No se encontró información del usuario actual.");
            }

            // Listener para aplicar filtros al seleccionar un género
            generoFiltro.addActionListener(e -> filtrarAlbumes());

            // Listener para aplicar filtros al cambiar la fecha
            fechaFiltro.addPropertyChangeListener("date", e -> filtrarAlbumes());

            // Listener para limpiar todos los filtros
            limpiarTodosFiltrosBtn.addActionListener(e -> limpiarFiltros());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar la ventana de álbumes favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarComboBox() {
        for (Genero genero : Genero.values()) {
            generoFiltro.addItem(genero.name());
        }
    }

// Modify the cargarDatosDeLaBaseDeDatos method
    private void cargarDatosDeLaBaseDeDatos(DefaultTableModel modelo) {
        try {
            ObtenerAlbumesFavoritosBO favoritos = BOFactory.obtenerAlbumesFavoritosFactory();

            List<AlbumVistaDTO> albumes = favoritos.obtenerAlbumesFavoritos(null, null);
            this.albumes = albumes;

            System.out.println("Número de álbumes favoritos encontrados: " + albumes.size());

            modelo.setRowCount(0);

            for (AlbumVistaDTO album : albumes) {
                System.out.println("Álbum Favorito: " + album.getNombre()
                        + ", Artista: " + album.getArtistaVista().getNombre()
                        + ", Imagen: " + album.getImagen());

                ImageIcon imagen = cargarImagen(album.getImagen());

                modelo.addRow(new Object[]{
                    imagen,
                    album.getNombre(),
                    album.getArtistaVista().getNombre()
                });
            }

            System.out.println("Filas añadidas a la tabla: " + modelo.getRowCount());
        } catch (BOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar álbumes favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initGeneroFiltro() {
        // Populate genre filter combo box
        generoFiltro.addItem("Todos");
        for (Genero genero : Genero.values()) {
            generoFiltro.addItem(genero.name());
        }

        // Combine listeners for comprehensive filtering
        ActionListener filterListener = e -> {
            // Retrieve all filtering criteria
            String nombreAlbum = nombreDelAlbumTxt.getText().trim().toLowerCase();
            String nombreArtista = nombreArtistaTxt.getText().trim().toLowerCase();
            String generoSeleccionado = (String) generoFiltro.getSelectedItem();
            LocalDate fechaSeleccionada = fechaFiltro.getDate() != null
                    ? fechaFiltro.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate() : null;

            try {
                // Convert genre to Genero enum, handling "Todos" case
                Genero genero = generoSeleccionado != null && !generoSeleccionado.equals("Todos")
                        ? Genero.valueOf(generoSeleccionado)
                        : null;

                // Obtain albums using existing method
                ObtenerAlbumesFavoritosBO favoritos = BOFactory.obtenerAlbumesFavoritosFactory();
                List<AlbumVistaDTO> todosAlbumes = favoritos.obtenerAlbumesFavoritos(genero, fechaSeleccionada);

                // Filter albums based on additional criteria
                List<AlbumVistaDTO> albumesFiltrados = todosAlbumes.stream()
                        .filter(album -> {
                            // Filter by album name (case-insensitive)
                            boolean matchNombreAlbum = nombreAlbum.isEmpty()
                                    || album.getNombre().toLowerCase().contains(nombreAlbum);

                            // Filter by artist name (case-insensitive)
                            boolean matchNombreArtista = nombreArtista.isEmpty()
                                    || album.getArtistaVista().getNombre().toLowerCase().contains(nombreArtista);

                            return matchNombreAlbum && matchNombreArtista;
                        })
                        .collect(Collectors.toList());

                // Update table with filtered results
                DefaultTableModel modelo = (DefaultTableModel) tablaAlbumFavoritos.getModel();
                modelo.setRowCount(0);

                for (AlbumVistaDTO album : albumesFiltrados) {
                    ImageIcon imagen = cargarImagen(album.getImagen());
                    modelo.addRow(new Object[]{
                        imagen,
                        album.getNombre(),
                        album.getArtistaVista().getNombre()
                    });
                }

                // Show message if no results found
                if (albumesFiltrados.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "No se encontraron álbumes que coincidan con los criterios de búsqueda.",
                            "Filtro de Álbumes",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (BOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al filtrar álbumes: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        };

        // Add listeners to all filtering components
        generoFiltro.addActionListener(filterListener);
        fechaFiltro.addPropertyChangeListener("date", e -> filterListener.actionPerformed(null));

        // Add document listeners for text fields to trigger filtering
        nombreDelAlbumTxt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }

            public void removeUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }

            public void insertUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }
        });

        nombreArtistaTxt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }

            public void removeUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }

            public void insertUpdate(DocumentEvent e) {
                filterListener.actionPerformed(null);
            }
        });

        // Add clear filters button functionality
        limpiarTodosFiltrosBtn.addActionListener(e -> {
            // Clear all filter fields
            nombreDelAlbumTxt.setText("");
            nombreArtistaTxt.setText("");
            generoFiltro.setSelectedItem("Todos");
            fechaFiltro.setDate(null);

            // Reload all albums
            cargarDatosDeLaBaseDeDatos((DefaultTableModel) tablaAlbumFavoritos.getModel());
        });
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IMAGEN", "NOMBRE DEL ALBUM", "ARTISTA"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };

        tablaAlbumFavoritos.setModel(modelo);
        tablaAlbumFavoritos.getTableHeader().setReorderingAllowed(false);

        // Configurar render personalizado
        RenderCeldas render = new RenderCeldas(tablaAlbumFavoritos);
        render.setColumnAlignment(0, SwingConstants.CENTER); // Imagen centrada
        render.setColumnAlignment(1, SwingConstants.CENTER); // Nombre centrado
        render.setColumnAlignment(2, SwingConstants.LEFT);   // Artista a la izquierda

        // Configurar colores y estilos
        tablaAlbumFavoritos.setBackground(new Color(35, 58, 68));
        tablaAlbumFavoritos.setForeground(Color.WHITE);
        tablaAlbumFavoritos.setRowHeight(50);
        tablaAlbumFavoritos.setSelectionBackground(new Color(58, 107, 128));
        tablaAlbumFavoritos.setSelectionForeground(Color.WHITE);
        tablaAlbumFavoritos.getTableHeader().setBackground(new Color(35, 58, 68));
        tablaAlbumFavoritos.getTableHeader().setForeground(Color.WHITE);
        tablaAlbumFavoritos.setShowHorizontalLines(true);
        tablaAlbumFavoritos.setShowVerticalLines(false);
        tablaAlbumFavoritos.setGridColor(new Color(255, 255, 255, 50));

        // Configurar columnas
        tablaAlbumFavoritos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaAlbumFavoritos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaAlbumFavoritos.getColumnModel().getColumn(2).setPreferredWidth(150);

        // Configurar scroll pane
        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(35, 58, 68));

        cargarDatosDeLaBaseDeDatos(modelo);
    }

    private void filtrarAlbumes() {
        try {
            // Retrieve all filtering criteria
            String nombreAlbum = nombreDelAlbumTxt.getText().trim().toLowerCase();
            String nombreArtista = nombreArtistaTxt.getText().trim().toLowerCase();
            String generoSeleccionado = generoFiltro.getSelectedItem().toString();
            LocalDate fechaSeleccionada = fechaFiltro.getDate() != null
                    ? fechaFiltro.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate() : null;

            // Convert genre to Genero enum, handling "Todos" case
            Genero genero = generoSeleccionado.equals("Todos") ? null : Genero.valueOf(generoSeleccionado);

            // Obtain albums using existing method
            ObtenerAlbumesFavoritosBO favoritos = BOFactory.obtenerAlbumesFavoritosFactory();
            List<AlbumVistaDTO> todosAlbumes = favoritos.obtenerAlbumesFavoritos(genero, fechaSeleccionada);

            // Filter albums based on additional criteria
            List<AlbumVistaDTO> albumesFiltrados = todosAlbumes.stream()
                    .filter(album -> {
                        // Filter by album name (case-insensitive)
                        boolean matchNombreAlbum = nombreAlbum.isEmpty()
                                || album.getNombre().toLowerCase().contains(nombreAlbum);

                        // Filter by artist name (case-insensitive)
                        boolean matchNombreArtista = nombreArtista.isEmpty()
                                || album.getArtistaVista().getNombre().toLowerCase().contains(nombreArtista);

                        return matchNombreAlbum && matchNombreArtista;
                    })
                    .collect(Collectors.toList());

            // Update table with filtered results
            DefaultTableModel modelo = (DefaultTableModel) tablaAlbumFavoritos.getModel();
            modelo.setRowCount(0);

            for (AlbumVistaDTO album : albumesFiltrados) {
                ImageIcon imagen = cargarImagen(album.getImagen());
                modelo.addRow(new Object[]{
                    imagen,
                    album.getNombre(),
                    album.getArtistaVista().getNombre()
                });
            }

            // Show message if no results found
            if (albumesFiltrados.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron álbumes que coincidan con los criterios de búsqueda.",
                        "Filtro de Álbumes",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (BOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al filtrar álbumes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

// Add this method to handle clearing all filters
    private void limpiarFiltros() {
        // Clear text fields
        nombreDelAlbumTxt.setText("");
        nombreArtistaTxt.setText("");

        // Reset genre filter to "Todos"
        generoFiltro.setSelectedItem("Todos");

        // Clear date filter
        fechaFiltro.setDate(null);

        // Reload all albums
        cargarDatosDeLaBaseDeDatos((DefaultTableModel) tablaAlbumFavoritos.getModel());
    }

    private ImageIcon cargarImagen(String nombreImagen) {
        try {
            if (nombreImagen == null || nombreImagen.trim().isEmpty()) {
                return crearImagenPorDefecto();
            }

            String rutaCompleta = "src/ImagenesAlbum/" + nombreImagen;
            File archivoImagen = new File(rutaCompleta);

            if (!archivoImagen.exists()) {
                System.err.println("Imagen no encontrada: " + rutaCompleta);
                return crearImagenPorDefecto();
            }

            ImageIcon originalIcon = new ImageIcon(rutaCompleta);
            if (originalIcon.getIconWidth() <= 0) {
                return crearImagenPorDefecto();
            }

            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + nombreImagen);
            e.printStackTrace();
            return crearImagenPorDefecto();
        }
    }

    private ImageIcon crearImagenPorDefecto() {
        BufferedImage placeholderImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholderImage.createGraphics();

        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, 49, 49);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString("No Image", 5, 30);
        g2d.dispose();

        return new ImageIcon(placeholderImage);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlbumFavoritos = new javax.swing.JTable();
        menu = new controlador.PanelRound();
        albumFavLb = new javax.swing.JLabel();
        artistaLb = new javax.swing.JLabel();
        artistasFavLb = new javax.swing.JLabel();
        perfilLb = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        salir = new javax.swing.JLabel();
        albumLb = new javax.swing.JLabel();
        cancionesFavLb = new javax.swing.JLabel();
        baneadosLb = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        panelInformacionAlbum = new controlador.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ImagenAlbumPanel = new javax.swing.JPanel();
        imagenAlbum = new javax.swing.JLabel();
        nombreDelAlbumTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Canciones = new javax.swing.JScrollPane();
        cancionesDelAlbum = new javax.swing.JTable();
        nombreArtistaTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        generoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fechaLanzamientoTxt = new javax.swing.JTextField();
        agregarFavoritoAlbumBtn = new javax.swing.JButton();
        generoFiltro = new javax.swing.JComboBox<>();
        fechaFiltro = new com.toedter.calendar.JDateChooser();
        limpiarTodosFiltrosBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound5.setBackground(new java.awt.Color(35, 58, 68));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        tablaAlbumFavoritos.setBackground(new java.awt.Color(35, 58, 68));
        tablaAlbumFavoritos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Imagen", "Nombre", "Artista"
            }
        ));
        tablaAlbumFavoritos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaAlbumFavoritos.setGridColor(new java.awt.Color(35, 58, 68));
        tablaAlbumFavoritos.setSelectionBackground(new java.awt.Color(35, 58, 68));
        jScrollPane1.setViewportView(tablaAlbumFavoritos);
        if (tablaAlbumFavoritos.getColumnModel().getColumnCount() > 0) {
            tablaAlbumFavoritos.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        Fondo.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 510, 600));

        menu.setBackground(new java.awt.Color(58, 107, 128));

        albumFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        albumFavLb.setForeground(new java.awt.Color(255, 255, 255));
        albumFavLb.setText("Album Favoritos");
        albumFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumFavLb.setEnabled(false);
        albumFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumFavLbMouseClicked(evt);
            }
        });

        artistaLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistaLb.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb.setText("Artista");
        artistaLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLbMouseClicked(evt);
            }
        });

        artistasFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistasFavLb.setForeground(new java.awt.Color(255, 255, 255));
        artistasFavLb.setText("Artistas Favoritos");
        artistasFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistasFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistasFavLbMouseClicked(evt);
            }
        });

        perfilLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        perfilLb.setForeground(new java.awt.Color(255, 255, 255));
        perfilLb.setText("Mi perfil");
        perfilLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfilLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfilLbMouseClicked(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        salir.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("SALIR");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
            }
        });

        albumLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        albumLb.setForeground(new java.awt.Color(255, 255, 255));
        albumLb.setText("      Album");
        albumLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumLbMouseClicked(evt);
            }
        });

        cancionesFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        cancionesFavLb.setForeground(new java.awt.Color(255, 255, 255));
        cancionesFavLb.setText("Canciones Favoritas");
        cancionesFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancionesFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancionesFavLbMouseClicked(evt);
            }
        });

        baneadosLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        baneadosLb.setForeground(new java.awt.Color(255, 255, 255));
        baneadosLb.setText("Baneados");
        baneadosLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        baneadosLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baneadosLbMouseClicked(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setForeground(new java.awt.Color(24, 40, 54));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(albumLb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(albumFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistasFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancionesFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baneadosLb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfilLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(salir)))
                .addGap(111, 111, 111))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(jSeparator7)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(baneadosLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancionesFavLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(menuLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(perfilLb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salir)))
                        .addGap(84, 84, 84))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistasFavLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        Fondo.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 70));

        panelInformacionAlbum.setBackground(new java.awt.Color(35, 58, 68));
        panelInformacionAlbum.setRoundBottomLeft(30);
        panelInformacionAlbum.setRoundBottomRight(30);
        panelInformacionAlbum.setRoundTopLeft(30);
        panelInformacionAlbum.setRoundTopRight(30);

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Artista:");

        jPanel1.setBackground(new java.awt.Color(26, 26, 26));

        ImagenAlbumPanel.setBackground(new java.awt.Color(153, 255, 255));
        ImagenAlbumPanel.setPreferredSize(new java.awt.Dimension(119, 119));

        javax.swing.GroupLayout ImagenAlbumPanelLayout = new javax.swing.GroupLayout(ImagenAlbumPanel);
        ImagenAlbumPanel.setLayout(ImagenAlbumPanelLayout);
        ImagenAlbumPanelLayout.setHorizontalGroup(
            ImagenAlbumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenAlbumPanelLayout.createSequentialGroup()
                .addComponent(imagenAlbum)
                .addGap(0, 152, Short.MAX_VALUE))
        );
        ImagenAlbumPanelLayout.setVerticalGroup(
            ImagenAlbumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenAlbumPanelLayout.createSequentialGroup()
                .addComponent(imagenAlbum)
                .addGap(0, 152, Short.MAX_VALUE))
        );

        nombreDelAlbumTxt.setBackground(new java.awt.Color(26, 26, 26));
        nombreDelAlbumTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nombreDelAlbumTxt.setForeground(new java.awt.Color(255, 255, 255));
        nombreDelAlbumTxt.setText("Nombre Del Album");
        nombreDelAlbumTxt.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(nombreDelAlbumTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ImagenAlbumPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(ImagenAlbumPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreDelAlbumTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Informacion");

        jPanel3.setBackground(new java.awt.Color(35, 58, 68));

        Canciones.setBackground(new java.awt.Color(35, 58, 68));

        cancionesDelAlbum.setBackground(new java.awt.Color(35, 58, 68));
        cancionesDelAlbum.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        cancionesDelAlbum.setForeground(new java.awt.Color(255, 255, 255));
        cancionesDelAlbum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Canciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Canciones.setViewportView(cancionesDelAlbum);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Canciones, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Canciones, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        nombreArtistaTxt.setEditable(false);
        nombreArtistaTxt.setBackground(new java.awt.Color(35, 58, 68));
        nombreArtistaTxt.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        nombreArtistaTxt.setForeground(new java.awt.Color(255, 255, 255));
        nombreArtistaTxt.setBorder(null);

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genero:");

        generoTxt.setEditable(false);
        generoTxt.setBackground(new java.awt.Color(35, 58, 68));
        generoTxt.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        generoTxt.setForeground(new java.awt.Color(255, 255, 255));
        generoTxt.setBorder(null);

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de lanzamiento:");

        fechaLanzamientoTxt.setEditable(false);
        fechaLanzamientoTxt.setBackground(new java.awt.Color(35, 58, 68));
        fechaLanzamientoTxt.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        fechaLanzamientoTxt.setForeground(new java.awt.Color(255, 255, 255));
        fechaLanzamientoTxt.setBorder(null);

        agregarFavoritoAlbumBtn.setBackground(new java.awt.Color(58, 107, 128));
        agregarFavoritoAlbumBtn.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        agregarFavoritoAlbumBtn.setToolTipText("");

        javax.swing.GroupLayout panelInformacionAlbumLayout = new javax.swing.GroupLayout(panelInformacionAlbum);
        panelInformacionAlbum.setLayout(panelInformacionAlbumLayout);
        panelInformacionAlbumLayout.setHorizontalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregarFavoritoAlbumBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(generoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaLanzamientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformacionAlbumLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(149, 149, 149)))
        );
        panelInformacionAlbumLayout.setVerticalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addComponent(nombreArtistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaLanzamientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(agregarFavoritoAlbumBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInformacionAlbumLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(410, Short.MAX_VALUE)))
        );

        Fondo.add(panelInformacionAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 590, 490));

        generoFiltro.setBackground(new java.awt.Color(35, 58, 68));
        generoFiltro.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        generoFiltro.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(generoFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 160, 34));
        Fondo.add(fechaFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 110, 140, 30));

        limpiarTodosFiltrosBtn.setBackground(new java.awt.Color(58, 107, 128));
        limpiarTodosFiltrosBtn.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        limpiarTodosFiltrosBtn.setText("Limpiar Filtros");
        limpiarTodosFiltrosBtn.setToolTipText("");
        Fondo.add(limpiarTodosFiltrosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 160, -1, 30));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void albumFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumFavLbMouseClicked
        dispose();
        new AlbumFavorito().setVisible(true);
    }//GEN-LAST:event_albumFavLbMouseClicked

    private void artistaLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_artistaLbMouseClicked

    private void artistasFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistasFavLbMouseClicked
        dispose();
        new ArtistaFavorito().setVisible(true);
    }//GEN-LAST:event_artistasFavLbMouseClicked

    private void perfilLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfilLbMouseClicked
        try {
            ActualizarUsuario actualizarUsuario = new ActualizarUsuario();
            actualizarUsuario.setLocationRelativeTo(this); // Center the new window
            actualizarUsuario.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al abrir la ventana de actualización: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_perfilLbMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new Inicio().setVisible(true);
        }
    }//GEN-LAST:event_salirMouseClicked

    private void albumLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumLbMouseClicked
        dispose();
        new Principal().setVisible(true);
    }//GEN-LAST:event_albumLbMouseClicked

    private void cancionesFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancionesFavLbMouseClicked
        this.dispose();
        new CancionesFavoritas().setVisible(true);

    }//GEN-LAST:event_cancionesFavLbMouseClicked

    private void baneadosLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baneadosLbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_baneadosLbMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Canciones;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel ImagenAlbumPanel;
    private javax.swing.JButton agregarFavoritoAlbumBtn;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JLabel baneadosLb;
    private javax.swing.JTable cancionesDelAlbum;
    private javax.swing.JLabel cancionesFavLb;
    private com.toedter.calendar.JDateChooser fechaFiltro;
    private javax.swing.JTextField fechaLanzamientoTxt;
    private javax.swing.JComboBox<String> generoFiltro;
    private javax.swing.JTextField generoTxt;
    private javax.swing.JLabel imagenAlbum;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton limpiarTodosFiltrosBtn;
    private controlador.PanelRound menu;
    private javax.swing.JTextField nombreArtistaTxt;
    private javax.swing.JTextField nombreDelAlbumTxt;
    private controlador.PanelRound panelInformacionAlbum;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaAlbumFavoritos;
    // End of variables declaration//GEN-END:variables
}
