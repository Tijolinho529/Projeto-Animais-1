package view;

import controller.AnimalController;
import model.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AnimalView extends JFrame {
    private JTextField txtNome = new JTextField(20);
    private JTextField txtEspecie = new JTextField(20);
    private JTextField txtIdade = new JTextField(5);
    private JTable tabela;
    private AnimalController controller = new AnimalController();

    public AnimalView() {
        setTitle("Cadastro de Animais");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnListar = new JButton("Listar");
        JButton btnExcluir = new JButton("Excluir");

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Espécie:"));
        panel.add(txtEspecie);
        panel.add(new JLabel("Idade:"));
        panel.add(txtIdade);

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnListar);
        botoes.add(btnExcluir);

        tabela = new JTable();

        add(panel, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);
        add(new JScrollPane(tabela), BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> {
            Animal a = new Animal();
            a.setNome(txtNome.getText());
            a.setEspecie(txtEspecie.getText());
            a.setIdade(Integer.parseInt(txtIdade.getText()));
            controller.salvar(a);
        });

        btnListar.addActionListener(e -> atualizarTabela());

        btnExcluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha != -1) {
                int id = (int) tabela.getValueAt(linha, 0);
                controller.excluir(id);
                atualizarTabela();
            }
        });

        atualizarTabela();
        setVisible(true);
    }

    private void atualizarTabela() {
        List<Animal> lista = controller.listar();
        String[] colunas = {"ID", "Nome", "Espécie", "Idade"}; 
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            Animal a = lista.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getNome();
            dados[i][2] = a.getEspecie();
            dados[i][3] = a.getIdade();
        }
        tabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}
