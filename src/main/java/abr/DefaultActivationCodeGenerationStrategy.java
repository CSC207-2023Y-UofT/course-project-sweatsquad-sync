package abr;

import java.util.Random;

public class DefaultActivationCodeGenerationStrategy implements ActivationCodeGenerationStrategy {

    private static final String ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    @Override
    public String generate() {
        StringBuilder codeBuilder = new StringBuilder();
        Random rnd = new Random();
        while (codeBuilder.length() < 16)
            codeBuilder.append(ALPHA_NUM.charAt((int)(rnd.nextFloat() * ALPHA_NUM.length())));

        return codeBuilder.toString();
    }
}
