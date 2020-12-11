package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public abstract class Game {
    public Game() {

    }
    public void game_Logic(Map<Integer, Map<Integer, Character>> board, Integer round_num) throws IOException {
        if (round_num < 9){
            if (round_num % 2 == 0){
                char player = 'X';
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Your turn. Enter position (row,place):");
                String position = reader.readLine();
                update_Board(board, round_num, player, position);
            }
            else {
                play_by_AI(board, round_num);
            }
            round_num += 1;
            game_Logic(board, round_num);
        }
        else {
            System.out.println("Game finished");
            System.out.println("Starting a new game");
            start();
        }
    }
    public abstract void start();

    public abstract Map<Integer, Map<Integer, Character>> play_by_AI(Map<Integer, Map<Integer, Character>> board, Integer round_num);

    public abstract Map<Integer, Map<Integer, Character>> update_Board(Map<Integer, Map<Integer, Character>> board_prev, Integer round_num, Character player, String position) throws IOException;
}