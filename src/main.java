import java.util.ArrayList;
import java.util.List;

class Simulation {
    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();
        Planet earth = new Planet("Earth", 6371, 5.972e24, 1.496e8, 0, 29.78);
        Sun sun = new Sun("Sun", 696340, 1.989e30, 5778);

        solarSystem.addSun(sun);
        solarSystem.addPlanet(earth);

        System.out.println(earth.toString());
        System.out.println(sun.toString());

        solarSystem.movePlanets();
        System.out.println("Planet Earth new position: (" + earth.getXPos() + ", " + earth.getYPos() + ")");
    }
}

class SolarSystem {
    private List<Planet> planets = new ArrayList<>();
    private Sun theSun;

    public SolarSystem() {}

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void addSun(Sun sun) {
        theSun = sun;
    }

    public void movePlanets() {
        double G = 0.1;
        double dt = 0.001;

        for(Planet p : planets) {
            p.moveTo(p.getXPos() + dt * p.getXVel(),
                    p.getYPos() + dt * p.getYVel());

            double rx = theSun.getXPos() - p.getXPos();
            double ry = theSun.getYPos() - p.getYPos();
            double r = Math.sqrt(Math.pow(rx, 2) + Math.pow(ry, 2));

            double accX = G * theSun.getMass() * rx / Math.pow(r, 3);
            double accY = G * theSun.getMass() * ry / Math.pow(r, 3);

            p.setXVel(p.getXVel() + dt * accX);
            p.setYVel(p.getYVel() + dt * accY);
        }
    }
}

class Planet {
    private String name;
    private double radius;
    private double mass;
    private double distance;
    private double x;
    private double y;
    private double velX;
    private double velY;

    public Planet(String name, double radius, double mass, double distance, double velX, double velY) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.distance = distance;
        this.x = distance;
        this.y = 0;
        this.velX = velX;
        this.velY = velY;
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    public void moveTo(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public double getXVel() {
        return velX;
    }

    public double getYVel() {
        return velY;
    }

    public void setXVel(double newVelX) {
        velX = newVelX;
    }

    public void setYVel(double newVelY) {
        velY = newVelY;
    }

    public String toString() {
        return "Planet " + name + " - Position: (" + x + ", " + y + "), Velocity: (" + velX + ", " + velY + ")";
    }
}

class Sun {
    private String name;
    private double radius;
    private double mass;
    private double temp;
    private double x;
    private double y;

    public Sun(String name, double radius, double mass, double temp) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.temp = temp;
        this.x = 0;
        this.y = 0;
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    public double getMass() {
        return mass;
    }

    public String toString() {
        return "Sun " + name + " - Position: (" + x + ", " + y + "), Mass: " + mass;
    }
}