package fr.eni.onepiecev4;

import fr.eni.onepiecev4.bll.MethodesJeu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnePieceV3ApplicationTests {

    public MethodesJeu methodesJeu;

    public OnePieceV3ApplicationTests(MethodesJeu methodesJeu) {
        this.methodesJeu = methodesJeu;
    }

    @Test
    void contextLoads() {
    }


    }

