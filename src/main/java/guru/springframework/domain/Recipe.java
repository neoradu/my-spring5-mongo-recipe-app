package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jt on 6/13/17.
 */

/*
 *Domain refactored JPA annotations where removed and the id field changed to string
 */
@Getter
@Setter
@Document //Identifies a domain object to be persisted to MongoDB.
public class Recipe {
	@Id //Identifies a domain object to be persisted to MongoDB.
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;
    
    @DBRef //An annotation that indicates the annotated field is to be stored using a com.mongodb.DBRef.
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
