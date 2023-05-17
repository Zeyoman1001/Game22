// Hi iam habiba

package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {

    //-----------------------Attributes------------------------------------------
    public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
    public static ArrayList<Hero> heroes = new ArrayList<Hero>();
    public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    public static Cell[][] map = new Cell[15][15];
    public static void loadHeroes(String filePath) throws IOException {

        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        // -----------------------------------------------------------------
        while (line != null) {
            String[] heros = line.split(",");
            // -----------------------------------------------------------------
            Hero h;
            String name = heros[0];
            int maxHp = Integer.parseInt(heros[2]);
            int attackDmg = Integer.parseInt(heros[4]);
            int maxActions = Integer.parseInt(heros[3]);
            // -----------------------------------------------------------------
            if (heros[1].equals("FIGH")) {
                h = new Fighter(name, maxHp, attackDmg, maxActions);
                System.out.println(h.getName());
            } else if (heros[1].equals("MED")) {
                h = new Medic(name, maxHp, attackDmg, maxActions);
            } else {
                h = new Explorer(name, maxHp, attackDmg, maxActions);
            }
            // ------------------------------------------------------------------
            availableHeroes.add(h);
            line = br.readLine();
        }
        // -------------------------------------------------------------------------
        br.close();
    }
    public static void startGame(Hero h) throws IOException {
        setRestToCharacter();
//----------------------------Distribution---------------------
        ArrayList<Point> Locations = new ArrayList<Point>();
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                Locations.add(new Point(i, j));
            }
        }

        for (int i = 0; i < 5; i++) {
            int point = getRandomInteger(Locations.size());
            Vaccine v = new Vaccine();
            map[Locations.get(point).x][Locations.get(point).y] = new CollectibleCell(v);
            Locations.remove(Locations.get(point));
        }
        for (int i = 0; i < 5; i++) {
            int point = getRandomInteger(Locations.size());
            Supply s = new Supply();
            map[Locations.get(point).x][Locations.get(point).y] = new CollectibleCell(s);
            Locations.remove(Locations.get(point));
        }
        for (int i = 0; i < 5; i++) {
            int point = getRandomInteger(Locations.size());
            map[Locations.get(point).x][Locations.get(point).y] = new TrapCell();
            Locations.remove(Locations.get(point));
        }
        for (int i = 0; i < 10; i++) {
            int point = getRandomInteger(Locations.size());
            Zombie z = new Zombie();
            map[Locations.get(point).x][Locations.get(point).y] = new CharacterCell(z);
            z.setLocation(Locations.get(point));
            zombies.add(z);
            Locations.remove(Locations.get(point));
        }
        // -------------------------------------------adding Hero-------------------
        heroes.add(h);
        h.setLocation(new Point (0,0));
        map[0][0] = new CharacterCell(h);
        availableHeroes.remove(h);
        h.setAllVisible();
    }

    public static void setRestToCharacter() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                map[i][j] = new CharacterCell(null);
            }
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    public static boolean checkWin() {
        return (moreThan5Heros() && collectedAndUsedAllVacciens());
    }

    // -------------------------------------------------------------------------------------------------------
    public static boolean checkGameOver() {

        return (NoHeroesLeft() || collectedAndUsedAllVacciens());
    }

    // -----------------------------------------------------------------------------------------------------

    public static Cell[][] getMap() {
        return map;
    }

    public static void endTurn() throws  InvalidTargetException, NotEnoughActionsException {
        for (int i =0 ; i<zombies.size()  ; i++) {
            Zombie z = zombies.get(i) ;
            z.attack();
            z.setTarget(null);
        }
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                map[x][y].setVisible(false);
            }
        }
        ArrayList<Point> Locations = new ArrayList<Point>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (map[i][j] instanceof CharacterCell && ((CharacterCell) (map[i][j])).getCharacter() == null)
                    Locations.add(new Point(i, j));
            }
        }
        Zombie z = new Zombie();
        zombies.add(z);
        int point = (int) (Math.random() * Locations.size());
        map[Locations.get(point).x][Locations.get(point).y] = new CharacterCell(z);
        z.setLocation(Locations.get(point));
        Locations.remove(Locations.get(point));

        for (Hero h : heroes) {
            h.setActionsAvailable(h.getMaxActions());
            h.setTarget(null);
            h.setSpecialAction(false);
            h.setAllVisible();
        }
    }

    public static int getRandomInteger(int size) {
        return ((int) (Math.random() * size));
    }

    public static boolean NoHeroesLeft() {
        return (heroes.size()) == 0;
    }

    public static boolean moreThan5Heros() {
        return ((heroes.size()) >= 5);
    }

    public static boolean collectedAndUsedAllVacciens() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (map[i][j] instanceof CollectibleCell
                        && ((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine) {
                    return false;
                }
            }
        }
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getVaccineInventory().size() != 0) {
                return false;
            }
        }
        return true;
    }
}
