/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Graphics2D;
import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerArtistasFavoritosBO;
import controlador.RenderCeldas;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.regex.PatternSyntaxException;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Sebastian Murrieta Verduzco -233463
 */
public class ArtistaFavorito extends javax.swing.JFrame {

    private boolean isMenuVisible = true;
    private List<ArtistaVistaDTO> artistas;
    private ObtenerArtistasFavoritosBO favoritos;

    public ArtistaFavorito() {
        try {
            // Initialize components
            initComponents();

            // Configure table
            configurarTabla();

            // Configure search functionality
            configurarBusqueda();

            // Initialize business logic
            this.favoritos = BOFactory.obtenerArtistasFavoritosFactory();

            // Move menu panel out of view
            menuDesplegablePanel.setLocation(-menuDesplegablePanel.getWidth(),
                    menuDesplegablePanel.getY());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar la ventana de artistas favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarBusqueda() {
        // Añadir listener al botón de búsqueda
        buscarBtn.addActionListener(e -> {
            String termino = busqueda.getText().trim();
            filterTable(termino);
        });

        // Añadir listener al campo de texto para búsqueda en tiempo real
        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String termino = busqueda.getText().trim();
                filterTable(termino);
            }
        });
    }

    private void filterTable(String searchTerm) {
        try {
            DefaultTableModel model = (DefaultTableModel) tablaArtistaFav.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tablaArtistaFav.setRowSorter(sorter);

            if (searchTerm.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                // Ignorar mayúsculas/minúsculas y buscar en la columna del nombre del artista
                RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(
                        "(?i)" + searchTerm, 1 // 1 es el índice de la columna del nombre del artista
                );
                sorter.setRowFilter(filter);
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("Error en el patrón de búsqueda: " + pse.getMessage());
            // Remover el filtro si hay un error en el patrón
            tablaArtistaFav.setRowSorter(null);
        }
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IMAGEN", "NOMBRE DEL ARTISTA"}, 0) {
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

        tablaArtistaFav.setModel(modelo);
        tablaArtistaFav.getTableHeader().setReorderingAllowed(false);

        // Configure custom renderer
        RenderCeldas render = new RenderCeldas(tablaArtistaFav);
        render.setColumnAlignment(0, SwingConstants.CENTER);
        render.setColumnAlignment(1, SwingConstants.CENTER);

        // Configure colors and styles
        tablaArtistaFav.setBackground(new Color(35, 58, 68));
        tablaArtistaFav.setForeground(Color.WHITE);
        tablaArtistaFav.setRowHeight(50);
        tablaArtistaFav.setSelectionBackground(new Color(58, 107, 128));
        tablaArtistaFav.setSelectionForeground(Color.WHITE);
        tablaArtistaFav.getTableHeader().setBackground(new Color(35, 58, 68));
        tablaArtistaFav.getTableHeader().setForeground(Color.WHITE);
        tablaArtistaFav.setShowHorizontalLines(true);
        tablaArtistaFav.setShowVerticalLines(false);
        tablaArtistaFav.setGridColor(new Color(255, 255, 255, 50));

        // Configure columns
        tablaArtistaFav.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaArtistaFav.getColumnModel().getColumn(1).setPreferredWidth(200);

        // Configure scroll pane
        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(35, 58, 68));

        cargarDatosDeLaBaseDeDatos(modelo);
    }

    private ImageIcon cargarImagen(String nombreImagen) {
        try {
            if (nombreImagen == null || nombreImagen.trim().isEmpty()) {
                return crearImagenPorDefecto();
            }

            String rutaCompleta = "src/ImagenesArtista/" + nombreImagen;
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

    private void cargarDatosDeLaBaseDeDatos(DefaultTableModel modelo) {
        try {
            // Get favorite artists (null parameters to get all)
            List<ArtistaVistaDTO> artistas = favoritos.obtenerArtistasFavoritos(null, null);
            this.artistas = artistas;

            System.out.println("Número de artistas encontrados: " + artistas.size());

            modelo.setRowCount(0);

            for (ArtistaVistaDTO artista : artistas) {
                System.out.println("Artista: " + artista.getNombre()
                        + ", Imagen: " + artista.getImagen());

                ImageIcon imagen = cargarImagen(artista.getImagen());

                modelo.addRow(new Object[]{
                    imagen,
                    artista.getNombre()
                });
            }

            System.out.println("Filas añadidas a la tabla: " + modelo.getRowCount());
        } catch (BOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar artistas favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        menuDesplegablePanel = new javax.swing.JPanel();
        albumLb = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        albumFavLb = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        artistaLb = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        artistasFavLb = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        salir = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        perfilLb = new javax.swing.JLabel();
        panelRound1 = new controlador.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound3 = new controlador.PanelRound();
        busqueda = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        menuBtn = new javax.swing.JButton();
        panelInformacionAlbum = new controlador.PanelRound();
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArtistaFav = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuDesplegablePanel.setBackground(new java.awt.Color(58, 107, 128));
        menuDesplegablePanel.setPreferredSize(new java.awt.Dimension(290, 660));

        albumLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        albumLb.setForeground(new java.awt.Color(255, 255, 255));
        albumLb.setText("Album");
        albumLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumLbMouseClicked(evt);
            }
        });

        albumFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        albumFavLb.setForeground(new java.awt.Color(255, 255, 255));
        albumFavLb.setText("Album Favoritos");
        albumFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        albumFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumFavLbMouseClicked(evt);
            }
        });

        artistaLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistaLb.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb.setText("Artista");
        artistaLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLbMouseClicked(evt);
            }
        });

        artistasFavLb.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        artistasFavLb.setForeground(new java.awt.Color(255, 255, 255));
        artistasFavLb.setText("Artistas Favoritos");
        artistasFavLb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistasFavLb.setEnabled(false);
        artistasFavLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistasFavLbMouseClicked(evt);
            }
        });

        salir.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("SALIR");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
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

        javax.swing.GroupLayout menuDesplegablePanelLayout = new javax.swing.GroupLayout(menuDesplegablePanel);
        menuDesplegablePanel.setLayout(menuDesplegablePanelLayout);
        menuDesplegablePanelLayout.setHorizontalGroup(
            menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(albumFavLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(artistaLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(artistasFavLb, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                        .addComponent(salir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(perfilLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        menuDesplegablePanelLayout.setVerticalGroup(
            menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDesplegablePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(albumLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(albumFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistaLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistasFavLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir)
                .addGap(8, 8, 8))
            .addGroup(menuDesplegablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDesplegablePanelLayout.createSequentialGroup()
                    .addContainerGap(582, Short.MAX_VALUE)
                    .addComponent(perfilLb)
                    .addGap(52, 52, 52)))
        );

        Fondo.add(menuDesplegablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 290, 660));

        panelRound1.setBackground(new java.awt.Color(58, 107, 128));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Artistas Favoritos");

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

        menuBtn.setBackground(new java.awt.Color(58, 107, 128));
        menuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/menu.png"))); // NOI18N
        menuBtn.setBorder(null);
        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        Fondo.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, -5, 1290, 80));

        panelInformacionAlbum.setBackground(new java.awt.Color(35, 58, 68));
        panelInformacionAlbum.setRoundBottomLeft(30);
        panelInformacionAlbum.setRoundBottomRight(30);
        panelInformacionAlbum.setRoundTopLeft(30);
        panelInformacionAlbum.setRoundTopRight(30);

        javax.swing.GroupLayout panelInformacionAlbumLayout = new javax.swing.GroupLayout(panelInformacionAlbum);
        panelInformacionAlbum.setLayout(panelInformacionAlbumLayout);
        panelInformacionAlbumLayout.setHorizontalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        panelInformacionAlbumLayout.setVerticalGroup(
            panelInformacionAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        Fondo.add(panelInformacionAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 590, 260));

        panelRound5.setBackground(new java.awt.Color(35, 58, 68));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        tablaArtistaFav.setBackground(new java.awt.Color(35, 58, 68));
        tablaArtistaFav.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaArtistaFav.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaArtistaFav.setGridColor(new java.awt.Color(35, 58, 68));
        tablaArtistaFav.setSelectionBackground(new java.awt.Color(35, 58, 68));
        jScrollPane1.setViewportView(tablaArtistaFav);
        if (tablaArtistaFav.getColumnModel().getColumnCount() > 0) {
            tablaArtistaFav.getColumnModel().getColumn(0).setPreferredWidth(200);
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

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busquedaActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void perfilLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfilLbMouseClicked

        dispose();
        new ActualizarUsuario().setVisible(true);
    }//GEN-LAST:event_perfilLbMouseClicked

    private void artistasFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistasFavLbMouseClicked
        dispose();
        new ArtistaFavorito().setVisible(true);
    }//GEN-LAST:event_artistasFavLbMouseClicked

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        int panelWidth = menuDesplegablePanel.getWidth();
        int targetX = isMenuVisible ? -panelWidth : 0; // Determina el objetivo según el estado
        isMenuVisible = !isMenuVisible; // Alternar estado

        // Desactivar tabla cuando el menú está visible
        tablaArtistaFav.setEnabled(!isMenuVisible);

        javax.swing.Timer timer = new javax.swing.Timer(15, new java.awt.event.ActionListener() {
            int currentX = menuDesplegablePanel.getX();

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if ((isMenuVisible && currentX < targetX) || (!isMenuVisible && currentX > targetX)) {
                    currentX += isMenuVisible ? 15 : -15; // Mover según el estado
                    menuDesplegablePanel.setLocation(currentX, menuDesplegablePanel.getY());
                } else {
                    ((javax.swing.Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();

    }//GEN-LAST:event_menuBtnActionPerformed

    private void artistaLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLbMouseClicked
        dispose();
        new Artista().setVisible(true);
    }//GEN-LAST:event_artistaLbMouseClicked

    private void albumFavLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumFavLbMouseClicked

        dispose();
        new AlbumFavorito().setVisible(true);    }//GEN-LAST:event_albumFavLbMouseClicked

    private void albumLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumLbMouseClicked
        dispose();
        new Principal().setVisible(true);
    }//GEN-LAST:event_albumLbMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Properly dispose of the window instead of System.exit(0)
            System.exit(0);
        }
    }//GEN-LAST:event_salirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton menuBtn;
    private javax.swing.JPanel menuDesplegablePanel;
    private controlador.PanelRound panelInformacionAlbum;
    private controlador.PanelRound panelRound1;
    private controlador.PanelRound panelRound3;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaArtistaFav;
    // End of variables declaration//GEN-END:variables
}
