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
import com.bmn.negocio.ObtenerAlbumesFiltradosBO;
import com.bmn.singletonUsuario.UsuarioST;
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
public class AlbumFavorito extends javax.swing.JFrame {

    private boolean isMenuVisible = true;
    private Usuario usuarioActual;
    private List<AlbumVistaDTO> albumes;
    private ObtenerAlbumesFiltradosBO obtenerAlbumes;

    public AlbumFavorito() {
        try {
            initComponents();

            // Configurar tabla
            configurarTabla();

            // Obtener usuario actual
            this.usuarioActual = UsuarioST.getInstance();
            if (usuarioActual != null) {
                System.out.println("Usuario actual: " + usuarioActual.getNombre());
            } else {
                System.out.println("No se encontró información del usuario actual.");
            }

            // Inicializar lógica de negocio
            this.obtenerAlbumes = BOFactory.obtenerAlbumesFiltradosFactory();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar la ventana de álbumes favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String searchTerm) {
        DefaultTableModel model = (DefaultTableModel) tablaAlbum.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tablaAlbum.setRowSorter(sorter);

        if (searchTerm.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(
                        "(?i)" + searchTerm, 1, 2 // Buscar en nombre del álbum y artista
                );
                sorter.setRowFilter(filter);
            } catch (PatternSyntaxException pse) {
                System.err.println("Error en patrón de búsqueda: " + pse.getMessage());
                tablaAlbum.setRowSorter(null);
            }
        }
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

        tablaAlbum.setModel(modelo);
        tablaAlbum.getTableHeader().setReorderingAllowed(false);

        // Configurar render personalizado
        RenderCeldas render = new RenderCeldas(tablaAlbum);
        render.setColumnAlignment(0, SwingConstants.CENTER); // Imagen centrada
        render.setColumnAlignment(1, SwingConstants.CENTER); // Nombre centrado
        render.setColumnAlignment(2, SwingConstants.LEFT);   // Artista a la izquierda

        // Configurar colores y estilos
        tablaAlbum.setBackground(new Color(35, 58, 68));
        tablaAlbum.setForeground(Color.WHITE);
        tablaAlbum.setRowHeight(50);
        tablaAlbum.setSelectionBackground(new Color(58, 107, 128));
        tablaAlbum.setSelectionForeground(Color.WHITE);
        tablaAlbum.getTableHeader().setBackground(new Color(35, 58, 68));
        tablaAlbum.getTableHeader().setForeground(Color.WHITE);
        tablaAlbum.setShowHorizontalLines(true);
        tablaAlbum.setShowVerticalLines(false);
        tablaAlbum.setGridColor(new Color(255, 255, 255, 50));

        // Configurar columnas
        tablaAlbum.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaAlbum.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaAlbum.getColumnModel().getColumn(2).setPreferredWidth(150);

        // Configurar scroll pane
        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(35, 58, 68));

        cargarDatosDeLaBaseDeDatos(modelo);
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

    private void cargarDatosDeLaBaseDeDatos(DefaultTableModel modelo) {
        try {
            List<AlbumVistaDTO> albumes = obtenerAlbumes.BuscarPorFiltro(null, null, null);
            this.albumes = albumes;

            System.out.println("Número de álbumes encontrados: " + albumes.size());

            modelo.setRowCount(0);

            for (AlbumVistaDTO album : albumes) {
                System.out.println("Álbum: " + album.getNombre()
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        panelInformacionAlbum = new controlador.PanelRound();
        panelRound5 = new controlador.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlbum = new javax.swing.JTable();
        generoFiltro = new javax.swing.JComboBox<>();
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
        artistaLb2 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(24, 40, 54));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        generoFiltro.setBackground(new java.awt.Color(35, 58, 68));
        generoFiltro.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        generoFiltro.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(generoFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 204, 34));

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

        artistaLb2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        artistaLb2.setForeground(new java.awt.Color(255, 255, 255));
        artistaLb2.setText("Baneados");
        artistaLb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        artistaLb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistaLb2MouseClicked(evt);
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
                .addComponent(artistaLb2)
                .addGap(73, 73, 73)
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
                            .addComponent(artistaLb2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        Fondo.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 70));

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

    private void artistaLb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb1MouseClicked
        CancionesFavoritas favoritas = new CancionesFavoritas();
        favoritas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_artistaLb1MouseClicked

    private void artistaLb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistaLb2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_artistaLb2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel albumFavLb;
    private javax.swing.JLabel albumLb;
    private javax.swing.JLabel artistaLb;
    private javax.swing.JLabel artistaLb1;
    private javax.swing.JLabel artistaLb2;
    private javax.swing.JLabel artistasFavLb;
    private javax.swing.JComboBox<String> generoFiltro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private controlador.PanelRound menu;
    private controlador.PanelRound panelInformacionAlbum;
    private controlador.PanelRound panelRound5;
    private javax.swing.JLabel perfilLb;
    private javax.swing.JLabel salir;
    private javax.swing.JTable tablaAlbum;
    // End of variables declaration//GEN-END:variables
}
