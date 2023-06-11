package com.example.projetinfov1;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        // Compare les scores dans l'ordre décroissant
        return Integer.compare(player2.getScore(), player1.getScore());
    }
}