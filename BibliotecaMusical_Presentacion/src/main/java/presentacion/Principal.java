package presentacion;

import java.awt.Graphics2D;
import com.bmd.entities.Usuario;
import com.bmn.dto.AlbumDTO;
import com.bmn.dto.AlbumVistaDTO;
import com.bmn.dto.FavoritoDTO;
import com.bmn.dto.constantes.Genero;
import static com.bmn.dto.constantes.Tipo.ALBUM;
import static com.bmn.dto.constantes.Tipo.CANCION;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.AgregarCancionFavoritaBO;
import com.bmn.negocio.AgregarFavoritoBO;
import com.bmn.negocio.ObtenerAlbumBO;
import com.bmn.negocio.ObtenerAlbumesFiltradosBO;
import com.bmn.singletonUsuario.UsuarioST;
import controlador.RenderCeldas;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Sebastian Murrieta Verduzco -233463
 */
public class Principal extends javax.swing.JFrame {

    private Usuario usuarioActual;
    private List<AlbumVistaDTO> albumes;
    private AgregarCancionFavoritaBO agregarFavoritoBO;
    private AlbumDTO detallado;

    public Principal() {
        try {
            this.agregarFavoritoBO = BOFactory.agregarCancionFavoritaFactory();
            initComponents(); // Inicializa los componentes de la interfaz gráfica
            configurarTabla(); // Configura la tabla principal de álbumes
            cargarComboBox(); // Carga los datos iniciales en los elementos de selección (combos)

            // Agregar un elemento predeterminado al combo de géneros
            generoFiltro.addItem("Todos");

            // Agregar listeners para realizar búsquedas dinámicas y aplicar filtros
            busqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    filtrarAlbumes(); // Filtra los álbumes al escribir en el campo de búsqueda
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    filtrarAlbumes(); // Filtra los álbumes al eliminar texto del campo de búsqueda
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    filtrarAlbumes(); // Filtra los álbumes al realizar cambios en el campo de búsqueda
                }
            });

            // Listener para aplicar filtros al seleccionar un género
            generoFiltro.addActionListener(e -> filtrarAlbumes());

            // Listener para aplicar filtros al cambiar la fecha
            fechaFiltro.addPropertyChangeListener("date", e -> filtrarAlbumes());

            // Listener para limpiar todos los filtros
            limpiarTodosFiltrosBtn.addActionListener(e -> limpiarFiltros());

            this.usuarioActual = UsuarioST.getInstance(); // Inicializa el usuario actual
        } catch (Exception e) {
            // Muestra un mensaje de error si ocurre un problema durante la inicialización
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String searchTerm) {
        // Filtra la tabla utilizando el término de búsqueda ingresado
        DefaultTableModel model = (DefaultTableModel) tablaAlbum.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tablaAlbum.setRowSorter(sorter);

        // Crea un filtro para buscar en las columnas "Nombre del álbum" y "Artista"
        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(
                "(?i)" + searchTerm, 1, 2
        );
        sorter.setRowFilter(filter); // Aplica el filtro
    }

    private void configurarTabla() {
        // Configura las propiedades de la tabla principal de álbumes
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IMAGEN", "NOMBRE DEL ÁLBUM", "ARTISTA"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición de celdas en la tabla
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Define el tipo de datos de cada columna
                if (columnIndex == 0) {
                    return ImageIcon.class; // La primera columna muestra imágenes
                }
                return Object.class;
            }
        };
        tablaAlbum.setModel(modelo);
        tablaAlbum.getTableHeader().setReorderingAllowed(false);

        // Configurar render personalizado
        RenderCeldas render = new RenderCeldas(tablaAlbum);
        render.setColumnAlignment(0, SwingConstants.CENTER); // Centra las imágenes
        render.setColumnAlignment(1, SwingConstants.CENTER); // Centra los nombres de álbumes
        render.setColumnAlignment(2, SwingConstants.LEFT);   // Alinea los artistas a la izquierda

        // Establece los colores y estilos de la tabla
        tablaAlbum.setBackground(new Color(35, 58, 68));
        tablaAlbum.setForeground(Color.WHITE);
        tablaAlbum.setRowHeight(50);
        tablaAlbum.setSelectionBackground(new Color(58, 107, 128));
        tablaAlbum.setSelectionForeground(Color.WHITE);
        tablaAlbum.getTableHeader().setBackground(new Color(35, 58, 68));
        tablaAlbum.getTableHeader().setForeground(Color.WHITE);

        // Configura el ancho de las columnas
        tablaAlbum.getColumnModel().getColumn(0).setPreferredWidth(100);  // Columna de imagen
        tablaAlbum.getColumnModel().getColumn(1).setPreferredWidth(200);  // Columna de nombre
        tablaAlbum.getColumnModel().getColumn(2).setPreferredWidth(150);  // Columna de artista

        // Configura la tabla de canciones
        cancionesDelAlbum.setBackground(new Color(35, 58, 68));
        cancionesDelAlbum.setForeground(Color.WHITE);
        cancionesDelAlbum.setRowHeight(50);
        cancionesDelAlbum.setSelectionBackground(new Color(58, 107, 128));
        cancionesDelAlbum.setSelectionForeground(Color.WHITE);
        cancionesDelAlbum.getTableHeader().setBackground(new Color(35, 58, 68));
        cancionesDelAlbum.getTableHeader().setForeground(Color.WHITE);

        // Configura el evento de selección de filas
        configurarSeleccionTabla();

        // Carga los datos iniciales en la tabla desde la base de datos
        cargarDatosDeLaBaseDeDatos(modelo);
    }

    private void configurarSeleccionTabla() {
        // Configura el evento de selección de filas en la tabla de álbumes
        tablaAlbum.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaAlbum.getSelectedRow(); // Obtiene la fila seleccionada
                if (selectedRow >= 0) {
                    try {
                        int modelRow = tablaAlbum.convertRowIndexToModel(selectedRow);

                        // Obtiene el álbum seleccionado
                        AlbumVistaDTO albumVista = this.albumes.get(modelRow);
                             

                        ObtenerAlbumBO albumBO = BOFactory.obtenerAlbumFactory();
                        AlbumDTO albumDetallado = albumBO.obtenerAlbum(albumVista.getId());
                            this.detallado = albumDetallado;
                        // Actualiza el panel con la información del álbum seleccionado
                        actualizarPanelInformacion(albumDetallado);
                    } catch (BOException | NullPointerException ex) {
                        // Muestra un mensaje de error si ocurre un problema al cargar los detalles
                        JOptionPane.showMessageDialog(this,
                                "Error al cargar detalles del álbum: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void actualizarPanelInformacion(AlbumDTO album) {
        if (album == null) {
            // Clear all fields if album is null
            imagenAlbum.setIcon(null);
            nombreDelAlbumTxt.setText("");
            nombreArtistaTxt.setText("");
            fechaLanzamientoTxt.setText("");
            generoTxt.setText("Sin género");  // Direct text when album is null

            // Clear songs table
            DefaultTableModel modeloCanciones = (DefaultTableModel) cancionesDelAlbum.getModel();
            modeloCanciones.setRowCount(0);

            return;
        }

        // Load album image
        ImageIcon albumIcon = cargarImagen(album.getImagenPortada());
        if (albumIcon != null) {
            Image scaled = albumIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagenAlbum.setIcon(new ImageIcon(scaled));
        } else {
            imagenAlbum.setIcon(null);
        }

        // Set album details
        nombreDelAlbumTxt.setText(album.getNombre() != null ? album.getNombre() : "");
        nombreArtistaTxt.setText(album.getArtista() != null && album.getArtista().getNombre() != null
                ? album.getArtista().getNombre()
                : "");
        fechaLanzamientoTxt.setText(album.getFechaLanzamiento() != null
                ? album.getFechaLanzamiento().toString()
                : "");
        generoTxt.setText(album.getGenero() != null
                ? album.getGenero().name()
                : "");

        // Configure songs table model
        DefaultTableModel modeloCanciones = new DefaultTableModel(
                new String[]{"Título", "Favorito"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 1 ? Boolean.class : String.class;
            }
        };

        // Populate songs table, handling potential null list
        if (album.getCanciones() != null) {
            album.getCanciones().forEach(cancion -> {
                modeloCanciones.addRow(new Object[]{
                    cancion.getNombre() != null ? cancion.getNombre() : "",
                    cancion.isFavorito()
                });
            });
        }

        cancionesDelAlbum.setModel(modeloCanciones);

        // Add table listener for checkbox changes
        cancionesDelAlbum.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 1) {
                int row = e.getFirstRow();
                String nombreCancion = (String) cancionesDelAlbum.getValueAt(row, 0);
                boolean nuevoEstado = (boolean) cancionesDelAlbum.getValueAt(row, 1);

                try {
                    // Check if album or its genre is null
                    if (album == null) {
                        throw new BOException("El álbum no puede ser nulo");
                    }
                    if (album.getGenero() == null) {
                        // Revert checkbox state
                        SwingUtilities.invokeLater(() -> {
                            cancionesDelAlbum.setValueAt(!nuevoEstado, row, 1);
                            JOptionPane.showMessageDialog(this,
                                    "No se puede marcar como favorito: El género del álbum es requerido",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        });
                        return;
                    }

                    FavoritoDTO favoritoDTO = new FavoritoDTO.Builder()
                            .setIdUsuario(UsuarioST.getInstance().getId())
                            .setIdReferencia(album.getId())
                            .setNombreCancion(nombreCancion)
                            .setGenero(detallado.getGenero())
                            .setTipo(CANCION)
                            .setFechaAgregacion(LocalDate.now())
                            .build();

                    agregarFavoritoBO.agregarCancionFavorita(favoritoDTO);
                    album.getCanciones().get(row).setFavorito(nuevoEstado);
                } catch (BOException ex) {
                    SwingUtilities.invokeLater(() -> {
                        cancionesDelAlbum.setValueAt(!nuevoEstado, row, 1);
                        JOptionPane.showMessageDialog(this,
                                "Error al modificar favoritos: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    });
                }
            }
        });

        // Update button color and text based on favorite status
        if (album.isFavorito()) {
            agregarFavoritoAlbumBtn.setBackground(new Color(24, 40, 54)); // Green for favorite
            agregarFavoritoAlbumBtn.setText("Eliminar de Favoritos");
        } else {
            agregarFavoritoAlbumBtn.setBackground(new Color(58, 107, 128)); // Default color
            agregarFavoritoAlbumBtn.setText("Agregar a Favoritos");
        } // Add action listener to toggle favorite status
        agregarFavoritoAlbumBtn
                .addActionListener(e -> {
                    try {
                        FavoritoDTO favoritoDTO = new FavoritoDTO.Builder()
                                .setIdUsuario(UsuarioST.getInstance().getId())
                                .setIdReferencia(album.getId())
                                .setTipo(ALBUM)
                                .setGenero(detallado.getGenero())
                                .setFechaAgregacion(LocalDate.now())
                                .build();

                        if (album.isFavorito()) {
                            // Remove from favorites

                            AgregarFavoritoBO agregarFavoritoBO = BOFactory.agregarFavoritoFactory();
                            agregarFavoritoBO.agregarFavorito(favoritoDTO);
                            album.setFavorito(false);
                        } else {
                            // Add to favorites
                            AgregarFavoritoBO agregarFavoritoBO = BOFactory.agregarFavoritoFactory();
                            agregarFavoritoBO.agregarFavorito(favoritoDTO);
                            album.setFavorito(true);
                        }
                        actualizarPanelInformacion(album);
                    } catch (BOException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Error al modificar favoritos: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
    }

    private void cargarComboBox() {
        for (Genero genero : Genero.values()) {
            generoFiltro.addItem(genero.name());
        }
    }

    private ImageIcon cargarImagen(String nombreImagen) {
        try {
            // Validate input
            if (nombreImagen == null || nombreImagen.trim().isEmpty()) {
                return crearImagenPorDefecto();
            }

            // Relative path from project root
            String rutaCompleta = "src/ImagenesAlbum/" + nombreImagen;

            File archivoImagen = new File(rutaCompleta);

            // Check if file exists
            if (!archivoImagen.exists()) {
                System.err.println("Imagen no encontrada: " + rutaCompleta);
                return crearImagenPorDefecto();
            }

            // Load the image
            ImageIcon originalIcon = new ImageIcon(rutaCompleta);

            // Validate image load
            if (originalIcon.getIconWidth() <= 0) {
                System.err.println("Error al cargar la imagen: " + rutaCompleta);
                return crearImagenPorDefecto();
            }

            // Scale the image
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            // Log the error
            System.err.println("Excepción al cargar imagen: " + nombreImagen);
            e.printStackTrace();

            // Return a default placeholder image
            return crearImagenPorDefecto();
        }
    }

    private ImageIcon crearImagenPorDefecto() {
        BufferedImage placeholderImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholderImage.createGraphics();

        // Set background
        g2d.setColor(new Color(200, 200, 200)); // Light gray
        g2d.fillRect(0, 0, 50, 50);

        // Draw border
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, 49, 49);

        // Add text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString("No Image", 5, 30);

        g2d.dispose();

        return new ImageIcon(placeholderImage);
    }

    private void cargarDatosDeLaBaseDeDatos(DefaultTableModel modelo) {
        try {
            // Obtain an instance of the BO for filtering albums
            ObtenerAlbumesFiltradosBO obtener = BOFactory.obtenerAlbumesFiltradosFactory();

            // Call the business logic method to search for albums
            List<AlbumVistaDTO> albumes = obtener.BuscarPorFiltro(null, null, null);

            this.albumes = albumes;

            // Debug: Imprimir número de álbumes
            System.out.println("Número de álbumes encontrados: " + albumes.size());

            // Clear existing rows
            modelo.setRowCount(0);

            // Iterate over the results and add them to the table
            for (AlbumVistaDTO album : albumes) {
                // Debug: Imprimir detalles de cada álbum
                System.out.println("Álbum: " + album.getNombre()
                        + ", Artista: " + album.getArtistaVista().getNombre()
                        + ", Imagen: " + album.getImagen());

                // Load image from file system
                ImageIcon imagen = cargarImagen(album.getImagen());

                modelo.addRow(new Object[]{
                    imagen, // Use the loaded image
                    album.getNombre(),
                    album.getArtistaVista().getNombre()
                });
            }

            // Debug: Imprimir número de filas añadidas
            System.out.println("Filas añadidas a la tabla: " + modelo.getRowCount());
        } catch (BOException e) {
            e.printStackTrace(); // Esto imprimirá el stack trace completo
            JOptionPane.showMessageDialog(this,
                    "Error al cargar álbumes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    private void filtrarAlbumes() {
        try {
            String nombreBusqueda = busqueda.getText().trim();
            String generoSeleccionado = generoFiltro.getSelectedItem().toString();
            LocalDate fechaSeleccionada = fechaFiltro.getDate() != null
                    ? fechaFiltro.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate() : null;

            ObtenerAlbumesFiltradosBO obtener = BOFactory.obtenerAlbumesFiltradosFactory();
            Genero genero = generoSeleccionado.equals("Todos") ? null : Genero.valueOf(generoSeleccionado);

            List<AlbumVistaDTO> albumesFiltrados = obtener.BuscarPorFiltro(
                    nombreBusqueda.isEmpty() ? null : nombreBusqueda,
                    fechaSeleccionada, genero
            );

            actualizarTabla(albumesFiltrados);
        } catch (BOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al filtrar álbumes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(List<AlbumVistaDTO> albumesFiltrados) {
        this.albumes = albumesFiltrados;
        DefaultTableModel modelo = (DefaultTableModel) tablaAlbum.getModel();
        modelo.setRowCount(0);

        for (AlbumVistaDTO album : albumesFiltrados) {
            ImageIcon imagen = cargarImagen(album.getImagen());
            modelo.addRow(new Object[]{
                imagen,
                album.getNombre(),
                album.getArtistaVista().getNombre()
            });
        }
    }

    private void limpiarFiltros() {
        busqueda.setText("");
        generoFiltro.setSelectedItem("Todos");
        fechaFiltro.setDate(null);
        filtrarAlbumes(); // Recargar tabla sin filtros
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
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
        artistaLb1 = new javax.swing.JLabel();
        baneadoLb = new javax.swing.JLabel();
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
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlbum = new javax.swing.JTable();
        generoFiltro = new javax.swing.JComboBox<>();
        panelRound3 = new controlador.PanelRound();
        busqueda = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        fechaFiltro = new com.toedter.calendar.JDateChooser();
        limpiarTodosFiltrosBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setMinimumSize(new java.awt.Dimension(1280, 720));
        Fondo.setPreferredSize(new java.awt.Dimension(1280, 720));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(58, 107, 128));

        albumFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        albumFavLb.setForeground(new java.awt.Color(255, 255, 255));
        albumFavLb.setText("Album Favoritos");
        albumFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        albumLb.setEnabled(false);
        albumLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumLbMouseClicked(evt);
            }
        });

        artistaLb1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistaLb1.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb1.setText("Canciones Favoritas");
        artistaLb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLb1MouseClicked(evt);
            }
        });

        baneadoLb.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        baneadoLb.setForeground(new java.awt.Color(255, 255, 255));
        baneadoLb.setText("Baneados");
        baneadoLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        baneadoLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baneadoLbMouseClicked(evt);
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
                .addComponent(artistaLb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baneadoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
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
                            .addComponent(baneadoLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(artistaLb1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        Fondo.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, -5, 1300, 70));

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
        if (cancionesDelAlbum.getColumnModel().getColumnCount() > 0) {
            cancionesDelAlbum.getColumnModel().getColumn(0).setResizable(false);
        }

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

        panelRound5.setBackground(new java.awt.Color(35, 58, 68));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        tablaAlbum.setBackground(new java.awt.Color(35, 58, 68));
        tablaAlbum.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlbum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaAlbum.setGridColor(new java.awt.Color(35, 58, 68));
        tablaAlbum.setSelectionBackground(new java.awt.Color(35, 58, 68));
        jScrollPane1.setViewportView(tablaAlbum);
        if (tablaAlbum.getColumnModel().getColumnCount() > 0) {
            tablaAlbum.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );

        Fondo.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 510, 610));

        generoFiltro.setBackground(new java.awt.Color(35, 58, 68));
        generoFiltro.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        generoFiltro.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(generoFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 136, 34));

        panelRound3.setBackground(new java.awt.Color(35, 58, 68));
        panelRound3.setCursorHandEnabled(true);
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        busqueda.setBackground(new java.awt.Color(35, 58, 68));
        busqueda.setForeground(new java.awt.Color(255, 255, 255));
        busqueda.setBorder(null);
        busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaActionPerformed(evt);
            }
        });

        buscarBtn.setBackground(new java.awt.Color(35, 58, 68));
        buscarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/lupa.png"))); // NOI18N
        buscarBtn.setBorder(null);
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        Fondo.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, -1, -1));
        Fondo.add(fechaFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 140, 30));

        limpiarTodosFiltrosBtn.setBackground(new java.awt.Color(58, 107, 128));
        limpiarTodosFiltrosBtn.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        limpiarTodosFiltrosBtn.setText("Limpiar Filtros");
        limpiarTodosFiltrosBtn.setToolTipText("");
        Fondo.add(limpiarTodosFiltrosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, -1, 30));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaActionPerformed
        String searchTerm = busqueda.getText().trim();
        filterTable(searchTerm);
    }//GEN-LAST:event_busquedaActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        busquedaActionPerformed(evt);
    }//GEN-LAST:event_buscarBtnActionPerformed

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

    private void artistasFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistasFavLbMouseClicked
        dispose();
        new ArtistaFavorito().setVisible(true);
    }//GEN-LAST:event_artistasFavLbMouseClicked

    private void artistaLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_artistaLbMouseClicked

    private void albumFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumFavLbMouseClicked
        dispose();
        new AlbumFavorito().setVisible(true);
    }//GEN-LAST:event_albumFavLbMouseClicked

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

    private void artistaLb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb1MouseClicked
        CancionesFavoritas favoritas = new CancionesFavoritas();
        favoritas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_artistaLb1MouseClicked

    private void baneadoLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baneadoLbMouseClicked
        dispose();
        new Baneado().setVisible(true);
    }//GEN-LAST:event_baneadoLbMouseClicked

    private void albumLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumLbMouseClicked
        dispose();
        new Principal().setVisible(true);
    }//GEN-LAST:event_albumLbMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Canciones;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel ImagenAlbumPanel;
    private javax.swing.JButton agregarFavoritoAlbumBtn;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistaLb1;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JLabel baneadoLb;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField busqueda;
    private javax.swing.JTable cancionesDelAlbum;
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
    private controlador.PanelRound panelRound3;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaAlbum;
    // End of variables declaration//GEN-END:variables

}
