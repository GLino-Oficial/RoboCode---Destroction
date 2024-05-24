package destroction;
import robocode.*;
import java.util.Random;
import java.awt.Color;

public class Destroction extends Robot {
    private Random random = new Random();
    private ScannedRobotEvent nearestEnemy;

    public void run() {
        setColors(Color.white, Color.black, Color.magenta);
        while (true) {
            turnGunRight(360); // Gira o canhão constantemente para procurar alvos
            ahead(100); // Avança em direção aos inimigos
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (nearestEnemy == null || e.getDistance() < nearestEnemy.getDistance()) {
            nearestEnemy = e; // Atualiza o inimigo mais próximo
        }
        
        double absoluteBearing = getHeading() + e.getBearing();
        double bearingFromGun = normalRelativeAngle(absoluteBearing - getGunHeading());
        turnGunRight(bearingFromGun); // Aponta o canhão para o inimigo
        fire(3); // Dispara com força máxima
    }

    public void onHitByBullet(HitByBulletEvent e) {
        double bulletHeading = e.getHeading();
        double moveAngle = normalRelativeAngle(bulletHeading - getHeading() + 180); // Calcula o ângulo para se mover para longe da bala
        turnRight(moveAngle + 90); // Gira o robô para se mover para longe da bala
        ahead(50); // Avança 50 unidades na nova direção
    }

    public void onHitWall(HitWallEvent e) {
        back(20); // Recua ao atingir a parede
    }

    private double normalRelativeAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}




