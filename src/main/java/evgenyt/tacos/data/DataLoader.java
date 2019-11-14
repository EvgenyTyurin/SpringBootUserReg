package evgenyt.tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import evgenyt.tacos.Ingredient;

@Component
public class DataLoader implements ApplicationRunner {

    private IngredientRepository ingredientRepository;

    @Autowired
    public DataLoader(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void run(ApplicationArguments args) {
        ingredientRepository.save(new Ingredient("LAV", "Lavash"));
    }

}