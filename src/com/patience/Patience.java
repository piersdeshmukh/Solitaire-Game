package com.patience;

public class Patience {

    public static void main (String... args) {
        Arena arena = new Arena();
        Points point_tally = new Points();
        View view = new View(arena, point_tally);
        Command command = view.getInputCommand();
        gameplay:
        while(!arena.isGameOver())
        {
            boolean run = false;
            while (!run)
            {
                switch (command.getName())
                {
                    case MOVE:
                        if (arena.isMoveAllowed(command)) {
                            arena.move(command);
                            point_tally.actionTaken(command);
                            run = true;
                        } else {
                            view.displayCommandInfeasible();
                        }
                        break;
                    case DRAW:
                        if (arena.isDrawAllowed()) {
                            arena.draw();
                            point_tally.actionTaken();
                            run = true;
                        } else {
                            view.displayCommandInfeasible();
                        }
                        break;
                    case QUIT:
                        view.displayQuit();
                        break gameplay;
                    default:
                        break;
                }
                view.presentInfo(point_tally);
                view.presentInfo(arena);
                command = view.getInputCommand();
            }

        }
        view.displayGameOver();
    }

}