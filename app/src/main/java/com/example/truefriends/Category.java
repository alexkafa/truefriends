package com.example.truefriends;

public enum Category {

    INTIMATE;

    public static Category fromString(String string){
        for(Category category : Category.values()){
            if(string.equalsIgnoreCase(category.name())) return category;
        }
        return null;
    }
}
