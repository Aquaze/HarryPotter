package com.harrypoter.game.UI;

import com.harrypoter.game.Character;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Entity {
    private static Set<Entity> entities = new HashSet<>();
    public int posX;
    public int posY;
    public Color color = Color.GRAY;
    public int hitDelay = 2000;
    public int hitCountdown;
    protected int movementSpeed = 100;
    protected int scale = 1;
    public int movementDelay;
    public String direction;
    protected Graphics manager;
    public Character character;
    public boolean existing = true;
    public int timeToLive = -1;
    public Entity(Graphics manager) {
        this.manager = manager;
        posX = 3;
        posY = 3;
        entities.add(this);
    }

    protected boolean canMove() {
        return movementDelay == 0;
    }

    public static Set<Entity> getEntities() {
        return entities.stream().filter(entity -> entity.existing).collect(Collectors.toSet());
    }

    public void move() {
        if(!canMove()) return;
        move(this.direction);
        // hasMoved();
    }

    public void onHitLimit() {
        hitCountdown = hitDelay;
    }

    public void onDamageHit() {
        hitCountdown = hitDelay;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void move(String direction) {
        if(!canMove()) return;
        // move if move inside the map

        int maxWidth = Graphics.WIDTH;
        int maxHeight = Graphics.HEIGHT;

        if(direction.contains("L") && posX > -1) posX += 1;
        if(direction.contains("R") && posX < maxWidth) posX -= 1;
        if(direction.contains("U") && posY > -1) posY -= 1;
        if(direction.contains("D") && posY < maxHeight) posY += 1;

        //trigger onHitLimit if the entity is at the limit

        if(posX < 0 || posX == maxWidth || posY < 0 || posY == maxHeight ) onHitLimit();
        
        if(!direction.equals("")) hasMoved();
    }
    public void hasMoved() {
        movementDelay = movementSpeed;
    }

    public void onNewFrame() {
        if(!existing) return;
        if(movementDelay > 0) movementDelay--;
        if(hitCountdown > 0) hitCountdown--;
        if(timeToLive > 0) timeToLive--;
        if(timeToLive == 0) existing = false;
    }

    public boolean isHit() {
        return hitCountdown > 0;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
