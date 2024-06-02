import javax.swing.*;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args){

        Musica[] musicas = dados.lerCSV("src/musicas_taylor_swift.csv");


        ImageIcon Image = new ImageIcon("src/eulindo.png");

        int numeroRandom = dados.numeroRandom();

        Musica musicaLeft = musicas[numeroRandom];
        final ImageIcon[] imagemLeft = {new ImageIcon("src/" + musicaLeft.getImagem())};

        Musica musicaRight = musicas[dados.numeroRandom(numeroRandom)];
        final ImageIcon[] imagemRight = {new ImageIcon("src/"+ musicas[dados.numeroRandom(numeroRandom)].getImagem())};


        JLabel label = new JLabel();
        label.setText("WHO's MORE POGGERS???!");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelOR = new JLabel();
        labelOR.setText("OR");

        JLabel labelImagemLeft = new JLabel();
        Image imagemLeftRes = imagemLeft[0].getImage();
        Image imagemLeftRes2 = imagemLeftRes.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
        imagemLeft[0] = new ImageIcon(imagemLeftRes2);
        labelImagemLeft.setIcon(imagemLeft[0]);

        // DESCRICAO IMAGEM LEFT
        JLabel labelNomeImagemLeft = new JLabel(musicaLeft.getNome());
        JLabel labelDescImagemLeft = new JLabel(musicaLeft.getDesc());


        JLabel labelImagemRight = new JLabel();
        Image imagemRightRes = imagemRight[0].getImage();
        Image imagemRightRes2 = imagemRightRes.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
        imagemRight[0] = new ImageIcon(imagemRightRes2);
        labelImagemRight.setIcon(imagemRight[0]);

        // DESCRICAO IMAGEM RIGHT
        JLabel labelNomeImagemRight = new JLabel(musicaRight.getNome());
        JLabel labelDescImagemRight = new JLabel(musicaRight.getDesc());




        JLabel labelAcertou = new JLabel();
        labelAcertou.setText("ACERTOU, DIEGO É DE FATO O MAIS GOSTOSO");
        labelAcertou.setVisible(false);

        final JFrame[] frame = {new JFrame()};
        frame[0].getContentPane().setBackground(new Color(0, 204, 204));

        frame[0].setTitle("Facemash");
        frame[0].setResizable(false);
        frame[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame[0].setSize(1100,800);


        frame[0].setIconImage(Image.getImage());

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 204, 204));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Define o layout como BoxLayout vertical

        panel.add(label);

        JPanel panelImagens = new JPanel();
        panelImagens.setBackground(new Color(255, 204, 204));


        panelImagens.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        JPanel panelImagemLeft = new JPanel();
        panelImagemLeft.setLayout(new BoxLayout(panelImagemLeft, BoxLayout.Y_AXIS));

        JPanel panelImagemRight = new JPanel();
        panelImagemRight.setLayout(new BoxLayout(panelImagemRight, BoxLayout.Y_AXIS));

        panelImagemLeft.setBackground(new Color(255, 204, 204));
        panelImagemRight.setBackground(new Color(255, 204, 204));

        panelImagemLeft.add(labelImagemLeft);
        panelImagemLeft.add(labelNomeImagemLeft);
        panelImagemLeft.add(labelDescImagemLeft);
        panelImagens.add(panelImagemLeft);

        panelImagens.add(labelOR);

        panelImagemRight.add(labelImagemRight);
        panelImagemRight.add(labelNomeImagemRight);
        panelImagemRight.add(labelDescImagemRight);
        panelImagens.add(panelImagemRight);


        panelImagens.add(labelAcertou);

        panel.add(panelImagens); // Adiciona o segundo painel ao painel principal
        JButton button = new JButton("Generate Rank!");
        panel.add(button);
        frame[0].add(panel);
        frame[0].setVisible(true);

        labelImagemLeft.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int numeroRandomLeft = dados.numeroRandom();
                int numeroRandomRight = dados.numeroRandom(numeroRandomLeft);
                Musica novaMusicaLeft = musicas[numeroRandomLeft];
                ImageIcon novaImagemLeft = new ImageIcon("src/" + novaMusicaLeft.getImagem());

                // Redimensionar a nova imagem
                Image imagemLeftRes = novaImagemLeft.getImage();
                Image imagemLeftRes2 = imagemLeftRes.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                novaImagemLeft = new ImageIcon(imagemLeftRes2);

                // Atualizar componentes com as novas informações
                labelNomeImagemLeft.setText(novaMusicaLeft.getNome());
                labelDescImagemLeft.setText(novaMusicaLeft.getDesc());
                labelImagemLeft.setIcon(novaImagemLeft);

                // Remover o MouseListener existente
                labelImagemLeft.removeMouseListener(this);

                // Adicionar o MouseListener novamente
                labelImagemLeft.addMouseListener(this);

                // Atualizar a tela
                frame[0].revalidate();

                // Redesenha os componentes atualizados
                labelNomeImagemLeft.repaint();
                labelDescImagemLeft.repaint();
                labelImagemLeft.repaint();

                Musica novaMusicaRight = musicas[numeroRandomRight];
                ImageIcon novaImagemRight = new ImageIcon("src/" + novaMusicaRight.getImagem());

                // Redimensionar a nova imagem
                Image imagemRightRes = novaImagemRight.getImage();
                Image imagemRightRes2 = imagemRightRes.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                novaImagemRight = new ImageIcon(imagemRightRes2);

                // Atualizar componentes com as novas informações
                labelNomeImagemRight.setText(novaMusicaRight.getNome());
                labelDescImagemRight.setText(novaMusicaRight.getDesc());
                labelImagemRight.setIcon(novaImagemRight);

                // Remover o MouseListener existente
                labelImagemRight.removeMouseListener(this);

                // Adicionar o MouseListener novamente
                labelImagemRight.addMouseListener(this);

                // Atualizar a tela
                frame[0].revalidate();

                // Redesenha os componentes atualizados
                labelNomeImagemRight.repaint();
                labelDescImagemRight.repaint();
                labelImagemRight.repaint();

                musicas[numeroRandomLeft].aumentar_pontuacao();

            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "data.txt";

                try {
                    TXTFileReaderWriter.writeTXTFile(filePath, dados.gerarRanking(musicas));
                } catch (TXTFileIOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Modified TXT File Contents:\n" + dados.gerarRanking(musicas));

                JOptionPane.showMessageDialog(null, "File modified successfully!");
            }
        });


        labelImagemRight.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int numeroRandomLeft = dados.numeroRandom();
                int numeroRandomRight = dados.numeroRandom(numeroRandomLeft);
                Musica novaMusicaLeft = musicas[numeroRandomLeft];
                ImageIcon novaImagemLeft = new ImageIcon("src/" + novaMusicaLeft.getImagem());

                // Redimensionar a nova imagem
                Image imagemLeftRes = novaImagemLeft.getImage();
                Image imagemLeftRes2 = imagemLeftRes.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                novaImagemLeft = new ImageIcon(imagemLeftRes2);

                // Atualizar componentes com as novas informações
                labelNomeImagemLeft.setText(novaMusicaLeft.getNome());
                labelDescImagemLeft.setText(novaMusicaLeft.getDesc());
                labelImagemLeft.setIcon(novaImagemLeft);

                // Remover o MouseListener existente
                labelImagemLeft.removeMouseListener(this);

                // Adicionar o MouseListener novamente
                labelImagemLeft.addMouseListener(this);

                // Atualizar a tela
                frame[0].revalidate();

                // Redesenha os componentes atualizados
                labelNomeImagemLeft.repaint();
                labelDescImagemLeft.repaint();
                labelImagemLeft.repaint();

                Musica novaMusicaRight = musicas[numeroRandomRight];
                ImageIcon novaImagemRight = new ImageIcon("src/" + novaMusicaRight.getImagem());

                // Redimensionar a nova imagem
                Image imagemRightRes = novaImagemRight.getImage();
                Image imagemRightRes2 = imagemRightRes.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                novaImagemRight = new ImageIcon(imagemRightRes2);

                // Atualizar componentes com as novas informações
                labelNomeImagemRight.setText(novaMusicaRight.getNome());
                labelDescImagemRight.setText(novaMusicaRight.getDesc());
                labelImagemRight.setIcon(novaImagemRight);

                // Remover o MouseListener existente
                labelImagemRight.removeMouseListener(this);

                // Adicionar o MouseListener novamente
                labelImagemRight.addMouseListener(this);

                // Atualizar a tela
                frame[0].revalidate();

                // Redesenha os componentes atualizados
                labelNomeImagemRight.repaint();
                labelDescImagemRight.repaint();
                labelImagemRight.repaint();

                musicas[numeroRandomRight].aumentar_pontuacao();

            }
        });


    }
}