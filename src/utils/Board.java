package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.MessageFormat;

public class Board extends Game {
    public Board() {

    }

    @Override
    public void start() {
        int round_o = 0;
        Map<Integer, Map<Integer, Character>> board_o = new HashMap<>();
        Map<Integer, Character> base = new HashMap<>();

        base.put(0, ' ');
        base.put(1, ' ');
        base.put(2, ' ');

        board_o.put(0, base);
        board_o.put(1, base);
        board_o.put(2, base);

        print_Board(board_o);
        try {
            game_Logic(board_o, round_o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print_Board(Map<Integer, Map<Integer, Character>> board) {
        Map<Integer, Character> board_chars = new HashMap<>();

        board_chars.put(0, '-');
        board_chars.put(1, '+');
        board_chars.put(2, '|');

        for (Map.Entry<Integer, Map<Integer, Character>> entry : board.entrySet()) {
            Map<Integer, Character> v = entry.getValue();
            String line = MessageFormat.format("{0}{3}{1}{3}{2}", v.get(0), v.get(1), v.get(2), board_chars.get(2));

            if (entry.getKey() != 2) {
                String borders = MessageFormat.format("{0}{1}{0}{1}{0}", board_chars.get(0), board_chars.get(1), board_chars.get(2));
                System.out.println(line);
                System.out.println(borders);
            } else {
                System.out.println(line);
            }
        }
    }

    public List<Integer> check_update(Map<Integer, Map<Integer, Character>> board, String position, Integer round_num) {
        clean_input(position);
        List<Integer> position_list = new ArrayList<Integer>(2);
        return position_list;
    }

    public static List<Integer> clean_input(String position) {
        List<Integer> position_list = new ArrayList<Integer>(2);
        List<Character> nums = new ArrayList<Character>(3);
        nums.add('0');
        nums.add('1');
        nums.add('2');

        position = position.trim();

        if (position.toLowerCase() == "exit") {
            System.out.println("The player doesn't want to continue playing.");
            System.exit(69420);
        } else {
            for (char num : position.toCharArray()) {
                if (nums.contains(num)) {
                    position_list.add(Character.getNumericValue(num));
                }
            }
        }
        return position_list;
    }

    public void check_winner(Map<Integer, Map<Integer, Character>> board, Character player) {

    }

    @Override
    public Map<Integer, Map<Integer, Character>> play_by_AI(Map<Integer, Map<Integer, Character>> board, Integer round_num) {
        Map<Integer, Map<Integer, Character>> board_new = board;
        return board_new;
    }

    @Override
    public Map<Integer, Map<Integer, Character>> update_Board(Map<Integer, Map<Integer, Character>> board_prev, Integer round_num, Character player, String position) throws IOException {
        List position_list = check_update(board_prev, position, round_num);
        Map<Integer, Map<Integer, Character>> board_new = board_prev;

        if (true) {
            Map<Integer, Character> till = board_new.get(position_list.get(0));
            till.put((Integer) position_list.get(1), player);
            board_new.put((Integer) position_list.get(0), till);
            round_num += 1;
            if (player.equals('X')) {
                print_Board(board_new);
                check_winner(board_new, player);
            } else {
                System.out.println("AI moves!");
                print_Board(board_new);
                check_winner(board_new, player);
                game_Logic(board_new, round_num);
            }
        } else {
            if (!player.equals('O')) {
                System.out.println("Invalid input");
            }
            game_Logic(board_prev, round_num);
        }
        return board_new;
    }
}
