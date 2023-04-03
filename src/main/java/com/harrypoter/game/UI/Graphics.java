package com.harrypoter.game.UI;

import com.harrypoter.game.*;
import com.harrypoter.game.UI.Enemies.Basilic;
import com.harrypoter.game.UI.Enemies.Detraqueur;
import com.harrypoter.game.UI.Enemies.Enemy;
import com.harrypoter.game.UI.Enemies.Troll;
import com.harrypoter.game.enums.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.harrypoter.game.UI.Entity.getEntities;

public class Graphics extends JPanel implements ActionListener {
    static final int WIDTH = 800;
    Random random = new Random();
    static final int HEIGHT = 800;
    static final int TICK_SIZE = 20;
    final Font font = new Font("TimesRoman", Font.BOLD, 20);
    int[][] currentScreen;
    int[][] previousScreen;
    Player player = new Player(this);
    private int textTimeToLive = 0;
    private String text = "";
    private List<Enemy> needToKill = new ArrayList<>();
    private int level = 0;
    private int levelDelay = 0;

    String direction;
    final Timer timer = new Timer(1, this);

    final int spawnTime = 10000;
    int spawnCountdown = 0;

    public void showText(String text) {
        this.text = text;
        textTimeToLive = 2000;
    }

    public Graphics() {
        Wizard wizard = new Wizard("bruh", Pet.OWL, new Wand(0, 0), new House(0));

        wizard.setProbOfProtego(0);
        wizard.setHealth(100);
        wizard.setCurrentHealth(100);
        player.character = wizard;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("[" + e.getKeyChar() + "] pressed (" + e.getKeyCode() + ")");
                int k = e.getKeyCode();
                // press numeric key to change the spell
                if (k >= KeyEvent.VK_NUMPAD1 && k <= KeyEvent.VK_NUMPAD9) {
                    AbstractSpell spell = player.character.getSpell(k - KeyEvent.VK_NUMPAD1);
                    if (spell != null) {
                        player.character.setCurrentSpell(spell);
                    }
                    System.out.println("spell changed to " + spell);

                    showText(spell.getName());
                }
                switch (k) {
                    case KeyEvent.VK_Q -> direction += 'R';
                    case KeyEvent.VK_Z -> direction += 'U';
                    case KeyEvent.VK_S -> direction += 'D';
                    case KeyEvent.VK_D -> direction += 'L';
                }
                String bulletDirection = "";
                switch (k) {
                    case KeyEvent.VK_LEFT -> bulletDirection = "R";
                    case KeyEvent.VK_UP -> bulletDirection = "U";
                    case KeyEvent.VK_DOWN -> bulletDirection = "D";
                    case KeyEvent.VK_RIGHT -> bulletDirection = "L";
                }

                if (KeyEvent.VK_LEFT == k || KeyEvent.VK_RIGHT == k || KeyEvent.VK_UP == k || KeyEvent.VK_DOWN == k) {
                    player.shoot(bulletDirection);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO remove after the current render to get a better feel of movement
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Q -> direction = direction.replace("R", "");
                    case KeyEvent.VK_Z -> direction = direction.replace("U", "");
                    case KeyEvent.VK_S -> direction = direction.replace("D", "");
                    case KeyEvent.VK_D -> direction = direction.replace("L", "");
                }
            }
        });
        start();
    }

    protected void start() {
        direction = "";
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.setColor(Color.DARK_GRAY);
        // render the text on the screen and decrease the time to live. Opacity of the
        // text is based on the time to live
        if (textTimeToLive > 0) {
            g.setFont(font);
            g.setColor(new Color(0, 0, 255,
                    (textTimeToLive > 200 ? 255 : (int) (((double) (textTimeToLive / 200f)) * 255))));
            g.drawString(text, (int) (WIDTH * 0.001), (int) (HEIGHT * 0.2));
            textTimeToLive--;
        }
        player.move(direction);
        if(getPlayer().existing == false) {
            showText("You died!");

        }
        for (Entity entity : getEntities()) {
            System.out.println(entity.posX);
            if (entity.posX < 0 || entity.posY < 0 || entity.posX*TICK_SIZE > WIDTH || entity.posY*TICK_SIZE > HEIGHT) {
                entity.existing = false;
            }
            if (entity.character != null && entity.character.getCurrentHealth() <= 0) {
                entity.existing = false;
            }
            if (!entity.existing)
                continue;

            entity.onNewFrame();
            List<Entity> colliding = isEntityColliding(entity);
            for (Entity collidedEntity : colliding) {
                if (entity instanceof Player && collidedEntity instanceof Enemy && !entity.isHit()) {
                    getPlayer().character.hit(10, "hit");
                    getPlayer().onDamageHit();
                    if (getPlayer().character.getCurrentHealth() <= 0) {
                        getPlayer().existing = false;
                    }
                }
                if (collidedEntity instanceof Bullet) {
                    AbstractSpell spell = ((Bullet) collidedEntity).spell;
                    if (spell instanceof WingardiumLeviosa) {
                        entity.movementDelay = 500;
                        entity.character.hit(20, ((Bullet) collidedEntity).spell.getName());
                    } else {
                        entity.character.hit(10, ((Bullet) collidedEntity).spell.getName());
                    }
                    collidedEntity.existing = false;
                    entity.onDamageHit();
                    System.out.println("Hit on" + entity.toString());
                    System.out.println(entity.character.getCurrentHealth());
                }
            }
            ;
            Color entityColor = entity.color;
            if (entity.hitCountdown > entity.hitDelay * 0.90) {
                g.setColor(Color.BLACK);
            } else
                g.setColor(new Color(entityColor.getRed(), entityColor.getGreen(), entityColor.getBlue(),
                        entity.isHit() ? (entity.hitCountdown % 300 < 150 ? 0 : 100) : 255));
            g.fillRect((int) (entity.posX * TICK_SIZE - ((TICK_SIZE * entity.scale - 1) * 0.5)),
                    (int) (entity.posY * TICK_SIZE - ((TICK_SIZE * entity.scale - 1) * 0.5)), TICK_SIZE * entity.scale,
                    TICK_SIZE * entity.scale);
        }
        g.setColor(new Color(1, 1, 1,
                getPlayer().isHit() ? (getPlayer().hitCountdown % 300 < 150 ? 0 : 255) : 255));

        g.setFont(font);
        g.drawString("Level " + level, (int) (WIDTH * 0.1), (int) (HEIGHT * 0.9));
        // show player health
        g.drawString(Main.name +" health: " + player.character.getCurrentHealth(), (int) (WIDTH * 0.1), (int) (HEIGHT * 0.95));

        // differents levels

        if (level != -1 && needToKill.stream().filter(arg0 -> arg0.existing).count() == 0) {
            needToKill.clear();
            if (levelDelay > 0) {
                showText("Nice! Next level in " + levelDelay);
                levelDelay--;
                return;
            }
            level++;

            if (level == 1) {
                System.out.println("Spawning enemy");
                Enemy enemy = new Troll(this);
                enemy.character = new Monster("Troll", 200);

                AbstractSpell spell = new WingardiumLeviosa();
                player.character.setCurrentSpell(spell);
                player.character.addSpell(spell);
                enemy.posX = random.nextInt(40);
                enemy.posY = random.nextInt(40);
                needToKill.add(enemy);
                showText("VS " + enemy.character.getName() + ". New " + spell.getName() + " spell");

            } else if (level == 2) {
                System.out.println("Spawning enemy");
                Enemy enemy = new Basilic(this);
                enemy.character = new Monster("Basilic", 200);
                enemy.posX = random.nextInt(40);
                enemy.posY = random.nextInt(40);
                SwordSpell spell = new SwordSpell();
                player.character.addSpell(spell);
                player.character.setCurrentSpell(spell);
                showText("VS " + enemy.character.getName() + ". New " + spell.getName() + " spell (Press num. keys to change)");
                needToKill.add(enemy);
            } else if (level == 3) {
                System.out.println("Spawning enemy");
                Enemy enemy = new Detraqueur(this);
                enemy.character = new Monster("Detraqueur", 10000);
                enemy.posX = random.nextInt(40);
                enemy.posY = random.nextInt(40);
                Patronus spell = new Patronus();
                player.character.addSpell(spell);
                player.character.setCurrentSpell(spell);
                showText("VS " + enemy.character.getName() + ". New " + spell.getName() + " spell");
                needToKill.add(enemy);
            } else {
                showText("You won ! Freeplay mode enabled");
                level = -1;
            }
            levelDelay = 5000;
        }

        if (level == -1) {
            if (spawnCountdown <= 0) {
                int randomMonster = random.nextInt(3);
                Stuppefix spell = new Stuppefix();
                player.character.addSpell(spell);
                player.character.setCurrentSpell(spell);
                if (randomMonster == 0) {
                    Enemy enemy = new Troll(this);
                    enemy.character = new Monster("Troll", 200);
                    enemy.posX = random.nextInt(40);
                    enemy.posY = random.nextInt(40);
                    needToKill.add(enemy);
                } else if (randomMonster == 1) {
                    Enemy enemy = new Basilic(this);
                    enemy.character = new Monster("Basilic", 200);
                    enemy.posX = random.nextInt(40);
                    enemy.posY = random.nextInt(40);
                    needToKill.add(enemy);
                } else if (randomMonster == 2) {
                    Enemy enemy = new Detraqueur(this);
                    enemy.character = new Monster("Detraqueur", 10000);
                    enemy.posX = random.nextInt(40);
                    enemy.posY = random.nextInt(40);
                    needToKill.add(enemy);
                }
                spawnCountdown = 10000;
            }
        }

        spawnCountdown--;
    }

    public boolean areColliding(Entity entity1, Entity entity2) {
        double distance = Point2D.distance(entity1.posX, entity1.posY, entity2.posX, entity2.posY);
        distance = distance - ((entity1.scale - 1) * 0.5) - ((entity1.scale - 1) * 0.5);
        return distance < 1;
    }

    public List<Entity> isEntityColliding(Entity entity) {
        List entityList = new ArrayList<>();
        for (Entity entity2 : getEntities()) {
            if (!entity.existing)
                continue;
            if (entity.equals(entity2))
                continue;
            if (areColliding(entity, entity2)) {
                if (entity instanceof Bullet && entity2 instanceof Bullet)
                    continue;
                if (entity2 instanceof Bullet) {
                    if (((Bullet) entity2).getShooter().equals(entity))
                        continue;
                }
                if (entity instanceof Bullet) {
                    if (((Bullet) entity).getShooter().equals(entity))
                        continue;
                }
                entityList.add(entity2);
            }
            ;
        }
        return entityList;
    }

    protected void collisionTest() {
        // timer.stop();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        collisionTest();
        repaint();
    }

}