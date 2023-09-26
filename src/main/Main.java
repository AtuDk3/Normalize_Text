package main;

import controller.NormalizeText;
import model.TextNormalizerModel;

public class Main {

    public static void main(String[] args) {
        TextNormalizerModel model = new TextNormalizerModel();
        
        new NormalizeText(model).run();
    }
    
}
