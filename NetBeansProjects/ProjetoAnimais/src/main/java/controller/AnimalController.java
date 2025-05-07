package controller;

import model.Animal;
import model.AnimalDAO;
import java.util.List;

public class AnimalController {
    private AnimalDAO dao = new AnimalDAO();

    public void salvar(Animal animal) {
        if (animal.getId() == 0)
            dao.inserir(animal);
        else
            dao.atualizar(animal);
    }

    public List<Animal> listar() {
        return dao.listar();
    }

    public void excluir(int id) {
        dao.remover(id);
    }
}
