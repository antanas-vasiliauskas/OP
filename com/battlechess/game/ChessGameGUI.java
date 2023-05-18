package game;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import pieces.*;

import java.util.Timer;
import java.util.TimerTask;



public class ChessGameGUI extends JFrame {

    private JPanel mainPanel;
    private JPanel chessBoardPanel;
    private JPanel movesPanel;
    private JTextArea movesTextArea;
    private File currentFile;
    private JPanel[][] squares;
    private GameState gameState;

    private boolean stateSelected = false;
    private ArrayList<Coordinates> possibleMoves = null;
    private Coordinates selectedPieceCords = null;
    private Piece pieceClicked = null;


    
    
    public ChessGameGUI(GameState gameState) {
        this.gameState = gameState;
        // Set up the main JFrame
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(954, 700);
        setResizable(false);

        // create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        
        // add action listeners to the menu items
        loadMenuItem.addActionListener(new LoadMenuItemListener());
        saveMenuItem.addActionListener(new LoadMenuItemListener());
        
        // add menu items to the menu and menu to the menu bar
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        // Create the chess board panel
        squares = new JPanel[8][8];
        chessBoardPanel = new ImageView("./images/board.png");
        chessBoardPanel.setPreferredSize(new Dimension(640, 640));
        chessBoardPanel.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.shadow"), 2));
        chessBoardPanel.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel squarePanel = new ClickablePanel(i, j, this);
                squarePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                squarePanel.setBackground(new Color(0,0,0,0));
                squares[i][j] = squarePanel;
                chessBoardPanel.add(squarePanel);
            }
        }

        
        


            


        // Create the moves panel
        movesPanel = new JPanel();
        movesPanel.setPreferredSize(new Dimension(300, 640));
        movesTextArea = new JTextArea("1. e4 c5");
        movesTextArea.setEditable(false);
        movesTextArea.setWrapStyleWord(true);
        movesTextArea.setLineWrap(true);
        movesTextArea.setPreferredSize(new Dimension(300, 640));
        movesTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        movesPanel.add(movesTextArea);
        
        // Create the main panel to hold the chess board and moves panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(chessBoardPanel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 2;
        mainPanel.add(movesPanel, gbc);
        
        // Add the main panel to the JFrame
        getContentPane().add(mainPanel);


        
        
        // Show the JFrame
        setVisible(true);
        
    }

    
    private void updateDisplay(){
        for(JPanel[] squareRow: squares){
            for(JPanel square: squareRow){
                square.removeAll();
                square.setBackground(new Color(0,0,0,0));
                square.repaint();
            }
        }
        for(Piece piece: gameState.pieces){
            String path = "./images/" + piece.getPieceName().toLowerCase() + "_" + (piece.isWhite() ? "white" : "black") + ".png";
            ImageView imageView = new ImageView(path);
            squares[8-piece.getRow()][piece.getColumn()-1].add(imageView);
        }
        if(stateSelected){
            for(Coordinates c: possibleMoves){
                ImageView imageView = new ImageView("./images/circle.png");
                squares[8-c.row][c.column-1].add(imageView);
            }
            squares[8-selectedPieceCords.row][selectedPieceCords.column-1].getComponent(0).setBackground(new Color(242, 48, 68, 150));
        }
        movesTextArea.setText(GameState.movesToString(gameState.moves));
        
        //revalidate();
        repaint();
    }

    public void onSquareClicked(Coordinates c){
        if(stateSelected == false){
            for(Piece piece: gameState.pieces){
                if(piece.getCoordinates().row == c.row && piece.getCoordinates().column == c.column && gameState.isWhiteToMove == piece.isWhite()){
                    pieceClicked = piece;
                    break;
                }
            }
            if(pieceClicked != null){
                // Get possible moves, update display
                stateSelected = true;
                possibleMoves = pieceClicked.getPossibleMoves(gameState, true);
                selectedPieceCords = pieceClicked.getCoordinates();
                //System.out.println(possibleMoves.size());
                updateDisplay();

            }
        }
        else {
            Coordinates cordClicked = null;
            for(Coordinates cord: possibleMoves){
                if(cord.row == c.row && cord.column == c.column){
                    cordClicked = c;
                    break;
                }
            }
            if(cordClicked != null){
                //"Move"    
                pieceClicked.move(cordClicked, gameState);
                
                stateSelected = false;
                possibleMoves = null;
                selectedPieceCords = null;
                pieceClicked = null;
                updateDisplay();
                if(gameState.movesAvailable() == 0){
                    if(GameState.isInCheck(gameState)){
                        JTextArea popUpText = new JTextArea((gameState.isWhiteToMove ? "Black" : "White") + " won.");
                        movesTextArea.setEditable(false);
                        movesTextArea.setWrapStyleWord(true);
                        movesTextArea.setLineWrap(true);
                        movesTextArea.setPreferredSize(new Dimension(300, 640));
                        movesTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                        JOptionPane.showMessageDialog(this, popUpText, (gameState.isWhiteToMove ? "Black" : "White") + " won.", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JTextArea popUpText = new JTextArea("Draw.");
                        movesTextArea.setEditable(false);
                        movesTextArea.setWrapStyleWord(true);
                        movesTextArea.setLineWrap(true);
                        movesTextArea.setPreferredSize(new Dimension(300, 640));
                        movesTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                        JOptionPane.showMessageDialog(this, popUpText, "Draw.", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else if(gameState.pieces.size() == 2){
                    JTextArea popUpText = new JTextArea("Draw.");
                    movesTextArea.setEditable(false);
                    movesTextArea.setWrapStyleWord(true);
                    movesTextArea.setLineWrap(true);
                    movesTextArea.setPreferredSize(new Dimension(300, 640));
                    movesTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                    JOptionPane.showMessageDialog(this, popUpText, "Draw.", JOptionPane.PLAIN_MESSAGE);
                }
                
            }
            else{
                // "Deselect"
                stateSelected = false;
                possibleMoves = null;
                selectedPieceCords = null;
                pieceClicked = null;
                updateDisplay();
            }
        }
        

    }

    private void saveToFile(File file) {
        // TODO: add implementation to save the chess game to a file
    }
    
    private void loadFromFile(File file) {
        // TODO: add implementation to load the chess game from a file
    }
    
    private class LoadMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(ChessGameGUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                try {
                    loadFromFile(currentFile);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ChessGameGUI.this, "Error loading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        GameState gameState = new GameState();
        ChessGameGUI gui = new ChessGameGUI(gameState);
        gui.updateDisplay();
    }
}






class ImageView extends JPanel {
    private Image backgroundImage;

    public ImageView(String imagePath) {
        super();
        backgroundImage = new ImageIcon(imagePath).getImage();
        Dimension size = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setBackground(new Color(0,0,0,0));
        setLayout(null); // Disable layout manager to manually position components
    }

    public ImageView(String imagePath, MouseAdapter listener){
        this(imagePath);
        this.addMouseListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}


class ClickablePanel extends JPanel {
    int i,j;
    ChessGameGUI gui;
    public ClickablePanel(int i, int j, ChessGameGUI gui) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.i = i;
        this.j = j;
        this.gui = gui;
        // Add a mouse listener to the panel
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.onSquareClicked(new Coordinates(8-i, j + 1));
            }
        });
    }
}


